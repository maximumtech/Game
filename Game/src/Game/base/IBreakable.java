package Game.base;

import Game.content.Material;

/**
 *
 * @author maximumtech
 */
public interface IBreakable {

    public Material getMaterial();

    public void onBreak(World world, int x, int y, ItemStack it);

    public int getTier();

    public float getHardness(World world, int x, int y);

    public boolean isBreakable(World world, int x, int y);

    public void onStartBreaking(World world, int x, int y, ItemStack is);
}
