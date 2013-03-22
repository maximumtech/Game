package Game.base;

/**
 *
 * @author maximumtech
 */
public class ItemStack {
    private ItemBase item = null;
    private short meta = 0;
    private String data = "";
    private int stackSize = 1;
    
    public ItemStack() {
        
    }
    
    public ItemStack(ItemBase item) {
        this();
        this.item = item;
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
    
    public ItemStack setAmount(int amt) {
        this.stackSize = amt;
        return this;
    }
    
    public ItemStack increment(int inc) {
        setAmount(getAmount() + inc);
        return this;
    }
    
    public ItemStack decrement(int dec) {
        setAmount(getAmount() - dec);
        return this;
    }
}
