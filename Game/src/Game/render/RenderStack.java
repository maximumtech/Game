package Game.render;

import org.newdawn.slick.Image;
import java.util.ArrayList;

/**
 *
 * @author maximumtech
 */
public class RenderStack implements RenderStackBase {
    private ArrayList<Image> stack = new ArrayList<>();
    
    public RenderStack() {
        
    }
    
    public Image getImage(int index) {
        return stack.get(index);
    }
    
    public int getSize() {
        return stack.size();
    }
    
    public RenderStack add(Image img) {
        stack.add(img);
        return this;
    }
    
    public void render(int x, int y) {
        for (Image img : stack) {
            ImageHandler.drawImage2D(img, x, y);
        }
    }
    
    public void render(int x, int y, int width, int height) {
        for (Image img : stack) {
            ImageHandler.drawImage2D(img, x, y, width, height);
        }
    }
}
