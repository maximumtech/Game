package Game.content;

import Game.base.IBreakable;
import Game.base.World;

/**
 *
 * @author maximumtech
 */
public class ItemShovel extends ItemTool {

    public ItemShovel(short id, ToolMaterial material) {
        super(id, material);
    }

    public float getHardnessModifier(World world, int x, int y, IBreakable block) {
        if ((block.getMaterial() == Material.DIRT) && material.getTier() >= block.getTier()) {
            return material.getModifier() * Math.max(1.5F, material.getTier());
        }
        return 1F;
    }
}
