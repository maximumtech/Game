package Game.content;

import Game.base.BlockBase;
import Game.base.World;

/**
 *
 * @author Mirw-
 */
public class BlockChest extends BlockGenReplaceable {
    
    private int chestWidth = 10;
    private int chestHeight = 4;
    
    public BlockChest(short id, int height, int width) {
        super(id);
        chestHeight = height;
        chestWidth = width;
    }
    
    public int getSize() {
        return chestWidth * chestHeight;
    }
    
    //add retrieve inventory from data
}
