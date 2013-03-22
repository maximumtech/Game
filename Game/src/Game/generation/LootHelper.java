/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.generation;

import Game.base.ItemBase;
import Game.base.ItemStack;
import java.util.ArrayList;
import java.util.Random;

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
    
    public ArrayList<ItemStack> poorItems = new ArrayList<>();
    public ArrayList<ItemStack> commonItems = new ArrayList<>();
    public ArrayList<ItemStack> uncommonItems = new ArrayList<>();
    public ArrayList<ItemStack> rareItems = new ArrayList<>();
    public ArrayList<ItemStack> epicItems = new ArrayList<>();
    
    public Rarity getRarity(ItemStack is) {
        if (poorItems.contains(is)) {
            return Rarity.POOR;
        }
        if (commonItems.contains(is)) {
            return Rarity.COMMON;
        }
        if (uncommonItems.contains(is)) {
            return Rarity.UNCOMMON;
        }
        if (rareItems.contains(is)) {
            return Rarity.RARE;
        }
        if (epicItems.contains(is)) {
            return Rarity.EPIC;
        }
        return Rarity.POOR;
    }
    
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
    
    Random rand = new Random();
    
    public ItemStack getItem(Rarity rarity) { // change to itemstack
        switch (rarity) {
            case POOR:
                int ran = rand.nextInt(poorItems.size());
                return poorItems.get(ran);
            case COMMON:
                int ran2 = rand.nextInt(commonItems.size());
                return commonItems.get(ran2);
            case UNCOMMON:
                int ran3 = rand.nextInt(uncommonItems.size());
                return uncommonItems.get(ran3);
            case RARE:
                int ran4 = rand.nextInt(rareItems.size());
                return rareItems.get(ran4);
            case EPIC:
                int ran5 = rand.nextInt(epicItems.size());
                return epicItems.get(ran5);
        }
        return null;
    }
}
