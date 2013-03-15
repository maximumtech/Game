package Game.render;

import org.newdawn.slick.Image;
import java.util.ArrayList;

/**
 *
 * @author maximumtech
 */
public class RenderStack implements BlockRenderBase {
    private ArrayList<Image> stack = new ArrayList<>();
    
    public RenderStack() {
        
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
