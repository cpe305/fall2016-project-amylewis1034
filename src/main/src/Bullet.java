package arcade.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

/**
 * Class to create a bullet shot by the Player for the game, Space Invaders.
 * 
 * @author Amy Lewis.
 * @see Bullet
 * @version 10/30/16
 */
public class Bullet extends Collidable implements FriendlyCollideObjects {
  private static final Logger LOGGER = Logger.getLogger(Bullet.class.toString());
  public static final int BULLET_WIDTH = 4;
  public static final int BULLET_HEIGHT = 9;
  public static final int BULLET_SPEED = 10;

  private BufferedImage bullet;

  /**
   * Constructor for Bullet class that initializes the image for each bullet and puts it in the
   * correct position based on the position of the Player.
   * 
   * @param x1Coord the x coordinate of the Bullet based on the Player
   * @param y1Coord the y coordinate of the Bullet based on the Player
   */
  public Bullet(double x1Coord, double y1Coord) {
    super(x1Coord, y1Coord);

    BufferedImageLoader buffLoader = new BufferedImageLoader();
    try {
      bullet = buffLoader.loadImage("/bullet.png");
      bullet = buffLoader.createResizedCopy(bullet, BULLET_WIDTH, BULLET_HEIGHT);
    } catch (Exception ex) {
      LOGGER.log(null, "Could not load image.", ex);
    }
  }

  public int getWidth() {
    return BULLET_WIDTH;
  }

  public int getHeight() {
    return BULLET_HEIGHT;
  }

  public double getXCoord() {
    return x1Coord;
  }

  public double getYCoord() {
    return y1Coord;
  }

  public Rectangle getRectBounds() {
    return new Rectangle((int) x1Coord, (int) y1Coord, BULLET_WIDTH, BULLET_HEIGHT);
  }

  /**
   * Method that allows the bullet to move across the screen with constant velocity after being
   * fired.
   */
  public void tick() {
    y1Coord -= BULLET_SPEED;
  }

  public void render(Graphics graphics) {
    graphics.drawImage(bullet, (int) x1Coord, (int) y1Coord, null);
  }
}
