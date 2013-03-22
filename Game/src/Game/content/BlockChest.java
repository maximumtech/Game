package Game.content;

import Game.base.BlockBase;
import Game.base.World;

/**
 *
 * @author Mirw-
 */
public class BlockChest extends BlockGenReplaceable {
    
    private int chestSize = 15;
    private int[] contents = new int[chestSize];
    
    public BlockChest(short id) {
        super(id);
        //addContent();
    }
    
    public void onUpdate(World world, int x, int y) {
        
    }
    
    public void addItems(String rarity){
        if(rarity == "shit"){
            //most likely run a for loop, first decided whether or not to place into current slot in chest
            //then choose the BlockID of an item from the rarity level defined.
            //if the slot in the chest is supposed to be empty, add in an empty entity that isn't usable.
            //contents.add(BlockID)
        }else if(rarity == "common"){
            //most likely run a for loop, first decided whether or not to place into current slot in chest
            //then choose the BlockID of an item from the rarity level defined.
            //contents.add(BlockID)
        }else if(rarity == "uncommon"){
            //most likely run a for loop, first decided whether or not to place into current slot in chest
            //then choose the BlockID of an item from the rarity level defined.
            //if the slot in the chest is supposed to be empty, add in an empty entity that isn't usable.
            //contents.add(BlockID)
        }else if(rarity == "rare"){
            //most likely run a for loop, first decided whether or not to place into current slot in chest
            //then choose the BlockID of an item from the rarity level defined.
            //if the slot in the chest is supposed to be empty, add in an empty entity that isn't usable.
            //contents.add(BlockID)
        }else if(rarity == "legendary"){
            //most likely run a for loop, first decided whether or not to place into current slot in chest
            //then choose the BlockID of an item from the rarity level defined.
            //if the slot in the chest is supposed to be empty, add in an empty entity that isn't usable.
            //contents.add(BlockID)
        }
    }
}
