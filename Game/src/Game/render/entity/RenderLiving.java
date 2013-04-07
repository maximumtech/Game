package Game.render.entity;

import org.newdawn.slick.Image;
import Game.entity.EntityLiving;
import Game.render.ImageHandler;

/**
 *
 * @author maximumtech
 */
public class RenderLiving extends RenderEntity {

    //private Image still = ImageHandler.getImage("blockGrass/blockGrass1");
    private Image still = ImageHandler.getImage("blockChest/blockChest");
    //TODO: add animation f/ player

    public RenderLiving(EntityLiving entity) {
        super(entity);
    }

    public void render(int x, int y) {
        ImageHandler.drawImage2D(still, x, y, 2, entity.getWidth(), entity.getHeight());
    }
}