package Game.inventory;

import Game.base.ItemStack;
import Game.misc.CollisonHelper;
import Game.render.ImageHandler;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author maximumtech
 */
public class Slot {

    private ItemStack storedItem = null;
    private IInventory inventory;
    private int slot;
    public static volatile ItemStack itemInHand = null;
    private boolean mouseDownleft = false;
    private boolean mouseDownright = false;

    public void onRenderUpdate(int x, int y) {
        boolean isDownLeft = Mouse.isButtonDown(0);
        if (!mouseDownleft && isDownLeft) {
            int mX = Mouse.getX();
            int mY = Mouse.getY();
            if (CollisonHelper.intersects(x, x + 40, y, y + 40, mX, mX, mY, mY)) {
                if (itemInHand != null) {
                    itemInHand = tryToAddItem(itemInHand);
                } else {
                    if (this.getStack() != null) {
                        itemInHand = this.getStack();
                        this.setItem(null);
                    }
                }
            }
        }
        mouseDownleft = isDownLeft;

        boolean isDownRight = Mouse.isButtonDown(1);
        if (!mouseDownright && isDownRight) {
            int mX = Mouse.getX();
            int mY = Mouse.getY();
            if (CollisonHelper.intersects(x, x + 40, y, y + 40, mX, mX, mY, mY)) {
                if (itemInHand != null) {
                    ItemStack item = tryToAddItem(itemInHand.clone(1));
                    int s = itemInHand.getAmount();
                    if (item == null) {
                        s--;
                    }
                    itemInHand.setAmount(s);
                    if (s <= 0) {
                        itemInHand = null;
                    }
                } else {
                    if (this.getStack() != null) {
                        int amt = this.getStack().getAmount() / 2;
                        if (this.getStack().getAmount() <= 1) {
                            amt = 1;
                        }
                        itemInHand = this.getStack().clone(amt);
                        this.setItemAmount(this.getStack().getAmount() - amt);
                    }
                }
            }
        }
        mouseDownright = isDownRight;
    }

    public ItemStack tryToAddItem(ItemStack stack) {
        int size = stack.getAmount();
        if (stack == null) {
        } else if (this.getStack() == null) {
            this.setItem(stack);
            size = 0;
        } else if (this.getStack().matches(stack) && stack.getAmount() > 0) {
            size = this.addItemAmount(stack.getAmount());
        }
        if (size <= 0) {
            return null;
        }
        stack.setAmount(size);
        return stack;
    }

    public Slot(IInventory inv, int slot, ItemStack item) {
        this.inventory = inv;
        this.slot = slot;
        this.storedItem = item;
    }

    public static enum SlotColor {

        RED("red"),
        BLUE("blue"),
        YELLOW("yellow");
        private String name = "";

        public String getName() {
            return name;
        }

        private SlotColor(String name) {
            this.name = name;
        }
    }

    public void render(int x, int y, int z, SlotColor color) {
        ImageHandler.drawImage2D(ImageHandler.getImage("gui/inventory/slot" + color.getName()), x, y, z, 40, 40, 255, 255, 255, 192);
        if (storedItem != null) {
            storedItem.renderGUI(x + 4, y + 4, z, 32, 32);
        }
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
