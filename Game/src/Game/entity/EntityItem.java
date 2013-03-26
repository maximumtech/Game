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
        this.setDead();
    }

    public EntityItem(World world, int x, int y) {
        super(world, x, y);
    }

    public EntityItem(World world, int x, int y, ItemStack item) {
        this(world, x, y);
        storedItem = item;
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
                p.giveItem(this);
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
