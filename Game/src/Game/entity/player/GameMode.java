package Game.entity.player;

import Game.base.GameBase;

/**
 *
 * @author m1
 */
public enum GameMode {

    SURVIVAL(1f, false, GameBase.blockSize * 7),
    CREATIVE(1000f, true, GameBase.blockSize * 100);
    private float hardnessMod;
    private int reachDistance;
    private boolean canFly;

    private GameMode(float hardness, boolean allowedFlight, int reachDistance) {
        this.hardnessMod = hardness;
        this.canFly = allowedFlight;
        this.reachDistance = reachDistance;
    }

    public float getBlockBreakingModifier() {
        return this.hardnessMod;
    }

    public int getReachDistance() {
        return this.reachDistance;
    }

    public boolean canFly() {
        return this.canFly;
    }
}
