package Game.render;

import org.newdawn.slick.Image;

/**
 *
 * @author maximumtech
 */
public interface BlockRenderBase {
    public void render(int x, int y);
    public void render(int x, int y, int width, int height);
    public BlockRenderBase add(Image img);
}
