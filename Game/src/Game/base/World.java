package Game.base;

import java.util.ArrayList;

/**
 *
 * @author maximumtech
 */
public class World {
    private int width;
    private int height;
    private int seaLevel;
    public int blockSize;
    private ArrayList<Short> ids;
    private ArrayList<Short> metas;
    private ArrayList<String> info;
    
    public World(int height, int width, int seaLevel, int blockSize) {
        this.blockSize = blockSize;
        this.width = width;
        this.height = height;
        this.seaLevel = seaLevel;
        ids = new ArrayList<>();
        metas = new ArrayList<>();
        info = new ArrayList<>();
        generate();
    }
    
    public void generate() {
        
    }
    
    public void render() {
        
    }
}
