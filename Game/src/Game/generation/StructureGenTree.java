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
        int height = 13 + rand.nextInt(7);
        int leavesProg = 1;
        boolean hitDelim = false;
        for (int i = 0; i < height; i++) {
            int yy = y + i;
            if(i < height - 2) {
                world.setBlock(x, yy, BlockBase.woodLog);
            }
            if(i>height - 11 && leavesProg > 0) {
                for(int o = x - leavesProg;o<=x + leavesProg;o++) {
                    if(o!=x || i >= height - 2) {
                        world.setBlock(o, yy, BlockBase.leaves);
                    }
                }
                if(leavesProg > 4) {
                    hitDelim = true;
                }
                if(hitDelim) {
                    leavesProg--;
                }else{
                    leavesProg++;
                }
            }
        }
    }
}
