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
 * @version 10/31/16
 */
public class EndBreakoutMenu implements ArcadeObserver {

  private static final int X_BUTTON_POS = SpaceInvaders.WIDTH + 100;
  private static final int Y_BUTTON_POS = 950;
  private static final int BUTTON_WIDTH = 100;
  private static final int BUTTON_HEIGHT = 50;
  private ArcadeConcreteSubject subject;
  private Arcade state;

  private Rectangle playBreakoutAgainButton =
      new Rectangle(X_BUTTON_POS, Y_BUTTON_POS, BUTTON_WIDTH * 3, BUTTON_HEIGHT);
  private Rectangle startMenuButton =
      new Rectangle(X_BUTTON_POS, Y_BUTTON_POS + BUTTON_WIDTH, BUTTON_WIDTH * 3, BUTTON_HEIGHT);
  private Rectangle exitButton =
      new Rectangle(X_BUTTON_POS, Y_BUTTON_POS + 2 * BUTTON_WIDTH, BUTTON_WIDTH, BUTTON_HEIGHT);

  public EndBreakoutMenu(ArcadeConcreteSubject subject) {
    this.subject = subject;
    state = subject.getState();
  }

  /**
   * Method that calls draws the Start Menu for the arcade.
   * 
   * @param graphics is a reference to the Java graphics class
   */
  public void render(Graphics graphics) {
    if (state == Arcade.ENDBREAKOUTMENU) {
      Font fnt = new Font("arial", Font.BOLD, 150);
      graphics.setFont(fnt);
      graphics.setColor(Color.white);
      graphics.drawString("Game Over", 500, BUTTON_WIDTH * 3);
      Font fntButton = new Font("arial", Font.BOLD, 30);
      graphics.setFont(fntButton);
      graphics.drawString("Exit", exitButton.x + 15, exitButton.y + 35);
      ((Graphics2D) graphics).draw(exitButton);
      graphics.drawString("Play Breakout Again", playBreakoutAgainButton.x + 5,
          playBreakoutAgainButton.y + 35);
      ((Graphics2D) graphics).draw(playBreakoutAgainButton);
      graphics.drawString("Go To Start Menu", startMenuButton.x + 10, startMenuButton.y + 35);
      ((Graphics2D) graphics).draw(startMenuButton);
    }
  }

  public void update() {
    state = subject.getState();
  }
}
