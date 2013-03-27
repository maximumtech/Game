package Game.content;

import Game.base.ItemStack;
import Game.base.World;

/**
 *
 * @author Mirw9
 */
public class BlockLeaves extends BlockWood{
    
    public BlockLeaves(short id){
        super(id);
        setHardness(2F);
        setMaterial(Material.PLANT);
    }
    
    public ItemStack[] getDroppedItem(World world, int x, int y, ItemStack item) {
        return new ItemStack[]{};
    }
}
