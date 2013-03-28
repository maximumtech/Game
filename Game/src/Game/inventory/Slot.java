package Game.inventory;

import Game.base.ItemStack;

/**
 *
 * @author maximumtech
 */
public class Slot {

    private ItemStack storedItem = null;
    private IInventory inventory;
    private int slot;

    public Slot(IInventory inv, int slot, ItemStack item) {
        this.inventory = inv;
        this.slot = slot;
        this.storedItem = item;
    }

    public IInventory getInventory() {
        return inventory;
    }

    public ItemStack getStack() {
        return storedItem;
    }

    public int addItemAmount(int amt) {
        if (storedItem != null) {
            return storedItem.increment(amt);
        }
        return 0;
    }

    public int removeItemAmount(int amt) {
        if (storedItem != null) {
            int dec = storedItem.decrement(amt);
            if (storedItem.getAmount() == 0) {
                storedItem = null;
                return 0;
            }
            return dec;
        }
        return 0;
    }

    public int setItemAmount(int amt) {
        if (storedItem != null) {
            return storedItem.setAmount(amt);
        }
        return 0;
    }

    public int getSlotIndex() {
        return slot;
    }

    public ItemStack setItem(ItemStack itm) {
        ItemStack i = storedItem;
        storedItem = itm;
        return i;
    }
}
