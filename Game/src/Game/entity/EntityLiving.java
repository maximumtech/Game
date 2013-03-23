package Game.entity;

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

    public void fall(int dist) {
        if (dist > 80) {
            attackEntityFrom(Math.max(0, (dist - 80) / 4), DamageSource.FALL);
        }
    }

    public void onLivingUpdate() {
        
    }

    public void onUpdate() {
        if (!isDead()) {
            onLivingUpdate();
        }
        super.onUpdate();
    }
}
