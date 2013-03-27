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
public class ItemPickaxe extends ItemTool {
    
    public ItemPickaxe(short id, ToolMaterial material) {
        super(id, material);
    }
    
    public float getHardnessModifier(World world, int x, int y, BlockBase block) {
        if((block.getMaterial() == Material.DIRT || block.getMaterial() == Material.STONE || block.getMaterial() == Material.METAL) && material.getTier()>=block.getTier()) {
            return material.getModifier();
        }
        return 1F;
    }
}
