package Game.entity;

import Game.base.GameBase;
import Game.base.World;
import Game.entity.player.GameMode;
import Game.render.RenderPlayer;

/**
 *
 * @author maximumtech
 */
public class EntityPlayer extends EntityLiving implements ICollector {
    
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
    
    public boolean onCollect(ICollectable item) {
        item.onCollected(this);
        return true;
    }
    
    public boolean canCollect(ICollectable item) {
        return true;
    }
    
    public boolean shouldRender() {
        return world.mainPlayer!=this;
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
    
    private boolean isFlying = false;
    
    public void setFlying(boolean flying) {
        isFlying = flying;
    }
    
    public boolean isFlying() {
        return getGameMode().canFly() && isFlying;
    }
    
    public boolean canFall() {
        return !isFlying();
    }
    
    public boolean canCollide() {
        return !isFlying();
    }
}
