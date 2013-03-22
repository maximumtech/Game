package Game.base;

import Game.entity.EntityPlayer;
import Game.entity.Entity;
import Game.generation.WorldGenBase;
import Game.generation.WorldGenTerrain;
import java.util.ArrayList;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

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
        entityList = new ArrayList<>();
        generate();
        EntityPlayer ep = new EntityPlayer(this, 160, 600, "");
        setMainPlayer(ep);
    }
    
    private void setMainPlayer(EntityPlayer player) {
        this.mainPlayer = player;
        spawnEntity(player);
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
        return getBlockID(x, y) == 0;
    }

    public int getCoordinateFromPixel(int pix) {
        return (int)Math.floor((double)pix / (double)GameBase.blockSize);
    }
    
    public int getPixelFromCoordinate(int coord) {
        return coord * GameBase.blockSize;
    }

    public int getRenderHeight() {
        return (Display.getHeight() / GameBase.blockSize) + 2;
    }

    public int getRenderWidth() {
        return (Display.getWidth() / GameBase.blockSize) + 2;
    }

    public short getBlockID(int x, int y) {
        if (((x * getWidth()) + y) > (getWidth() * getHeight()) - 1 || ((x * getWidth()) + y) < 0) {
            return 0;
        }
        return ids[(x * getWidth()) + y];
    }

    public short getBlockMeta(int x, int y) {
        if (((x * getWidth()) + y) > (getWidth() * getHeight()) - 1 || ((x * getWidth()) + y) < 0) {
            return 0;
        }
        return metas[(x * getWidth()) + y];
    }

    public String getBlockData(int x, int y) {
        if (((x * getWidth()) + y) > (getWidth() * getHeight()) - 1 || ((x * getWidth()) + y) < 0) {
            return "";
        }
        return data[(x * getWidth()) + y];
    }

    public BlockBase getBlock(int x, int y) {
        if (((x * getWidth()) + y) > (getWidth() * getHeight()) - 1 || ((x * getWidth()) + y) < 0) {
            return null;
        }
        return BlockBase.blocksList[ids[(x * getWidth()) + y]];
    }

    public short getBacktileID(int x, int y) {
        if (((x * getWidth()) + y) > (getWidth() * getHeight()) - 1 || ((x * getWidth()) + y) < 0) {
            return 0;
        }
        return backtileids[(x * getWidth()) + y];
    }

    public short getBacktileMeta(int x, int y) {
        if (((x * getWidth()) + y) > (getWidth() * getHeight()) - 1 || ((x * getWidth()) + y) < 0) {
            return 0;
        }
        return backtilemetas[(x * getWidth()) + y];
    }

    public String getBacktileData(int x, int y) {
        if (((x * getWidth()) + y) > (getWidth() * getHeight()) - 1 || ((x * getWidth()) + y) < 0) {
            return "";
        }
        return backtiledata[(x * getWidth()) + y];
    }

    public BackTileBase getBacktile(int x, int y) {
        if (((x * getWidth()) + y) > (getWidth() * getHeight()) - 1 || ((x * getWidth()) + y) < 0) {
            return null;
        }
        return BackTileBase.backtileList[backtileids[(x * getWidth()) + y]];
    }

    public void updateBlock(int x, int y) {
        BlockBase block = getBlock(x, y);
        if (block != null) {
            block.onUpdate(this, x, y);
        }
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
        if (((x * getWidth()) + y) > (getWidth() * getHeight()) - 1 || ((x * getWidth()) + y) < 0) {
            return;
        }
        ids[(x * width) + y] = id;
        metas[(x * width) + y] = meta;
        this.data[(x * width) + y] = data;
        updateBlockAndNeighbors(x, y);
    }

    public void setBlock(int x, int y, short id, short meta) {
        setBlock(x, y, id, meta, "");
    }

    public void setBlock(int x, int y, short id) {
        setBlock(x, y, id, (short) 0);
    }

    public void setBlock(int x, int y, BlockBase block) {
        setBlock(x, y, block.getBlockID());
    }
    
    public void setBlock(int x, int y, BlockBase block, short meta) {
        setBlock(x, y, block.getBlockID(), meta);
    }
    private WorldGenBase terrainGen;

    public void generate() {
        terrainGen = new WorldGenTerrain(this);
        terrainGen.generate();
    }

    public void onUpdate() {
        for (Entity entity : entityList) {
            entity.onUpdate();
        }
    }
}