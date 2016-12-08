package arcade.src.main;

import arcade.src.main.ArcadeConcreteSubject.Arcade;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Class that creates the Start Menu for the arcade and is the first thing the user sees.
 * 
 * @author Amy Lewis.
 * @see StartMenu
 * @version 10/31/16
 */
public class StartMenu implements ArcadeObserver {

  private static final int X_BUTTON_POS = SpaceInvaders.WIDTH + 100;
  private static final int Y_BUTTON_POS = 950;
  private static final int BUTTON_WIDTH = 100;
  private static final int BUTTON_HEIGHT = 50;
  private ArcadeConcreteSubject subject;
  private Arcade state;

  private Rectangle playSpaceButton =
      new Rectangle(X_BUTTON_POS, Y_BUTTON_POS, BUTTON_WIDTH * 3, BUTTON_HEIGHT);
  private Rectangle playSnakeButton =
      new Rectangle(X_BUTTON_POS, Y_BUTTON_POS + BUTTON_WIDTH, BUTTON_WIDTH * 2, BUTTON_HEIGHT);
  private Rectangle playBreakoutButton =
      new Rectangle(X_BUTTON_POS, Y_BUTTON_POS + 2 * BUTTON_WIDTH, BUTTON_WIDTH * 2, BUTTON_HEIGHT);
  private Rectangle helpButton =
      new Rectangle(X_BUTTON_POS, Y_BUTTON_POS + 3 * BUTTON_WIDTH, BUTTON_WIDTH, BUTTON_HEIGHT);
  private Rectangle highscoreButton =
      new Rectangle(X_BUTTON_POS, Y_BUTTON_POS + 4 * BUTTON_WIDTH, BUTTON_WIDTH * 2, BUTTON_HEIGHT);
  private Rectangle exitButton =
      new Rectangle(X_BUTTON_POS, Y_BUTTON_POS + 5 * BUTTON_WIDTH, BUTTON_WIDTH, BUTTON_HEIGHT);

  public StartMenu(ArcadeConcreteSubject subject) {
    this.subject = subject;
    state = subject.getState();
  }

  /**
   * Method that calls draws the Start Menu for the arcade.
   * 
   * @param graphics is a reference to the Java graphics class
   */
  public void render(Graphics graphics) {
    if (state == Arcade.STARTMENU) {
      Font fnt = new Font("arial", Font.BOLD, 200);
      graphics.setFont(fnt);
      graphics.setColor(Color.white);
      graphics.drawString("Dad's Arcade", SpaceInvaders.WIDTH / 2, 200);

      Font fntButton = new Font("arial", Font.BOLD, 30);
      graphics.setFont(fntButton);
      graphics.drawString("Play Space Invaders", playSpaceButton.x + 5, playSpaceButton.y + 35);
      ((Graphics2D) graphics).draw(playSpaceButton);
      graphics.drawString("Play Snake", playSnakeButton.x + 15, playSnakeButton.y + 35);
      ((Graphics2D) graphics).draw(playSnakeButton);
      fntButton = new Font("arial", Font.BOLD, 28);
      graphics.setFont(fntButton);
      graphics.drawString("Play Breakout", playBreakoutButton.x + 5, playBreakoutButton.y + 35);
      ((Graphics2D) graphics).draw(playBreakoutButton);
      fntButton = new Font("arial", Font.BOLD, 30);
      graphics.setFont(fntButton);
      graphics.drawString("Help", helpButton.x + 20, helpButton.y + 35);
      ((Graphics2D) graphics).draw(helpButton);
      graphics.drawString("High Scores", highscoreButton.x + 10, highscoreButton.y + 35);
      ((Graphics2D) graphics).draw(highscoreButton);
      graphics.drawString("Exit", exitButton.x + 20, exitButton.y + 35);
      ((Graphics2D) graphics).draw(exitButton);
    }
  }

  public void update() {
    state = subject.getState();
  }
}
