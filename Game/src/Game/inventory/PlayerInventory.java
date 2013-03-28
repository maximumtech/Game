package Game.inventory;

import Game.base.ItemStack;
import Game.entity.EntityPlayerSP;
import java.util.ArrayList;

/**
 *
 * @author maximumtech
 */
public class PlayerInventory implements IInventory {

    private int width = 10;
    private int height = 5;
    public EntityPlayerSP player;
    private ArrayList<Slot> slots = new ArrayList<>();

    public PlayerInventory(EntityPlayerSP player, int height) {
        this.player = player;
        this.height = height;
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                slots.add(new Slot(this, (x * getWidth()) + y, null));
            }
        }
    }

    public void render() {
    }

    public ItemStack[] getHotbar() {
        return new ItemStack[]{getStackInSlot(0, getHeight()), getStackInSlot(1, getHeight()), getStackInSlot(2, getHeight()), getStackInSlot(3, getHeight()), getStackInSlot(4, getHeight()), getStackInSlot(5, getHeight()), getStackInSlot(6, getHeight()), getStackInSlot(7, getHeight()), getStackInSlot(8, getHeight()), getStackInSlot(9, getHeight())};
    }

    public ItemStack getHotbar(int num) {
        return getHotbar()[num];
    }
    private int currentItem = 0;

    public ItemStack setSelectedItem(int num) {
        int cur = num;
        while (cur > getWidth()) {
            cur -= getWidth();
        }
        while (cur < 0) {
            cur += getWidth();
        }
        currentItem = cur;
        return getHotbar(cur);
    }

    public int setSelectedItem(ItemStack item) {
        for (int i = 0; i < getWidth(); i++) {
            if (getStackInSlot(i, getHeight()).matches(item)) {
                setSelectedItem(i);
                return i;
            }
        }
        return 0;
    }

    public int getSelectedSlot() {
        return currentItem;
    }

    public ItemStack getSelectedItem() {
        return getHotbar()[getSelectedSlot()];
    }

    public ItemStack addItemStack(ItemStack item) {
        int amt = item.getAmount();
        for (Slot slot : slots) {
            ItemStack is = slot.getStack();
            if (slot != null && is != null && is.matches(item)) {
                int extra = slot.addItemAmount(item.getAmount());
                if (extra <= 0) {
                    return null;
                } else {
                    amt = extra;
                }
            }
        }
        item.setAmount(amt);
        for (Slot slot : slots) {
            if (slot != null && slot.getStack() == null) {
                slot.setItem(item);
                return null;
            }
        }
        return item;
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

    public ItemStack removeItemStack(ItemStack item) {
        int amt = item.getAmount();
        for (Slot slot : slots) {
            ItemStack is = slot.getStack();
            if (slot != null && is != null && is.matches(item)) {
                int extra = slot.removeItemAmount(item.getAmount());
                if (extra <= 0) {
                    return null;
                } else {
                    amt = extra;
                }
            }
        }
        item.setAmount(amt);
        return item;
    }

    public ItemStack getStackInSlot(int slot) {
        return slots.get(slot).getStack();
    }

    public ItemStack getStackInSlot(int x, int y) {
        return slots.get((x * getWidth()) + y).getStack();
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
        return slots.get((x * getWidth()) + y);
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
