package Game.inventory;

import Game.base.GameBase;
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
    private boolean isOpen = false;

    public void toggleInventory() {
        isOpen = !isOpen;
    }

    public void render() {
        if (isOpen) {
            for (Slot slot : slots) {
                int x = slot.getSlotIndex() / getWidth();
                int y = slot.getSlotIndex() % getWidth();
                if (slot != null) {
                    slot.render(GameBase.screenWidth / 2 - 204 + x * 40, y * 40 + (y > 0 ? 10 : 0), x == getSelectedSlot() && y == 0);
                }
            }
        } else {
            int pos = 0;
            for (Slot slot : getHotbar()) {
                if (slot != null) {
                    slot.render(GameBase.screenWidth / 2 - 204 + pos * 40, 0, pos == getSelectedSlot());
                }
                pos++;
            }
        }
    }

    public Slot[] getHotbar() {
        return new Slot[]{getSlot(0, 0), getSlot(1, 0), getSlot(2, 0), getSlot(3, 0), getSlot(4, 0), getSlot(5, 0), getSlot(6, 0), getSlot(7, 0), getSlot(8, 0), getSlot(9, 0)};
    }

    public Slot getHotbar(int num) {
        return getHotbar()[num];
    }
    private int currentItem = 0;

    public ItemStack setSelectedItem(int num) {
        int cur = num;
        while (cur >= getWidth()) {
            cur -= getWidth();
        }
        while (cur < 0) {
            cur += getWidth();
        }
        currentItem = cur;
        return getHotbar(cur).getStack();
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
        return getHotbar()[getSelectedSlot()].getStack();
    }

    public int addItemStack(ItemStack item) {
        return addItemStack(item, item.getAmount());
    }

    public int addItemStack(ItemStack item, int amt) {
        for (Slot slot : slots) {
            ItemStack is = slot.getStack();
            if (slot != null && is != null && is.matches(item)) {
                amt = slot.addItemAmount(amt);
                if (amt < 0) {
                    return 0;
                } else {
                    amt += 1;
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

    public void incSelItem() {
        setSelectedItem(getSelectedSlot() + 1);
    }

    public void decSelItem() {
        setSelectedItem(getSelectedSlot() - 1);
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
        return slots.get((y * getWidth()) + x).getStack();
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
        return slots.get((y * getWidth()) + x);
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
