package Game.render;

import Game.base.World;
import Game.base.BlockBase;
import Game.base.BackTileBase;
import Game.base.GameBase;

/**
 *
 * @author maximumtech
 */
public class ScreenWorld extends Screen {

    public World world;

    public ScreenWorld(World world) {
        this.world = world;
    }

    public void render() {
        super.render();
        if (world != null && world.mainPlayer != null) {
            for (int x = Math.max(0, world.mainPlayer.getBlockX() - world.getRenderWidth()); x < Math.min(world.getWidth(), world.mainPlayer.getBlockX() + world.getRenderWidth()); x++) {
                for (int y = Math.max(0, world.mainPlayer.getBlockY() - world.getRenderHeight()); y < Math.min(world.getHeight(), world.mainPlayer.getBlockY() + world.getRenderHeight()); y++) {
                    BlockBase block = world.getBlock(x, y);
                    BackTileBase tile = world.getBacktile(x, y);
                    if (block != null) {
                        block.renderWorld(world, x * GameBase.blockSize, y * GameBase.blockSize);
                    }
                    if (tile != null) {
                        tile.renderWorld(world, x * GameBase.blockSize, y * GameBase.blockSize);
                    }
                }
            }
        }
    }

    public void onUpdate() {
        super.onUpdate();
        if (world != null) {
            world.onUpdate();
        }
    }

    public void action(ScreenComponent comp, int eventid) {
    }
}
