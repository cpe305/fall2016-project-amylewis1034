package arcade.src.main;

import java.awt.Rectangle;

/**
 * Class that get the bounds of a rectangle surrounding each collide-able object.
 * 
 * @author Amy Lewis.
 * @see Collidable
 * @version 10/30/16
 */

public class Collidable {

  public double x1Coord;
  public double y1Coord;

  public Collidable(double x1Coord, double y1Coord) {
    this.x1Coord = x1Coord;
    this.y1Coord = y1Coord;
  }

  public Rectangle getRectBounds(int width, int height) {
    return new Rectangle((int) x1Coord, (int) y1Coord, width, height);
  }
}
