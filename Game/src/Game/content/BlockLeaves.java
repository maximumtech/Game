package Game.content;

import Game.base.ItemStack;
import Game.base.World;

/**
 *
 * @author Mirw9
 */
public class BlockLeaves extends BlockNonColliding{
    
    public BlockLeaves(short id){
        super(id);
        setHardness(2F);
    }
    
    public ItemStack[] getDroppedItem(World world, int x, int y) {
        return new ItemStack[]{};
    }
}
