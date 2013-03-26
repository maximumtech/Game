package Game.content;

import Game.base.BlockBase;
import Game.base.World;

/**
 *
 * @author maximumtech
 */
public class BlockNonColliding extends BlockBase {
    
    public BlockNonColliding(short id) {
        super(id);
    }
    
    public boolean canCollide(World world, int x, int y) {
        return false;
    }
    
}
