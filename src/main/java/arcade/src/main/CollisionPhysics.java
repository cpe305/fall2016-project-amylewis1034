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
   * @param collideObjFriendly a friendly collide-able object
   * @param collideObjEnemy a enemy collide-able object
   * @return true if there was a friendly/enemy collision, false otherwise
   */
  public static boolean isCollision(FriendlyCollideObjects collideObjFriendly,
      EnemyCollideObjects collideObjEnemy) {
    if (collideObjFriendly.getRectBounds().intersects(collideObjEnemy.getRectBounds())) {
      return true;
    }
    return false;
  }
}
