package Game.content;

import Game.base.IBreakable;
import Game.base.World;

/**
 *
 * @author maximumtech
 */
public class ItemPickaxe extends ItemTool {

    public ItemPickaxe(short id, ToolMaterial material) {
        super(id, material);
    }

    public float getHardnessModifier(World world, int x, int y, IBreakable block) {
        if ((block.getMaterial() == Material.DIRT || block.getMaterial() == Material.STONE || block.getMaterial() == Material.METAL) && material.getTier() >= block.getTier()) {
            return material.getModifier();
        }
        return 1F;
    }
}
