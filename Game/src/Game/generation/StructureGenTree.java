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
        int height = 12 + rand.nextInt(5);
        int leavesProg = height / 2;
        boolean passedLast = false;
        for (int i = 0; leavesProg > 0; i++) {
            int yy = y + i;
            if (i < height - 2) {
                world.setBlock(x, yy, BlockBase.woodLog);
            }
            if (i > height - 11 && leavesProg > 0) {
                for (int o = x - leavesProg; o <= x + leavesProg; o++) {
                    if (o != x || i >= height - 2) {
                        world.setBlock(o, yy, BlockBase.leaves);
                    }
                }
                //if(leavesProg > 4) {
                //    hitDelim = true;
                //}
                //if(hitDelim) {
                if (rand.nextInt(4) == 0 || passedLast) {
                    leavesProg--;
                    passedLast = false;
                }else{
                    passedLast = true;
                }
                //}else{
                //    leavesProg++;
                //}
            }
        }
    }
}
