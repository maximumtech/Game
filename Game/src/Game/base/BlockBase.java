package Game.base;

import Game.render.RenderStackBase;

/**
 *
 * @author maximumtech
 */
public class BlockBase {

    public short blockID;
    public String name;
    private RenderStackBase image;
    public static BlockBase[] blocksList = new BlockBase[Short.MAX_VALUE];

    public BlockBase(short id) {
        this.blockID = id;
        blocksList[id] = this;
    }

    public void setImage(RenderStackBase img) {
        this.image = img;
    }

    public RenderStackBase getImage() {
        return image;
    }

    public void render(World world, int x, int y) {
        getImage().render(x, y, GameBase.blockSize, GameBase.blockSize);
    }

    public boolean canCollide(World world, int x, int y) {
        return true;
    }

    public boolean isSolid(World world, int x, int y) {
        return true;
    }

    public boolean isOpaque(World world, int x, int y) {
        return true;
    }

    public CollisonBox getCollisonBox(World world, int x, int y) {
        return new CollisonBox(x, y, x + GameBase.blockSize, y + GameBase.blockSize);
    }
    
    public void onNeighborUpdate(World world, int x, int y) {
    }

    public void onUpdate(World world, int x, int y) {
    }

    static {
        //declare blocks
    }
}
