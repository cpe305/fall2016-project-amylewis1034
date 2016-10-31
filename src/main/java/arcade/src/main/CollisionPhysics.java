package arcade.src.main;

import java.util.LinkedList;

/**
 * Class that calculates when a collision between friendly and enemy objects occurs.
 * 
 * @author Amy Lewis.
 * @see SpaceInvaders
 * @version 10/30/16
 */

public class CollisionPhysics {

  /**
   * The method that calculates when a collision between friendly and enemy objects occurs.
   * 
   * @param collideObjA a friendly collide-able object
   * @param objListB a list of enemy collide-able objects that collideObjA could have collided with
   * @return true if there was a friendly/enemy collision, false otherwise
   */
  public static boolean isCollision(CollideObjectA collideObjA,
      LinkedList<CollideObjectB> objListB) {
    for (int i = 0; i < objListB.size(); i++) {
      if (collideObjA.getRectBounds().intersects(objListB.get(i).getRectBounds())) {
        return true;
      }
    }
    return false;
  }
}
