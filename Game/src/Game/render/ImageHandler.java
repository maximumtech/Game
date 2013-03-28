package Game.render;

import Game.base.GameBase;
import org.newdawn.slick.Image;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

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
                Image i = new Image(System.getProperty("user.dir") + "/res/" + key + ".png").getFlippedCopy(false, true);
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
                File dir = new File(System.getProperty("user.dir") + "/res/" + key + "/");
                RenderStackBase rs = null;
                for (File file : dir.listFiles()) {
                    if (file.getName().endsWith(".png")) {
                        imgs.add(new Image(file.toString()).getFlippedCopy(false, true));
                    } else if (file.getName().equals("config.txt")) {
                        ArrayList<String> lines = new ArrayList<>();
                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNext()) {
                            lines.add(scanner.nextLine());
                        }
                        for (String line : lines) {
                            if (line.startsWith("type:")) {
                                String type = line.substring(5);
                                if (type.equals("overlay")) {
                                    rs = new RenderStack();
                                } else if (type.equals("animation")) {
                                    rs = new AnimatedRenderStack();
                                } else if (type.equals("random")) {
                                    rs = new RenderStackRandom();
                                } else {
                                    //??
                                }
                            }
                            if (line.startsWith("animDelay:")) {
                                String delay = line.substring(10);
                                if (rs instanceof AnimatedRenderStack) {
                                    for (String str : delay.replace(" ", "").split(",")) {
                                        try {
                                            ((AnimatedRenderStack) rs).add(Long.parseLong(str));
                                        } catch (Exception e) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (rs == null) {
                    rs = new RenderStack();
                }
                for (Image img : imgs) {
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

    public static void drawImage2D(Image image, int x, int y, int z) {
        if (image != null) {
            drawImage2D(image, x, y, z, image.getWidth(), image.getHeight());
        }
    }

    public static void drawImage2D(Image image, int x, int y, int z, int width, int height) {
        if (image != null) {
            drawImage2D(image, x, y, z, width, height, 256, 256, 256, 256);
        }
    }

    public static void drawImage2D(Image image, int x, int y, int z, int width, int height, int r, int g, int b, int a) {
        if (image != null) {
            GL11.glPushMatrix();
            GL11.glColor4f(r / 256F, g / 256F, g / 256F, a / 256F);
            image.getTexture().bind();
            GL11.glTranslatef(0, 0, z);
            GL11.glBegin(GL11.GL_QUADS);
            image.drawEmbedded(x, y, width, height);
            GL11.glEnd();
            GL11.glTranslatef(0, 0, -z);
            GL11.glPopMatrix();
        }
    }
}
