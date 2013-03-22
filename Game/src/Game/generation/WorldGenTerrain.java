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
    boolean isHill = false;
    ;
    int maxProg = 0;
    int prog = 0;
    int mProg = 0;
    private int undulatingLevel;
    public Random rand = new Random();

    public void generateColumn(int x) {
        boolean isNewHill = rand.nextInt(50) == 0 && !isHill;
        if (isNewHill) {
            maxProg = rand.nextInt((world.getHeight() - world.getSeaLevel()) / 2);
            prog = 0;
            mProg = 0;
            isHill = true;
        }
        int level = undulatingLevel;
        if(isHill) {
            int progCurve = maxProg / 2;
            level+=prog;
            int inc = rand.nextInt(4);
            if(prog<progCurve) {
                prog+=inc;
            }else{
                prog-=inc;
            }
            mProg+=inc;
            if(mProg >= maxProg) {
                isHill = false;
                mProg = 0;
                prog = 0;
                maxProg = 0;
            }
        }
        for (int y = 0; y <= world.getHeight(); y++) {
            if (y == level) {
                world.setBlock(x, y, BlockBase.grass);
            } else if (y < level && y > level - (world.getSeaLevel() / 3)) {
                world.setBlock(x, y, BlockBase.dirt);
            } else if (y < level) {
                world.setBlock(x, y, BlockBase.stone);
            }
            if (y == 0) {
                world.setBlock(x, y, (short) 0); // bedrock
            }
        }
        int change = rand.nextInt(8);
        if (change == 0) {
            undulatingLevel++;
        } else if (change == 1) {
            undulatingLevel--;
        }
        if (undulatingLevel < world.getSeaLevel() - 10) {
            undulatingLevel += rand.nextInt(6);
        } else if (undulatingLevel > world.getSeaLevel() + 10) {
            undulatingLevel -= rand.nextInt(6);
        }
    }
}
