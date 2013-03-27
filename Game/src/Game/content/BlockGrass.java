package Game.content;

import Game.base.BlockBase;
import Game.base.World;

/**
 *
 * @author maximumtech
 */
public class BlockGrass extends BlockGenReplaceable {
    
    public BlockGrass(short id) {
        super(id);
        setHardness(0.8F);
    }
    
    public void onUpdate(World world, int x, int y) {
        BlockBase block = world.getBlock(x, y + 1);
        short meta = world.getBlockMeta(x, y);
        if(block!=null && block.isOpaque(world, x, y)) {
            world.setBlock(x, y, BlockBase.dirt, meta);
        }
    }
    
}
