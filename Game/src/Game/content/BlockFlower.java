package Game.content;

import Game.base.BlockBase;
import Game.base.World;
import Game.misc.Side;

/**
 *
 * @author maximumtech
 */
public class BlockFlower extends BlockNonColliding {

    public BlockFlower(short id) {
        super(id);
        setHardness(20F);
        setMaterial(Material.PLANT);
    }

    public boolean canBePlacedHere(World world, int x, int y) {
        BlockBase block = world.getBlock(x, y - 1);
        if (block != null && block.isSolid(world, x, y, Side.TOP) && canBePlacedOn(block)) {
            return true;
        }
        return false;
    }

    public boolean canBePlacedOn(BlockBase block) {
        return block == BlockBase.grass || block == BlockBase.dirt;
    }

    public void onUpdate(World world, int x, int y) {
        if (!canBePlacedHere(world, x, y)) {
            onBreak(world, x, y, null);
        }
    }

    public boolean canBeReplaced(World world, int x, int y, BlockBase replacer) {
        return true;
    }
}
