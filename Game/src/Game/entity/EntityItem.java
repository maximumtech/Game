package Game.entity;

import Game.base.GameBase;
import Game.base.ItemStack;
import Game.base.World;

/**
 *
 * @author maximumtech
 */
public class EntityItem extends Entity {
    public ItemStack storedItem = null;
    public int floatTick = 15;
    public boolean downFloat = false;
    public EntityItem(World world) {
        super(world);
        sizeX = GameBase.blockSize;
        sizeY = GameBase.blockSize;
    }
    public EntityItem(World world, int x, int y) {
        super(world, x, y);
    }
    public EntityItem(World world, int x, int y, ItemStack item) {
        this(world, x, y);
        storedItem = item;
    }
    
    public void onCollide(Entity ent) {
        if(ent instanceof EntityPlayer && ent == world.mainPlayer) {
            //setDead();
        }
    }
    
    public void render() {
        storedItem.renderWorld(world, getX(), getY());
    }
}
