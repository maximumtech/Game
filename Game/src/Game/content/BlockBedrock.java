package Game.content;

import Game.base.World;

/**
 *
 * @author Mirw9
 */
public class BlockBedrock extends BlockGenReplaceable {
    
    public BlockBedrock(short id){
        super(id);
    }
    
    public boolean isBreakable(World world, int x, int y) {
        return y>0?true:false;
    }
    
}
