package arcade.src.main;

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
public class StartMenu {

  private static final int xButtonPosition = SpaceInvaders.WIDTH / 2 + 120;
  private static final int yButtonPosition = 150;
  private static final int buttonWidth = 100;
  private static final int buttonHeight = 50; 

  public Rectangle playSpaceButton =
      new Rectangle(xButtonPosition, yButtonPosition, buttonWidth * 4, buttonHeight);
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
    Font fnt = new Font("arial", Font.BOLD, 50);
    graphics.setFont(fnt);
    graphics.setColor(Color.white);
    graphics.drawString("Dad's Arcade", SpaceInvaders.WIDTH / 2, 100);

    Font fntButton = new Font("arial", Font.BOLD, 30);
    graphics.setFont(fntButton);
    graphics.drawString("Play Space Invaders", playSpaceButton.x + 15, playSpaceButton.y + 30);
    ((Graphics2D) graphics).draw(playSpaceButton);
    graphics.drawString("Help", helpButton.x + 15, helpButton.y + 30);
    ((Graphics2D) graphics).draw(helpButton);
    graphics.drawString("Exit", exitButton.x + 15, exitButton.y + 30);
    ((Graphics2D) graphics).draw(exitButton);
  }

  public static void main(String[] args) {


  }

}
