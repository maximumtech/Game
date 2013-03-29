package Game.generation;

import Game.base.BlockBase;
import Game.base.World;
import java.util.ArrayList;
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
        ores.add(new StructureGenOre(world, BlockBase.coalore, 8, world.getSeaLevel() / 10, world.getSeaLevel(), 3));
        ores.add(new StructureGenOre(world, BlockBase.copperore, 6, world.getSeaLevel() / 20, world.getSeaLevel(), 2));
        ores.add(new StructureGenOre(world, BlockBase.tinore, 6, world.getSeaLevel() / 20, world.getSeaLevel(), 2));
        ores.add(new StructureGenOre(world, BlockBase.ironore, 12, world.getSeaLevel() / 40, world.getSeaLevel() / 10 * 8, 5));
        ores.add(new StructureGenOre(world, BlockBase.silverore, 18, world.getSeaLevel() / 50, world.getSeaLevel() / 10 * 5, 8));
        ores.add(new StructureGenOre(world, BlockBase.goldore, 6, world.getSeaLevel() / 70, world.getSeaLevel() / 10 * 5, 2));
    }
    boolean isHill = false;
    boolean goingDownHill = false;
    boolean roundHill;
    int hillSize = 0;
    int hillTopSize = 0;
    private int undulatingLevel;
    public Random rand = new Random();
    public static StructureGenTree treeGen;
    int lastTree = 10;
    private ArrayList<StructureGenOre> ores = new ArrayList<>();

    public void generate() {
        super.generate();
        for (int x = 0; x < world.getWidth() / 100; x++) {
            int minX = x * 100;
            for (StructureGenOre ore : ores) {
                for (int num = 0; num < ore.getAmountPer(); num++) {
                    ore.generate(minX + rand.nextInt(100), 1 + rand.nextInt(ore.getMaxHeight() - 1));
                }
            }
        }
    }

    public void generateColumn(int x) {
        lastTree--;
        boolean isNewHill = rand.nextInt(400) == 0 && !isHill;
        boolean spawnTree = rand.nextInt(20) == 0 && lastTree <= 0 && x > 10 && x < world.getWidth() - 10;
        if (spawnTree && isHill && !roundHill) {
            spawnTree = false;
        }
        boolean spawnFlower = rand.nextInt(15) == 0;
        boolean spawnGrass = rand.nextInt(3) == 0;
        boolean spawnMushroom = rand.nextInt(18) == 0;
        if (spawnTree) {
            lastTree = 10;
        }
        if (isNewHill) {
            goingDownHill = false;
            hillSize = 0;
            roundHill = rand.nextInt(2) == 0;
            hillTopSize = rand.nextInt((world.getHeight() - world.getSeaLevel()) / (roundHill ? 8 : 4));
            isHill = true;
        }
        int level = undulatingLevel;
        if (isHill) {
            if (hillSize > hillTopSize && !goingDownHill) {
                goingDownHill = true;
            }
            if (goingDownHill && hillSize <= 0) {
                isHill = false;
                goingDownHill = false;
                hillSize = 0;
                hillTopSize = 0;
                roundHill = false;
            } else {
                if (goingDownHill) {
                    hillSize -= rand.nextInt(roundHill ? 2 : 4);
                } else {
                    hillSize += rand.nextInt(roundHill ? 2 : 4);
                }
                level += hillSize;
            }
        }
        for (int y = 0; y <= world.getHeight(); y++) {
            if (y == level + 1) {
                if (spawnTree) {
                    treeGen.generate(x, y);
                } else if (spawnMushroom) {
                    world.setBlock(x, y, BlockBase.mushroom);
                } else if (spawnFlower) {
                    world.setBlock(x, y, BlockBase.redflower);
                } else if (spawnGrass) {
                    world.setBlock(x, y, BlockBase.tallgrass);
                }
            } else if (y == level) {
                world.setBlock(x, y, BlockBase.grass);
            } else if (y < level && y > level - (world.getSeaLevel() / 4)) {
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
