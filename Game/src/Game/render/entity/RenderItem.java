package Game.render.entity;

import Game.entity.EntityItem;

/**
 *
 * @author maximumtech
 */
public class RenderItem extends RenderEntity {

    public RenderItem(EntityItem entity) {
        super(entity);
    }

    public void render(int x, int y) {
        EntityItem item = ((EntityItem) entity);
        if (item.storedItem != null) {
            item.storedItem.renderWorld(item.world, x, y);
        }
    }
}
