package Game.entity;

import Game.base.ItemStack;
import Game.base.World;
import Game.inventory.PlayerInventory;

/**
 *
 * @author maximumtech
 */
public class EntityPlayerSP extends EntityPlayer implements ICollector {
    
    public PlayerInventory inventory;
    
    public EntityPlayerSP(World world, int x, int y, String name) {
        super(world, x, y, name);
        inventory = new PlayerInventory(this, 5);
    }
    
    public boolean onCollect(ICollectable item) {
        if(item instanceof EntityItem) {
            ItemStack is = inventory.addItemStack(((EntityItem) item).storedItem);
            if(is == null) {
                item.onCollected(this);
            }else{
                ((EntityItem) item).storedItem = is;
            }
        }
        return true;
    }

    public boolean canCollect(ICollectable item) {
        return true;
    }
    
    public boolean shouldRender() {
        return false;
    }
    
    private boolean isFlying = false;

    public void setFlying(boolean flying) {
        isFlying = flying;
    }

    public boolean isFlying() {
        return getGameMode().canFly() && isFlying;
    }

    public boolean canFall() {
        return !isFlying();
    }

    public boolean canCollide() {
        return !isFlying();
    }
}
