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
    boolean goingDownHill = false;
    int hillSize = 0;
    int hillTopSize = 0;
    boolean isRoundHill = false;
    private int undulatingLevel;
    public Random rand = new Random();

    public void generateColumn(int x) {
        boolean isNewHill = rand.nextInt(50) == 0 && !isHill;
        if (isNewHill) {
            goingDownHill = false;
            isRoundHill = rand.nextInt(2) == 0;
            hillSize = 0;
            hillTopSize = isRoundHill ? world.getSeaLevel() / 4 : rand.nextInt(world.getSeaLevel() * 4 / 3);
        }
        int level = undulatingLevel;
        if (isHill) {
            if(hillSize > hillTopSize) {
                goingDownHill = true;
            }
            if (goingDownHill && hillSize <= 0) {
                isHill = false;
                goingDownHill = false;
                isRoundHill = false;
                hillSize = 0;
                hillTopSize = 0;
            } else {
                if (goingDownHill) {
                    hillSize -= isRoundHill ? rand.nextInt(2) : rand.nextInt(4);
                } else {
                    hillSize += isRoundHill ? rand.nextInt(2) : rand.nextInt(4);
                }
                level += hillSize;
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
                world.setBlock(x, y, BlockBase.bedrock);
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
