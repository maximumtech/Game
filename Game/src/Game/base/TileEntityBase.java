package Game.base;

import Game.interaction.ITickHandler;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author maximumtech
 */
public class TileEntityBase implements Serializable, ITickHandler {

    private int x;
    private int y;
    public transient World world;
    public static Random rand = new Random();

    public TileEntityBase(World world, int x, int y) {
        this.world = world;
        this.x = x;
        this.y = y;
        Game.interaction.TickHandler.instance.registerTickHandler(this);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public World getWorld() {
        return world;
    }

    public void tick() {
    }
}
