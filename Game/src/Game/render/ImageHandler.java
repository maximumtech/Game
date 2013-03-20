package Game.render;

import org.newdawn.slick.Image;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author maximumtech
 */
public class ImageHandler {

    private static HashMap<String, Image> img = new HashMap<>();
    private static HashMap<String, RenderStackBase> stk = new HashMap<>();

    public static Image getImage(String key) {
        try {
            if (img.containsKey(key)) {
                return img.get(key);
            } else {
                Image i = new Image("res/" + key + ".png");
                img.put(key, i);
                return i;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static RenderStackBase getRenderStack(String key) {
        try {
            if (stk.containsKey(key)) {
                return stk.get(key);
            } else {
                ArrayList<Image> imgs = new ArrayList<>();
                File dir = new File("res/" + key + "/");
                RenderStackBase rs = null;
                for (File file : dir.listFiles()) {
                    if (file.getName().endsWith(".png")) {
                        imgs.add(new Image(file.toString()));
                    } else if (file.getName().equals("config.txt")) {
                        ArrayList<String> lines = new ArrayList<>();
                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNext()) {
                            lines.add(scanner.nextLine());
                        }
                        for(String line : lines) {
                            if(line.startsWith("type:")) {
                                String type = line.substring(5);
                                if(type.equals("overlay")) {
                                    rs = new RenderStack();
                                }else if(type.equals("animation")) {
                                    //animation
                                }else{
                                    //??
                                }
                            }
                        }
                    }
                }
                if (rs == null) {
                    rs = new RenderStack();
                }
                for(Image img : imgs) {
                    rs.add(img);
                }
                stk.put(key, rs);
                return rs;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void drawImage2D(Image image, int x, int y) {
        if (image != null) {
            image.draw(x, y);
        }
    }

    public static void drawImage2D(Image image, int x, int y, int width, int height) {
        if (image != null) {
            image.draw(x, y, width, height);
        }
    }
}
