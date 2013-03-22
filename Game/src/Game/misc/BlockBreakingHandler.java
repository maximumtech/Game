package Game.misc;

import Game.base.BlockBase;
import Game.base.GameBase;
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
    boolean isBreaking = false;

    public void beginBreaking(int x, int y) {
        currentBlockX = x;
        currentBlockY = y;
        progress = 0F;
        isBreaking = true;
    }

    public void onContinuedBreaking(int x, int y) {
        if (currentBlockX == x && currentBlockY == y) {
            if (progress >= 1F) {
                onComplete(x, y);
            }
            BlockBase block = world.getBlock(x, y);
            if (block != null) {
                progress += block.blockHardness;
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
            }
            isBreaking = false;
            progress = 0F;
            currentBlockX = -1;
            currentBlockY = -1;
        }else{
            isBreaking = false;
            progress = 0F;
            currentBlockX = -1;
            currentBlockY = -1;
        }
    }

    public void renderBreaking(int x, int y, float progress) {
        if (progress < 0.1F) {
            ImageHandler.drawImage2D(breaking.getImage(0), x, y, GameBase.blockSize, GameBase.blockSize);
        } else if (progress < 0.2F) {
            ImageHandler.drawImage2D(breaking.getImage(1), x, y, GameBase.blockSize, GameBase.blockSize);
        } else if (progress < 0.3F) {
            ImageHandler.drawImage2D(breaking.getImage(2), x, y, GameBase.blockSize, GameBase.blockSize);
        } else if (progress < 0.4F) {
            ImageHandler.drawImage2D(breaking.getImage(3), x, y, GameBase.blockSize, GameBase.blockSize);
        } else if (progress < 0.5F) {
            ImageHandler.drawImage2D(breaking.getImage(4), x, y, GameBase.blockSize, GameBase.blockSize);
        } else if (progress < 0.6F) {
            ImageHandler.drawImage2D(breaking.getImage(5), x, y, GameBase.blockSize, GameBase.blockSize);
        } else if (progress < 0.7F) {
            ImageHandler.drawImage2D(breaking.getImage(6), x, y, GameBase.blockSize, GameBase.blockSize);
        } else if (progress < 0.8F) {
            ImageHandler.drawImage2D(breaking.getImage(7), x, y, GameBase.blockSize, GameBase.blockSize);
        } else if (progress < 0.9F) {
            ImageHandler.drawImage2D(breaking.getImage(8), x, y, GameBase.blockSize, GameBase.blockSize);
        } else if (progress < 1F) {
            ImageHandler.drawImage2D(breaking.getImage(9), x, y, GameBase.blockSize, GameBase.blockSize);
        }
    }
}
