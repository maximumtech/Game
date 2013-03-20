package Game.base;

/**
 *
 * @author maximumtech
 */
public class BackTileBase extends ItemBase {

    public static BackTileBase[] backtileList = new BackTileBase[Short.MAX_VALUE];

    public BackTileBase(short id) {
        super(id, ItemType.BACKTILE);
        backtileList[id] = this;
    }
    
    public void renderWorld(World world, int x, int y) {
        getImage().render(x - 2, y - 2, GameBase.blockSize + 2, GameBase.blockSize + 2);
    }

    public boolean isOpaque(World world, int x, int y) {
        return true;
    }

    public void onNeighborUpdate(World world, int x, int y) {
    }

    public void onUpdate(World world, int x, int y) {
    }

    public boolean canPlaceBlockInAir(World world, int x, int y) {
        return canPlaceBlockOnBlock(world, x, y);
    }

    public boolean canPlaceBlockOnBlock(World world, int x, int y) {
        return true;
    }

    public boolean canBeBrokenApartFromAir(World world, int x, int y) {
        return canBeBroken(world, x, y);
    }

    public boolean canBeBroken(World world, int x, int y) {
        return true;
    }
    
    public boolean shouldRender() {
        //check lighting
        //check if on screen
        //check for blocks
        return true;
    }


    static {
    }
}
