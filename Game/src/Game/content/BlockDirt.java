package Game.content;

import Game.base.BlockBase;

/**
 *
 * @author maximumtech
 */
public class BlockDirt extends BlockBase {
    
    public BlockDirt(short id) {
        super(id);
        setMaterial(Material.DIRT);
    }
    
}
