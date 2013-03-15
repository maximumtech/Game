package Game.base;

import org.newdawn.slick.Image;

/**
 *
 * @author maximumtech
 */
public abstract class BlockBase {
    
    public short blockID;
    public short metadata;
    public String name;
    private Image image;
    
    public BlockBase(short id) {
        this.blockID = id;
    }
    
    public BlockBase(short id, short meta) {
        this.blockID = id;
        this.metadata = meta;
    }
    
    public void setImage(Image img) {
        this.image = img;
    }
    
    public Image getImage() {
        return image;
    }
    
    
    
}
