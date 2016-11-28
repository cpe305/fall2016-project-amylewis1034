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
  SpaceInvaders siGame = null;
  SnakeGrid snakeGame = null;

  public KeyInput(SpaceInvaders siGame) {
    this.siGame = siGame;
  }
  
  public KeyInput(SnakeGrid snakeGame) {
    this.snakeGame = snakeGame;
  }

  public void keyPressed(KeyEvent event) {
    if (siGame != null) {
      siGame.keyPressed(event);
    }
    if (snakeGame != null) {
      snakeGame.keyPressed(event);
    }
  }

  public void keyReleased(KeyEvent event) {
    if (siGame != null) {
      siGame.keyReleased(event);
    }
  }
}
