package Game.generation;

import Game.base.BlockBase;
import Game.base.World;

/**
 *
 * @author maximumtech
 */
public class WorldGenTerrain extends WorldGenColumn {
    
    public WorldGenTerrain(World world) {
        super(world);
    }
    
    public void generateColumn(int x) {
        for(int y = 0;y<world.getHeight();y++) {
            if(y<world.getSeaLevel()) {
                world.setBlock(x, y, BlockBase.stone);
            }
        }
    }
    
}
