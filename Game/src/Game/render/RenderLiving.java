package Game.render;

import org.newdawn.slick.Image;
import Game.entity.EntityLiving;

/**
 *
 * @author maximumtech
 */
public class RenderLiving extends RenderEntity {
    private Image still = ImageHandler.getImage("blockGrass/blockGrass");
    //TODO: add animation f/ player
    
    public RenderLiving(EntityLiving entity) {
        super(entity);
    }
    
    public void render(int x, int y) {
        ImageHandler.drawImage2D(still, x, y, 2, entity.sizeX, entity.sizeY);
    }
}
