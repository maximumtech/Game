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
    private Timer timer;

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
        timer = new Timer(20F);
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
            timer.update();
            render();
            calculateFPS();
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
        for (int i = 0; i < timer.ticksElapsed; i++) {
            runTick();
        }
        if (renderScreen != null) {
            GL11.glPushMatrix();
            renderScreen.render();
            GL11.glPopMatrix();
        }
    }

    protected void runTick() {
        calculateTPS();
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
    private long timeDeltaFPS;
    private long timeLastLoopFPS;
    private long timeLastFPS;
    private int fps;
    private int fpsCurrent;

    /**
     * Calculate the current FPS.
     */
    public void calculateFPS() {

        this.timeDeltaFPS = Time.MILLISECONDS.getCurrent() - this.timeLastLoopFPS;
        this.timeLastLoopFPS = Time.MILLISECONDS.getCurrent();
        this.timeLastFPS += this.timeDeltaFPS;
        this.fps++;

        if (this.timeLastFPS >= 1000) {
            this.fpsCurrent = this.fps;
            this.timeLastFPS = 0;
            this.fps = 0;
        }
    }
    private long timeDeltaTPS;
    private long timeLastLoopTPS;
    private long timeLastTPS;
    private int tps;
    private int tpsCurrent;

    public void calculateTPS() {
        this.timeDeltaTPS = Time.MILLISECONDS.getCurrent() - this.timeLastLoopTPS;
        this.timeLastLoopTPS = Time.MILLISECONDS.getCurrent();
        this.timeLastTPS += this.timeDeltaTPS;
        this.tps++;

        if (this.timeLastTPS >= 1000) {
            this.tpsCurrent = this.tps;
            this.timeLastTPS = 0;
            this.tps = 0;
        }
    }

    public int getFPS() {
        return fpsCurrent;
    }

    public int getTPS() {
        return tpsCurrent;
    }
}
