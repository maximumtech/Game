package Game.base;

import Game.interaction.WorldClickHandler;
import Game.interaction.KeyboardHandler;
import Game.interaction.MouseHandler;
import Game.interaction.WorldKeyHandler;
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

    private static final String outPrefix = "Game : ";
    public static GameBase instance;
    public static Screen renderScreen;
    public static int blockSize = 16;
    public static int reachDistance = 8;
    private static FontRenderer[] fontRenderer;

    public static void print(String str) {
        System.out.println(outPrefix + str);
    }

    public static FontRenderer getFontRenderer(int size) {
        if (fontRenderer[size] == null) {
            fontRenderer[size] = new FontRenderer(size);
        }
        return fontRenderer[size];
    }

    public static void loadFontSizes(int min, int max) {
        for (int i = min; i < max; i++) {
            getFontRenderer(i);
        }
    }

    public static FontRenderer getFontRendererForWidth(int beginSize, int maxSize, String string, int maxWidth) {
        FontRenderer font = getFontRenderer(beginSize);
        int size = beginSize + 1;
        while (font.getStringWidth(string) < maxWidth && size < maxSize) {
            font = getFontRenderer(size);
            size++;
        }
        return font;
    }

    public static void main(String[] args) {
        new GameBase(args);
    }

    public GameBase(String[] args) {
        print("Game Copyright (C) 2013 Maxwell Bruce");
        instance = this;
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.setTitle("Game");
            Display.setResizable(true);
            Display.create();
            print("Display Created");
        } catch (LWJGLException e) {
            print("Failed to create display");
            e.printStackTrace();
        }
        print("Starting OpenGL");
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
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
        GL11.glCullFace(GL11.GL_BACK);
        fontRenderer = new FontRenderer[100];
        loadFontSizes(6, 12);
        print("OpenGL Started, Tick Handling Initializing");
        new TickHandler();
        print("Tick Handler Initialized, Starting Render Loop");
        World world = new World(600, 1000, 500, 16);
        renderScreen = new ScreenWorld(world);
        new WorldKeyHandler();
        new WorldClickHandler(world);
        new BlockBreakingHandler(world);
        while (!closeRequested()) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            if (Display.wasResized()) {
                GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
            }
            fpsct++;
            render();
            this.calculateFPS();
            Display.update();
            Display.sync(30);
        }
        closeRequested = true;
        print("Closing");
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
            renderScreen.render();
            GL11.glPopMatrix();
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
    private long lastfps = 0L;
    private int fpsct = 0;
    private int fps = 0;

    /**
     * Calculate the current FPS.
     */
    public void calculateFPS() {
        long time = System.currentTimeMillis();
        if (time >= lastfps - 1000L) {
            lastfps = time;
            fps = fpsct;
            fpsct = 0;
        }
    }

    /**
     * Returns the FPS
     */
    public int getFPS() {
        return fps;
    }
}
