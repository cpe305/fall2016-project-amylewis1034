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

  private static final int xButtonPosition = SpaceInvaders.WIDTH + 100;
  private static final int yButtonPosition = 950;
  private static final int buttonWidth = 100;
  private static final int buttonHeight = 50;
  private ArcadeConcreteSubject subject;
  private Arcade state;

  public Rectangle playSpaceButton =
      new Rectangle(xButtonPosition, yButtonPosition, buttonWidth * 3, buttonHeight);
  public Rectangle playSnakeButton =
      new Rectangle(xButtonPosition, yButtonPosition + buttonWidth, buttonWidth * 2, buttonHeight);
  public Rectangle helpButton =
      new Rectangle(xButtonPosition, yButtonPosition + 2 * buttonWidth, buttonWidth, buttonHeight);
  public Rectangle highScoreButton = new Rectangle(xButtonPosition,
      yButtonPosition + 3 * buttonWidth, buttonWidth * 2, buttonHeight);
  public Rectangle exitButton =
      new Rectangle(xButtonPosition, yButtonPosition + 4 * buttonWidth, buttonWidth, buttonHeight);

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
      Font fnt = new Font("arial", Font.BOLD, 50);
      graphics.setFont(fnt);
      graphics.setColor(Color.white);
      graphics.drawString("Dad's Arcade", xButtonPosition, 850);

      Font fntButton = new Font("arial", Font.BOLD, 30);
      graphics.setFont(fntButton);
      graphics.drawString("Play Space Invaders", playSpaceButton.x + 5, playSpaceButton.y + 35);
      ((Graphics2D) graphics).draw(playSpaceButton);
      graphics.drawString("Play Snake", playSnakeButton.x + 5, playSnakeButton.y + 35);
      ((Graphics2D) graphics).draw(playSnakeButton);
      graphics.drawString("Help", helpButton.x + 20, helpButton.y + 35);
      ((Graphics2D) graphics).draw(helpButton);
      graphics.drawString("High Scores", highScoreButton.x + 10, highScoreButton.y + 35);
      ((Graphics2D) graphics).draw(highScoreButton);
      graphics.drawString("Exit", exitButton.x + 20, exitButton.y + 35);
      ((Graphics2D) graphics).draw(exitButton);
    }
  }

  public void update() {
    state = subject.getState();
  }
}
