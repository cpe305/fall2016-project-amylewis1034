package arcade.src.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
