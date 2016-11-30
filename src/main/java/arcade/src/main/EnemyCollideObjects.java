package arcade.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
* Interface for enemy collide-able objects in the game, Space Invaders.
* 
* @author Amy Lewis.
* @see EnemyCollideObjects
* @version 10/30/16
*/
public interface EnemyCollideObjects {

  public void tick();

  public void render(Graphics graphics);

  public double getXCoord();

  public double getYCoord();
  
  public Rectangle getRectBounds();
}
