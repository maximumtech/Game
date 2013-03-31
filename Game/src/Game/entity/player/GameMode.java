package Game.entity.player;

import Game.base.GameBase;

/**
 *
 * @author m1
 */
public enum GameMode {

    SURVIVAL("Survival", 1f, false, GameBase.blockSize * 7),
    CREATIVE("Creative", 1000f, true, GameBase.blockSize * 100);
    private float hardnessMod;
    private int reachDistance;
    private boolean canFly;
    private String title;

    private GameMode(String t, float hardness, boolean allowedFlight, int reachDistance) {
        this.hardnessMod = hardness;
        this.canFly = allowedFlight;
        this.reachDistance = reachDistance;
        this.title = t;
    }
    
    public String getTitle() {
        return this.title;
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
