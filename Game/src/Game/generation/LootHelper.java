/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.generation;

import Game.base.ItemBase;

/**
 *
 * @author maximumtech
 */
public class LootHelper {
    
    public static enum Rarity {
        POOR,
        COMMON,
        UNCOMMON,
        RARE,
        EPIC;
    }
    
    //add items
    
    public int getItemWeight(Rarity rarity) { // change to itemstack
        switch (rarity) {
            case POOR:
                return 75;
            case COMMON:
                return 15;
            case UNCOMMON:
                return 6;
            case RARE:
                return 3;
            case EPIC:
                return 1;
        }
        return 0;
    }
    
    public ItemBase getItem(Rarity rarity) { // change to itemstack
        switch (rarity) {
            case POOR:
                
            case COMMON:
                
            case UNCOMMON:
                
            case RARE:
                
            case EPIC:
                
        }
        return null;
    }
}
