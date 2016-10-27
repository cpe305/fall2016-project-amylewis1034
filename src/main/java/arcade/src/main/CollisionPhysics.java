package arcade.src.main;

import java.util.LinkedList;

public class CollisionPhysics {
  
  public static boolean Collision(CollideObjectA collideObjA, LinkedList<CollideObjectB> objListB) {
    for (int i = 0; i < objListB.size(); i++) {
      if (collideObjA.getRectBounds().intersects(objListB.get(i).getRectBounds())) {
        return true;
      }
    }
    return false;
  }
}
