package Game.render.gui;

import Game.base.GameBase;
import Game.entity.EntityPlayer;
import Game.render.FontRenderer;
import java.util.ArrayList;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Richard Roe
 */
public class GuiDebug extends Screen {
    
    private FontRenderer fr;
    private ArrayList<String> debugOutput;
    
    public GuiDebug() {
        this.fr = GameBase.getFontRenderer(12);
        this.debugOutput = new ArrayList<String>();
    }
    
    public void render() {
        GL11.glPushMatrix();
        GL11.glTranslatef(0, 0, 4F);
        int x = 2;
        int y = 2;
        //Because the screen ortho is flipped (generally Y axis is inverted, I have to iterate through
        //the list backwards so the output isn't retarded and dumb looking.
        this.onUpdate();
        for(int x1 = this.debugOutput.size(); x1 > 0; x1--) {
            //Make sure to subrtact 1 so you don't get a range check error!
            String s = this.debugOutput.get(x1 - 1);
            fr.drawString(s, x, y+12, 4);
            y += 12;
        }
        GL11.glPopMatrix();
    }
    
    public void onUpdate() {
        this.updateOutput();
    }
    
    public void updateOutput() {
        EntityPlayer p = GameBase.instance.getWorld().mainPlayer;
        debugOutput.clear();
        int xMid = p.getMidX();
        int yMid = p.getMidY();
        //Block sizes are 16 pixels, so we need to figure it out without having a remainder. Bleh.
        int xDelta = xMid % 16;
        int yDelta = yMid % 16;
        int xFin = xMid - xDelta;
        int yFin = yMid - yDelta;
        debugOutput.add("X: " + (xFin/16));
        debugOutput.add("Y: " + (yFin/16));
        debugOutput.add("FPS: " + GameBase.instance.getFPS());
    }
    
}
