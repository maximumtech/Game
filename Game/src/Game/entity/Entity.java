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
        return getCollisonBox().minX;
    }

    public int getY() {
        return getCollisonBox().minY;
    }

    public int getMaxX() {
        return getCollisonBox().maxX;
    }

    public int getMaxY() {
        return getCollisonBox().maxY;
    }

    public int getBlockX() {
        return world.getCoordinateFromPixel(getX());
    }

    public int getBlockY() {
        return world.getCoordinateFromPixel(getY());
    }

    public int getMaxBlockX() {
        return world.getCoordinateFromPixel(getMaxX());
    }

    public int getMaxBlockY() {
        return world.getCoordinateFromPixel(getMaxY());
    }

    public boolean isColliding(int x, int y) {
        CollisonBox box = getCollisonBox(x, y);
        for (int xx = getBlockX(); xx < getMaxBlockX(); xx++) {
            for (int yy = getBlockY(); yy < getMaxBlockY(); yy++) {
                BlockBase block = world.getBlock(xx, yy);
                if (block != null && block.canCollide(world, xx, yy)) {
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
        if (!isColliding(x, y)) {
            this.x = x;
            this.y = y;
        }
    }

    public void onUpdate() {
        if (isColliding(getX(), getY())) {
            motionY += GameBase.blockSize / 4;
        }
        if (canMove()) {
            while (motionX > 0) {
                setPosition(getX() + 1, getY());
                motionX--;
            }
            while (motionX < 0) {
                setPosition(getX() - 1, getY());
                motionX++;
            }
            while (motionY > 0) {
                setPosition(getX(), getY() + 1);
                motionY--;
            }
            while (motionY < 0) {
                setPosition(getX(), getY() - 1);
                motionY++;
            }
        }
        motionX = 0;
        motionY = 0;
    }

    public CollisonBox getCollisonBox() {
        return new CollisonBox(x, y, x + sizeX, y + sizeY);
    }

    public CollisonBox getCollisonBox(int x, int y) {
        return new CollisonBox(x, y, x + sizeX, y + sizeY);
    }
}
