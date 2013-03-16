package Game.interaction;

import org.lwjgl.input.Mouse;

/**
 *
 * @author maximumtech
 */
public class MouseHandler {

    public static MouseHandler instance = new MouseHandler();
    boolean wasLeftDown = false;
    boolean wasRightDown = false;
    long leftms = 0L;
    long rightms = 0L;

    public void onUpdate() {
        if (Mouse.isButtonDown(0)) {
            if (!wasLeftDown) {
                wasLeftDown = true;
                clickLeftDown(Mouse.getX(), Mouse.getY());
                leftms = System.currentTimeMillis();
            }
            clickLeftHeld(Mouse.getX(), Mouse.getY(), System.currentTimeMillis() - leftms);
        } else {
            if (wasLeftDown) {
                wasLeftDown = false;
                clickLeftUp(Mouse.getX(), Mouse.getY(), leftms);
            }
        }
        if (Mouse.isButtonDown(1)) {
            if (!wasRightDown) {
                wasRightDown = true;
                clickRightDown(Mouse.getX(), Mouse.getY());
                rightms = System.currentTimeMillis();
            }
            clickRightHeld(Mouse.getX(), Mouse.getY(), System.currentTimeMillis() - rightms);
        } else {
            if (wasRightDown) {
                wasRightDown = false;
                clickRightUp(Mouse.getX(), Mouse.getY(), rightms);
            }
        }
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
