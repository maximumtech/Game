package Game.misc;

import Game.base.BlockBase;
import Game.base.GameBase;
import Game.base.World;
import Game.render.ImageHandler;
import Game.render.RenderStack;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

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

    public void beginBreaking(int x, int y) {
        currentBlockX = x;
        currentBlockY = y;
        progress = 0F;
        isBreaking = true;
    }
    
    public void resetBreaking() {
        currentBlockX = -1;
        currentBlockY = -1;
        progress = 0F;
        isBreaking = false;
    }

    public void onContinuedBreaking(int x, int y) {
        if (currentBlockX == x && currentBlockY == y) {
            if (progress >= 1F) {
                onComplete(x, y);
                return;
            }
            BlockBase block = world.getBlock(x, y);
            if (block != null) {
                progress += block.blockHardness * GameBase.instance.getWorld().mainPlayer.getGameMode().getBlockBreakingModifier();
            }
        } else {
            isBreaking = false;
            progress = 0F;
            currentBlockX = -1;
            currentBlockY = -1;
        }
    }

    public void onComplete(int x, int y) {
        if (currentBlockX == x && currentBlockY == y) {
            BlockBase block = world.getBlock(x, y);
            if (block != null) {
                block.onBlockBreak(world, x, y);
                world.setBlock(x, y, (short)0);
            }
            isBreaking = false;
            progress = 0F;
            currentBlockX = -1;
            currentBlockY = -1;
        } else {
            isBreaking = false;
            progress = 0F;
            currentBlockX = -1;
            currentBlockY = -1;
        }
    }

    public void renderBreaking() {
        if (isBreaking && currentBlockX > -1 && currentBlockY > -1) {
            int x = world.getPixelFromCoordinate(currentBlockX);
            int y = world.getPixelFromCoordinate(currentBlockY);
            if (progress < 0.1F) {
                ImageHandler.drawImage2D(breaking.getImage(0), x, y, 1, GameBase.blockSize, GameBase.blockSize, Color.black);
            } else if (progress < 0.2F) {
                ImageHandler.drawImage2D(breaking.getImage(1), x, y, 1, GameBase.blockSize, GameBase.blockSize, Color.black);
            } else if (progress < 0.3F) {
                ImageHandler.drawImage2D(breaking.getImage(2), x, y, 1, GameBase.blockSize, GameBase.blockSize, Color.black);
            } else if (progress < 0.4F) {
                ImageHandler.drawImage2D(breaking.getImage(3), x, y, 1, GameBase.blockSize, GameBase.blockSize, Color.black);
            } else if (progress < 0.5F) {
                ImageHandler.drawImage2D(breaking.getImage(4), x, y, 1, GameBase.blockSize, GameBase.blockSize, Color.black);
            } else if (progress < 0.6F) {
                ImageHandler.drawImage2D(breaking.getImage(5), x, y, 1, GameBase.blockSize, GameBase.blockSize, Color.black);
            } else if (progress < 0.7F) {
                ImageHandler.drawImage2D(breaking.getImage(6), x, y, 1, GameBase.blockSize, GameBase.blockSize, Color.black);
            } else if (progress < 0.8F) {
                ImageHandler.drawImage2D(breaking.getImage(7), x, y, 1, GameBase.blockSize, GameBase.blockSize, Color.black);
            } else if (progress < 0.9F) {
                ImageHandler.drawImage2D(breaking.getImage(8), x, y, 1, GameBase.blockSize, GameBase.blockSize, Color.black);
            } else if (progress < 1F) {
                ImageHandler.drawImage2D(breaking.getImage(9), x, y, 1, GameBase.blockSize, GameBase.blockSize, Color.black);
            }
        }
    }
}
