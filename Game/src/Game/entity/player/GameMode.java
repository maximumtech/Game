package Game.entity.player;

/**
 *
 * @author m1
 */
public enum GameMode {
    
    SURVIVAL(0.05f, false),
    CREATIVE(1000f, true);
    
    private float hard;
    private boolean canFly;
    
    private GameMode(float hardness, boolean allowedFlight) {
        this.hard = hardness;
        this.canFly = allowedFlight;
    }
    
    public float getModeBlockHardness() {
        return this.hard;
    }
    
    public boolean getModeFlightAbility() {
        return this.canFly;
    }

}