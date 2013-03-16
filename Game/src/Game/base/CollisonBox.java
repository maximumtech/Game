package Game.base;

import org.lwjgl.util.Rectangle;

/**
 *
 * @author maximumtech
 */
public class CollisonBox {
    public int minX;
    public int minY;
    public int maxX;
    public int maxY;
    
    public boolean intersects(CollisonBox box) {
        Rectangle thisRect = new Rectangle(minX, minY, maxX, maxY);
        Rectangle otherRect = new Rectangle(box.minX, box.minY, box.maxX, box.maxY);
        if(thisRect.intersects(otherRect)) {
            return true;
        }
        return false;
    }
    
    public CollisonBox(int minX, int minY, int maxX, int maxY) {
        this.maxX = maxX < minX ? minX : maxX;
        this.maxY = maxY < minY ? minY : maxY;
        this.minX = maxX > minX ? minX : maxX;
        this.minY = maxY > minY ? minY : maxY;
    }
}
