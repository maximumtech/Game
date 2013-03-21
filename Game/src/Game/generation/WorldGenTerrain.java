package Game.generation;

import Game.base.BlockBase;
import Game.base.World;
import java.util.Random;

/**
 *
 * @author maximumtech
 */
public class WorldGenTerrain extends WorldGenColumn {
    
    public WorldGenTerrain(World world) {
        super(world);
        undulatingLevel = world.getSeaLevel();
    }
    
    private int undulatingLevel;
    public Random rand = new Random();
    
    public void generateColumn(int x) {
        for(int y = 0;y<world.getHeight();y++) {
            if(y==undulatingLevel) {
                world.setBlock(x, y, BlockBase.grass);
            }else if(y<undulatingLevel && y>undulatingLevel - (world.getSeaLevel() / 3)) {
                world.setBlock(x, y, BlockBase.dirt);
            }else if(y<undulatingLevel){
                world.setBlock(x, y, BlockBase.stone);
            }
            if(y==0) {
                world.setBlock(x, y, (short)0); // bedrock
            }
        }
        int change = rand.nextInt(8);
        if(change==0) {
            undulatingLevel++;
        }else if(change==1) {
            undulatingLevel--;
        }
        if(undulatingLevel<world.getSeaLevel() - 10) {
            undulatingLevel += rand.nextInt(6);
        }else if (undulatingLevel > world.getSeaLevel() + 10) {
            undulatingLevel -= rand.nextInt(6);
        }
    }
    
}
