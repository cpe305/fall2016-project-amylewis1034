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
   * The method that calculates when a collision between friendly and a list of enemy objects
   * occurs.
   * 
   * @param collideObjA a friendly collide-able object
   * @param objListB a list of enemy collide-able objects that collideObjA could have collided with
   * @return true if there was a friendly/enemy collision, false otherwise
   */
  public static boolean isCollisionA(CollideObjectA collideObjA,
      LinkedList<CollideObjectB> objListB) {
    for (int i = 0; i < objListB.size(); i++) {
      if (collideObjA.getRectBounds().intersects(objListB.get(i).getRectBounds())) {
        return true;
      }
    }
    return false;
  }

  /**
   * The method that calculates when a collision between enemy and a list of friendly objects
   * occurs.
   * 
   * @param collideObjB a enemy collide-able object
   * @param objListA a list of friendly collide-able objects that collideObjB could have collided
   *        with
   * @return true if there was a friendly/enemy collision, false otherwise
   */
  public static boolean isCollisionB(CollideObjectB collideObjB,
      LinkedList<CollideObjectA> objListA) {
    for (int i = 0; i < objListA.size(); i++) {
      if (collideObjB.getRectBounds().intersects(objListA.get(i).getRectBounds())) {
        return true;
      }
    }
    return false;
  }
}
