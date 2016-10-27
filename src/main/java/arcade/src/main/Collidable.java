package arcade.src.main;

import java.awt.Rectangle;

public class Collidable {
  public double x1Coord, y1Coord;
  
  public Collidable(double x1Coord, double y1Coord) {
    this.x1Coord = x1Coord;
    this.y1Coord = y1Coord;
  }
  
  public Rectangle getRectBounds(int width, int height) {
    return new Rectangle((int) x1Coord, (int) y1Coord, width, height);
  } 
}
