package arcade.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

/**
 * Class to create a player for the game, Space Invaders.
 * 
 * @author Amy Lewis.
 * @see Player
 * @version 10/30/16
 */
public class Player extends Collidable implements FriendlyCollideObjects {
  private static final Logger LOGGER = Logger.getLogger(Player.class.toString());
  public static final int PLAYER_WIDTH = 40;
  public static final int PLAYER_HEIGHT = 50;

  private double x1Vel = 0.0;
  private double y1Vel = 0.0;
  private SpaceInvaders siGame;

  private BufferedImage spaceship;

  /**
   * Constructor for Player class that loads an image for a player and puts it in the correct
   * initial position.
   * 
   * @param x1Coord the initial x position for the player
   * @param y1Coord the initial y position for the player
   * @param siGame a reference to the Space Invaders game
   */
  public Player(double x1Coord, double y1Coord, SpaceInvaders siGame) {
    super(x1Coord, y1Coord);

    this.siGame = siGame;

    BufferedImageLoader buffLoader = new BufferedImageLoader();
    try {
      spaceship = buffLoader.loadImage("/spaceship.png");
      spaceship = buffLoader.createResizedCopy(spaceship, PLAYER_WIDTH, PLAYER_HEIGHT);
    } catch (Exception ex) {
      LOGGER.log(null, "Could not load image.", ex);
    }
  }

  public int getWidth() {
    return PLAYER_WIDTH;
  }

  public int getHeight() {
    return PLAYER_HEIGHT;
  }

  public double getXCoord() {
    return x1Coord;
  }

  public void setXCoord(double x1Coord) {
    this.x1Coord = x1Coord;
  }

  public void setXVel(double x1Vel) {
    this.x1Vel = x1Vel;
  }

  public double getYCoord() {
    return y1Coord;
  }

  public void setYCoord(double y1Coord) {
    this.y1Coord = y1Coord;
  }

  public void setYVel(double y1Vel) {
    this.y1Vel = y1Vel;
  }

  public Rectangle getRectBounds() {
    return new Rectangle((int) x1Coord, (int) y1Coord, PLAYER_WIDTH, PLAYER_HEIGHT);
  }

  /**
   * Method that allows the player to move fluidly by increasing its velocity and also keeps the
   * player within the bounds of the game.
   */
  public void tick() {
    x1Coord += x1Vel;
    y1Coord += y1Vel;

    if (x1Coord <= 0) {
      x1Coord = 0;
    }
    if (x1Coord >= siGame.getWidth() - PLAYER_WIDTH) {
      x1Coord = (double) siGame.getWidth() - PLAYER_WIDTH;
    }
    if (y1Coord <= 0) {
      y1Coord = 0;
    }
    if (y1Coord >= siGame.getHeight() - PLAYER_HEIGHT) {
      y1Coord = (double) siGame.getHeight() - PLAYER_HEIGHT;
    }
  }

  public void render(Graphics graphics) {
    graphics.drawImage(spaceship, (int) x1Coord, (int) y1Coord, null);
  }
}
