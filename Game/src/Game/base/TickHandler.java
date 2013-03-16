/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.base;

/**
 *
 * @author maximumtech
 */
public class TickHandler {
    
    public static TickHandler instance;
    private long lastTick;
    private long msPerTick = 50;
    
    public TickHandler() {
        instance = this;
        lastTick = System.currentTimeMillis();
    }
    
    public void run() {
        long time = System.currentTimeMillis();
        if(time >= lastTick + msPerTick) {
            lastTick = time;
            tick();
        }
    }
    
    private void tick() {
        GameBase.instance.runTick();
    }
    
}
