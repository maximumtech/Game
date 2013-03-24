package Game.entity;

import Game.base.GameBase;
import Game.base.ItemStack;
import Game.base.World;
import Game.misc.MathHelper;

/**
 *
 * @author maximumtech
 */
public class EntityItem extends Entity implements ICollectable {

    public ItemStack storedItem = null;
    public int floatTick = 15;
    public boolean downFloat = false;

    public EntityItem(World world) {
        super(world);
        sizeX = GameBase.blockSize;
        sizeY = GameBase.blockSize;
    }

    public void onCollected(ICollector collector) {
    }

    public EntityItem(World world, int x, int y) {
        super(world, x, y);
    }

    public EntityItem(World world, int x, int y, ItemStack item) {
        this(world, x, y);
        storedItem = item;
    }

    public void onCollide(Entity ent) {
        if (ent instanceof EntityPlayer && ent == world.mainPlayer) {
            //setDead();
        }
    }

    public boolean canFall() {
        return false;
    }

    public void gravitateToEntity(Entity entity) {
        int[] motion = MathHelper.getDirectMotionTo(this, entity, 4);
        this.motionX = motion[0];
        this.motionY = motion[1];
    }

    public void onUpdate() {
        Entity collector = (Entity) world.getNearestEntity(this, 80D, new Class<?>[]{ICollector.class});
        if (collector != null) {
            gravitateToEntity(collector);
        }
        super.onUpdate();
    }

    public void render() {
        storedItem.renderWorld(world, getX(), getY());
    }
}
