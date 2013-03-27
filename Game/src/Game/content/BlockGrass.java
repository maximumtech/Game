package Game.content;

import Game.base.BlockBase;
import Game.base.World;

/**
 *
 * @author maximumtech
 */
public class BlockGrass extends BlockDirt {

    public BlockGrass(short id) {
        super(id);
        setHardness(0.8F);
        setRandomTick(1100);
    }
    
    public BlockBase getBlockForPlacement(World world, int x, int y) {
        BlockBase block = world.getBlock(x, y + 1);
        return (block==null || block.isOpaque(world, x, y + 1)) ? this : BlockBase.dirt;
    }

    public void tick(World world, int x, int y) {
        for (int xx = x - 1; xx < x + 1; xx++) {
            for (int yy = y - 1; yy < y + 1; yy++) {
                BlockBase block = world.getBlock(xx, yy);
                if(block == BlockBase.dirt) {
                    world.setBlock(x, y, BlockBase.grass, world.getBlockMeta(xx, yy));
                }else if(block == null) {
                    world.setBlock(x, y, BlockBase.tallgrass);
                }else if(block == BlockBase.tallgrass && rand.nextInt(10) == 0) {
                    world.setBlock(x, y, BlockBase.mushroom);
                }
            }
        }
    }

    public void onUpdate(World world, int x, int y) {
        BlockBase block = world.getBlock(x, y + 1);
        short meta = world.getBlockMeta(x, y);
        if (block != null && block.isOpaque(world, x, y)) {
            world.setBlock(x, y, BlockBase.dirt, meta);
        }
    }
}
