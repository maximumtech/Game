package Game.render;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import static org.lwjgl.opengl.GL11.*;



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
        uniFont.addGlyphs(400,600);
        uniFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
        try {
            uniFont.loadGlyphs();
        } catch (SlickException e) {e.printStackTrace();};
        GL11.glEnable(GL11.GL_TEXTURE_2D);
	}
	
	public int getStringWidth(String str) {
		int toreturn=uniFont.getWidth(str);
		return toreturn;
	}
	
	public void drawStringWithColor(String str, int x, int y, Color color) {
		glPushMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		uniFont.drawString(Float.parseFloat(x+""), Float.parseFloat(y+""), str, color);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		glPopMatrix();
	}

	public void drawString(String str, int x, int y) {
		glPushMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		uniFont.drawString(x, y, str);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		glPopMatrix();
	}
	
}
