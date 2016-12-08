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
  BreakoutGrid breakoutGame = null;

  public KeyInput(SpaceInvaders siGame) {
    this.siGame = siGame;
  }

  public KeyInput(SnakeGrid snakeGame) {
    this.snakeGame = snakeGame;
  }
  
  public KeyInput(BreakoutGrid breakoutGame) {
    this.breakoutGame = breakoutGame;
  }

  /**
   * Activates keyPressed for specific game in progress. For each game it gives the user control
   * with key events.
   */
  public void keyPressed(KeyEvent event) {
    if (siGame != null) {
      siGame.keyPressed(event);
    }
    if (snakeGame != null) {
      snakeGame.keyPressed(event);
    }
    if (breakoutGame != null) {
      breakoutGame.keyPressed(event);
    }
  }

  /**
   * Notifies when a user has released a key.
   */
  public void keyReleased(KeyEvent event) {
    if (siGame != null) {
      siGame.keyReleased(event);
    }
    if (breakoutGame != null) {
      breakoutGame.keyReleased(event);
    }
  }
}
