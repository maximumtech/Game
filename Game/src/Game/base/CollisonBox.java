package Game.base;

import Game.misc.Side;
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
    
    public boolean isColliding(CollisonBox box, Side side) {
        boolean inColumn = (box.minX >= this.minX || box.maxX <= this.maxX);
        boolean inRow = (box.minY >= this.minY || box.maxY <= this.maxY);
        if(side == Side.BOTTOM) {
            if(inColumn && box.maxY > this.minY) {
                return true;
            }
        } else if (side == Side.TOP) {
            if (inColumn && box.minY > this.maxY) {
                return true;
            }
        }else if (side == Side.LEFT) {
            if (inRow && box.maxX > this.minX) {
                return true;
            }
        } else if (side == Side.RIGHT) {
            if (inRow && box.minX < this.maxX) {
                return true;
            }
        }
        return false;
    }
    
    public CollisonBox(int minX, int minY, int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.minX = minX;
        this.minY = minY;
    }
}
