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
    
    public EntityPlayer(World world, int x, int y, String name) {
        super(world, x, y);
        sizeX = GameBase.blockSize * 2;
        sizeY = GameBase.blockSize * 3;
        this.username = name;
        renderer = new RenderPlayer(this);
    }
    
    public boolean shouldRender() {
        return world.mainPlayer!=this;
    }
    
    public void render(){
        super.render();
    }
}
