package Game.misc;

import Game.base.BacktileBase;
import Game.base.BlockBase;
import Game.base.GameBase;
import Game.base.IBreakable;
import Game.base.ItemStack;
import Game.base.World;
import Game.render.ImageHandler;
import Game.render.RenderStack;

/**
 *
 * @author maximumtech
 */
public class BlockBreakingHandler {

    World world;
    public static BlockBreakingHandler instance;
    private RenderStack breaking = (RenderStack) ImageHandler.getRenderStack("blockBreakAnimation");

    public BlockBreakingHandler(World world) {
        this.world = world;
        instance = this;
    }
    private int currentBlockX;
    private int currentBlockY;
    public float progress = 0F;
    public boolean isBreaking = false;
    private IBreakable iBreaking = null;
    private ItemStack item;

    public void beginBreaking(int x, int y, ItemStack item) {
        progress = 0F;
        isBreaking = true;
        this.item = item;
        IBreakable block = world.getTopBreakablePixel(x, y);
        iBreaking = block;
        int bx = world.getCoordinateFromPixel(x);
        int by = world.getCoordinateFromPixel(y);
        currentBlockX = bx;
        currentBlockY = by;
        if (block != null) {
            block.onStartBreaking(world, bx, by, item);
        }
    }

    public void resetBreaking() {
        currentBlockX = -1;
        currentBlockY = -1;
        progress = 0F;
        isBreaking = false;
        item = null;
        iBreaking = null;
    }

    public void onContinuedBreaking(int x, int y, ItemStack item) {
        IBreakable block = world.getTopBreakablePixel(x, y);
        int bx = world.getCoordinateFromPixel(x);
        int by = world.getCoordinateFromPixel(y);
        if (currentBlockX == bx && currentBlockY == by && (this.item != null ? this.item.matches(item) : true) && block.equals(iBreaking)) {
            if (progress >= 1F) {
                onComplete(x, y, item);
                return;
            }
            if (block != null) {
                progress += ((block.getHardness(world, bx, by) / 20) * GameBase.instance.getWorld().mainPlayer.getGameMode().getBlockBreakingModifier() * (item != null ? item.getItem().getHardnessModifier(world, bx, by, block) : 1F));
            }
        } else {
            isBreaking = false;
            progress = 0F;
            currentBlockX = -1;
            currentBlockY = -1;
            iBreaking = null;
            this.item = null;
        }
    }

    public void onComplete(int x, int y, ItemStack item) {
        IBreakable block = world.getTopBreakablePixel(x, y);
        int bx = world.getCoordinateFromPixel(x);
        int by = world.getCoordinateFromPixel(y);
        if (currentBlockX == bx && currentBlockY == by && (this.item != null ? this.item.matches(item) : true) && block.equals(iBreaking)) {
            if (block != null) {
                block.onBreak(world, bx, by, item);
            }
            isBreaking = false;
            iBreaking = null;
            progress = 0F;
            currentBlockX = -1;
            currentBlockY = -1;
            this.item = null;
        } else {
            iBreaking = null;
            isBreaking = false;
            progress = 0F;
            currentBlockX = -1;
            currentBlockY = -1;
            this.item = null;
        }
    }

    public void renderBreaking() {
        if (isBreaking && currentBlockX > -1 && currentBlockY > -1) {
            int x = world.getPixelFromCoordinate(currentBlockX);
            int y = world.getPixelFromCoordinate(currentBlockY);
            if (progress < 0.1F) {
                ImageHandler.drawImage2D(breaking.getImage(0), x, y, 1, GameBase.blockSize, GameBase.blockSize, 0, 0, 0, 256);
            } else if (progress < 0.2F) {
                ImageHandler.drawImage2D(breaking.getImage(1), x, y, 1, GameBase.blockSize, GameBase.blockSize, 0, 0, 0, 256);
            } else if (progress < 0.3F) {
                ImageHandler.drawImage2D(breaking.getImage(2), x, y, 1, GameBase.blockSize, GameBase.blockSize, 0, 0, 0, 256);
            } else if (progress < 0.4F) {
                ImageHandler.drawImage2D(breaking.getImage(3), x, y, 1, GameBase.blockSize, GameBase.blockSize, 0, 0, 0, 256);
            } else if (progress < 0.5F) {
                ImageHandler.drawImage2D(breaking.getImage(4), x, y, 1, GameBase.blockSize, GameBase.blockSize, 0, 0, 0, 256);
            } else if (progress < 0.6F) {
                ImageHandler.drawImage2D(breaking.getImage(5), x, y, 1, GameBase.blockSize, GameBase.blockSize, 0, 0, 0, 256);
            } else if (progress < 0.7F) {
                ImageHandler.drawImage2D(breaking.getImage(6), x, y, 1, GameBase.blockSize, GameBase.blockSize, 0, 0, 0, 256);
            } else if (progress < 0.8F) {
                ImageHandler.drawImage2D(breaking.getImage(7), x, y, 1, GameBase.blockSize, GameBase.blockSize, 0, 0, 0, 256);
            } else if (progress < 0.9F) {
                ImageHandler.drawImage2D(breaking.getImage(8), x, y, 1, GameBase.blockSize, GameBase.blockSize, 0, 0, 0, 256);
            } else if (progress < 1F) {
                ImageHandler.drawImage2D(breaking.getImage(9), x, y, 1, GameBase.blockSize, GameBase.blockSize, 0, 0, 0, 256);
            }
        }
    }
}
