package Game.content;

/**
 *
 * @author maximumtech
 */
public enum ToolMaterial {
    WOOD(4F, 1, 0),
    STONE(6F, 3, 1),
    COPPER(7F, 4, 2),
    BRASS(10F, 4, 3),
    IRON(12F, 8, 4),
    SILVER(15F, 10, 5),
    GOLD(18F, 12, 6);
    
    private float blockBreakingModifier = 1F;
    private int entityDamage = 0;
    private int tier = 0;

    private ToolMaterial(float modifier, int damage, int tier) {
        this.blockBreakingModifier = modifier;
        this.entityDamage = damage;
        this.tier = tier;
    }
    
    public float getModifier() {
        return blockBreakingModifier;
    }
    
    public int getDamage() {
        return entityDamage;
    }
    
    public int getTier() {
        return tier;
    }
            
}
