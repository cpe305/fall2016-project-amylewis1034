package arcade.src.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

/**
 * Class that controls relationships between entities in the game, Space Invaders.
 * 
 * @author Amy Lewis.
 * @see Controller
 * @version 10/30/16
 */

public class Controller {
  public static final int ENEMY_WIDTH = 80;
  public static final int ENEMY_POSITION = -10;

  private LinkedList<CollideObjectA> friendlyList = new LinkedList<CollideObjectA>();
  private LinkedList<CollideObjectB> enemyList = new LinkedList<CollideObjectB>();

  SpaceInvaders siGame;
  CollideObjectA friendObj;
  CollideObjectB enemyObj;

  Random randNum = new Random();

  public Controller(SpaceInvaders siGame) {
    this.siGame = siGame;
  }

  /**
   * Adds a collide-able object to the friendly list.
   * 
   * @param objA a friendly collide-able object
   */
  public void addCollideObjectA(CollideObjectA objA) {
    friendlyList.add(objA);
  }

  /**
   * Removes a collide-able object from the friendly list.
   * 
   * @param objA a friendly collide-able object
   */
  public void removeCollideObjectA(CollideObjectA objA) {
    friendlyList.remove(objA);
  }

  /**
   * Adds a collide-able object to the enemy list.
   * 
   * @param objB an enemy collide-able object
   */
  public void addCollideObjectB(CollideObjectB objB) {
    enemyList.add(objB);
  }

  /**
   * Removes a collide-able object from the enemy list.
   * 
   * @param objB an enemy collide-able object
   */
  public void removeCollideObjectB(CollideObjectB objB) {
    enemyList.remove(objB);
  }

  /**
   * Adds another active enemy to the game at the top of the screen with a random x position and a
   * random speed.
   * 
   * @param numEnemy the current number of enemies in the game
   */
  public void addEnemy(int numEnemy) {
    for (int i = 0; i < numEnemy; i++) {
      addCollideObjectB(new Enemy(randNum.nextInt(siGame.getWidth() - ENEMY_WIDTH), ENEMY_POSITION,
          siGame, this));
    }
  }

  public LinkedList<CollideObjectA> getCollideObjectAList() {
    return friendlyList;
  }

  public LinkedList<CollideObjectB> getCollideObjectBList() {
    return enemyList;
  }

  /**
   * Method that calls the specific tick() method for each object in the friendly list and each
   * object in the enemy list.
   */
  public void tick() {
    // CollideObjectA
    for (int i = 0; i < friendlyList.size(); i++) {
      friendObj = friendlyList.get(i);
      friendObj.tick();
    }

    // CollideObjectB
    for (int j = 0; j < enemyList.size(); j++) {
      enemyObj = enemyList.get(j);
      enemyObj.tick();
    }
  }

  /**
   * Method that calls the specific render() method for each object in the friendly list and each
   * object in the enemy list.
   * 
   * @param graphics is a reference to the Java graphics class
   */
  public void render(Graphics graphics) {
    // CollideObjectA
    for (int i = 0; i < friendlyList.size(); i++) {
      friendObj = friendlyList.get(i);
      friendObj.render(graphics);
    }

    // CollideObjectB
    for (int j = 0; j < enemyList.size(); j++) {
      enemyObj = enemyList.get(j);
      enemyObj.render(graphics);
    }
  }
}
