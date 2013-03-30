package Game.interaction;

import Game.base.GameBase;
import Game.base.World;
import Game.entity.player.GameMode;
import Game.render.gui.ScreenWorld;

/**
 *
 * @author maximumtech
 */
public class WorldKeyHandler implements IKeyboardHandler, ITickHandler {

    public WorldKeyHandler() {
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
            if (key.equals(KeyConfig.instance.getKeyFromName("inventory", "e"))) {
                if (GameBase.renderScreen != null && GameBase.renderScreen instanceof ScreenWorld) {
                    //((ScreenWorld) GameBase.renderScreen).toggleInventory();
                }
            }
            
            //Not everyone uses a mouse all the time, some people play on laptops while on an airplane >.>
            //So that's why I'm adding this. Feel free to bind each one to numbers though, but I feel this 
            //to be sufficient for the time being -- that time being midnight.
            if(key.equals(KeyConfig.instance.getKeyFromName("itemswitchup", "right"))) {
                world.mainPlayer.inventory.incSelItem();
            }
            
            if(key.equals(KeyConfig.instance.getKeyFromName("itemswitchdown", "left"))) {
                world.mainPlayer.inventory.decSelItem();
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
