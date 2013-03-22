package Game.entity;

import Game.base.GameBase;
import Game.base.World;
import Game.render.RenderPlayer;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author maximumtech
 */
public class EntityPlayer extends EntityLiving {
    
    public String username = "";
    
    public EntityPlayer(World world, int x, int y, String name) {
        super(world, x, y);
        sizeX = GameBase.blockSize * 2;
        sizeY = GameBase.blockSize * 3;
        this.username = name;
        renderer = new RenderPlayer(this);
    }
    
    public void render() {
        if (renderer != null) {
            if(world.mainPlayer != this) {
                renderer.render(getX(), getY());
            }
        }
    }
}
