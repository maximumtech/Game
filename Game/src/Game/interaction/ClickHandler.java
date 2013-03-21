package Game.interaction;

import Game.base.BlockBase;
import Game.base.GameBase;
import Game.base.World;

/**
 *
 * @author maximumtech
 */
public class ClickHandler implements IMouseHandler {

    public ClickHandler() {
        MouseHandler.instance.registerHandler(this);
    }

    public void clickLeftDown(int x, int y) {
        World world = GameBase.instance.getWorld();
        if (world != null) {
            int xx = world.getCoordinateFromPixel(x);
            int yy = world.getCoordinateFromPixel(y);
            world.setBlock(xx, yy+12, (short)0);
        }
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
