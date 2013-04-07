package Game.render;

import Game.base.GameBase;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author maximumtech
 */
public class FontRenderer {

    private UnicodeFont uniFont;

    public FontRenderer(int size) {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        try {
            uniFont = new UnicodeFont("res/arial.ttf", size, false, false);
        } catch (SlickException e1) {
            e1.printStackTrace();
        }
        uniFont.addAsciiGlyphs();
        uniFont.addGlyphs(400, 600);
        uniFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
        try {
            uniFont.loadGlyphs();
        } catch (SlickException e) {
            e.printStackTrace();
        }
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public int getStringWidth(String str) {
        int toreturn = uniFont.getWidth(str);
        return toreturn;
    }

    public void drawStringWithColor(String str, int x, int y, int z, Color color) {
        glPushMatrix();
        GL11.glTranslatef(0, 0, z);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, GameBase.screenWidth, GameBase.screenHeight, 0, -100, 100);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        uniFont.drawString(x, GameBase.screenHeight - y, str, color);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, GameBase.screenWidth, 0, GameBase.screenHeight, -100, 100);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        glPopMatrix();
    }

    public void drawString(String str, int x, int y, int z) {
        drawStringWithColor(str, x, y, z, Color.white);
    }
}
