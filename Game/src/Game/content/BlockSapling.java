/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game.content;

import Game.base.World;
import Game.generation.WorldGenTerrain;

/**
 *
 * @author maximumtech
 */
public class BlockSapling extends BlockFlower {

    public BlockSapling(short id) {
        super(id);
        setRandomTick(4000);
    }

    public void tick(World world, int x, int y) {
        short meta = world.getBlockMeta(x, y);
        if (meta < 3) {
            world.setBlock(x, y, BlockBase.sapling, meta + 1);
        } else {
            WorldGenTerrain.treeGen.generate(x, y);
        }
    }
}
