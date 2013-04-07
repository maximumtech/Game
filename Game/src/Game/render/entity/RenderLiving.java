package Game.render.entity;

import Game.entity.EntityLiving;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author maximumtech
 */
public class RenderLiving extends RenderEntity {

    public RenderLiving(EntityLiving entity) {
        super(entity);
    }

    public void render(int x, int y, int z) {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glTranslatef(0, 0, z);
        GL11.glLineWidth(1F);
        GL11.glColor3f(1F, 1F, 1F);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glVertex2i(x, y);
        GL11.glVertex2i(x + entity.getWidth(), y);
        GL11.glVertex2i(x + entity.getWidth(), y + entity.getHeight());
        GL11.glVertex2i(x, y + entity.getHeight());
        GL11.glVertex2i(x, y);
        GL11.glEnd();
        GL11.glTranslatef(0, 0, -z);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }
}
