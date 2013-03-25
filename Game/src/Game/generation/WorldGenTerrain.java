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
    
    public void generateTree(int x, int y){
        Random randHeight = new Random();
        int height = randHeight.nextInt(4) + 6;
        
        for(int i = 1; i < height; i++){
            if(i == height - 3){
                world.setBlock(x - 2, y + i, BlockBase.leaves);
                world.setBlock(x - 1, y + i, BlockBase.leaves);
                world.setBlock(x + 2, y + i, BlockBase.leaves);
                world.setBlock(x + 1, y + i, BlockBase.leaves);
                world.setBlock(x, y + i, BlockBase.wood);
            } else if(i == height - 2){
                world.setBlock(x - 1, y + i, BlockBase.leaves);
                world.setBlock(x + 1, y + i, BlockBase.leaves);
                world.setBlock(x, y + i, BlockBase.wood);
            } else if(i == height - 1){
                world.setBlock(x, y + i, BlockBase.leaves);
            } else {
                world.setBlock(x, y + i, BlockBase.wood);
            }
        }
    }
    
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
                if(rand.nextInt(10) == 0){
                    generateTree(x, y);
                }
                world.setBlock(x, y, BlockBase.grass);
            } else if (y < level && y > level - (world.getSeaLevel() / 3)) {
                world.setBlock(x, y, BlockBase.dirt);
            } else if (y < level) {
                world.setBlock(x, y, BlockBase.stone);
            }
            if (y == 0) {
                world.setBlock(x, y, (short) 0); //Bedrock broke
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
