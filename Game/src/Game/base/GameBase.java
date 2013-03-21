package Game.base;

import Game.interaction.KeyboardHandler;
import Game.interaction.MouseHandler;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import java.util.Random;
import Game.render.Screen;
import Game.render.ScreenWorld;

/**
 *
 * @author maximumtech
 */
public class GameBase {

    public static GameBase instance;
    public Random rand;
    public Screen renderScreen;
    public static int blockSize = 16;

    public static void main(String[] args) {
        new GameBase(args);
    }

    public GameBase(String[] args) {
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
        GL11.glOrtho(0, 800, 0, 600, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        System.out.println("OpenGL Started, Tick Handling Initializing");
        new TickHandler();
        System.out.println("Tick Handler Initialized, Starting Render Loop");
        World world = new World(20, 20, 10, 16);
        renderScreen = new ScreenWorld(world);
        while (!closeRequested()) {
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

    private void render() {
        if (renderScreen != null) {
            renderScreen.render();
        }
    }

    protected void runTick() {
        MouseHandler.instance.onUpdate();
        KeyboardHandler.instance.onUpdate();
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
