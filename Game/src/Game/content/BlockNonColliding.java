package Game.content;

import Game.base.BlockBase;
import Game.base.World;
import Game.misc.Side;

/**
 *
 * @author maximumtech
 */
public class BlockNonColliding extends BlockBase {
    
    public BlockNonColliding(short id) {
        super(id);
    }
    
    public boolean canCollide(World world, int x, int y, Side side) {
        return false;
    }
    
    public boolean isSolid(World world, int x, int y, Side side) {
        return false;
    }
    
    public boolean isOpaque(World world, int x, int y) {
        return false;
    }
    
}
