package Game.render.gui;

import Game.interaction.IMouseHandler;
import org.newdawn.slick.Image;
import Game.interaction.MouseHandler;
import Game.render.ImageHandler;
import org.lwjgl.input.Mouse;

/**
 *
 * @author maximumtech
 */
public class CompButton implements IMouseHandler, ScreenComponent {

    private static Image def = ImageHandler.getImage("buttondef");
    private static Image over = ImageHandler.getImage("buttonover");
    private static Image held = ImageHandler.getImage("buttonover");
    Screen parentScreen;
    public String text;
    public int x;
    public int y;
    public int width;
    public int height;

    public CompButton(Screen scr, int x, int y, int width, int height, String text) {
        parentScreen = scr;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        MouseHandler.instance.registerHandler(this);
    }

    public void render() {
        int x = Mouse.getX();
        int y = Mouse.getY();
        if (x > this.x && x < this.x + this.width && y > this.y && y < this.y + this.height) {
            if (Mouse.isButtonDown(0)) {
                ImageHandler.drawImage2D(held, this.x, this.y, 1, width, height);
            } else {
                ImageHandler.drawImage2D(over, this.x, this.y, 1, width, height);
            }
        } else {
            ImageHandler.drawImage2D(def, this.x, this.y, 1, width, height);
        }
    }

    public void onUpdate() {
    }

    public void clickLeftDown(int x, int y) {
        parentScreen.action(this, 0);
    }

    public void clickLeftHeld(int x, int y, long msDown) {
    }

    public void clickLeftUp(int x, int y, long msDown) {
    }

    public void clickRightDown(int x, int y) {
    }

    public void clickRightHeld(int x, int y, long msDown) {
    }

    public void clickRightUp(int x, int y, long msDown) {
    }

    public void scrollDown(int x, int y) {
    }

    public void scrollUp(int x, int y) {
    }
}
