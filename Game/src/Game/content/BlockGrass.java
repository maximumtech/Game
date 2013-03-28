package Game.content;

import Game.base.BlockBase;
import Game.base.ItemBase;
import Game.base.ItemStack;
import Game.base.World;

/**
 *
 * @author maximumtech
 */
public class BlockGrass extends BlockDirt {

    public BlockGrass(short id) {
        super(id);
        setHardness(0.8F);
        setRandomTick(10000);
    }

    public BlockBase getBlockForPlacement(World world, int x, int y) {
        BlockBase block = world.getBlock(x, y + 1);
        return (block == null || block.isOpaque(world, x, y + 1)) ? this : BlockBase.dirt;
    }

    public void tick(World world, int x, int y) {
        for (int xx = x - 1; xx <= x + 1; xx++) {
            for (int yy = y - 1; yy <= y + 1; yy++) {
                BlockBase block = world.getBlock(xx, yy);
                if (block == BlockBase.dirt) {
                    world.setBlock(xx, yy, BlockBase.grass, world.getBlockMeta(xx, yy));
                }
            }
        }
        BlockBase block = world.getBlock(x, y + 1);
        if (block == null) {
            world.setBlock(x, y + 1, BlockBase.tallgrass);
        } else if (block == BlockBase.tallgrass && rand.nextInt(10) == 0) {
            world.setBlock(x, y + 1, BlockBase.mushroom);
        } else if (block == BlockBase.mushroom && rand.nextInt(100) == 0) {
            world.setBlock(x, y + 1, BlockBase.sapling);
        }
    }

    public ItemStack[] getDroppedItem(World world, int x, int y, ItemStack item) {
        return new ItemStack[]{new ItemStack((ItemBase) BlockBase.dirt)};
    }

    public void onUpdate(World world, int x, int y) {
        BlockBase block = world.getBlock(x, y + 1);
        short meta = world.getBlockMeta(x, y);
        if (block != null && block.isOpaque(world, x, y)) {
            world.setBlock(x, y, BlockBase.dirt, meta);
        }
    }
}
