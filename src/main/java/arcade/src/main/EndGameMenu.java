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
public class EndGameMenu implements ArcadeObserver {

  private static final int xButtonPosition = SpaceInvaders.WIDTH / 2;
  private static final int yButtonPosition = 150;
  private static final int buttonWidth = 100;
  private static final int buttonHeight = 50; 
  public ArcadeConcreteSubject subject  = new ArcadeConcreteSubject();;
  
  private Arcade state = Arcade.ENDGAMEMENU;

  private Rectangle playAgainButton =
      new Rectangle(xButtonPosition, yButtonPosition, buttonWidth * 2, buttonHeight);
  private Rectangle startMenuButton =
      new Rectangle(xButtonPosition, yButtonPosition + buttonWidth, buttonWidth * 3, buttonHeight);
  private Rectangle exitButton =
      new Rectangle(xButtonPosition, yButtonPosition + 2 * buttonWidth, buttonWidth, buttonHeight);
  
  public void init() {
    subject = new ArcadeConcreteSubject();
    subject.registerObservers(this);
  }
 
  /**
   * Method that calls draws the Start Menu for the arcade.
   * 
   * @param graphics is a reference to the Java graphics class
   */
  public void render(Graphics graphics) {   
    if (state == Arcade.ENDGAMEMENU) {
      Font fnt = new Font("arial", Font.BOLD, 50);
      graphics.setFont(fnt);
      graphics.setColor(Color.white);
      graphics.drawString("Game Over", SpaceInvaders.WIDTH / 2, 100);
  
      Font fntButton = new Font("arial", Font.BOLD, 30);
      graphics.setFont(fntButton);
      graphics.drawString("Play Again", playAgainButton.x + 15, playAgainButton.y + 35);
      ((Graphics2D) graphics).draw(playAgainButton);
      graphics.drawString("Go To Start Menu", startMenuButton.x + 10, startMenuButton.y + 35);
      ((Graphics2D) graphics).draw(startMenuButton);
      graphics.drawString("Exit", exitButton.x + 15, exitButton.y + 35);
      ((Graphics2D) graphics).draw(exitButton);
    }
  }

  public void update() {
    state = subject.getState();
  }
}
