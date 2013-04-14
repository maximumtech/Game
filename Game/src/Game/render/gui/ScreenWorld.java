package Game.render.gui;

import Game.base.World;
import Game.base.BlockBase;
import Game.base.BacktileBase;
import Game.base.GameBase;
import Game.entity.Entity;
import Game.inventory.Slot;
import Game.misc.BlockBreakingHandler;
import Game.render.FontRenderer;
import org.lwjgl.input.Mouse;
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
        int wid = GameBase.screenWidth / 2;
        int hei = GameBase.screenHeight / 2;
        GL11.glTranslatef(-world.mainPlayer.getX() + wid - (world.mainPlayer.getWidth() / 2), -world.mainPlayer.getY() + hei - (world.mainPlayer.getHeight() / 2), 0);
    }
    public boolean debug = true;

    private void renderSky() {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glTranslatef(0, 0, -8);
        GL11.glColor3f(0F, 0.6F, 0.9F);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2i(0, 0);
        GL11.glVertex2i(0, GameBase.screenHeight);
        GL11.glVertex2i(GameBase.screenWidth, GameBase.screenHeight);
        GL11.glVertex2i(GameBase.screenWidth, 0);
        GL11.glEnd();
        GL11.glColor3f(1F, 1F, 1F);
        GL11.glTranslatef(0, 0, 8);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }

    public void render() {
        super.render();
        renderSky();
        GL11.glPushMatrix();
        translateToPlayer();
        if (world != null && world.mainPlayer != null) {
            int wid = world.getRenderWidth() / 2;
            int hei = world.getRenderHeight() / 2;
            for (int x = Math.max(0, world.mainPlayer.getBlockX() - wid); x < Math.min(world.getWidth(), world.mainPlayer.getBlockX() + wid); x++) {
                for (int y = Math.max(0, world.mainPlayer.getBlockY() - hei); y < Math.min(world.getHeight(), world.mainPlayer.getBlockY() + hei); y++) {
                    BlockBase block = world.getBlock(x, y);
                    BacktileBase tile = world.getBacktile(x, y);
                    if (tile != null) {
                        tile.renderWorld(world, x * GameBase.blockSize, y * GameBase.blockSize);
                    }
                    if (block != null) {
                        block.renderWorld(world, x * GameBase.blockSize, y * GameBase.blockSize);
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
        }
        GL11.glPopMatrix();
        // guis
        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0, 9);
        world.mainPlayer.inventory.render();
        int wid = GameBase.screenWidth / 2;
        int hei = GameBase.screenHeight / 2;
        world.mainPlayer.renderer.render(wid - (world.mainPlayer.getWidth() / 2), hei - (world.mainPlayer.getHeight() / 2), 3);
        FontRenderer pt12 = GameBase.getFontRenderer(12);
        pt12.drawString(world.mainPlayer.username, (GameBase.screenWidth / 2) - (pt12.getStringWidth(world.mainPlayer.username) / 2), (GameBase.screenHeight / 2) + 48, 4);
        if (Slot.itemInHand != null) {
            Slot.itemInHand.renderGUI(Mouse.getX() - 16, Mouse.getY() - 16, 0, 32, 32);
        }
        if (debug) {
            singlePlayerDebug.updateDebug();
            for (int i = 0; i < singlePlayerDebug.getOutput().size(); i++) {
                String str = singlePlayerDebug.getOutput().get(i);
                pt12.drawString(str, 2, Display.getHeight() - 2 - i * 16, 0);
            }
        }
        GL11.glPopMatrix();
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
