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

  private static final int xButtonPosition = SpaceInvaders.WIDTH / 2;
  private static final int yButtonPosition = 150;
  private static final int buttonWidth = 100;
  private static final int buttonHeight = 50; 
  public ArcadeConcreteSubject subject  = new ArcadeConcreteSubject();;
  
  private Arcade state = Arcade.STARTMENU;

  public Rectangle playSpaceButton =
      new Rectangle(xButtonPosition, yButtonPosition, buttonWidth * 3, buttonHeight);
  public Rectangle helpButton =
      new Rectangle(xButtonPosition, yButtonPosition + buttonWidth, buttonWidth, buttonHeight);
  public Rectangle exitButton =
      new Rectangle(xButtonPosition, yButtonPosition + 2 * buttonWidth, buttonWidth, buttonHeight);
 
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
      graphics.drawString("Dad's Arcade", SpaceInvaders.WIDTH / 2, 100);
  
      Font fntButton = new Font("arial", Font.BOLD, 30);
      graphics.setFont(fntButton);
      graphics.drawString("Play Space Invaders", playSpaceButton.x + 5, playSpaceButton.y + 35);
      ((Graphics2D) graphics).draw(playSpaceButton);
      graphics.drawString("Help", helpButton.x + 20, helpButton.y + 35);
      ((Graphics2D) graphics).draw(helpButton);
      graphics.drawString("Exit", exitButton.x + 20, exitButton.y + 35);
      ((Graphics2D) graphics).draw(exitButton);
    }
  }
  
  public void update() {
    state = subject.getState();
  }
}
