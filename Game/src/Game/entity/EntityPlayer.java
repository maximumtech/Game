package Game.entity;

import Game.base.GameBase;
import Game.base.World;
import Game.render.RenderPlayer;

/**
 *
 * @author maximumtech
 */
public class EntityPlayer extends EntityLiving {
    
    public String username = "";
    RenderPlayer renderer;
    
    public EntityPlayer(World world, int x, int y, String name) {
        super(world, x, y);
        this.username = name;
        renderer = new RenderPlayer(this);
    }
    
    public void render() {
        renderer.render(getX(), getY());
    }
    
    static {
        sizeX = GameBase.blockSize * 2;
        sizeY = GameBase.blockSize * 3;
    }
}
