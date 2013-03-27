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
    
    public void onBlockBreak(World world, int x, int y) {
        super.onBlockBreak(world, x, y);
        if(world.getBlock(x, y + 1) == BlockBase.woodLog) {
            BlockBase.woodLog.onBlockBreak(world, x, y + 1);
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
    
}
