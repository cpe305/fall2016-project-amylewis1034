package arcade.src.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import arcade.src.main.ArcadeConcreteSubject.Arcade;

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

  /**
   * Takes the user's inputed button click and processes that command.
   * 
   * @param moEvent gets the user's input mouse event
   */
  public void mousePressed(MouseEvent moEvent) {
    int mouseX = moEvent.getX();
    int mouseY = moEvent.getY();

    if (SpaceInvaders.getSubject().getState() == Arcade.STARTMENU) {
      // Buttons for Start Menu
      // Play Space Invaders Buttons
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH * 3) {
        if (mouseY >= Y_BUTTON_POS && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT) {
          SpaceInvaders.getSubject().setState(Arcade.SPACEINVADERS);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      // Play Snake Button
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH * 2) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 2
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 3) {
          SpaceInvaders.getSubject().setState(Arcade.SNAKE);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      // Play Breakout Button
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH * 3) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 4
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 5) {
          SpaceInvaders.getSubject().setState(Arcade.BREAKOUT);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      // Help Button
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 6
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 7) {
          // add help stuff
        }
      }
      // Highscores Button
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH * 2) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 8
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 9) {
          SpaceInvaders.getSubject().setState(Arcade.HIGHSCORES);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      // Exit Button
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 10
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 11) {
          System.exit(1); // NOSONAR
        }
      }
    } else if (SpaceInvaders.getSubject().getState() == Arcade.ENDSPACEGAMEMENU) {
      // Buttons for End Space Invaders Menu
      // Play Space Invaders Again Button
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH * 3) {
        if (mouseY >= Y_BUTTON_POS && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT) {
          SpaceInvaders.getSubject().setState(Arcade.SPACEINVADERS);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      // Go back to Start Menu Button
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH * 3) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 2
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 3) {
          SpaceInvaders.getSubject().setState(Arcade.STARTMENU);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      // Exit Button
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 4
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 5) {
          System.exit(1); // NOSONAR
        }
      }
    } else if (SpaceInvaders.getSubject().getState() == Arcade.ENDSNAKEMENU) {
      // Buttons for End Snake Menu
      // Play Snake Again Button
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH * 3) {
        if (mouseY >= Y_BUTTON_POS && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT) {
          SpaceInvaders.getSubject().setState(Arcade.SNAKE);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      // Go back to Start Menu Button
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH * 3) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 2
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 3) {
          SpaceInvaders.getSubject().setState(Arcade.STARTMENU);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      // Exit Button
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 4
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 5) {
          System.exit(1); // NOSONAR
        }
      }
    } else if (SpaceInvaders.getSubject().getState() == Arcade.ENDBREAKOUTMENU) {
      // Buttons for End Breakout Menu
      // Play Snake Again Button
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH * 3) {
        if (mouseY >= Y_BUTTON_POS && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT) {
          SpaceInvaders.getSubject().setState(Arcade.BREAKOUT);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      // Go back to Start Menu Button
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH * 3) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 2
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 3) {
          SpaceInvaders.getSubject().setState(Arcade.STARTMENU);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      // Exit Button
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 4
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 5) {
          System.exit(1); // NOSONAR
        }
      }
    } else if (SpaceInvaders.getSubject().getState() == Arcade.HIGHSCORES) {
      // Buttons for Highscore Menu
      // Go back to Start Menu Button
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH * 3) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 8
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 9) {
          SpaceInvaders.getSubject().setState(Arcade.STARTMENU);
          SpaceInvaders.getSubject().notifyObservers();
        }
      }
      // Exit Button
      if (mouseX >= X_BUTTON_POS && mouseX <= X_BUTTON_POS + BUTTON_WIDTH) {
        if (mouseY >= Y_BUTTON_POS + BUTTON_HEIGHT * 10
            && mouseY <= Y_BUTTON_POS + BUTTON_HEIGHT * 11) {
          System.exit(1); // NOSONAR
        }
      }
    }
  }

  public void mouseClicked(MouseEvent moEvent) {}

  public void mouseReleased(MouseEvent moEvent) {}

  public void mouseEntered(MouseEvent moEvent) {}

  public void mouseExited(MouseEvent moEvent) {}

}
