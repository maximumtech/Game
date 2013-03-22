package Game.interaction;

import Game.base.BlockBase;
import Game.base.GameBase;
import Game.base.World;

/**
 *
 * @author maximumtech
 */
public class WorldClickHandler implements IMouseHandler {
    
    World world;

    public WorldClickHandler(World world) {
        this.world = world;
        MouseHandler.instance.registerHandler(this);
    }

    public void clickLeftDown(int x, int y) {
        
    }

    public void clickLeftHeld(int x, int y, long msDown) {
    }

    public void clickLeftUp(int x, int y, long msDown) {
    }

    public void clickRightDown(int x, int y) {
    }

    public void clickRightHeld(int x, int y, long msDown) {
    }

    public void clickRightUp(int x, int y, long msDown) {
    }
}
