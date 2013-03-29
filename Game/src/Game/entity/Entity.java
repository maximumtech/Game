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

    public int getMidX() {
        return x + sizeX / 2;
    }

    public int getMidY() {
        return y + sizeY / 2;
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

    public boolean canCollide() {
        return true;
    }

    public boolean isColliding(int x, int y, Side side) {
        if (!canCollide()) {
            return false;
        }
        CollisonBox box = getCollisonBox(x, y);
        int minX = box.minX;
        int maxX = box.maxX;
        int minY = box.minY;
        int maxY = box.maxY;
        int minBlockX = world.getCoordinateFromPixel(minX);
        int maxBlockX = world.getCoordinateFromPixel(maxX);
        int minBlockY = world.getCoordinateFromPixel(minY);
        int maxBlockY = world.getCoordinateFromPixel(maxY);
        if (side == Side.TOP) {
            int top = world.getCoordinateFromPixel(maxY);
            for (int cX = minBlockX; cX < maxBlockX; cX++) {
                if (top < 0 || cX < 0 || top >= world.getHeight() || cX >= world.getWidth()) {
                    return true;
                }
                BlockBase block = world.getBlock(cX, top);
                if (block != null && block.canCollide(world, cX, top, Side.BOTTOM)) {
                    CollisonBox box2 = block.getCollisonBox(world, cX, top);
                    if (box.intersects(box2)) {
                        isJumping = false;
                        jumpTick = 0;
                        return true;
                    }
                }
            }
        } else if (side == Side.BOTTOM) {
            int bottom = world.getCoordinateFromPixel(minY - 1);
            for (int cX = minBlockX; cX < maxBlockX; cX++) {
                if (bottom < 0 || cX < 0 || bottom >= world.getHeight() || cX >= world.getWidth()) {
                    return true;
                }
                BlockBase block = world.getBlock(cX, bottom);
                if (block != null && block.canCollide(world, cX, bottom, Side.TOP)) {
                    CollisonBox box2 = block.getCollisonBox(world, cX, bottom);
                    if (box.intersects(box2)) {
                        return true;
                    }
                }
            }
        } else if (side == Side.RIGHT) {
            int right = world.getCoordinateFromPixel(maxX);
            for (int cY = minBlockY; cY < maxBlockY; cY++) {
                if (right < 0 || cY < 0 || cY >= world.getHeight() || right >= world.getWidth()) {
                    return true;
                }
                BlockBase block = world.getBlock(right, cY);
                if (block != null && block.canCollide(world, right, cY, Side.LEFT)) {
                    CollisonBox box2 = block.getCollisonBox(world, right, cY);
                    if (box.intersects(box2)) {
                        return true;
                    }
                }
            }
        } else if (side == Side.LEFT) {
            int left = world.getCoordinateFromPixel(minX - 1);
            for (int cY = minBlockY; cY < maxBlockY; cY++) {
                if (left < 0 || cY < 0 || cY >= world.getHeight() || left >= world.getWidth()) {
                    return true;
                }
                BlockBase block = world.getBlock(left, cY);
                if (block != null && block.canCollide(world, left, cY, Side.RIGHT)) {
                    CollisonBox box2 = block.getCollisonBox(world, left, cY);
                    if (box.intersects(box2)) {
                        return true;
                    }
                }
            }
        }
        return false;
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
            if (block != null && block.canCollide(world, x, y, Side.TOP)) {
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

    public boolean canFall() {
        return true;
    }

    public void onUpdate() {
        if (!World.isLocal && this != world.mainPlayer) {
            return;
        }
        boolean onGround = isOnGround();
        if (!onGround && (!isJumping) && canFall()) {
            fallT++;
            motionY -= Math.min(GameBase.blockSize * 8, Math.max(10, fallT));
            wasOnGround = true;
        } else if (isJumping && canFall()) {
            if (jumpTick == 0) {
                motionY = 10;
            } else if (jumpTick > 0 && jumpTick < 20) {
                motionY += 20 - jumpTick * 2;
            } else if (jumpTick == 20) {
                jumpTick = 0;
                isJumping = false;
            }
            jumpTick++;
        } else {
            if (wasOnGround) {
                fall(fallT);
            }
            fallT = 0;
            jumpTick = 0;
            isJumping = false;
        }
        if (canMove()) {
            while (motionX > 0 && !isColliding(getX(), getY(), Side.RIGHT)) {
                setPosition(getX() + 1, getY());
                motionX--;
            }
            while (motionX < 0 && !isColliding(getX(), getY(), Side.LEFT)) {
                setPosition(getX() - 1, getY());
                motionX++;
            }
            while (motionY > 0 && !isColliding(getX(), getY(), Side.TOP)) {
                setPosition(getX(), getY() + 1);
                motionY--;
            }
            while (motionY < 0 && !isColliding(getX(), getY(), Side.BOTTOM)) {
                setPosition(getX(), getY() - 1);
                motionY++;
            }
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
