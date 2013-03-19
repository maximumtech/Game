package Game.render;

import org.newdawn.slick.Image;
import java.util.HashMap;

/**
 *
 * @author maximumtech
 */
public class ImageHandler {
    
    private static HashMap<String, Image> img = new HashMap<>();
    
    public static Image getImage(String key) {
        try {
            if(img.containsKey(key)) {
                return img.get(key);
            }else{
                Image i = new Image("res/" + key + ".png");
                img.put(key, i);
                return i;
            }
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
