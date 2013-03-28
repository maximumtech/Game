package Game.base;

import Game.entity.EntityLiving;
import Game.misc.DamageSource;
import Game.render.RenderStackBase;

/**
 *
 * @author maximumtech
 */
public abstract class ItemBase {

    private String name = "";
    private int itemID;
    public static ItemBase[] totalList = new ItemBase[Short.MAX_VALUE * 3];
    public static ItemBase[] itemsList = new ItemBase[Short.MAX_VALUE];
    private RenderStackBase image;
    private int maxStackSize = 999;

    public int getMaxStackSize() {
        return maxStackSize;
    }

    public int getRawID() {
        return itemID;
    }

    public int getDamageVsEntity(EntityLiving ent) {
        return 1;
    }

    public float getHardnessModifier(World world, int x, int y, BlockBase block) {
        return 1F;
    }

    public boolean attackEntity(EntityLiving ent) {
        ent.attackEntityFrom(getDamageVsEntity(ent), DamageSource.PLAYER);
        return true;
    }

    public ItemBase setMaxStackSize(int size) {
        this.maxStackSize = size;
        return this;
    }

    public ItemBase(short id, ItemType type) {
        itemID = (Short.MAX_VALUE * (type == ItemType.BLOCK ? 0 : type == ItemType.BACKTILE ? 1 : 2)) + id;
        totalList[itemID] = this;
        if (type == ItemType.ITEM) {
            itemsList[id] = this;
        }
    }

    public ItemBase(short id) {
        itemID = Short.MAX_VALUE * 2 + id;
        totalList[itemID] = this;
        itemsList[id] = this;
    }

    public ItemBase getFromID(short id, ItemType type) {
        if (type == ItemType.ITEM) {
            return itemsList[id];
        } else if (type == ItemType.BLOCK) {
            return BlockBase.blocksList[id];
        } else if (type == ItemType.BACKTILE) {
            return BackTileBase.backtileList[id];
        }
        return null;
    }

    public short getItemID() {
        return getID(ItemType.ITEM);
    }

    public void renderWorld(World world, int x, int y, ItemStack stack) {
        getImage().render(x + 2, y + 2, 1, GameBase.blockSize - 4, GameBase.blockSize - 4);
    }

    public void renderGUI(int x, int y, int width, int height, ItemStack stack) {
        getImage().render(x, y, 4, width, height);
    }

    protected short getID(ItemType type) {
        if (type == ItemType.BLOCK) {
            return (short) itemID;
        } else if (type == ItemType.BACKTILE) {
            return (short) (itemID - (Short.MAX_VALUE));
        } else if (type == ItemType.ITEM) {
            return (short) (itemID - (Short.MAX_VALUE * 2));
        }
        return 0;
    }

    protected static enum ItemType {

        BLOCK,
        BACKTILE,
        ITEM;
    }

    public ItemBase setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemBase setImage(RenderStackBase img) {
        this.image = img;
        return this;
    }

    public RenderStackBase getImage() {
        return image;
    }
}
