package arcade.src.main;

import arcade.src.main.ArcadeConcreteSubject.Arcade;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Class that handles mouse clicks by the user.
 * 
 * @author Amy Lewis.
 * @see MouseClickInput
 * @version 10/31/16
 */
public class MouseClickInput implements MouseListener {

  private static final int xButtonPosition = SpaceInvaders.WIDTH + 100;
  private static final int yButtonPosition = 950;
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

    if (SpaceInvaders.getSubject().getState() == Arcade.STARTMENU) {
      if (mouseX >= xButtonPosition && mouseX <= xButtonPosition + buttonWidth * 3) {
        if (mouseY >= yButtonPosition && mouseY <= yButtonPosition + buttonHeight) {
          SpaceInvaders.getSubject().setState(Arcade.SPACEINVADERS);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      if (mouseX >= xButtonPosition && mouseX <= xButtonPosition + buttonWidth * 2) {
        if (mouseY >= yButtonPosition + buttonHeight * 2
            && mouseY <= yButtonPosition + buttonHeight * 3) {
          SpaceInvaders.getSubject().setState(Arcade.SNAKE);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      if (mouseX >= xButtonPosition && mouseX <= xButtonPosition + buttonWidth) {
        if (mouseY >= yButtonPosition + buttonHeight * 4
            && mouseY <= yButtonPosition + buttonHeight * 5) {
          // add help stuff
        }
      }
      if (mouseX >= xButtonPosition && mouseX <= xButtonPosition + buttonWidth * 2) {
        if (mouseY >= yButtonPosition + buttonHeight * 6
            && mouseY <= yButtonPosition + buttonHeight * 7) {
          SpaceInvaders.getSubject().setState(Arcade.HIGHSCORES);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      if (mouseX >= xButtonPosition && mouseX <= xButtonPosition + buttonWidth) {
        if (mouseY >= yButtonPosition + buttonHeight * 8
            && mouseY <= yButtonPosition + buttonHeight * 9) {
          System.exit(1);
        }
      }
    } else if (SpaceInvaders.getSubject().getState() == Arcade.ENDSPACEGAMEMENU) {
      if (mouseX >= xButtonPosition && mouseX <= xButtonPosition + buttonWidth * 2) {
        if (mouseY >= yButtonPosition && mouseY <= yButtonPosition + buttonHeight) {
          SpaceInvaders.getSubject().setState(Arcade.SPACEINVADERS);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      if (mouseX >= xButtonPosition && mouseX <= xButtonPosition + buttonWidth * 3) {
        if (mouseY >= yButtonPosition + buttonHeight * 2
            && mouseY <= yButtonPosition + buttonHeight * 3) {
          SpaceInvaders.getSubject().setState(Arcade.STARTMENU);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      if (mouseX >= xButtonPosition && mouseX <= xButtonPosition + buttonWidth) {
        if (mouseY >= yButtonPosition + buttonHeight * 4
            && mouseY <= yButtonPosition + buttonHeight * 5) {
          System.exit(1);
        }
      }
    } else if (SpaceInvaders.getSubject().getState() == Arcade.ENDSNAKEMENU) {
      if (mouseX >= xButtonPosition && mouseX <= xButtonPosition + buttonWidth * 2) {
        if (mouseY >= yButtonPosition && mouseY <= yButtonPosition + buttonHeight) {
          SpaceInvaders.getSubject().setState(Arcade.SNAKE);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      if (mouseX >= xButtonPosition && mouseX <= xButtonPosition + buttonWidth * 3) {
        if (mouseY >= yButtonPosition + buttonHeight * 2
            && mouseY <= yButtonPosition + buttonHeight * 3) {
          SpaceInvaders.getSubject().setState(Arcade.STARTMENU);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      if (mouseX >= xButtonPosition && mouseX <= xButtonPosition + buttonWidth) {
        if (mouseY >= yButtonPosition + buttonHeight * 4
            && mouseY <= yButtonPosition + buttonHeight * 5) {
          System.exit(1);
        }
      }
    } else if (SpaceInvaders.getSubject().getState() == Arcade.HIGHSCORES) {
      if (mouseX >= xButtonPosition && mouseX <= xButtonPosition + buttonWidth * 3) {
        if (mouseY >= yButtonPosition + buttonHeight * 6
            && mouseY <= yButtonPosition + buttonHeight * 7) {
          SpaceInvaders.getSubject().setState(Arcade.STARTMENU);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      if (mouseX >= xButtonPosition && mouseX <= xButtonPosition + buttonWidth) {
        if (mouseY >= yButtonPosition + buttonHeight * 8
            && mouseY <= yButtonPosition + buttonHeight * 9) {
          System.exit(1); //NOSONAR
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
