package Game.entity;

import Game.base.BlockBase;
import Game.base.World;
import Game.base.GameBase;
import Game.misc.CollisonHelper;
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
    
    public int getHeight() {
        return (int)(this.getMaxY() - (this.getMidY() * 2));
    }

    public boolean isColliding(int x, int y, Side side) {
        if (!canCollide()) {
            return false;
        }
        int minX = getX();
        int maxX = getMaxX();
        int minY = getY();
        int maxY = getMaxY();
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
                    int bx = world.getPixelFromCoordinate(cX);
                    int by = world.getPixelFromCoordinate(top);
                    if (CollisonHelper.intersects(this, bx, by, bx + block.getCollisonWidth(world, cX, top), by + block.getCollisonHeight(world, x, y))) {
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
                    int bx = world.getPixelFromCoordinate(cX);
                    int by = world.getPixelFromCoordinate(bottom);
                    if (CollisonHelper.intersects(this, bx, by, bx + block.getCollisonWidth(world, cX, bottom), by + block.getCollisonHeight(world, cX, bottom))) {
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
                    int bx = world.getPixelFromCoordinate(right);
                    int by = world.getPixelFromCoordinate(cY);
                    if (CollisonHelper.intersects(this, bx, by, bx + block.getCollisonWidth(world, right, cY), by + block.getCollisonHeight(world, right, cY))) {
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
                    int bx = world.getPixelFromCoordinate(left);
                    int by = world.getPixelFromCoordinate(cY);
                    if (CollisonHelper.intersects(this, bx, by, bx + block.getCollisonWidth(world, left, cY), by + block.getCollisonHeight(world, left, cY))) {
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
            for (Entity ent : world.entityList) {
                synchronized (ent) {
                    if (ent != this) {
                        if (CollisonHelper.intersects(ent, this)) {
                            this.onCollide(ent);
                        }
                    }
                }
            }
        }
    }
}
