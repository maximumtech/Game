package Game.entity;

import Game.base.GameBase;
import Game.base.World;
import Game.misc.DamageSource;

/**
 *
 * @author maximumtech
 */
public abstract class EntityLiving extends Entity {

    private int health;
    private int maxHealth;
    public int motionX;
    public int motionY;

    public EntityLiving(World world) {
        this(world, 0, 0);
    }
    
    public void render() {
        
    }

    public EntityLiving(World world, int x, int y) {
        super(world, x, y);
    }

    public void setMaxHealth(int hp) {
        maxHealth = hp;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public boolean isDead() {
        return health == 0;
    }

    protected void setHealth(int hp) {
        health = Math.max(0, Math.min(hp, getMaxHealth()));
    }

    public boolean attackEntityFrom(int damage, DamageSource source) {
        if (!isDead()) {
            setHealth(health - damage);
        }
        return true;
    }

    public void heal(int amt) {
        if (!isDead()) {
            setHealth(health + amt);
        }
    }

    public boolean canMove() {
        return !isDead();
    }

    public boolean isOnGround() {
        int x = world.getCoordinateFromPixel(getX());
        int y = world.getCoordinateFromPixel(getY());
        y -= 1;
        return !world.isAirBlock(x, y); //TODO: make pixel worth, not block worth
    }

    public void fall(int dist) {
        if (dist > 80) {
            attackEntityFrom(Math.max(0, (dist - 80) / 4), DamageSource.FALL);
        }
    }
    private int fallT;
    private boolean wasOnGround = true;

    public void onLivingUpdate() {
        if(!isColliding(getX(), getY())) {
            motionY+= GameBase.blockSize / 8;
        }
        if (canMove()) {
            setPosition(getX() + motionX, getY() + motionY);
            boolean onGround = isOnGround();
            if (!onGround) {
                fallT++;
                motionY -= Math.min(fallT + GameBase.blockSize, GameBase.blockSize * 5);
                wasOnGround = true;
            } else {
                if (wasOnGround) {
                    fall(fallT);
                }
                fallT = 0;
            }
            if (motionX > 0) {
                motionX = Math.max(0, (int) (motionX / 16D) - 3);
            }
            if (motionY > 0) {
                motionY = Math.max(0, (int) (motionY / 16D) - 3);
            }
        }
    }

    public void jump() {
        if (isOnGround()) {
            motionY = GameBase.blockSize * 6; // 6 blocks of initial force
        }
    }

    public void onUpdate() {
        super.onUpdate();
        if (!isDead()) {
            onLivingUpdate();
        }
    }
}
