package Game.inventory;

import Game.base.ItemStack;

/**
 *
 * @author maximumtech
 */
public interface IInventory {
    
    public ItemStack addItemStack(ItemStack item);

    public ItemStack removeItemStack(ItemStack item);
    
    public ItemStack getStackInSlot(int slot);
    
    public ItemStack getStackInSlot(int x, int y);
    
    public boolean hasStackInSlot(int slot);
    
    public boolean hasStackInSlot(int x, int y);
    
    public Slot getSlot(int slot);
    
    public Slot getSlot(int x, int y);
    
    public int getWidth();
    
    public int getHeight();
    
    public int getSize();
}
