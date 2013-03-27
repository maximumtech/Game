package Game.base;

import Game.content.*;
import Game.entity.EntityItem;
import Game.misc.Side;
import Game.render.ImageHandler;
import java.util.Random;

/**
 *
 * @author maximumtech
 */
public class BlockBase extends ItemBase {

    public float blockHardness = 1f;
    public static BlockBase[] blocksList = new BlockBase[Short.MAX_VALUE];
    public static final BlockBase stone = (BlockBase) new BlockStone((short) 1).setTier(0).setImage(ImageHandler.getRenderStack("blockStone")).setName("Stone");
    public static final BlockBase dirt = (BlockBase) new BlockDirt((short) 2).setImage(ImageHandler.getRenderStack("blockDirt")).setName("Dirt");
    public static final BlockBase grass = (BlockBase) new BlockGrass((short) 3).setImage(ImageHandler.getRenderStack("blockGrass")).setName("Grass");
    public static final BlockBase chest = (BlockBase) new BlockChest((short) 4, 10, 4).setImage(ImageHandler.getRenderStack("blockChest")).setName("Chest");
    public static final BlockBase bedrock = (BlockBase) new BlockBedrock((short) 5).setImage(ImageHandler.getRenderStack("blockBedrock")).setName("Bedrock");
    public static final BlockBase woodLog = (BlockBase) new BlockWood((short) 6).setImage(ImageHandler.getRenderStack("blockWood")).setName("Wood");
    public static final BlockBase leaves = (BlockBase) new BlockLeaves((short) 7).setImage(ImageHandler.getRenderStack("blockLeaves")).setName("Leaves");
    public static final BlockBase redflower = (BlockBase) new BlockFlower((short) 8).setImage(ImageHandler.getRenderStack("blockRedFlower")).setName("Red Flower");
    public static final BlockBase mushroom = (BlockBase) new BlockFlower((short) 9).setImage(ImageHandler.getRenderStack("blockMushroom")).setName("Mushroom");
    public static final BlockBase tallgrass = (BlockBase) new BlockFlower((short) 10).setImage(ImageHandler.getRenderStack("blockTallGrass")).setName("Tall Grass");
    public static final BlockBase coalore = (BlockBase) new BlockStone((short) 11).setTier(1).setImage(ImageHandler.getRenderStack("blockCoalOre")).setName("Coal Ore");
    public static final BlockBase copperore = (BlockBase) new BlockStone((short) 12).setTier(1).setImage(ImageHandler.getRenderStack("blockCopperOre")).setName("Copper Ore");
    public static final BlockBase tinore = (BlockBase) new BlockStone((short) 13).setTier(2).setImage(ImageHandler.getRenderStack("blockTinOre")).setName("Tin Ore");
    public static final BlockBase ironore = (BlockBase) new BlockStone((short) 14).setTier(3).setImage(ImageHandler.getRenderStack("blockIronOre")).setName("Iron Ore");
    public static final BlockBase silverore = (BlockBase) new BlockStone((short) 15).setTier(4).setImage(ImageHandler.getRenderStack("blockSilverOre")).setName("Silver Ore");
    public static final BlockBase goldore = (BlockBase) new BlockStone((short) 16).setTier(5).setImage(ImageHandler.getRenderStack("blockGoldOre")).setName("Gold Ore");
    public static final BlockBase sapling = (BlockBase) new BlockSapling((short) 17).setImage(ImageHandler.getRenderStack("blockSapling")).setName("Sapling");
    
    public static Random rand = new Random();
    
    public BlockBase(short id) {
        super(id, ItemType.BLOCK);
        blocksList[id] = this;
    }
    
    private int tier = 0;
    
    public BlockBase setTier(int tier) {
        this.tier = tier;
        return this;
    }
    
    public int getTier() {
        return tier;
    }
    
    private boolean requiresRandomTick = false;
    private int tickRate = 0;
    
    public boolean requiresRandomTick() {
        return requiresRandomTick;
    }
    
    public BlockBase getBlockForPlacement(World world, int x, int y) {
        return this;
    }
    
    public int getTickRate() {
        return tickRate;
    }
    
    public void tick(World world, int x, int y) {
        
    }
    
    public BlockBase setRandomTick(int tick) {
        requiresRandomTick = true;
        tickRate = tick;
        return this;
    }
    
    public boolean canBePlacedHere(World world, int x, int y) {
        return true;
    }
    
    public boolean isBreakable(World world, int x, int y) {
        return true;
    }
    
    public BlockBase setHardness(float hardness) {
        this.blockHardness = hardness;
        return this;
    }

    public short getBlockID() {
        return getID(ItemType.BLOCK);
    }

    public void renderWorld(World world, int x, int y) {
        getImage().render(x, y, 0, GameBase.blockSize, GameBase.blockSize);
    }

    public boolean canCollide(World world, int x, int y, Side side) {
        return true;
    }

    public boolean isSolid(World world, int x, int y, Side side) {
        return true;
    }

    public boolean isOpaque(World world, int x, int y) {
        return true;
    }

    public void onBlockBreak(World world, int x, int y, ItemStack it) {
        ItemStack[] items = getDroppedItem(world, x, y, it);
        for (ItemStack item : items) {
            EntityItem itm = new EntityItem(world, world.getPixelFromCoordinate(x), world.getPixelFromCoordinate(y), item);
            itm.motionX += 2 - rand.nextInt(5);
            itm.motionY += rand.nextInt(5);
            world.spawnEntity(itm);
        }
        world.setBlock(x, y, (short) 0);
    }
    
    private Material material;
    
    public BlockBase setMaterial(Material material) {
        this.material = material;
        return this;
    }
    
    public Material getMaterial() {
        return material;
    }

    public ItemStack[] getDroppedItem(World world, int x, int y, ItemStack item) {
        return new ItemStack[]{new ItemStack((ItemBase)this)};
    }

    public void onRightClick(World world, int x, int y, ItemStack is) {
        int xx = world.getCoordinateFromPixel(x);
        int yy = world.getCoordinateFromPixel(y);
        onPlace(world, xx, yy);
    }
    
    public void onPlace(World world, int x, int y) {
    }
    
    public void onStartBreaking(World world, int x, int y, ItemStack item) {
        
    }
    
    public boolean canBeReplaced(World world, int x, int y, BlockBase replacer) {
        return false;
    }

    public CollisonBox getCollisonBox(World world, int x, int y) {
        int xx = world.getPixelFromCoordinate(x);
        int yy = world.getPixelFromCoordinate(y);
        return new CollisonBox(xx, yy, xx + GameBase.blockSize, yy + GameBase.blockSize);
    }

    public void onNeighborUpdate(World world, int x, int y) {
        onUpdate(world, x, y);
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
