package Game.misc;

import Game.entity.Entity;

/**
 *
 * @author maximumtech
 */
public class CollisonHelper {

    public static boolean intersects(int minX, int maxX, int minY, int maxY, int minX2, int maxX2, int minY2, int maxY2) {
        return (maxX2 > minX && maxX > minX2) || (maxY2 > minY && maxY > minY2);
    }

    public static boolean intersects(Entity ent1, Entity ent2) {
        return intersects(ent1.getX(), ent1.getMaxX(), ent1.getY(), ent1.getMaxY(), ent2.getX(), ent2.getMaxX(), ent2.getY(), ent2.getMaxY());
    }

    public static boolean intersects(Entity ent, int minX, int maxX, int minY, int maxY) {
        return intersects(ent.getX(), ent.getMaxX(), ent.getY(), ent.getMaxY(), minX, maxX, minY, maxY);
    }
}
