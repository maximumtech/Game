package Game.base;

import Game.content.*;
import Game.entity.EntityItem;
import Game.render.ImageHandler;

/**
 *
 * @author maximumtech
 */
public class BlockBase extends ItemBase {

    public float blockHardness = 0.05F;
    public static BlockBase[] blocksList = new BlockBase[Short.MAX_VALUE];
    public static final BlockBase stone = (BlockBase) new BlockGenReplaceable((short) 1).setImage(ImageHandler.getRenderStack("blockStone")).setName("Stone");
    public static final BlockBase dirt = (BlockBase) new BlockGenReplaceable((short) 2).setImage(ImageHandler.getRenderStack("blockDirt")).setName("Dirt");
    public static final BlockBase grass = (BlockBase) new BlockGrass((short) 3).setImage(ImageHandler.getRenderStack("blockGrass")).setName("Grass");
    public static final BlockBase chest = (BlockBase) new BlockChest((short) 4, 10, 4).setImage(ImageHandler.getRenderStack("blockChest")).setName("Chest");
    //public static final BlockBase bedrock = (BlockBase) new BlockBedrock((short) 5).setImage(ImageHandler.getRenderStack("blockBedrock")).setName("Bedrock");
    public static final BlockBase wood = (BlockBase) new BlockWood((short) 6).setImage(ImageHandler.getRenderStack("blockWood")).setName("Wood");
    public static final BlockBase leaves = (BlockBase) new BlockLeaves((short) 7).setImage(ImageHandler.getRenderStack("blockLeaves")).setName("Leaves");

    public BlockBase(short id) {
        super(id, ItemType.BLOCK);
        blocksList[id] = this;
    }
    
    public boolean isBreakable(World world, int x, int y) {
        return true;
    }
    
    public BlockBase setHardness(float hardness) {
        this.blockHardness = hardness / 20;
        return this;
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

    public void onBlockBreak(World world, int x, int y) {
        ItemStack[] items = getDroppedItem(world, x, y);
        for (ItemStack item : items) {
            EntityItem itm = new EntityItem(world, world.getPixelFromCoordinate(x), world.getPixelFromCoordinate(y), item);
            world.spawnEntity(itm);
        }
    }

    public ItemStack[] getDroppedItem(World world, int x, int y) {
        return new ItemStack[]{new ItemStack((ItemBase)this)};
    }

    public void onRightClick(World world, int x, int y) {
    }
    
    public void onPlace(World world, int x, int y) {
    }
    
    public void onStartBreaking(World world, int x, int y) {
        
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
