package Game.base;

import Game.content.*;
import Game.render.ImageHandler;

/**
 *
 * @author maximumtech
 */
public class BlockBase extends ItemBase {

    public static BlockBase[] blocksList = new BlockBase[Short.MAX_VALUE];
    public static final BlockBase stone = (BlockBase) new BlockGenReplaceable((short) 1).setImage(ImageHandler.getRenderStack("blockStone")).setName("Stone");
    public static final BlockBase dirt = (BlockBase) new BlockGenReplaceable((short) 2).setImage(ImageHandler.getRenderStack("blockDirt")).setName("Dirt");
    public static final BlockBase grass = (BlockBase) new BlockGrass((short) 3).setImage(ImageHandler.getRenderStack("blockGrass")).setName("Grass");

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
        int xx = world.getPixelFromCoordinate(x);
        int yy = world.getPixelFromCoordinate(y);
        return new CollisonBox(xx, yy, xx + GameBase.blockSize, yy + GameBase.blockSize);
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
}
