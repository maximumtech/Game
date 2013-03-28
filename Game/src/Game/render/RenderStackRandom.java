package Game.render;

import org.newdawn.slick.Image;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author maximumtech
 */
public class RenderStackRandom implements RenderStackBase {

    private ArrayList<Image> stack = new ArrayList<>();

    public RenderStackRandom() {
    }

    public Image getImage(int index) {
        return stack.get(index);
    }

    public int getSize() {
        return stack.size();
    }

    public RenderStackRandom add(Image img) {
        stack.add(img);
        return this;
    }

    public void render(int x, int y, int z) {
        int num = stack.size();
        Image img = stack.get(new Random(x * y).nextInt(num));
        ImageHandler.drawImage2D(img, x, y, z);
    }

    public void render(int x, int y, int z, int width, int height) {
        int num = stack.size();
        Image img = stack.get(new Random(x * y).nextInt(num));
        ImageHandler.drawImage2D(img, x, y, z, width, height);
    }
}
