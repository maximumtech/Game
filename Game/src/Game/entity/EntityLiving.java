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
        return health==0;
    }
    
    protected void setHealth(int hp) {
        health = Math.max(0, Math.min(hp, getMaxHealth()));
    }
    
    public boolean attackEntityFrom(int damage, DamageSource source) {
        if(canBeHurt(source))setHealth(health - damage);
        return true;
    }
    
    public boolean canBeHurt(DamageSource source) {
        return true;
    }
    
    public boolean canMove() {
        return true;
    }
}
