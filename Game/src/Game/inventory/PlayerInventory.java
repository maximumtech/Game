package Game.inventory;

import Game.base.BlockBase;
import Game.base.ItemStack;
import Game.entity.EntityPlayerSP;
import Game.render.ImageHandler;
import java.util.ArrayList;
import org.lwjgl.opengl.Display;

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

    public void render(boolean hotbarOnly) {
        if (hotbarOnly) {
            ImageHandler.drawImage2D(ImageHandler.getImage("gui/inventory/inv"), Display.getWidth() / 2 - 208, 0, 3, 424, Display.getHeight() / 12, 256, 256, 256, 255);
            int pos = 0;
            for (ItemStack item : getHotbar()) {
                if (item != null) {
                    item.renderGUI(pos * 40 + 8 + Display.getWidth() / 4, Display.getHeight() / 24 - 16, 32, 32);
                }
                if (pos == getSelectedSlot()) {
                }
                pos++;
            }
        }
    }

    public ItemStack[] getHotbar() {
        return new ItemStack[]{getStackInSlot(0, getHeight() - 1), getStackInSlot(1, getHeight() - 1), getStackInSlot(2, getHeight() - 1), getStackInSlot(3, getHeight() - 1), getStackInSlot(4, getHeight() - 1), getStackInSlot(5, getHeight() - 1), getStackInSlot(6, getHeight() - 1), getStackInSlot(7, getHeight() - 1), getStackInSlot(8, getHeight() - 1), getStackInSlot(9, getHeight() - 1)};
    }

    public ItemStack getHotbar(int num) {
        return getHotbar()[num];
    }
    private int currentItem = 9;

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

    public int addItemStack(ItemStack item) {
        return addItemStack(item, item.getAmount());
    }

    public int addItemStack(ItemStack item, int amt) {
        for (Slot slot : slots) {
            ItemStack is = slot.getStack();
            if (slot != null && is != null && is.matches(item)) {
                amt = slot.addItemAmount(item.getAmount());
                return amt;
            }
        }
        for (int i = slots.size() - 1; i > 0; i--) {
            Slot slot = slots.get(i);
            if (slot != null && slot.getStack() == null) {
                slot.setItem(item);
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
