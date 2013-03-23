package Game.interaction;

import java.util.ArrayList;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author maximumtech
 */
public class KeyboardHandler {

    public static KeyboardHandler instance = new KeyboardHandler();
    private boolean[] states = new boolean[256];
    private long[] millis = new long[states.length];
    private ArrayList<IKeyboardHandler> handlers = new ArrayList<>();

    public void registerHandler(IKeyboardHandler handler) {
        handlers.add(handler);
    }

    public KeyboardHandler() {
        for (int i = 0; i < states.length; i++) {
            states[i] = false;
            millis[i] = 0L;
        }
    }

    public void onUpdate() {
        if (Keyboard.isCreated()) {
            for (int i = 0; i < states.length; i++) {
                boolean wasDown = states[i];
                boolean isDown = Keyboard.isKeyDown(i);
                if (!wasDown && isDown) {
                    keyDown(Keyboard.getKeyName(i).toLowerCase());
                    states[i] = true;
                    millis[i] = System.currentTimeMillis();
                } else if (wasDown && !isDown) {
                    keyUp(Keyboard.getKeyName(i).toLowerCase(), System.currentTimeMillis() - millis[i]);
                    states[i] = false;
                } else if (isDown) {
                    keyHeld(Keyboard.getKeyName(i).toLowerCase(), System.currentTimeMillis() - millis[i]);
                }
            }
        }
    }

    public void keyDown(String key) {
        for (IKeyboardHandler handler : handlers) {
            handler.keyDown(key);
        }
    }

    public void keyHeld(String key, long ms) {
        for (IKeyboardHandler handler : handlers) {
            handler.keyHeld(key, ms);
        }
    }

    public void keyUp(String key, long ms) {
        for (IKeyboardHandler handler : handlers) {
            handler.keyUp(key, ms);
        }
    }
}
