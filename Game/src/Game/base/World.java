package Game.base;

import Game.entity.Entity;
import Game.entity.EntityPlayerSP;
import Game.generation.WorldGenTerrain;
import Game.misc.MathHelper;
import java.util.ArrayList;
import java.util.Random;
import org.lwjgl.opengl.Display;

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
    private TileEntityBase[] data;
    private short[] backtileids;
    private short[] backtilemetas;
    private TileEntityBase[] backtiledata;
    public EntityPlayerSP mainPlayer;
    public ArrayList<Entity> entityList;
    private ArrayList<Entity> spawnEntityList;
    private ArrayList<Entity> despawnEntityList;
    public static boolean isLocal = true;

    public World(int width, int height, int seaLevel, int blockSize) {
        GameBase.blockSize = blockSize;
        this.width = width;
        this.height = height;
        this.seaLevel = seaLevel;
        ids = new short[(width * width) + height];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = 0;
        }
        metas = new short[(width * width) + height];
        for (int i = 0; i < metas.length; i++) {
            metas[i] = 0;
        }
        data = new TileEntityBase[(width * width) + height];
        for (int i = 0; i < data.length; i++) {
            data[i] = null;
        }
        backtileids = new short[(width * width) + height];
        for (int i = 0; i < backtileids.length; i++) {
            backtileids[i] = 0;
        }
        backtilemetas = new short[(width * width) + height];
        for (int i = 0; i < backtilemetas.length; i++) {
            backtilemetas[i] = 0;
        }
        backtiledata = new TileEntityBase[(width * width) + height];
        for (int i = 0; i < backtiledata.length; i++) {
            backtiledata[i] = null;
        }
        entityList = new ArrayList<>();
        spawnEntityList = new ArrayList<>();
        despawnEntityList = new ArrayList<>();
        generate();
        EntityPlayerSP ep = new EntityPlayerSP(this, 160, getPixelFromCoordinate(seaLevel) + 160, "TempName");
        setMainPlayer(ep);
    }

    public Entity getNearestEntity(int x, int y, double dist, Class<?>[] entityType) {
        Entity ent1 = null;
        synchronized (entityList) {
            for (Entity ent2 : entityList) {
                synchronized (ent2) {
                    boolean isClass = entityType != null;
                    if (!isClass) {
                        for (Class<?> clas : entityType) {
                            for (Class<?> clas2 : ent2.getClass().getInterfaces()) {
                                if (clas.equals(clas2)) {
                                    isClass = true;
                                    break;
                                }
                                if (!isClass) {
                                    if (clas.equals(ent2.getClass())) {
                                        isClass = true;
                                    }
                                }
                            }
                        }
                    }
                    if (entityType != null ? isClass : true) {
                        double ent1Dist = ent1 == null ? 0D : MathHelper.getDistance(ent1.getMidX(), ent1.getMidY(), x, y);
                        double ent2Dist = MathHelper.getDistance(ent2.getMidX(), ent2.getMidY(), x, y);
                        if (ent2Dist >= ent1Dist && ent2Dist <= dist) {
                            ent1 = ent2;
                        }
                    }
                }
            }
        }
        return ent1;
    }

    public Entity getNearestEntity(Entity entity, double dist, Class<?>[] entityType, int iter) {
        Entity ent1 = null;
        if (entity == null) {
            return null;
        }
        synchronized (entityList) {
            int itr = 0;
            for (Entity ent2 : entityList) {
                if (itr < iter) {
                    itr++;
                    continue;
                }
                synchronized (ent2) {
                    boolean isClass = entityType == null;
                    if (!isClass) {
                        for (Class<?> clas : entityType) {
                            Class<?>[] clases = ent2.getClass().getInterfaces();
                            for (Class<?> clas2 : clases) {
                                if (clas.equals(clas2)) {
                                    isClass = true;
                                    break;
                                }
                                if (!isClass) {
                                    if (clas.equals(ent2.getClass())) {
                                        isClass = true;
                                    }
                                }
                            }
                        }
                    }
                    if (ent2 != null && isClass && entity != ent2) {
                        double ent1Dist = ent1 == null ? 0D : MathHelper.getDistance(ent1.getMidX(), ent1.getMidY(), entity.getMidX(), entity.getMidY());
                        double ent2Dist = MathHelper.getDistance(ent2.getMidX(), ent2.getMidY(), entity.getMidX(), entity.getMidY());
                        if (ent2Dist >= ent1Dist && ent2Dist <= dist) {
                            ent1 = ent2;
                        }
                    }
                }
            }
        }
        return ent1;
    }

    private void setMainPlayer(EntityPlayerSP player) {
        this.mainPlayer = player;
        spawnEntity(player);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPixelWidth() {
        return getPixelFromCoordinate(getWidth());
    }

    public int getPixelHeight() {
        return getPixelFromCoordinate(getHeight());
    }

    public int getSeaLevel() {
        return seaLevel;
    }

    public synchronized void spawnEntity(Entity ent) {
        spawnEntityList.add(ent);
    }

    public synchronized void despawnEntity(Entity ent) {
        despawnEntityList.add(ent);
    }

    public boolean isAirBlock(int x, int y) {
        return getBlockID(x, y) == 0;
    }

    public int getCoordinateFromPixel(int pix) {
        return (int) Math.floor((double) pix / (double) GameBase.blockSize);
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
        if (((x * getWidth()) + y) > (getWidth() * getWidth()) + getHeight() - 1 || ((x * getWidth()) + y) < 0) {
            return 0;
        }
        return ids[(x * getWidth()) + y];
    }

    public short getBlockMeta(int x, int y) {
        if (((x * getWidth()) + y) > (getWidth() * getWidth()) + getHeight() - 1 || ((x * getWidth()) + y) < 0) {
            return 0;
        }
        return metas[(x * getWidth()) + y];
    }

    public TileEntityBase getBlockTileEntity(int x, int y) {
        if (((x * getWidth()) + y) > (getWidth() * getWidth()) + getHeight() - 1 || ((x * getWidth()) + y) < 0) {
            return null;
        }
        return data[(x * getWidth()) + y];
    }

    public BlockBase getBlock(int x, int y) {
        if (((x * getWidth()) + y) > (getWidth() * getWidth()) + getHeight() - 1 || ((x * getWidth()) + y) < 0) {
            return null;
        }
        return BlockBase.blocksList[ids[(x * getWidth()) + y]];
    }

    public short getBacktileID(int x, int y) {
        if (((x * getWidth()) + y) > (getWidth() * getWidth()) + getHeight() - 1 || ((x * getWidth()) + y) < 0) {
            return 0;
        }
        return backtileids[(x * getWidth()) + y];
    }

    public short getBacktileMeta(int x, int y) {
        if (((x * getWidth()) + y) > (getWidth() * getWidth()) + getHeight() - 1 || ((x * getWidth()) + y) < 0) {
            return 0;
        }
        return backtilemetas[(x * getWidth()) + y];
    }

    public TileEntityBase getBacktileTileEntity(int x, int y) {
        if (((x * getWidth()) + y) > (getWidth() * getWidth()) + getHeight() - 1 || ((x * getWidth()) + y) < 0) {
            return null;
        }
        return backtiledata[(x * getWidth()) + y];
    }

    public BackTileBase getBacktile(int x, int y) {
        if (((x * getWidth()) + y) > (getWidth() * getWidth()) + getHeight() - 1 || ((x * getWidth()) + y) < 0) {
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

    public void neighborUpdate(int x, int y) {
        BlockBase block = getBlock(x, y);
        if (block != null) {
            block.onNeighborUpdate(this, x, y);
        }
    }

    public void updateBlockAndNeighbors(int x, int y) {
        updateBlock(x, y);
        updateNeighbors(x, y);
    }

    public void updateNeighbors(int x, int y) {
        neighborUpdate(x + 1, y);
        neighborUpdate(x - 1, y);
        neighborUpdate(x, y + 1);
        neighborUpdate(x, y - 1);
    }

    public void setBlock(int x, int y, short id, short meta, TileEntityBase data) {
        if (((x * getWidth()) + y) > ((getWidth() * getWidth())) + getHeight() - 1 || ((x * getWidth()) + y) < 0) {
            return;
        }
        BlockBase block = BlockBase.blocksList[id];
        block = block != null ? block.getBlockForPlacement(this, x, y) : null;
        if (block == null || block.canBePlacedHere(this, x, y)) {
            ids[(x * width) + y] = id;
            metas[(x * width) + y] = meta;
            this.data[(x * width) + y] = data;
            if (block != null) {
                block.onPlace(this, x, y);
            }
        }
        updateBlockAndNeighbors(x, y);
    }

    public void setBlock(int x, int y, short id, short meta) {
        setBlock(x, y, id, meta, null);
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

    public void setTileEntity(int x, int y, TileEntityBase data) {
        if (((x * getWidth()) + y) > ((getWidth() * getWidth())) + getHeight() - 1 || ((x * getWidth()) + y) < 0) {
            return;
        }
        this.data[(x * width) + y] = data;
    }

    public int[] getRelativePixelFromScreen(int x, int y) {
        int wid = Display.getWidth() / 2;
        int hei = Display.getHeight() / 2;
        int xx = mainPlayer.getMidX() - wid + x;
        int yy = mainPlayer.getMidY() - hei + y;
        return new int[]{xx, yy};
    }

    public int[] getRelativeCoordinateFromScreen(int x, int y) {
        int[] pix = getRelativePixelFromScreen(x, y);
        return new int[]{getCoordinateFromPixel(pix[0]), getCoordinateFromPixel(pix[1])};
    }
    private WorldGenTerrain terrainGen;

    public void generate() {
        terrainGen = new WorldGenTerrain(this);
        terrainGen.generate();
    }

    private void updateEntities() {
        synchronized (entityList) {
            entityList.addAll(spawnEntityList);
            entityList.removeAll(despawnEntityList);
            despawnEntityList.clear();
            spawnEntityList.clear();
            for (Entity entity : entityList) {
                synchronized (entity) {
                    entity.onUpdate();
                }
            }
        }
    }
    public static Random rand = new Random();

    public void onUpdate() {
        updateEntities();
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                BlockBase block = getBlock(x, y);
                if (block != null && block.requiresRandomTick() && (rand.nextInt(block.getTickRate()) == 0)) {
                    block.tick(this, x, y);
                }
            }
        }
    }
}
