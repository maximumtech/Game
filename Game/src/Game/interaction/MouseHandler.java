package Game.interaction;

import org.lwjgl.input.Mouse;
import java.util.ArrayList;

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
    private ArrayList<IMouseHandler> handlers = new ArrayList<>();

    public void registerHandler(IMouseHandler handler) {
        handlers.add(handler);
    }

    public void onUpdate() {
        try {
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
        } catch (IllegalStateException e) {
        }
    }

    public void clickLeftDown(int x, int y) {
        for (IMouseHandler handler : handlers) {
            handler.clickLeftDown(x, y);
        }
    }

    public void clickLeftHeld(int x, int y, long msDown) {
        for (IMouseHandler handler : handlers) {
            handler.clickLeftHeld(x, y, msDown);
        }
    }

    public void clickLeftUp(int x, int y, long msDown) {
        for (IMouseHandler handler : handlers) {
            handler.clickLeftUp(x, y, msDown);
        }
    }

    public void clickRightDown(int x, int y) {
        for (IMouseHandler handler : handlers) {
            handler.clickRightDown(x, y);
        }
    }

    public void clickRightHeld(int x, int y, long msDown) {
        for (IMouseHandler handler : handlers) {
            handler.clickRightHeld(x, y, msDown);
        }
    }

    public void clickRightUp(int x, int y, long msDown) {
        for (IMouseHandler handler : handlers) {
            handler.clickRightUp(x, y, msDown);
        }
    }
}
