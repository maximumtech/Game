package Game.base;

import Game.render.RenderStackBase;

/**
 *
 * @author maximumtech
 */
public class BlockBase {
    
    public short blockID;
    public short metadata;
    public String name;
    private RenderStackBase image;
    public static int blockSize = 16;
    
    public BlockBase(short id) {
        this.blockID = id;
    }
    
    public BlockBase(short id, short meta) {
        this.blockID = id;
        this.metadata = meta;
    }
    
    public void setImage(RenderStackBase img) {
        this.image = img;
    }
    
    public RenderStackBase getImage() {
        return image;
    }
    
    public void render(int x, int y) {
        getImage().render(x, y, blockSize, blockSize);
    }
    
    public boolean canCollide(World world, int x, int y) {
        return true;
    }
    
    public boolean isSolid(World world, int x, int y) {
        return true;
    }
    
    public CollisonBox getCollisonBox(World world, int x, int y) {
        return new CollisonBox(x, y, x+blockSize, y+blockSize);
    }
    
}
