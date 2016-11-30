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

  private LinkedList<FriendlyCollideObjects> friendlyList =
      new LinkedList<FriendlyCollideObjects>();
  private LinkedList<EnemyCollideObjects> enemyList = new LinkedList<EnemyCollideObjects>();

  SpaceInvaders siGame;
  FriendlyCollideObjects friendObj;
  EnemyCollideObjects enemyObj;

  Random randNum = new Random();

  public Controller(SpaceInvaders siGame) {
    this.siGame = siGame;
  }

  /**
   * Adds a collide-able object to the friendly list.
   * 
   * @param objFriendly a friendly collide-able object
   */
  public void addFriendlyCollideObject(FriendlyCollideObjects objFriendly) {
    friendlyList.add(objFriendly);
  }

  /**
   * Removes a collide-able object from the friendly list.
   * 
   * @param objFriendly a friendly collide-able object
   */
  public void removeFriendlyCollideObject(FriendlyCollideObjects objFriendly) {
    friendlyList.remove(objFriendly);
  }

  /**
   * Adds a collide-able object to the enemy list.
   * 
   * @param objEnemy an enemy collide-able object
   */
  public void addCollideObjectB(EnemyCollideObjects objEnemy) {
    enemyList.add(objEnemy);
  }

  /**
   * Removes a collide-able object from the enemy list.
   * 
   * @param objEnemy an enemy collide-able object
   */
  public void removeEnemyCollideObject(EnemyCollideObjects objEnemy) {
    enemyList.remove(objEnemy);
  }

  /**
   * Adds another active enemy to the game at the top of the screen with a random x position and a
   * random speed.
   * 
   * @param numEnemy the current number of enemies in the game
   */
  public void addEnemy(int numEnemy) {
    for (int i = 0; i < numEnemy; i++) {
      int x1Coord = randNum.nextInt(siGame.getWidth() - ENEMY_WIDTH);
      addCollideObjectB(new Enemy(x1Coord, ENEMY_POSITION, siGame, this));
    }
  }

  public LinkedList<FriendlyCollideObjects> getFriendlyCollideObjectList() {
    return friendlyList;
  }

  public LinkedList<EnemyCollideObjects> getEnemyCollideObjectList() {
    return enemyList;
  }

  /**
   * Method that calls the specific tick() method for each object in the friendly list and each
   * object in the enemy list.
   */
  public void tick() {
    // FriendlyCollideObject
    for (int i = 0; i < friendlyList.size(); i++) {
      friendObj = friendlyList.get(i);
      friendObj.tick();
    }

    // EnemyCollideObject
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
    // FriendlyCollideObject
    for (int i = 0; i < friendlyList.size(); i++) {
      friendObj = friendlyList.get(i);
      friendObj.render(graphics);
    }

    // EnemyCollideObject
    for (int j = 0; j < enemyList.size(); j++) {
      enemyObj = enemyList.get(j);
      enemyObj.render(graphics);
    }
  }
}
