package Game.base;

import Game.entity.EntityPlayer;
import Game.entity.Entity;
import java.util.ArrayList;

/**
 *
 * @author maximumtech
 */
public class World {
    private int width;
    private int height;
    private int seaLevel;
    private short[] ids;
    private short[] metas;
    private String[] data;
    private short[] backtileids;
    private short[] backtilemetas;
    private String[] backtiledata;
    public EntityPlayer mainPlayer;
    public ArrayList<Entity> entityList;
    
    
    public World(int width, int height, int seaLevel, int blockSize) {
        GameBase.blockSize = blockSize;
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
            data[i] = "";
        }
        backtileids = new short[width * height];
        for (int i = 0; i < backtileids.length; i++) {
            backtileids[i] = 0;
        }
        backtilemetas = new short[width * height];
        for (int i = 0; i < backtilemetas.length; i++) {
            backtilemetas[i] = 0;
        }
        backtiledata = new String[width * height];
        for (int i = 0; i < backtiledata.length; i++) {
            backtiledata[i] = "";
        }
        generate();
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getSeaLevel() {
        return seaLevel;
    }
    
    public void spawnEntity(Entity ent) {
        entityList.add(ent);
    }
    
    public boolean isAirBlock(int x, int y) {
        return getBlockID(x, y)==0;
    }
    
    public int getCoordinateFromPixel(int pix) {
        return pix / GameBase.blockSize;
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
    
    public short getBacktileID(int x, int y) {
        return backtileids[(x * width) + y];
    }
    
    public short getBacktileMeta(int x, int y) {
        return backtilemetas[(x * width) + y];
    }
    
    public String getBacktileData(int x, int y) {
        return backtiledata[(x * width) + y];
    }
    
    
    public void updateBlock(int x, int y) {
        getBlock(x, y).onUpdate(this, x, y);
    }
    
    public void updateBlockAndNeighbors(int x, int y) {
        updateBlock(x, y);
        updateNeighbors(x, y);
    }
    
    public void updateNeighbors(int x, int y) {
        updateBlock(x + 1, y);
        updateBlock(x - 1, y);
        updateBlock(x, y + 1);
        updateBlock(x, y - 1);
    }
    
    public void setBlock(int x, int y, short id, short meta, String data) {
        ids[(x * width) + y] = id;
        metas[(x * width) + y] = meta;
        this.data[(x * width) + y] = data;
        updateBlockAndNeighbors(x, y);
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
        for(Entity entity : entityList) {
            entity.render();
        }
    }
    
    public void onUpdate() {
        for (Entity entity : entityList) {
            entity.onUpdate();
        }
    }
}