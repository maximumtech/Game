package Game.base.backend;

import java.util.ArrayList;

/**
 *
 * @author Richard Roe
 */
public class Debug {

    private ArrayList<String> output;
    
    //Class to allow for dynamic debugging. Will be adding more when there is networking.
    public Debug() {
        this.output = new ArrayList<>();
    }
    
    public void updateDebug() {
        output.clear();
    }
    
    public ArrayList<String> getOutput() {
        return this.output;
    }

}
