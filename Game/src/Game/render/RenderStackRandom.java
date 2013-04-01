package Game.render;

import Game.base.GameBase;
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
        Image img = stack.get((Math.max(x / GameBase.blockSize, 1) * Math.max(y / GameBase.blockSize, 1)) % stack.size());
        ImageHandler.drawImage2D(img, x, y, z);
    }

    public void render(int x, int y, int z, int width, int height) {
        Image img = stack.get((Math.max(x / GameBase.blockSize, 1) * Math.max(y / GameBase.blockSize, 1)) % stack.size());
        ImageHandler.drawImage2D(img, x, y, z, width, height);
    }
}
