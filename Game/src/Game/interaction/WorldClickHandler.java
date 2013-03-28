package Game.interaction;

import Game.base.BlockBase;
import Game.base.GameBase;
import Game.base.ItemStack;
import Game.base.World;
import Game.misc.BlockBreakingHandler;
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
    private long lastPlace = 0L;

    public void clickRightHeld(int x, int y, long msDown) {
        if (world.mainPlayer.inventory.getSelectedItem() != null && world.mainPlayer.inventory.getSelectedItem().getItem() instanceof BlockBase) {
            int[] xy = world.getRelativeCoordinateFromScreen(x, y);
            int[] xy2 = world.getRelativePixelFromScreen(x, y);
            BlockBase replacer = (BlockBase) world.mainPlayer.inventory.getSelectedItem().getItem();
            BlockBase block = world.getBlock(xy[0], xy[1]);
            boolean canPlace = MathHelper.getDistance(GameBase.instance.getWorld().mainPlayer.getMidX(), GameBase.instance.getWorld().mainPlayer.getMidY(), xy2[0], xy2[1]) <= (double) GameBase.instance.getWorld().mainPlayer.getGameMode().getReachDistance();
            if (block == null || block.canBeReplaced(world, xy[0], xy[1], replacer) && msDown > lastPlace + 50L) {
                if (canPlace) {
                    if (block != null) {
                        block.onBlockBreak(world, xy[0], xy[1], world.mainPlayer.inventory.getSelectedItem());
                    }
                    if (world.setBlock(xy[0], xy[1], replacer)) {
                        world.mainPlayer.inventory.removeItemStack(world.mainPlayer.inventory.getSelectedItem(), 1);
                        lastPlace = msDown;
                    }
                }
            }
        }
    }

    public void clickRightUp(int x, int y, long msDown) {
        lastPlace = 0L;
    }
}
