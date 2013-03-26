package Game.content;

import Game.base.BlockBase;

/**
 *
 * @author Mirw-
 */
public class BlockChest extends BlockBase {
    
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
