package Game.base;

import Game.interaction.WorldClickHandler;
import Game.interaction.KeyboardHandler;
import Game.interaction.MouseHandler;
import Game.interaction.MovementHandler;
import Game.misc.BlockBreakingHandler;
import Game.render.FontRenderer;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import Game.render.gui.Screen;
import Game.render.gui.ScreenWorld;

/**
 *
 * @author maximumtech
 */
public class GameBase {

    public static GameBase instance;
    public static Screen renderScreen;
    public static int blockSize = 16;
    public static int reachDistance = 8;
    public static FontRenderer fontRenderer;
    
    public static void main(String[] args) {
        new GameBase(args);
    }

    public GameBase(String[] args) {
        System.out.println("Game Copyright (C) 2013 Maxwell Bruce");
        instance = this;
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.setTitle("Game");
            Display.setResizable(true);
            Display.create();
            System.out.println("Display Created!");
        } catch (LWJGLException e) {
            System.out.println("Failed to create display.");
            e.printStackTrace();
        }
        System.out.println("Starting OpenGL");
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 0, 600, -10, 10);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        //GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        GL11.glDepthMask(true);
        System.out.println("OpenGL Started, Tick Handling Initializing");
        new TickHandler();
        System.out.println("Tick Handler Initialized, Starting Render Loop");
        World world = new World(600, 100, 30, 16);
        fontRenderer = new FontRenderer(12);
        renderScreen = new ScreenWorld(world);
        new MovementHandler();
        new WorldClickHandler(world);
        new BlockBreakingHandler(world);
        ItemStack.fontRenderer = new FontRenderer(4);
        while (!closeRequested()) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            if (Display.wasResized()) {
                GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
            }
            render();
            Display.update();
            Display.sync(30);
        }
        closeRequested = true;
        System.out.println("Closing");
        Display.destroy();
    }

    public World getWorld() {
        if (renderScreen != null && renderScreen instanceof ScreenWorld) {
            return ((ScreenWorld) renderScreen).world;
        }
        return null;
    }

    private void render() {
        if (renderScreen != null) {
            GL11.glPushMatrix();
            if (renderScreen instanceof ScreenWorld) {
                World world = ((ScreenWorld) renderScreen).world;
                if (world != null && world.mainPlayer != null) {
                    int wid = Display.getWidth() / 2;
                    int hei = Display.getHeight() / 2;
                    world.mainPlayer.renderer.render(wid - (world.mainPlayer.sizeX / 2), hei - (world.mainPlayer.sizeY / 2));
                    translateToPlayer();
                }
            }
            renderScreen.render();
            GL11.glPopMatrix();
        }
    }

    public void translateToPlayer() {
        if (renderScreen instanceof ScreenWorld) {
            World world = ((ScreenWorld) renderScreen).world;
            int wid = Display.getWidth() / 2;
            int hei = Display.getHeight() / 2;
            GL11.glTranslatef(-world.mainPlayer.getX() + wid - (world.mainPlayer.sizeX / 2), -world.mainPlayer.getY() + hei - (world.mainPlayer.sizeY / 2), 0);
        }
    }

    protected void runTick() {
        MouseHandler.instance.onUpdate();
        KeyboardHandler.instance.onUpdate();
        Game.interaction.TickHandler.instance.tick();
        if (renderScreen != null) {
            renderScreen.onUpdate();
        }
    }
    private static boolean closing = false;
    public static boolean closeRequested = false;

    public static void setClosing() {
        closing = true;
    }

    private static boolean closeRequested() {
        try {
            boolean temp = Display.isCloseRequested() || closing;
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return closing;
    }
}
