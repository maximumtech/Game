package Game.render;

import org.newdawn.slick.Image;

/**
 *
 * @author maximumtech
 */
public class ImageHandler {
    
    public static Image logo = getImage("logo");
    
    private static Image getImage(String key) {
        try {
            return new Image("res/" + key + ".png");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void drawImage2D(Image image, int x, int y) {
        if(image!=null)image.draw(x, y);
    }

    public static void drawImage2D(Image image, int x, int y, int width, int height) {
        if (image != null)image.draw(x, y, width, height);
    }
    
}
