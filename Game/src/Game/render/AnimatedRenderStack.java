package Game.render;

import org.newdawn.slick.Image;
import java.util.ArrayList;

/**
 *
 * @author maximumtech
 */
public class AnimatedRenderStack implements RenderStackBase {
    private ArrayList<Image> stack = new ArrayList<>();
    private ArrayList<Long> delayStack = new ArrayList<>();
    private int next = 0;
    private long prevTime = System.currentTimeMillis();
    
    public AnimatedRenderStack() {
        
    }
    
    public Image getImage(int index) {
        return stack.get(index);
    }
    
    public long getDelay(int index) {
        return delayStack.get(index);
    }
    
    public int getSize() {
        return stack.size();
    }
    
    public AnimatedRenderStack add(Image img) {
        stack.add(img);
        return this;
    }
    
    public AnimatedRenderStack add(Image img, long delayMillis) {
        stack.add(img);
        delayStack.add(delayMillis);
        return this;
    }
    
    public AnimatedRenderStack add(long delayMillis) {
        delayStack.add(delayMillis);
        return this;
    }
    
    public void render(int x, int y) {
        ImageHandler.drawImage2D(stack.get(next),x, y);
        if(prevTime <= System.currentTimeMillis() - delayStack.get(next)) {
            prevTime = System.currentTimeMillis();
            next++;
            if(next>=stack.size())next = 0;
        }
    }
    
    public void render(int x, int y, int width, int height) {
        ImageHandler.drawImage2D(stack.get(next), x, y, width, height);
        if (prevTime <= System.currentTimeMillis() - delayStack.get(next)) {
            prevTime = System.currentTimeMillis();
            next++;
            if (next >= stack.size()) {
                next = 0;
            }
        }
    }
}
