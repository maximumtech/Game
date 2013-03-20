package Game.generation;

import Game.base.World;

/**
 *
 * @author maximumtech
 */
public class WorldGenColumn extends WorldGenBase {
    
    public WorldGenColumn(World world) {
        super(world);
    }
    
    public void generate() {
        for(int x = 0;x<world.getWidth();x++) {
            generateColumn(x);
        }
    }
    
    public void generateColumn(int x) {
        
    }
    
}
