package Game.render;

import org.newdawn.slick.Image;

/**
 *
 * @author maximumtech
 */
public interface RenderStackBase {
    public void render(int x, int y, int z);
    public void render(int x, int y, int z, int width, int height);
    public RenderStackBase add(Image img);
    public int getSize();
}
