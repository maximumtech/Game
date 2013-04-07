package Game.base;

import Game.render.FontRenderer;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author maximumtech
 */
public class ItemStack {

    private ItemBase item = null;
    private short meta = 0;
    private String data = "";
    private int stackSize = 1;

    public ItemStack(ItemBase item) {
        this(item, 1);
    }

    public boolean matches(ItemStack stack) {
        if (stack == null) {
            return false;
        }
        return this.getItem().getRawID() == stack.getItem().getRawID() && this.getMeta() == stack.getMeta();
    }

    public void renderWorld(World world, int x, int y) {
        getItem().renderWorld(world, x, y, this);
    }

    public ItemStack clone() {
        ItemStack ret = new ItemStack(this.getItem());
        ret.setAmount(this.getAmount());
        ret.setMeta(this.getMeta());
        ret.setData(this.getData());
        return ret;
    }

    public ItemStack clone(int amt) {
        ItemStack ret = new ItemStack(this.getItem());
        ret.setAmount(amt);
        ret.setMeta(this.getMeta());
        ret.setData(this.getData());
        return ret;
    }

    public void renderGUI(int x, int y, int z, int width, int height) {
        getItem().renderGUI(x, y, width, height, this);
        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0, z);
        GameBase.getFontRenderer(12).drawString("" + getAmount(), x, y + 12, 4);
        GameBase.getFontRendererForWidth(6, 12, getItem().getName(), width).drawString(getItem().getName(), x, y + height, 4);
        GL11.glPopMatrix();
    }

    public ItemStack(ItemBase item, int amt) {
        this.item = item;
        stackSize = amt;
    }

    public ItemStack(ItemBase item, int amt, short meta) {
        this(item, amt);
        this.meta = meta;
    }

    public ItemStack(ItemBase item, int amt, short meta, String data) {
        this(item, amt, meta);
        this.data = data;
    }

    public ItemStack setItem(ItemBase item) {
        this.item = item;
        return this;
    }

    public ItemBase getItem() {
        return item;
    }

    public ItemStack setMeta(short meta) {
        this.meta = meta;
        return this;
    }

    public short getMeta() {
        return meta;
    }

    public ItemStack setData(String data) {
        this.data = data;
        return this;
    }

    public String getData() {
        return data;
    }

    public int getAmount() {
        return Math.max(0, Math.min(stackSize, getItem().getMaxStackSize()));
    }

    public int setAmount(int amt) {
        int pamt = getAmount();
        stackSize = Math.max(0, Math.min(amt, getItem().getMaxStackSize()));
        return pamt - stackSize;
    }

    public int increment(int inc) {
        return setAmount(getAmount() + inc);
    }

    public int decrement(int dec) {
        return setAmount(getAmount() - dec);
    }
}
