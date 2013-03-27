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
        treeGen = new StructureGenTree(world);
    }
    boolean isHill = false;
    boolean goingDownHill = false;
    int hillSize = 0;
    int hillTopSize = 0;
    private int undulatingLevel;
    public Random rand = new Random();
    StructureGenTree treeGen;
    int lastTree = 10;

    public void generateColumn(int x) {
        lastTree--;
        boolean isNewHill = rand.nextInt(50) == 0 && !isHill;
        boolean spawnTree = rand.nextInt(10) == 0 && lastTree <= 0;
        boolean spawnFlower = rand.nextInt(15) == 0;
        boolean spawnGrass = rand.nextInt(3) == 0;
        boolean spawnMushroom = rand.nextInt(18) == 0;
        if (spawnTree) {
            lastTree = 10;
        }
        if (isNewHill) {
            goingDownHill = false;
            hillSize = 0;
            hillTopSize = rand.nextInt(world.getSeaLevel() * 4 / 3);
        }
        int level = undulatingLevel;
        if (isHill) {
            if (hillSize > hillTopSize + level) {
                goingDownHill = true;
            }
            if (goingDownHill && hillSize <= 0) {
                isHill = false;
                goingDownHill = false;
                hillSize = 0;
                hillTopSize = 0;
            } else {
                if (goingDownHill) {
                    hillSize -= rand.nextInt(4);
                } else {
                    hillSize += rand.nextInt(4);
                }
                level += hillSize;
            }
        }
        for (int y = 0; y <= world.getHeight(); y++) {
            if(y==level+1) {
                if (spawnTree) {
                    treeGen.generate(x, y);
                } else if (spawnMushroom) {
                    world.setBlock(x, y, BlockBase.mushroom);
                } else if (spawnFlower) {
                    world.setBlock(x, y, BlockBase.redflower);
                } else if (spawnGrass) {
                    world.setBlock(x, y, BlockBase.tallgrass);
                }
            }else if (y == level) {
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
