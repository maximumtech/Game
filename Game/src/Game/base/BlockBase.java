package Game.base;

/**
 *
 * @author maximumtech
 */
public class BlockBase extends ItemBase {

    public static BlockBase[] blocksList = new BlockBase[Short.MAX_VALUE];

    public BlockBase(short id) {
        super(id, ItemType.BLOCK);
        blocksList[id] = this;
    }
    
    public short getBlockID() {
        return getID(ItemType.BLOCK);
    }

    public void renderWorld(World world, int x, int y) {
        getImage().render(x, y, GameBase.blockSize, GameBase.blockSize);
    }

    public boolean canCollide(World world, int x, int y) {
        return true;
    }

    public boolean isSolid(World world, int x, int y) {
        return true;
    }

    public boolean isOpaque(World world, int x, int y) {
        return true;
    }

    public CollisonBox getCollisonBox(World world, int x, int y) {
        return new CollisonBox(x, y, x + GameBase.blockSize, y + GameBase.blockSize);
    }
    
    public void onNeighborUpdate(World world, int x, int y) {
    }

    public void onUpdate(World world, int x, int y) {
    }
    
    public boolean isGenerationReplacable() {
        return false;
    }
    
    public boolean shouldRender() {
        //check lighting
        //check if on screen
        return true;
    }

    static {
        //declare blocks
    }
}
