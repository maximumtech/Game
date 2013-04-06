package Game.inventory;

import Game.base.ItemStack;

/**
 *
 * @author maximumtech
 */
public interface IInventory {

    public int addItemStack(ItemStack item);

    public int addItemStack(ItemStack item, int amt);

    public int removeItemStack(ItemStack item);

    public int removeItemStack(ItemStack item, int amt);

    public ItemStack getStackInSlot(int slot);

    public ItemStack getStackInSlot(int x, int y);

    public boolean hasStackInSlot(int slot);

    public boolean hasStackInSlot(int x, int y);

    public Slot getSlot(int slot);

    public Slot getSlot(int x, int y);

    public int getWidth();

    public int getHeight();

    public int getSize();

    public int hasItem(ItemStack item);
}
