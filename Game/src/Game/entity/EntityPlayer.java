package Game.entity;

import Game.base.GameBase;
import Game.base.World;
import Game.entity.player.GameMode;
import Game.render.RenderPlayer;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

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

    public void render() {
        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0, 4F);
        GameBase.getFontRenderer(12).drawString(this.username, 
                (Display.getWidth()/2) - (GameBase.getFontRenderer(12).getStringWidth(this.username)/2) ,
                (Display.getHeight()/2) + 12, 4);
        GL11.glPopMatrix();
        super.render();
    }

    public GameMode getGameMode() {
        return this.mode;
    }

    public void setGameMode(GameMode newMode) {
        this.mode = newMode;
    }
}
