package Game.base;

/**
 *
 * @author maximumtech
 */
public class World {
    private int width;
    private int height;
    private int seaLevel;
    
    public World(int height, int width, int seaLevel, int blockSize) {
        BlockBase.blockSize = blockSize;
        this.width = width;
        this.height = height;
        this.seaLevel = seaLevel;
        generate();
    }
    
    public void generate() {
        
    }
    
    public void render() {
        
    }
}
