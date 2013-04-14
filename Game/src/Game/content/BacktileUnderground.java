package Game.content;

import Game.base.BacktileBase;
import Game.base.World;

/**
 *
 * @author maximumtech
 */
public class BacktileUnderground extends BacktileBase {

    public BacktileUnderground(short id) {
        super(id);
        setMaterial(Material.DIRT);
    }

    public boolean isBreakable(World world, int x, int y) {
        return true;
    }
}
