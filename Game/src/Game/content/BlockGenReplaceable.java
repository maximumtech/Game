package Game.content;

import Game.base.BlockBase;

/**
 *
 * @author maximumtech
 */
public class BlockGenReplaceable extends BlockBase {
    
    public BlockGenReplaceable(short id) {
        super(id);
    }
    
    public boolean isGenerationReplacable() {
        return true;
    }
    
}
