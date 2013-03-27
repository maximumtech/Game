package Game.content;

import Game.base.BlockBase;
import Game.base.World;

/**
 *
 * @author maximumtech
 */
public class BlockWood extends BlockNonColliding {

    public BlockWood(short id) {
        super(id);
        setHardness(0.2F);
    }
    
    public boolean isBreakable(World world, int x, int y) {
        short meta = world.getBlockMeta(x, y);
        if(meta == 1) {
            BlockBase block = world.getBlock(x, y - 1);
            if(block instanceof BlockWood) {
                return false;
            }
        }
        return true;
    }

    public void onBlockBreak(World world, int x, int y) {
        if (world.getBlockMeta(x, y) == (short) 1) {
            BlockBase block = world.getBlock(x, y + 1);
            if (block != null && (block == BlockBase.woodLog || (block == BlockBase.leaves && world.getBlockMeta(x, y + 1) == (short) 1))) {
                block.onBlockBreak(world, x, y + 1);
            }
            for (int xx = x + 1; xx < x + 10; xx++) {
                if (world.getBlock(xx, y) == BlockBase.leaves) {
                    world.setBlock(xx, y, (short) 0);
                } else {
                    break;
                }
            }
            for (int xx = x - 1; xx > x - 10; xx--) {
                if (world.getBlock(xx, y) == BlockBase.leaves) {
                    world.setBlock(xx, y, (short) 0);
                } else {
                    break;
                }
            }
        }
        super.onBlockBreak(world, x, y);
    }
}
