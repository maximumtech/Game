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
        setDimensions(GameBase.blockSize * 2, GameBase.blockSize * 3);
        this.username = name;
        renderer = new RenderPlayer(this);
        this.mode = GameMode.SURVIVAL;
    }

    public void render() {
        GameBase.getFontRenderer(12).drawString(username, getMidX() - GameBase.getFontRenderer(12).getStringWidth(username) / 2, getMaxY() + 30, 4);
        super.render();
    }

    public GameMode getGameMode() {
        return this.mode;
    }

    public void setGameMode(GameMode newMode) {
        this.mode = newMode;
    }
}
