package Game.base;

import Game.render.FontRenderer;

/**
 *
 * @author maximumtech
 */
public class ItemStack {
    private ItemBase item = null;
    private short meta = 0;
    private String data = "";
    private int stackSize = 1;
    public static FontRenderer fontRenderer;
    
    public ItemStack() {
        
    }
    
    public ItemStack(ItemBase item) {
        this();
        this.item = item;
    }
    
    public void renderWorld(World world, int x, int y) {
        getItem().renderWorld(world, x, y, meta, data);
    }
    
    public void renderGUI(int x, int y) {
        getItem().renderGUI(x, y, meta, data);
        fontRenderer.drawString("" + stackSize, x, y);
        fontRenderer.drawString("" + getItem().getName(), x, y + GameBase.blockSize);
    }
    
    public ItemStack(ItemBase item, int amt) {
        this(item);
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
        this.item= item;
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
        return Math.max(1, Math.min(stackSize, getItem().getMaxStackSize()));
    }
    
    public int setAmount(int amt) {
        int pamt = getAmount();
        stackSize = Math.max(1, Math.min(amt, getItem().getMaxStackSize()));
        return pamt + amt - stackSize;
    }
    
    public int increment(int inc) {
        return setAmount(getAmount() + inc);
    }

    public int decrement(int dec) {
        return setAmount(getAmount() - dec);
    }
}
