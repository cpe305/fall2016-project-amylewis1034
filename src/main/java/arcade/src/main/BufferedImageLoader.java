package arcade.src.main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

/**
 * Class that loads images for the game, Space Invaders.
 * 
 * @author Amy Lewis.
 * @see BufferedImageLoader
 * @version 10/30/16
 *
 */
public class BufferedImageLoader {
  private static final Logger LOGGER = Logger.getLogger(BufferedImageLoader.class.toString());
  private BufferedImage bufferedImage;

  /**
   * Method to load images from the resource folder.
   * 
   * @param path the location of the image in the resource folder
   * @return the image from the resource folder
   */
  public BufferedImage loadImage(String path) {
    try {
      bufferedImage = ImageIO.read(getClass().getResource(path));
    } catch (Exception ex) {
      LOGGER.warning(ex.toString());
    }
    return bufferedImage;
  }

  /**
   * Method to resize the image so it fits in the scene.
   * 
   * @param originalImage the image before it is resized
   * @param scaledWidth the desired width
   * @param scaledHeight the desired height
   * @return the image after being resized
   */
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
