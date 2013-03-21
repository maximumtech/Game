package Game.entity;

import Game.base.BlockBase;
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

    public EntityLiving(World world) {
        this(world, 0, 0);
    }

    public EntityLiving(World world, int x, int y) {
        super(world, x, y);
        maxHealth = 20;
        health = maxHealth;
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
        int y = world.getCoordinateFromPixel(getY() - 1);
        BlockBase block = world.getBlock(x, y);
        return block == null ? false : block.canCollide(world, x, y);
    }

    public void fall(int dist) {
        if (dist > 80) {
            attackEntityFrom(Math.max(0, (dist - 80) / 4), DamageSource.FALL);
        }
    }
    private int fallT;
    private boolean wasOnGround = true;

    public void onLivingUpdate() {
        boolean onGround = isOnGround();
        if (!onGround && (!isJumping)) {
            fallT++;
            motionY -= Math.min(GameBase.blockSize * 8, Math.max(10, fallT));
            wasOnGround = true;
        }else if(isJumping) {
            if(jumpTick == 0) {
                motionY = 10;
            }else if(jumpTick > 0 && jumpTick < 20) {
                motionY += 20-jumpTick * 2;
            }else if(jumpTick==20) {
                jumpTick = 0;
                isJumping = false;
            }
            jumpTick++;
        } else {
            if (wasOnGround) {
                fall(fallT);
            }
            fallT = 0;
        }
    }
    
    private boolean isJumping = false;
    private int jumpTick = 0;

    public void jump() {
        if (isOnGround()) {
            isJumping = true;
            jumpTick = 0;
        }
    }

    public void onUpdate() {
        if (!isDead()) {
            onLivingUpdate();
        }
        super.onUpdate();
    }
}
