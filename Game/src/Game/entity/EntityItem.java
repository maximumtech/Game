package Game.entity;

import Game.base.GameBase;
import Game.base.ItemStack;
import Game.base.World;
import Game.misc.MathHelper;
import Game.render.gui.ScreenWorld;

/**
 *
 * @author maximumtech
 */
public class EntityItem extends Entity implements ICollectable, ICollector {

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

    public boolean canCollect(ICollectable collectable) {
        if (collectable instanceof EntityItem) {
            EntityItem item = ((EntityItem) collectable);
            if (item.storedItem.getItem() == storedItem.getItem() && item.storedItem.getMeta() == storedItem.getMeta() && item.storedItem.getData().equals(storedItem.getData())) {
                return true;
            }
        }
        return false;
    }
    
    protected boolean isCollecting = false;

    public boolean onCollect(ICollectable collectable) {
        isCollecting = true;
        if (collectable instanceof EntityItem) {
            EntityItem item = ((EntityItem) collectable);
            if(item.isCollecting) {
                isCollecting = false;
                return false;
            }
            int ext = storedItem.increment(item.storedItem.getAmount());
            if (ext == 0) {
                item.setDead();
            } else {
                ext = item.storedItem.decrement(ext);
                if (ext != 0) {
                    item.setDead();
                }
            }
            return true;
        }
        return false;
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
        isCollecting = false;
        ICollector pi = (ICollector) world.getNearestEntity(this, 112D, new Class<?>[]{ICollector.class}, 0);
        int iter = 1;
        Entity p = (Entity)pi;
        boolean isGood = pi!=null?pi.canCollect(this):true;
        while (pi!=null && !isGood) {
            pi = (ICollector) world.getNearestEntity(this, 112D, new Class<?>[]{ICollector.class}, iter);
            if(pi!=null && pi.canCollect(this)) {
                isGood = true;
            }
            p = (Entity)pi;
            iter++;
        }
        if (p != null) {
            if (p.getX() > this.getX()) {
                this.motionX = 3;//Math.min(3, p.getX() - this.getX());
            } else if (p.getX() < this.getX()) {
                this.motionX = -3;//Math.max(-3, p.getX() - this.getX());
            } else {
                this.motionX = 0;
            }
            if (p.getY() > this.getY()) {
                this.motionY = 3;//Math.min(3, p.getY() - this.getY());
            } else if (p.getY() < this.getY()) {
                this.motionY = -3;//Math.max(-3, p.getY() - this.getY());
            } else {
                this.motionY = 0;
            }
            if (MathHelper.getDistance(getMidX(), getMidY(), p.getMidX(), p.getY()) < 32) {
                pi.onCollect(this);
            }
        } else {
            if (this.floatTick > 16) {
                this.downFloat = true;
            } else if (this.floatTick < 0) {
                this.downFloat = false;
            }
            if (this.downFloat) {
                this.floatTick -= 1;
                if (this.floatTick % 3 == 0) {
                    this.motionY = -1;
                } else {
                    this.motionY = 0;
                }
            } else {
                this.floatTick += 1;
                if (this.floatTick % 3 == 0) {
                    this.motionY = 1;
                } else {
                    this.motionY = 0;
                }
            }
        }
        super.onUpdate();
    }

    public void render() {
        storedItem.renderWorld(world, getX(), getY());
    }
}
