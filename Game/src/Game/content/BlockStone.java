package Game.content;

import Game.base.BlockBase;

/**
 *
 * @author maximumtech
 */
public class BlockStone extends BlockBase {
    
    public BlockStone(short id) {
        super(id);
    }
    
    public boolean isGenerationReplacable() {
        return true;
    }
    
}
