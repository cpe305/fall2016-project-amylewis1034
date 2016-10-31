package arcade.src.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Class that interprets the user's key input.
 * 
 * @author Amy Lewis.
 * @see KeyInput
 * @version 10/30/16
 */

public class KeyInput extends KeyAdapter {
  SpaceInvaders siGame;

  public KeyInput(SpaceInvaders siGame) {
    this.siGame = siGame;
  }

  public void keyPressed(KeyEvent event) {
    siGame.keyPressed(event);
  }

  public void keyReleased(KeyEvent event) {
    siGame.keyReleased(event);
  }
}
