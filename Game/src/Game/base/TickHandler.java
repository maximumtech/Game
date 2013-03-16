package Game.base;

/**
 *
 * @author maximumtech
 */
public class TickHandler extends Thread {

    private long msPerTick = 50;
    public static Thread thread;
    
    public TickHandler() {
        super("tick");
        this.start();
        TickHandler.thread = this;
    }

    public void run() {
        while (!GameBase.closeRequested) {
            GameBase.instance.runTick();
            try {
                Thread.sleep(msPerTick);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
