package Game.inventory;

import Game.base.GameBase;
import Game.base.ItemStack;
import Game.entity.EntityPlayerSP;

/**
 *
 * @author maximumtech
 */
public class PlayerInventory extends BasicInventory {

    public EntityPlayerSP player;

    public PlayerInventory(EntityPlayerSP player, int height) {
        super(10, height);
        this.player = player;
    }
    private boolean isOpen = false;

    public void toggleInventory() {
        isOpen = !isOpen;
    }

    public void render() {
        if (isOpen) {
            for (Slot slot : slots) {
                if (slot != null) {
                    int x = slot.getSlotIndex() / getWidth();
                    int y = slot.getSlotIndex() % getWidth();
                    int xx = GameBase.screenWidth / 2 - 204 + x * 40;
                    int yy = y * 40 + (y > 0 ? 10 : 0);
                    slot.onRenderUpdate(xx, yy);
                    slot.render(xx, yy, 0, (x == getSelectedSlot() && y == 0) ? Slot.SlotColor.RED : Slot.SlotColor.BLUE);
                }
            }
        } else {
            int pos = 0;
            for (Slot slot : getHotbar()) {
                if (slot != null) {
                    int xx = GameBase.screenWidth / 2 - 204 + pos * 40;
                    int yy = 0;
                    slot.onRenderUpdate(xx, yy);
                    slot.render(xx, yy, 0, pos == getSelectedSlot() ? Slot.SlotColor.RED : Slot.SlotColor.BLUE);
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

    public void incSelItem() {
        setSelectedItem(getSelectedSlot() + 1);
    }

    public void decSelItem() {
        setSelectedItem(getSelectedSlot() - 1);
    }

    public int addItemStack(ItemStack item, int amt) {
        int y = 0;
        boolean cont = true;
        for (int x = 0; cont; x++) {
            if (x >= getWidth()) {
                x = 0;
                y++;
            }
            if (y >= getHeight()) {
                cont = false;
                continue;
            }
            Slot slot = getSlot(x, y);
            if (slot != null && slot.getStack() != null && slot.getStack().matches(item)) {
                amt = slot.addItemAmount(amt);
                if (amt < 0) {
                    return 0;
                } else {
                    cont = false;
                }
            }
        }
        y = 0;
        cont = true;
        for (int x = 0; cont; x++) {
            if (x >= getWidth()) {
                x = 0;
                y++;
            }
            if (y >= getHeight()) {
                cont = false;
            }
            Slot slot = getSlot(x, y);
            if (slot != null && slot.getStack() == null) {
                slot.setItem(item);
                amt = slot.setItemAmount(amt);
                if (amt < 0) {
                    return 0;
                } else {
                    return amt;
                }
            }
        }
        return amt;
    }
}
