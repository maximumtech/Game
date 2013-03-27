package Game.entity;

import Game.base.GameBase;
import Game.base.World;
import Game.entity.player.GameMode;
import Game.render.RenderPlayer;

/**
 *
 * @author maximumtech
 */
public class EntityPlayer extends EntityLiving {
    
    public String username = "";
    private GameMode mode;
    
    public EntityPlayer(World world, int x, int y, String name) {
        super(world, x, y);
        sizeX = GameBase.blockSize * 2;
        sizeY = GameBase.blockSize * 3;
        this.username = name;
        renderer = new RenderPlayer(this);
        this.mode = GameMode.SURVIVAL;
    }
    
    public void render(){
        //TODO: flip text & make work
        //GL11.glTranslatef(getMidX() - GameBase.fontRenderer.getStringWidth(username) / 2, getMaxY() + 30, 0);
        super.render();
    }
    
    public GameMode getGameMode() {
        return this.mode;
    }
    
    public void setGameMode(GameMode newMode) {
        this.mode = newMode;
    }
}
