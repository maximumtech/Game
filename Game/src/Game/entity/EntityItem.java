package Game.entity;

import Game.base.GameBase;
import Game.base.ItemStack;
import Game.base.World;
import Game.render.entity.RenderItem;

/**
 *
 * @author maximumtech
 */
public class EntityItem extends Entity implements ICollectable {

    public ItemStack storedItem = null;

    public void onCollected(ICollector collector) {
        this.setDead();
    }

    public EntityItem(World world, int x, int y) {
        super(world, x, y);
        renderer = new RenderItem(this);
        setDimensions(GameBase.blockSize, GameBase.blockSize);
    }

    public void onCollide(Entity ent) {
        if (chasing != null && ent.equals(chasing)) {
            chasing.onCollect(this);
        }
    }

    public EntityItem(World world, int x, int y, ItemStack item) {
        this(world, x, y);
        storedItem = item;
    }
    private ICollector chasing = null;

    public void onUpdate() {
        ICollector pi = (ICollector) world.getNearestEntity(this, 112D, new Class<?>[]{ICollector.class}, 0);
        int iter = 1;
        Entity p = (Entity) pi;
        boolean isGood = pi != null ? pi.canCollect(this) : true;
        while (pi != null && !isGood) {
            pi = (ICollector) world.getNearestEntity(this, 112D, new Class<?>[]{ICollector.class}, iter);
            if (pi != null && pi.canCollect(this)) {
                isGood = true;
            }
            p = (Entity) pi;
            iter++;
        }
        if (p != null) {
            if (p.getMidX() > this.getMidX()) {
                this.motionX = Math.min(3, p.getMidX() - this.getMidX());
            } else if (p.getMidX() < this.getMidX()) {
                this.motionX = Math.min(-3, this.getMidX() - p.getMidX());
            } else {
                this.motionX = 0;
            }
            if (p.getY() > this.getY()) {
                this.motionY = Math.min(16, p.getMidY() - this.getMidY());
            } else {
                this.motionY = 0;
            }
            chasing = pi;
        } else {
            chasing = null;
        }
        super.onUpdate();
    }
}
