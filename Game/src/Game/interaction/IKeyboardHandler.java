package Game.interaction;

/**
 *
 * @author maximumtech
 */
public interface IKeyboardHandler {
    public void keyDown(String key);

    public void keyHeld(String key, long ms);

    public void keyUp(String key, long ms);
}
