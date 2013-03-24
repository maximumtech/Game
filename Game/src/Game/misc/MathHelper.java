package Game.misc;

import Game.entity.Entity;

/**
 *
 * @author Richard Roe
 */
public class MathHelper {
    
    public static double getDistance(int x1, int y1, int x2, int y2) {
       double xSquared = (x2 - x1) * (x2 - x1); 
       double ySquared = (y2 - y1) * (y2 - y1);
       return (Math.sqrt(xSquared + ySquared) < 0 ? Math.sqrt(xSquared + ySquared) * -1 : Math.sqrt(xSquared + ySquared));
    }
    
    public static double getAngle(int x1, int y1, int x2, int y2) {
        if (y2 < y1) {
            int yB = y1;
            y1 = y2;
            y2 = yB;
        }
        if (x2 < x1) {
            int xB = x1;
            x1 = x2;
            x2 = xB;
        }
        return Math.atan2(y2 - y1, x2 - x1) * 180.0 / Math.PI;
    }
    
    public static double getAngle(Entity ent1, Entity ent2) {
        return getAngle(ent1.getX() + ent1.sizeX / 2, ent1.getY() + ent1.sizeY / 2, ent2.getX() + ent2.sizeX / 2, ent2.getY() + ent2.sizeY / 2);
    }
    
    public static double getAngle(Entity ent1, int x, int y) {
        return getAngle(ent1.getX() + ent1.sizeX / 2, ent1.getY() + ent1.sizeY / 2, x, y);
    }
    
    public static int[] getDirectMotionTo(int x1, int y1, int x2, int y2, int speed) {
        double angle = getAngle(x1, y1, x2, y2);
        int x = (int) Math.sin(Math.toRadians(angle)) * speed;
        int y = (int) Math.cos(Math.toRadians(angle)) * speed;
        return new int[]{x, y};
    }
    
    public static int[] getDirectMotionTo(Entity ent1, int x2, int y2, int speed) {
        double angle = getAngle(ent1, x2, y2);
        int x = (int) Math.sin(Math.toRadians(angle)) * speed;
        int y = (int) Math.cos(Math.toRadians(angle)) * speed;
        return new int[]{x, y};
    }
    
    public static int[] getDirectMotionTo(Entity ent1, Entity ent2, int speed) {
        double angle = getAngle(ent1, ent2);
        int x = (int) Math.sin(Math.toRadians(angle)) * speed;
        int y = (int) Math.cos(Math.toRadians(angle)) * speed;
        return new int[]{x, y};
    }
    
}
