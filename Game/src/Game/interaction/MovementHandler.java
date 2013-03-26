package Game.interaction;

import Game.base.GameBase;
import Game.base.World;
import Game.entity.player.GameMode;

/**
 *
 * @author maximumtech
 */
public class MovementHandler implements IKeyboardHandler, ITickHandler {

    public MovementHandler() {
        KeyboardHandler.instance.registerHandler(this);
        TickHandler.instance.registerTickHandler(this);
    }

    public void keyDown(String key) {
        World world = GameBase.instance.getWorld();
        if (world != null && world.mainPlayer != null) {
            if (key.equals(KeyConfig.instance.getKeyFromName("gamemode", "g"))) {
                if (world.mainPlayer.getGameMode() == GameMode.SURVIVAL) {
                    world.mainPlayer.setGameMode(GameMode.CREATIVE);
                } else {
                    world.mainPlayer.setGameMode(GameMode.SURVIVAL);
                }
            }
            if (key.equals(KeyConfig.instance.getKeyFromName("jump", "w"))) {
                if (world.mainPlayer.getGameMode().canFly() && !world.mainPlayer.isFlying()) {
                    if (playerFlyTick == 0) {
                        playerFlyTick = 7;
                    } else {
                        playerFlyTick = 0;
                        world.mainPlayer.setFlying(true);
                    }
                } else if (world.mainPlayer.isFlying()) {
                    if (playerFlyTick == 0) {
                        playerFlyTick = 7;
                    } else {
                        playerFlyTick = 0;
                        world.mainPlayer.setFlying(false);
                    }
                }
            }
        }
    }
    private int playerFlyTick = 0;

    public void tick() {
        if (playerFlyTick > 0) {
            playerFlyTick--;
        }
    }

    public void keyHeld(String key, long ms) {
        World world = GameBase.instance.getWorld();
        if (world != null && world.mainPlayer != null) {
            if (key.equals(KeyConfig.instance.getKeyFromName("jump", "w"))) {
                if (world.mainPlayer.isFlying()) {
                    world.mainPlayer.motionY += GameBase.blockSize / 2;
                } else {
                    world.mainPlayer.jump();
                }
            }
            if (key.equals(KeyConfig.instance.getKeyFromName("left", "a"))) {
                world.mainPlayer.motionX -= GameBase.blockSize / 2;
            }
            if (key.equals(KeyConfig.instance.getKeyFromName("right", "d"))) {
                world.mainPlayer.motionX += GameBase.blockSize / 2;
            }
            if (key.equals(KeyConfig.instance.getKeyFromName("down", "s"))) {
                if (world.mainPlayer.isFlying()) {
                    world.mainPlayer.motionY -= GameBase.blockSize / 2;
                }
            }
        }
    }

    public void keyUp(String key, long ms) {
    }
}
