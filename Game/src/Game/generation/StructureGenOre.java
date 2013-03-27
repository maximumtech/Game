package Game.generation;

import Game.base.BlockBase;
import Game.base.World;

/**
 *
 * @author maximumtech
 */
public class StructureGenOre extends StructureGenBase {
    
    private BlockBase ore;
    private int size;
    private int amt;
    private int maxHeight;
    private int density;
    
    public StructureGenOre(World world, BlockBase ore, int size, int amt, int maxHeight, int density) {
        super(world);
        this.ore = ore;
        this.size = size;
        this.amt = amt;
        this.maxHeight = maxHeight;
        this.density = density;
    }
    
    public int getAmountPer() {
        return amt;
    }
    
    public int getMaxHeight() {
        return maxHeight;
    }
    
    public void generate(int xx, int yy) {
        for(int x = xx;x<xx+size;x++) {
            for (int y = yy; y < yy + size; y++) {
                if(rand.nextInt(density)==0) {
                    BlockBase block = world.getBlock(x, y);
                    if(block!=null && block.isGenerationReplacable()) {
                        world.setBlock(x, y, ore);
                    }
                }
            }
        }
    }
    
}
