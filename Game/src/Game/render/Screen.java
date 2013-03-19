package Game.render;

import java.util.ArrayList;

/**
 *
 * @author maximumtech
 */
public abstract class Screen {
    
    protected ArrayList<ScreenComponent> components = new ArrayList<>();
    
    public void render() {
        for(ScreenComponent comp : components) {
            comp.render();
        }
    }

    public void onUpdate() {
        for (ScreenComponent comp : components) {
            comp.onUpdate();
        }
    }
    
    public void action(ScreenComponent comp) {
        
    }
    
}
