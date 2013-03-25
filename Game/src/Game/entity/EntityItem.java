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
        
        //TODO: Read:
        //The math stuff is being a bitch. I'll do more research on it at a later date
        //but for now we've got bigger fish to fry. Let's work on PlayerSprite rendering,
        //and Textual overlay/sprite rendering & animations/block placing. Don't touch this 
        //for now, it's perfectly fine as a temporary ordeal.
        
        Entity p = (Entity) world.getNearestEntity(this, 150D, new Class<?>[]{ICollector.class});
        if (p != null && MathHelper.getDistance(this.getBlockX(), this.getBlockY(), p.getBlockX(), p.getBlockY()) < 7) {
            if (p.getBlockX() < this.getBlockX()) {
                this.motionX = -3;
            } else {
                this.motionX = 3;
            }
            if (p.getBlockY() < this.getBlockY()) {
                this.motionY = -3;
            } else if (p.getBlockY() > this.getBlockY()){
                this.motionY = 3;
            }else {
                this.motionY = 0;
            }

            if (MathHelper.getDistance(this.getBlockX(), this.getBlockY(), p.getBlockX(), p.getBlockY()) < 2) {
                ((ScreenWorld) GameBase.renderScreen).world.despawnEntity(this);
                //TODO: Implement Inventory.
                System.out.println("Giving player Item [ID: " + ((EntityItem)this).storedItem.getItem().getItemID() + "] and despawning entity.");
            }
        }else {
            if(this.floatTick > 16) {
                this.downFloat = true;
            }else if(this.floatTick < 0) {
                this.downFloat = false;
            }
            if(this.downFloat) {
                this.floatTick -= 1;
                if(this.floatTick % 3 == 0) {
                    this.motionY = -1;
                }else {
                    this.motionY = 0;
                }
            }else {
                this.floatTick += 1;
                if(this.floatTick % 3 == 0) {
                    this.motionY = 1;
                }else {
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
