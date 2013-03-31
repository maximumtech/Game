package Game.base.backend;

import Game.base.GameBase;
import Game.entity.EntityPlayer;

/**
 *
 * @author Richard Roe
 */
public class SingleplayerDebug extends Debug {

    private EntityPlayer thePlayer;

    public SingleplayerDebug() {
        super();
        this.thePlayer = GameBase.instance.getWorld().mainPlayer;
    }

    @Override
    public void updateDebug() {
        super.updateDebug();
        this.getOutput().add("FPS: " + GameBase.instance.getFPS());
        this.getOutput().add("TPS: " + GameBase.instance.getTPS());
        this.getOutput().add("X: " + this.thePlayer.getBlockX());
        this.getOutput().add("Y: " + this.thePlayer.getBlockY());
        this.getOutput().add("GameMode: " + this.thePlayer.getGameMode().getTitle());
        this.getOutput().add("Health: " + this.thePlayer.getHealth() / 2);
    }
}
