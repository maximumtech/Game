package Game.interaction;

import Game.base.BlockBase;
import Game.base.World;
import Game.misc.BlockBreakingHandler;

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
        int[] xy = world.getRelativeCoordinateFromScreen(x, y);
        BlockBase block = world.getBlock(xy[0], xy[1]);
        if (block != null && block.isBreakable(world, xy[0], xy[1]) && !BlockBreakingHandler.instance.isBreaking) {
            block.onStartBreaking(world, xy[0], xy[1]);
            BlockBreakingHandler.instance.beginBreaking(xy[0], xy[1]);
        } else if (BlockBreakingHandler.instance.isBreaking) {
            BlockBreakingHandler.instance.resetBreaking();
        }
    }

    public void clickLeftHeld(int x, int y, long msDown) {
        int[] xy = world.getRelativeCoordinateFromScreen(x, y);
        BlockBase block = world.getBlock(xy[0], xy[1]);
        if (block != null && block.isBreakable(world, xy[0], xy[1]) && BlockBreakingHandler.instance.isBreaking) {
            BlockBreakingHandler.instance.onContinuedBreaking(xy[0], xy[1]);
        } else if (block != null && block.isBreakable(world, xy[0], xy[1]) && !BlockBreakingHandler.instance.isBreaking) {
            block.onStartBreaking(world, xy[0], xy[1]);
            BlockBreakingHandler.instance.beginBreaking(xy[0], xy[1]);
        } else if (BlockBreakingHandler.instance.isBreaking) {
            BlockBreakingHandler.instance.resetBreaking();
        }
    }

    public void clickLeftUp(int x, int y, long msDown) {
        BlockBreakingHandler.instance.resetBreaking();
    }

    public void clickRightDown(int x, int y) {
    }

    public void clickRightHeld(int x, int y, long msDown) {
    }

    public void clickRightUp(int x, int y, long msDown) {
    }
}
