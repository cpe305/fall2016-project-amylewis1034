package arcade.src.main;

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
   * @param collideObjB a enemy collide-able object
   * @return true if there was a friendly/enemy collision, false otherwise
   */
  public static boolean isCollision(CollideObjectA collideObjA, CollideObjectB collideObjB) {
    if (collideObjA.getRectBounds().intersects(collideObjB.getRectBounds())) {
      return true;
    }
    return false;
  }
}
