package Game.misc;

/**
 *
 * @author Richard Roe
 */
public class MathHelper {
    
    /**
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return 
     */
    public static double getDistance(int x1, int y1, int x2, int y2) {
       double xSquared = (x2 - x1) * (x2 - x1); 
       double ySquared = (y2 - y1) * (y2 - y1);
       return (Math.sqrt(xSquared + ySquared) < 0 ? Math.sqrt(xSquared + ySquared) * -1 : Math.sqrt(xSquared + ySquared));
    }
    
}
