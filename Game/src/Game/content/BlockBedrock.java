package Game.content;

import Game.base.BlockBase;
import Game.base.World;

/**
 *
 * @author Mirw9
 */
public class BlockBedrock extends BlockBase {
    
    public BlockBedrock(short id){
        super(id);
    }
    
    public boolean isBreakable(World world, int x, int y) {
        return false;
    }
    
}
