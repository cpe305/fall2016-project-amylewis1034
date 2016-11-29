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

  private static final int X_BUTTON_POS = SpaceInvaders.WIDTH + 100;
  private static final int Y_BUTTON_POS = 950;
  private static final int BUTTON_WIDTH = 100;
  private static final int BUTTON_HEIGHT = 50;

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
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH * 3) {
        if (mouseY >= Y_BUTTON_POS && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT) {
          SpaceInvaders.getSubject().setState(Arcade.SPACEINVADERS);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH * 2) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 2
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 3) {
          SpaceInvaders.getSubject().setState(Arcade.SNAKE);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 4
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 5) {
          // add help stuff
        }
      }
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH * 2) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 6
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 7) {
          SpaceInvaders.getSubject().setState(Arcade.HIGHSCORES);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 8
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 9) {
          System.exit(1); //NOSONAR
        }
      }
    } else if (SpaceInvaders.getSubject().getState() == Arcade.ENDSPACEGAMEMENU) {
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH * 2) {
        if (mouseY >= Y_BUTTON_POS && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT) {
          SpaceInvaders.getSubject().setState(Arcade.SPACEINVADERS);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH * 3) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 2
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 3) {
          SpaceInvaders.getSubject().setState(Arcade.STARTMENU);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 4
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 5) {
          System.exit(1); //NOSONAR
        }
      }
    } else if (SpaceInvaders.getSubject().getState() == Arcade.ENDSNAKEMENU) {
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH * 2) {
        if (mouseY >= Y_BUTTON_POS && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT) {
          SpaceInvaders.getSubject().setState(Arcade.SNAKE);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH * 3) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 2
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 3) {
          SpaceInvaders.getSubject().setState(Arcade.STARTMENU);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 4
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 5) {
          System.exit(1); //NOSONAR
        }
      }
    } else if (SpaceInvaders.getSubject().getState() == Arcade.HIGHSCORES) {
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH * 3) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 6
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 7) {
          SpaceInvaders.getSubject().setState(Arcade.STARTMENU);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 8
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 9) {
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
