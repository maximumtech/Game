package Game.inventory;

import Game.base.ItemStack;

/**
 *
 * @author maximumtech
 */
public interface IInventory {
    
    public boolean addItemStack(ItemStack item);
    
    public boolean decrItemStack(ItemStack item, int amt);
    
    public ItemStack getStackInSlot(int slot);
    
    public boolean hasStackInSlot(int slot);
    
    public Slot getSlot(int slot);
    
    public int getWidth();
    
    public int getHeight();
    
    public int getSize();
}
