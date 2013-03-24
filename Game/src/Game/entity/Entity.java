package Game.entity;

import Game.base.BlockBase;
import Game.base.World;
import Game.base.CollisonBox;
import Game.base.GameBase;
import Game.misc.Side;
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
    public RenderEntity renderer = null;
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
        if (renderer != null && shouldRender()) {
            renderer.render(getX(), getY());
        }
    }

    public void onCollide(Entity entity) {
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
        return x + sizeX;
    }

    public int getMaxY() {
        return y + sizeY;
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
    public boolean isJumping = false;
    public int jumpTick = 0;

    public void jump() {
        if (isOnGround()) {
            isJumping = true;
            jumpTick = 0;
        }
    }

    public void setDead() {
        world.despawnEntity(this);
    }

    public boolean shouldRender() {
        return true;
    }

    public boolean isOnGround() {
        int y = world.getCoordinateFromPixel(getY() - 1);
        boolean ground = false;
        for (int x = world.getCoordinateFromPixel(getX()); x < world.getCoordinateFromPixel(getMaxX()); x++) {
            BlockBase block = world.getBlock(x, y);
            if (block != null && block.canCollide(world, x, y)) {
                ground = true;
            }
        }
        return this.y == 0 ? true : ground;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    private int fallT;
    private boolean wasOnGround = true;

    public void fall(int dist) {
    }

    public boolean isMovementBlocked(int xx, int yy, Side side) {
        CollisonBox box = this.getCollisonBox(xx, yy);
        for (int x = getBlockX(); x < getMaxBlockX(); x++) {
            for (int y = getBlockY(); y < getMaxBlockY(); y++) {
                BlockBase block = world.getBlock(x, y);
                if (block != null && block.canCollide(world, x, y)) {
                    if (block.getCollisonBox(world, x, y).isColliding(box, side)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void onUpdate() {
        boolean onGround = isOnGround();
        if (!onGround && (!isJumping)) {
            fallT++;
            motionY -= Math.min(GameBase.blockSize * 8, Math.max(13, fallT));
            wasOnGround = true;
        } else if (isJumping) {
            if (jumpTick == 0) {
                motionY = 10;
            } else if (jumpTick > 0 && jumpTick < 15) {
                motionY += 15 - jumpTick * 2;
            } else if (jumpTick == 15) {
                jumpTick = 0;
                isJumping = false;
            }
            jumpTick++;
        } else {
            if (wasOnGround) {
                fall(fallT);
            }
            fallT = 0;
        }
        if (canMove()) {
            while (motionX > 0 && !isMovementBlocked(getX() + 1, getY(), Side.RIGHT)) {
                setPosition(getX() + 1, getY());
                motionX--;
            }
            while (motionX < 0 && !isMovementBlocked(getX() - 1, getY(), Side.LEFT)) {
                setPosition(getX() - 1, getY());
                motionX++;
            }
            while (motionY > 0 && !isMovementBlocked(getX(), getY() + 1, Side.TOP)) {
                setPosition(getX(), getY() + 1);
                motionY--;
            }
            while (motionY < 0 && !isMovementBlocked(getX(), getY() - 1, Side.BOTTOM)) {
                setPosition(getX(), getY() - 1);
                motionY++;
            }
            //while (isMovementBlocked(Side.BOTTOM)) {
            //    setPosition(getX(), getY() + 1);
            //}
            
            //while (isMovementBlocked(Side.TOP)) {
            //    setPosition(getX(), getY() - 1);
            //}
            motionX = 0;
            motionY = 0;
        }
        synchronized (world.entityList) {
            CollisonBox box = getCollisonBox();
            for (Entity ent : world.entityList) {
                synchronized (ent) {
                    if (ent != this) {
                        CollisonBox box2 = ent.getCollisonBox();
                        if (box.intersects(box2)) {
                            this.onCollide(ent);
                        }
                    }
                }
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
