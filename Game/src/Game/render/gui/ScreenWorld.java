package Game.render.gui;

import Game.base.World;
import Game.base.BlockBase;
import Game.base.BackTileBase;
import Game.base.GameBase;
import Game.entity.Entity;
import Game.misc.BlockBreakingHandler;
import Game.render.FontRenderer;
import java.util.ArrayList;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author maximumtech
 */
public class ScreenWorld extends Screen {

    public World world;

    public ScreenWorld(World world) {
        this.world = world;
    }

    public void translateToPlayer() {
        int wid = Display.getWidth() / 2;
        int hei = Display.getHeight() / 2;
        GL11.glTranslatef(-world.mainPlayer.getX() + wid - (world.mainPlayer.sizeX / 2), -world.mainPlayer.getY() + hei - (world.mainPlayer.sizeY / 2), 0);
    }
    public boolean debug = true;

    public void render() {
        super.render();
        world.mainPlayer.inventory.render(true);
        int wid = Display.getWidth() / 2;
        int hei = Display.getHeight() / 2;
        world.mainPlayer.renderer.render(wid - (world.mainPlayer.sizeX / 2), hei - (world.mainPlayer.sizeY / 2));
        FontRenderer pt12 = GameBase.getFontRenderer(12);
        if (debug) {
            this.singlePlayerDebug.updateDebug();
            for (int i = 0; i < this.singlePlayerDebug.getOutput().size(); i++) {
                String str = this.singlePlayerDebug.getOutput().get(i);
                pt12.drawString(str, 2, Display.getHeight() - 2 - i * 16, 4);
            }
        }
        translateToPlayer();
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
            BlockBreakingHandler.instance.renderBreaking();
            synchronized (world.entityList) {
                for (Entity ent : world.entityList) {
                    synchronized (ent) {
                        ent.render();
                    }
                }
            }
            GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
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
