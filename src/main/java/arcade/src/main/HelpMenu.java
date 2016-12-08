package arcade.src.main;

import arcade.src.main.ArcadeConcreteSubject.Arcade;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

/**
 * Class that creates the High Score Menu for the arcade and is the first thing the user sees.
 * 
 * @author Amy Lewis.
 * @see StartMenu
 * @version 11/17/16
 */
public class HelpMenu implements ArcadeObserver {
  private static final Logger LOGGER = Logger.getLogger(SpaceInvaders.class.toString());
  private static final int X_BUTTON_POS = SpaceInvaders.WIDTH + 100;
  private static final int Y_BUTTON_POS = 1350;
  private static final int X_IMAGE_SIZE = 950;
  private static final int Y_IMAGE_SIZE = 400;
  private static final int BUTTON_WIDTH = 100;
  private static final int BUTTON_HEIGHT = 50;
  private static final int TITLE_POSITION = 25;
  private static final int X_RULES_POS = 1025;
  private static final int Y_RULES_POS = 300;
  private static final int SPACE_POS = 800;
  private ArcadeConcreteSubject subject;
  private Arcade state;

  private transient BufferedImage spaceHelp = null;
  private transient BufferedImage snakeHelp = null;
  private transient BufferedImage breakoutHelp = null;

  private Rectangle startMenuButton =
      new Rectangle(X_BUTTON_POS, Y_BUTTON_POS, BUTTON_WIDTH * 3, BUTTON_HEIGHT);
  private Rectangle exitButton =
      new Rectangle(X_BUTTON_POS, Y_BUTTON_POS + BUTTON_WIDTH, BUTTON_WIDTH, BUTTON_HEIGHT);

  /**
   * Constructor for Help Menu.
   * 
   * @param subject Help Menu is an observer so it takes a subject
   */
  public HelpMenu(ArcadeConcreteSubject subject) {
    this.subject = subject;
    state = subject.getState();

    BufferedImageLoader buffLoader = new BufferedImageLoader();
    try {
      spaceHelp = buffLoader.loadImage("/HelpSpace.PNG");
      spaceHelp = buffLoader.createResizedCopy(spaceHelp, X_IMAGE_SIZE, Y_IMAGE_SIZE);
      snakeHelp = buffLoader.loadImage("/HelpSnake.PNG");
      snakeHelp = buffLoader.createResizedCopy(snakeHelp, X_IMAGE_SIZE, Y_IMAGE_SIZE);
      breakoutHelp = buffLoader.loadImage("/HelpBreakout.PNG");
      breakoutHelp = buffLoader.createResizedCopy(breakoutHelp, X_IMAGE_SIZE, Y_IMAGE_SIZE);
    } catch (Exception ex) {
      LOGGER.log(null, "Could not load image.", ex);
    }
  }

  /**
   * Method that calls draws the Start Menu for the arcade.
   * 
   * @param graphics is a reference to the Java graphics class
   */
  public void render(Graphics graphics) {
    if (state == Arcade.HELP) {
      Font fnt = new Font("arial", Font.BOLD, 200);
      graphics.setFont(fnt);
      graphics.setColor(Color.WHITE);
      graphics.drawString("Help", SpaceInvaders.HEIGHT + TITLE_POSITION, 200);

      Font fntButton = new Font("arial", Font.BOLD, 30);
      graphics.setFont(fntButton);

      graphics.drawImage(snakeHelp, TITLE_POSITION, Y_RULES_POS, null);
      graphics.drawImage(breakoutHelp, X_RULES_POS, Y_RULES_POS, null);
      graphics.drawImage(spaceHelp, Y_RULES_POS * 2, SPACE_POS, null);

      graphics.drawString("Go To Start Menu", startMenuButton.x + 10, startMenuButton.y + 35);
      ((Graphics2D) graphics).draw(startMenuButton);
      graphics.drawString("Exit", exitButton.x + 15, exitButton.y + 35);
      ((Graphics2D) graphics).draw(exitButton);
    }
  }

  /**
   * Updates observer if the state has been changed.
   */
  public void update() {
    state = subject.getState();
  }
}
