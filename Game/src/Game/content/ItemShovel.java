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
public class ItemShovel extends ItemTool {
    
    public ItemShovel(short id, ToolMaterial material) {
        super(id, material);
    }
    
    public float getHardnessModifier(World world, int x, int y, BlockBase block) {
        if((block.getMaterial() == Material.DIRT) && material.getTier()>=block.getTier()) {
            return material.getModifier() * Math.max(1.5F, material.getTier());
        }
        return 1F;
    }
}
