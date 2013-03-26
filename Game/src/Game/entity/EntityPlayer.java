package Game.entity;

import Game.base.GameBase;
import Game.base.ItemStack;
import Game.base.World;
import Game.entity.player.GameMode;
import Game.misc.Side;
import Game.render.RenderPlayer;
import Game.render.gui.ScreenWorld;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

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
    
    public GameMode getPlayerGameMode() {
        return this.mode;
    }
    
    public void setGameMode(GameMode newMode) {
        this.mode = newMode;
    }
    
    public void onUpdate() {
        if(this.getPlayerGameMode().getModeFlightAbility()) {
            this.handleFlight();
        }else {
            super.onUpdate();
        }
    }
    
    public void handleFlight() {
        this.motionX = 0;
        this.motionY = 0;
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) this.setYPos(this.getY() + 15);
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) this.setYPos(this.getY() - 15);
        if(Keyboard.isKeyDown(Keyboard.KEY_A)) this.setXPos(this.getX() - 20);
        if(Keyboard.isKeyDown(Keyboard.KEY_D)) this.setXPos(this.getX() + 20);
    }
}
