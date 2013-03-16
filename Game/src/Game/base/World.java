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
    private short[] ids;
    private short[] metas;
    private String[] data;
    
    public World(int width, int height, int seaLevel, int blockSize) {
        this.blockSize = blockSize;
        this.width = width;
        this.height = height;
        this.seaLevel = seaLevel;
        ids = new short[width * height];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = 0;
        }
        metas = new short[width * height];
        for (int i = 0; i < metas.length; i++) {
            metas[i] = 0;
        }
        data = new String[width * height];
        for (int i = 0; i < data.length; i++) {
            data[i] = null;
        }
        generate();
    }
    
    public short getBlockID(int x, int y) {
        return ids[(x * width) + y];
    }
    
    public short getBlockMeta(int x, int y) {
        return metas[(x * width) + y];
    }
    
    public String getBlockData(int x, int y) {
        return data[(x * width) + y];
    }
    
    public BlockBase getBlock(int x, int y) {
        return BlockBase.blocksList[ids[(x * width) + y]];
    }
    
    public void updateBlock(int x, int y) {
        getBlock(x, y).onUpdate(this, x, y);
    }
    
    public void setBlock(int x, int y, short id, short meta, String data) {
        ids[(x * width) + y] = id;
        metas[(x * width) + y] = meta;
        this.data[(x * width) + y] = data;
        updateBlock(x, y);
        getBlock(x, y).updateNeighbors(this, x, y);
    }
    
    public void setBlock(int x, int y, short id, short meta) {
        setBlock(x, y, id, meta, "");
    }
    
    public void setBlock(int x, int y, short id) {
        setBlock(x, y, id, (short)0);
    }
    
    public void generate() {
        
    }
    
    public void render() {
        
    }
}
