package arcade.src.main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class BufferedImageLoader {

  private BufferedImage bufferedImage;

  public BufferedImage loadImage(String path) {
    try {
      bufferedImage = ImageIO.read(getClass().getResource(path));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return bufferedImage;
  }

  public BufferedImage createResizedCopy(BufferedImage originalImage, int scaledWidth,
      int scaledHeight) {
    BufferedImage scaledImage =
        new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D graphics = scaledImage.createGraphics();

    graphics.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
    graphics.dispose();

    return scaledImage;
  }
}
