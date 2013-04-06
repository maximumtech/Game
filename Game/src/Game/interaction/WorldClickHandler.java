package Game.interaction;

import Game.base.BlockBase;
import Game.base.GameBase;
import Game.base.World;
import Game.entity.Entity;
import Game.misc.BlockBreakingHandler;
import Game.misc.CollisonHelper;
import Game.misc.MathHelper;

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
        int[] xy = world.getRelativeCoordinateFromScreen(x, y);
        int[] xy2 = world.getRelativePixelFromScreen(x, y);
        BlockBase block = world.getBlock(xy[0], xy[1]);
        boolean canBreak = MathHelper.getDistance(GameBase.instance.getWorld().mainPlayer.getMidX(), GameBase.instance.getWorld().mainPlayer.getMidY(), xy2[0], xy2[1]) <= (double) GameBase.instance.getWorld().mainPlayer.getGameMode().getReachDistance();
        if (block != null && block.isBreakable(world, xy[0], xy[1]) && BlockBreakingHandler.instance.isBreaking && canBreak) {
            BlockBreakingHandler.instance.onContinuedBreaking(xy[0], xy[1], null);
        } else if (block != null && block.isBreakable(world, xy[0], xy[1]) && !BlockBreakingHandler.instance.isBreaking && canBreak) {
            block.onStartBreaking(world, xy[0], xy[1], null);
            BlockBreakingHandler.instance.beginBreaking(xy[0], xy[1], null);
        } else if (BlockBreakingHandler.instance.isBreaking || !canBreak) {
            BlockBreakingHandler.instance.resetBreaking();
        }

    }

    public void clickLeftUp(int x, int y, long msDown) {
        BlockBreakingHandler.instance.resetBreaking();
    }

    public void clickRightDown(int x, int y) {
    }

    public void clickRightHeld(int x, int y, long msDown) {
        int[] xy = world.getRelativeCoordinateFromScreen(x, y);
        int[] xy2 = world.getRelativePixelFromScreen(x, y);
        if (world.mainPlayer.inventory.getSelectedItem() != null && world.mainPlayer.inventory.getSelectedItem().getItem() instanceof BlockBase) {
            BlockBase replacer = (BlockBase) world.mainPlayer.inventory.getSelectedItem().getItem();
            BlockBase block = world.getBlock(xy[0], xy[1]);
            Entity nearestEntity = world.getNearestEntity(x, y, 80D, null);
            boolean canPlace = MathHelper.getDistance(GameBase.instance.getWorld().mainPlayer.getMidX(), GameBase.instance.getWorld().mainPlayer.getMidY(), xy2[0], xy2[1]) <= (double) GameBase.instance.getWorld().mainPlayer.getGameMode().getReachDistance();
            if ((block == null || block.canBeReplaced(world, xy[0], xy[1], replacer))) {
                if (canPlace) {
                    boolean canCollide = replacer == null ? false : replacer.canCollide(world, xy[0], xy[1]);
                    if (nearestEntity != null && canCollide) {
                        return;
                    }
                    if (block != null) {
                        block.onBlockBreak(world, xy[0], xy[1], world.mainPlayer.inventory.getSelectedItem());
                    }
                    if (world.setBlock(xy[0], xy[1], replacer)) {
                        world.mainPlayer.inventory.removeItemStack(world.mainPlayer.inventory.getSelectedItem(), 1);
                    }
                }
            }
        }
    }

    public void clickRightUp(int x, int y, long msDown) {
    }

    public void scrollDown(int x, int y) {
        world.mainPlayer.inventory.incSelItem();
    }

    public void scrollUp(int x, int y) {
        world.mainPlayer.inventory.decSelItem();
    }
}
