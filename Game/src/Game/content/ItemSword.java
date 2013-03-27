/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.content;

import Game.base.BlockBase;
import Game.base.World;
import Game.entity.EntityLiving;

/**
 *
 * @author maximumtech
 */
public class ItemSword extends ItemTool {
    
    public ItemSword(short id, ToolMaterial material) {
        super(id, material);
    }
    
    public int getDamageVsEntity(EntityLiving ent) {
        return material.getDamage();
    }
    
    public float getHardnessModifier(World world, int x, int y, BlockBase block) {
        if(block.getMaterial() == Material.PLANT && material.getTier()>=block.getTier()) {
            return material.getModifier();
        }else if(material.getTier() >= block.getTier()) {
            return 1.5F;
        }
        return 1F;
    }
}
