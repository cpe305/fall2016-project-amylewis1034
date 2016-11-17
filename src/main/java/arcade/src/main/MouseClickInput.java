package arcade.src.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class that runs the game, Space Invaders.
 * 
 * @author Amy Lewis.
 * @see MouseClickInput
 * @version 10/31/16
 */
public class MouseClickInput implements MouseListener {

  private static final int xButtonPosition = SpaceInvaders.WIDTH / 2 + 120;
  private static final int yButtonPosition = 150;
  private static final int buttonWidth = 100;
  private static final int buttonHeight = 50;

  public void mouseClicked(MouseEvent moEvent) {
    // TODO Auto-generated method stub

  }

  /**
   * Takes the user's inputed button click and processes that command.
   * 
   * @param moEvent gets the user's input mouse event
   */
  public void mousePressed(MouseEvent moEvent) {
    int mouseX = moEvent.getX();
    int mouseY = moEvent.getY();
    /*
     * public Rectangle playSpaceButton = new Rectangle(xButtonPosition, yButtonPosition,
     * buttonWidth, buttonHeight); public Rectangle helpButton = new Rectangle(xButtonPosition,
     * yButtonPosition + buttonWidth, buttonWidth, buttonHeight); public Rectangle exitButton = new
     * Rectangle(xButtonPosition, yButtonPosition + 2 * buttonWidth, buttonWidth, buttonHeight);
     */
    if (SpaceInvaders.arcade == Arcade.STARTMENU) {
      if (mouseX >= xButtonPosition && mouseX <= xButtonPosition + buttonWidth * 4) {
        if (mouseY >= yButtonPosition && mouseY <= yButtonPosition + buttonHeight) {
          SpaceInvaders.arcade = Arcade.SPACEINVADERS;
        }
      }

      if (mouseX >= xButtonPosition && mouseX <= xButtonPosition + buttonWidth) {
        if (mouseY >= yButtonPosition + buttonHeight * 4
            && mouseY <= yButtonPosition + buttonHeight * 5) {
          System.exit(1);
        }
      }
    } else if (SpaceInvaders.arcade == Arcade.ENDGAMEMENU) {
      if (mouseX >= xButtonPosition && mouseX <= xButtonPosition + buttonWidth * 2) {
        if (mouseY >= yButtonPosition && mouseY <= yButtonPosition + buttonHeight) {
          SpaceInvaders.arcade = Arcade.SPACEINVADERS;
        }
      }
      if (mouseX >= xButtonPosition && mouseX <= xButtonPosition + buttonWidth * 3) {
        if (mouseY >= yButtonPosition + buttonHeight * 2
            && mouseY <= yButtonPosition + buttonHeight * 3) {
          SpaceInvaders.arcade = Arcade.STARTMENU;
        }
      }
      if (mouseX >= xButtonPosition && mouseX <= xButtonPosition + buttonWidth) {
        if (mouseY >= yButtonPosition + buttonHeight * 4
            && mouseY <= yButtonPosition + buttonHeight * 5) {
          System.exit(1);
        }
      }
    }
  }

  public void mouseReleased(MouseEvent moEvent) {
    // TODO Auto-generated method stub

  }

  public void mouseEntered(MouseEvent moEvent) {
    // TODO Auto-generated method stub

  }

  public void mouseExited(MouseEvent moEvent) {
    // TODO Auto-generated method stub

  }

}
