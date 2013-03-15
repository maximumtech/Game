package Game.render;

import org.newdawn.slick.Image;
import java.util.ArrayList;

/**
 *
 * @author maximumtech
 */
public class AnimatedRenderStack implements BlockRenderBase {
    private ArrayList<Image> stack = new ArrayList<>();
    private ArrayList<Long> delayStack = new ArrayList<>();
    private int next = 0;
    private long prevTime = System.currentTimeMillis();
    
    public AnimatedRenderStack() {
        
    }
    
    public AnimatedRenderStack add(Image img) {
        stack.add(img);
        delayStack.add(1L);
        return this;
    }
    
    public AnimatedRenderStack add(Image img, long delayMillis) {
        stack.add(img);
        delayStack.add(delayMillis);
        return this;
    }
    
    public void render(int x, int y) {
        stack.get(next).draw(x, y);
        if(prevTime <= System.currentTimeMillis() - delayStack.get(next)) {
            prevTime = System.currentTimeMillis();
            next++;
            if(next>=stack.size())next = 0;
        }
    }
    
    public void render(int x, int y, int width, int height) {
        stack.get(next).draw(x, y, width, height);
        if (prevTime <= System.currentTimeMillis() - delayStack.get(next)) {
            prevTime = System.currentTimeMillis();
            next++;
            if (next >= stack.size()) {
                next = 0;
            }
        }
    }
}
