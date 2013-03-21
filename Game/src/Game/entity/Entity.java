package Game.entity;

import Game.base.BlockBase;
import Game.base.World;
import Game.base.CollisonBox;
import Game.base.GameBase;
import Game.render.RenderEntity;

/**
 *
 * @author maximumtech
 */
public abstract class Entity {

    public String name = "";
    public World world;
    private int x = 0;
    private int y = 0;
    public int sizeX = 0;
    public int sizeY = 0;
    RenderEntity renderer = null;
    public int motionX;
    public int motionY;

    public Entity(World world) {
        this.world = world;
        motionX = 0;
        motionY = 0;
    }
    
    public boolean canMove() {
        return true;
    }

    public void render() {
        if (renderer != null) {
            renderer.render(getX(), getY());
        }
    }

    public Entity(World world, int x, int y) {
        this(world);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getBlockX() {
        return x / GameBase.blockSize;
    }

    public int getBlockY() {
        return y / GameBase.blockSize;
    }

    public boolean isColliding(int x, int y) {
        CollisonBox box = getCollisonBox(x, y);
        for (int xx = box.minX / GameBase.blockSize - 1; xx < box.maxX / GameBase.blockSize + 1; xx++) {
            for (int yy = box.minY / GameBase.blockSize - 1; yy < box.maxY / GameBase.blockSize + 1; yy++) {
                BlockBase block = world.getBlock(xx, yy);
                if (block != null) {
                    CollisonBox box2 = block.getCollisonBox(world, xx, yy);
                    if (box.intersects(box2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void setPosition(int x, int y) {
        //if (!isColliding(x, y)) {
        this.x = x;
        this.y = y;
        //}
    }

    public void onUpdate() {
    }

    public CollisonBox getCollisonBox() {
        return new CollisonBox(x, y, x + sizeX, y + sizeY);
    }

    public CollisonBox getCollisonBox(int x, int y) {
        return new CollisonBox(x, y, x + sizeX, y + sizeY);
    }
}
