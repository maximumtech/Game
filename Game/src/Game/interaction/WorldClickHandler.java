package Game.interaction;

import Game.base.BlockBase;
import Game.base.GameBase;
import Game.base.ItemStack;
import Game.base.BlockEntityBase;
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

    public void clickRightHeld(int x, int y, long msDown) {
        int[] xy = world.getRelativeCoordinateFromScreen(x, y);
        int[] xy2 = world.getRelativePixelFromScreen(x, y);
        ItemStack selItem = world.mainPlayer.inventory.getSelectedItem();
        if (selItem != null && selItem.getItem() instanceof BlockBase) {
            BlockBase replacer = (BlockBase) selItem.getItem();
            BlockBase block = world.getBlock(xy[0], xy[1]);
            //Entity nearestEntity = world.getNearestEntity(x, y, 80D, null);
            boolean canPlace = MathHelper.getDistance(GameBase.instance.getWorld().mainPlayer.getMidX(), GameBase.instance.getWorld().mainPlayer.getMidY(), xy2[0], xy2[1]) <= (double) GameBase.instance.getWorld().mainPlayer.getGameMode().getReachDistance();
            short tmeta = replacer.getMetaForPlacing(world, x, y, selItem);
            BlockEntityBase tent = replacer.getBlockEntityForPlacing(world, x, y, selItem);
            if (block == null || (block.canBeReplaced(world, xy[0], xy[1], replacer) && !block.isSameBlockForPlacing(world, x, y, replacer.getBlockID(), tmeta, tent))) {
                if (canPlace) {
                    //boolean canCollide = replacer == null ? false : replacer.canCollide(world, xy[0], xy[1]);
                    //if (nearestEntity != null && canCollide) {
                    //    return;
                    //}
                    if (block != null) {
                        block.onBreak(world, xy[0], xy[1], selItem);
                    }
                    if (world.setBlock(xy[0], xy[1], replacer)) {
                        world.mainPlayer.inventory.removeItemStack(selItem, 1);
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
