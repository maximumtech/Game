package Game.entity;

import Game.base.GameBase;
import Game.base.ItemStack;
import Game.base.World;
import Game.render.RenderPlayer;
import Game.render.gui.ScreenWorld;
import org.newdawn.slick.Color;

/**
 *
 * @author maximumtech
 */
public class EntityPlayer extends EntityLiving implements ICollector {
    
    public String username = "";
    
    public EntityPlayer(World world, int x, int y, String name) {
        super(world, x, y);
        sizeX = GameBase.blockSize * 2;
        sizeY = GameBase.blockSize * 3;
        this.username = name;
        renderer = new RenderPlayer(this);
    }
    
    public void onCollect(ICollectable item) {
        
    }
    
    public boolean shouldRender() {
        return world.mainPlayer!=this;
    }
    
    public void render(){
        GameBase.fontRenderer.drawStringWithColor("Test", ((ScreenWorld)GameBase.renderScreen).world.mainPlayer.getBlockX(),
                ((ScreenWorld)GameBase.renderScreen).world.mainPlayer.getBlockY() + 30, Color.white);
        super.render();
    }
}
