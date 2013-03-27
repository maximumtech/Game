package Game.content;

import Game.base.ItemBase;
import Game.entity.EntityLiving;

/**
 *
 * @author maximumtech
 */
public class ItemTool extends ItemBase {
    
    protected ToolMaterial material;
    
    public ItemTool(short id, ToolMaterial material) {
        super(id);
        this.material = material;
    }
    
    public int getDamageVsEntity(EntityLiving ent) {
        return material.getDamage() / 2;
    }
}
