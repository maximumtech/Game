package Game.render;

import Game.interaction.IMouseHandler;
import org.newdawn.slick.Image;

/**
 *
 * @author maximumtech
 */
public class CompButton implements IMouseHandler, ScreenComponent {
    
    Image def = ImageHandler.getImage("buttondef");
    Image over = ImageHandler.getImage("buttonover");
    Image hold = ImageHandler.getImage("buttonover");
    Screen parentScreen;
    public String text;
    public int x;
    public int y;
    public int width;
    public int height;
    
    public CompButton(Screen scr, int x, int y, int width, int height) {
        parentScreen = scr;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render() {
    }

    public void onUpdate() {
    }

    public void clickLeftDown(int x, int y) {
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
}
