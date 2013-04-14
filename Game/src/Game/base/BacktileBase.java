package Game.base;

import Game.content.*;
import Game.entity.EntityItem;
import Game.render.ImageHandler;

/**
 *
 * @author maximumtech
 */
public class BacktileBase extends ItemBase implements IBreakable {

    public float hardness = 1f;
    public static BacktileBase[] backtileList = new BacktileBase[Short.MAX_VALUE];
    public static BacktileBase stone = (BacktileBase) new BacktileUnderground((short) 1).setTier(0).setImage(ImageHandler.getRenderStack("backtileStone")).setName("Stone Wall");
    public static BacktileBase dirt = (BacktileBase) new BacktileUnderground((short) 2).setTier(0).setImage(ImageHandler.getRenderStack("backtileDirt")).setName("Dirt Wall");

    public BacktileBase(short id) {
        super(id, ItemType.BACKTILE);
        backtileList[id] = this;
    }
    private int tier = 0;

    public BacktileBase setTier(int tier) {
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

    public BacktileBase getBacktileForPlacement(World world, int x, int y) {
        return this;
    }

    public int getTickRate() {
        return tickRate;
    }

    public void tick(World world, int x, int y) {
    }

    public BacktileBase setRandomTick(int tick) {
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

    public BacktileBase setHardness(float hardness) {
        this.hardness = hardness;
        return this;
    }

    public short getBacktileID() {
        return getID(ItemType.BACKTILE);
    }

    public void renderWorld(World world, int x, int y) {
        getImage().render(x - 2, y - 2, -1, GameBase.blockSize + 4, GameBase.blockSize + 4);
    }

    public boolean isOpaque(World world, int x, int y) {
        return true;
    }

    public void onBreak(World world, int x, int y, ItemStack it) {
        ItemStack[] items = getDroppedItem(world, x, y, it);
        for (ItemStack item : items) {
            if (item == null) {
                continue;
            }
            EntityItem itm = new EntityItem(world, world.getPixelFromCoordinate(x), world.getPixelFromCoordinate(y), item);
            itm.motionX += 2 - rand.nextInt(5);
            itm.motionY += rand.nextInt(5);
            world.spawnEntity(itm);
        }
        world.setBlock(x, y, (short) 0);
    }
    private Material material;

    public BacktileBase setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public ItemStack[] getDroppedItem(World world, int x, int y, ItemStack item) {
        return new ItemStack[]{new ItemStack((ItemBase) this)};
    }

    public void onRightClick(World world, int x, int y, ItemStack is) {
    }

    public void onPlace(World world, int x, int y) {
    }

    public void onStartBreaking(World world, int x, int y, ItemStack item) {
    }

    public boolean canBeReplaced(World world, int x, int y, BacktileBase replacer) {
        return false;
    }

    public short getMetaForPlacing(World world, int x, int y, ItemStack from) {
        return 0;
    }

    public boolean isSameBacktileForPlacing(World world, int x, int y, short tid, short tmeta, BacktileEntityBase tileentity) {
        short id = world.getBacktileID(x, y);
        short meta = world.getBacktileMeta(x, y);
        return (id == tid && meta == tmeta);
    }

    public BacktileEntityBase getBacktileEntityForPlacing(World world, int x, int y, ItemStack from) {
        return null;
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
        return true;
    }
}
