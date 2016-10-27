package arcade.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface CollideObjectA {

  public void tick();

  public void render(Graphics graphics);

  public double getXCoord();

  public double getYCoord();
  
  public Rectangle getRectBounds();
}
