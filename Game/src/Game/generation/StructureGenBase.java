package Game.generation;

import Game.base.World;
import java.util.Random;

/**
 *
 * @author maximumtech
 */
public class StructureGenBase {

    World world;
    public Random rand = null;

    public StructureGenBase(World world) {
        this.world = world;
        rand = new Random();
    }

    public boolean canGenerate(int x, int y) {
        return true;
    }

    public void generate(int x, int y) {
    }
}
