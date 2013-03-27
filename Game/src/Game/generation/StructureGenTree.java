package Game.generation;

import Game.base.BlockBase;
import Game.base.World;

/**
 *
 * @author maximumtech
 */
public class StructureGenTree extends StructureGenBase {

    public StructureGenTree(World world) {
        super(world);
    }

    public void generate(int x, int y) {
        int height = 13 + rand.nextInt(5);
        int leavesProg = height / 2;
        boolean passedLast = false;
        for (int i = 0; leavesProg > 0 || i < height; i++) {
            int yy = y + i;
            if (leavesProg == 0) {
                leavesProg = 1;
            }
            BlockBase block1 = world.getBlock(x, yy);
            if (i < height - 1 && (block1 == null || block1.canBeReplaced(world, x, y, BlockBase.woodLog))) {
                world.setBlock(x, yy, BlockBase.woodLog, (short) 1);
            }
            if (i > height - 11 && leavesProg > 0) {
                for (int o = x - leavesProg; o <= x + leavesProg; o++) {
                    BlockBase block2 = world.getBlock(o, yy);
                    if (block2 == null || block2.canBeReplaced(world, x, y, BlockBase.woodLog)) {
                        if (o != x) {
                            world.setBlock(o, yy, BlockBase.leaves);
                        } else if (i >= height - 1) {
                            world.setBlock(o, yy, BlockBase.leaves, (short) 1);
                        }
                    }
                }
                //if(leavesProg > 4) {
                //    hitDelim = true;
                //}
                //if(hitDelim) {
                if (rand.nextInt(4) == 0 || passedLast) {
                    leavesProg--;
                    passedLast = false;
                } else {
                    passedLast = true;
                }
                //}else{
                //    leavesProg++;
                //}
            } else if (i == height - 11) {
                world.setBlock(x + 1, yy, BlockBase.leaves);
                world.setBlock(x - 1, yy, BlockBase.leaves);
            }
        }
    }
}
