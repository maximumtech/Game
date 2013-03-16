package Game.render;

import org.newdawn.slick.Image;

/**
 *
 * @author maximumtech
 */
public interface RenderStackBase {
    public void render(int x, int y);
    public void render(int x, int y, int width, int height);
    public RenderStackBase add(Image img);
}
