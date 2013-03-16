package Game.interaction;

/**
 *
 * @author maximumtech
 */
public interface IMouseHandler {
    public void clickLeftDown(int x, int y);

    public void clickLeftHeld(int x, int y, long msDown);

    public void clickLeftUp(int x, int y, long msDown);

    public void clickRightDown(int x, int y);

    public void clickRightHeld(int x, int y, long msDown);

    public void clickRightUp(int x, int y, long msDown);
}
