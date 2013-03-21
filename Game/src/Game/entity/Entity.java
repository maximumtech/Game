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

    public int getMaxX() {
        return x+sizeX;
    }

    public int getMaxY() {
        return y+sizeY;
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
        int minX = getBlockX();
        int maxX = getMaxBlockX();
        int minY = getBlockY();
        int maxY = getMaxBlockY();
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
    
    public void setPositionAllowCollison(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void onUpdate() {
        if (isColliding(getX(), getY())) {
            int exY = GameBase.blockSize / 4;
            while(exY>0 && isColliding(getX(), getY())) {
                //setPositionAllowCollison(getX(), getY() + 1);
                exY--;
            }
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
    }

    public CollisonBox getCollisonBox() {
        return new CollisonBox(x, y, x + sizeX, y + sizeY);
    }

    public CollisonBox getCollisonBox(int x, int y) {
        return new CollisonBox(x, y, x + sizeX, y + sizeY);
    }
}
