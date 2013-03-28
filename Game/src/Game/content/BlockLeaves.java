package Game.content;

import Game.base.BlockBase;
import Game.base.ItemStack;
import Game.base.World;
import Game.misc.Side;

/**
 *
 * @author Mirw9
 */
public class BlockLeaves extends BlockWood {

    public BlockLeaves(short id) {
        super(id);
        setHardness(2F);
        setMaterial(Material.PLANT);
    }

    public ItemStack[] getDroppedItem(World world, int x, int y, ItemStack item) {
        return new ItemStack[]{rand.nextInt(25) == 0 ? new ItemStack(BlockBase.sapling) : null};
    }

    public boolean canCollide(World world, int x, int y, Side side) {
        return false;
    }
}
