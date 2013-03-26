package Game.interaction;

import java.util.ArrayList;

/**
 *
 * @author maximumtech
 */
public class TickHandler {
    
    public static TickHandler instance = new TickHandler();
    
    private ArrayList<ITickHandler> handlers = new ArrayList<>();
    
    public void tick() {
        for(ITickHandler handler : handlers) {
            handler.tick();
        }
    }
    
    public void registerTickHandler(ITickHandler handler) {
        handlers.add(handler);
    }
    
}
