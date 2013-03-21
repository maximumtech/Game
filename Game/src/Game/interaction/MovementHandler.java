package Game.interaction;

import Game.base.GameBase;
import Game.base.World;
import Game.render.ScreenWorld;

/**
 *
 * @author maximumtech
 */
public class MovementHandler implements IKeyboardHandler {
    
    public MovementHandler() {
        KeyboardHandler.instance.registerHandler(this);
    }

    public void keyDown(String key) {
    }

    public void keyHeld(String key, long ms) {
        if(GameBase.renderScreen != null && GameBase.renderScreen instanceof ScreenWorld) {
            World world = ((ScreenWorld)GameBase.renderScreen).world;
            if(world!=null && world.mainPlayer != null) {
                if(key.equals("w")) {
                    world.mainPlayer.jump();
                }
                if(key.equals("a")) {
                    world.mainPlayer.motionX = -GameBase.blockSize / 2;
                }
                if(key.equals("d")) {
                    world.mainPlayer.motionX = GameBase.blockSize / 2;
                }
            }
        }
    }

    public void keyUp(String key, long ms) {
    }
}
