/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.content;

import Game.base.BlockBase;
import Game.base.World;

/**
 *
 * @author maximumtech
 */
public class ItemAxe extends ItemTool {
    
    public ItemAxe(short id, ToolMaterial material) {
        super(id, material);
    }
    
    public float getHardnessModifier(World world, int x, int y, BlockBase block) {
        if((block.getMaterial() == Material.WOOD || block.getMaterial() == Material.PLANT) && material.getTier()>=block.getTier()) {
            return material.getModifier() * (block.getMaterial() == Material.PLANT ? 0.5F : 1F);
        }
        return 1F;
    }
}
