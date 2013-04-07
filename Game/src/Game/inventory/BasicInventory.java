package Game.inventory;

import Game.base.ItemStack;
import java.util.ArrayList;

/**
 *
 * @author maximumtech
 */
public class BasicInventory implements IInventory {

    private int width = 10;
    private int height = 5;
    protected ArrayList<Slot> slots = new ArrayList<>();

    public BasicInventory(int width, int height) {
        this.height = height;
        this.width = width;
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                slots.add(new Slot(this, (x * getWidth()) + y, null));
            }
        }
    }

    public int addItemStack(ItemStack item) {
        return addItemStack(item, item.getAmount());
    }

    public int addItemStack(ItemStack item, int amt) {
        for (Slot slot : slots) {
            if (slot != null && slot.getStack() != null && slot.getStack().matches(item)) {
                amt = slot.addItemAmount(amt);
                if (amt < 0) {
                    return 0;
                } else {
                    break;
                }
            }
        }
        for (Slot slot : slots) {
            if (slot != null && slot.getStack() == null) {
                slot.setItem(item);
                slot.setItemAmount(amt);
                amt = 0;
                return amt;
            }
        }
        return amt;
    }

    public int hasItem(ItemStack item) {
        for (Slot slot : slots) {
            ItemStack is = slot.getStack();
            if (slot != null && is != null && is.matches(item)) {
                return slot.getStack().getAmount();
            }
        }
        return 0;
    }

    public int removeItemStack(ItemStack item) {
        return removeItemStack(item, item.getAmount());
    }

    public int removeItemStack(ItemStack item, int amt) {
        for (Slot slot : slots) {
            ItemStack is = slot.getStack();
            if (slot != null && is != null && is.matches(item)) {
                amt = slot.removeItemAmount(amt);
                break;
            }
        }
        return amt;
    }

    public ItemStack getStackInSlot(int slot) {
        return slots.get(slot).getStack();
    }

    public ItemStack getStackInSlot(int x, int y) {
        return slots.get((x * getHeight()) + y).getStack();
    }

    public boolean hasStackInSlot(int slot) {
        return getStackInSlot(slot) != null;
    }

    public boolean hasStackInSlot(int x, int y) {
        return getStackInSlot(x, y) != null;
    }

    public Slot getSlot(int slot) {
        return slots.get(slot);
    }

    public Slot getSlot(int x, int y) {
        return slots.get((x * getHeight()) + y);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSize() {
        return getWidth() * getHeight();
    }
}
