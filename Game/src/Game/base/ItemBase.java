package Game.base;

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
    
    public ItemBase setMaxStackSize(int size) {
        this.maxStackSize = size;
        return this;
    }

    public ItemBase(short id, ItemType type) {
        itemID = (Short.MAX_VALUE * (type == ItemType.BLOCK ? 0 : type == ItemType.BACKTILE ? 1 : 2)) + id;
        totalList[itemID] = this;
        if (type == ItemType.ITEM) {
            itemsList[itemID] = this;
        }
    }
    
    public ItemBase getFromID(short id, ItemType type) {
        if(type == ItemType.ITEM) {
            return itemsList[id];
        }else if(type==ItemType.BLOCK){
            return BlockBase.blocksList[id];
        }else if(type==ItemType.BACKTILE) {
            return BackTileBase.backtileList[id];
        }
        return null;
    }

    public short getItemID() {
        return getID(ItemType.ITEM);
    }
    
    public void renderWorld(World world, int x, int y) {
        getImage().render(x + 2, y + 2, GameBase.blockSize - 2, GameBase.blockSize - 2);
    }

    public void renderGUI(int x, int y) {
        getImage().render(x + 2, y + 2, GameBase.blockSize - 2, GameBase.blockSize - 2);
    }
    
    public void renderWorld(World world, int x, int y, short meta, String data) {
        getImage().render(x + 2, y + 2, GameBase.blockSize - 2, GameBase.blockSize - 2);
    }

    public void renderGUI(int x, int y, short meta, String data) {
        getImage().render(x + 2, y + 2, GameBase.blockSize - 2, GameBase.blockSize - 2);
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
