package arcade.src.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

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

  public void addCollideObject(CollideObjectA objA) {
    friendlyList.add(objA);
  }

  public void removeCollideObject(CollideObjectA objA) {
    friendlyList.remove(objA);
  }

  public void addCollideObject(CollideObjectB objB) {
    enemyList.add(objB);
  }

  public void removeCollideObject(CollideObjectB objB) {
    enemyList.remove(objB);
  }

  public void addEnemy(int numEnemy) {
    for (int i = 0; i < numEnemy; i++) {
      addCollideObject(new Enemy(randNum.nextInt(siGame.getWidth() - ENEMY_WIDTH), ENEMY_POSITION, siGame));
    }
  }
  
  public LinkedList<CollideObjectA> getCollideObjectAList() {
    return friendlyList;
  }
  
  public LinkedList<CollideObjectB> getCollideObjectBList() {
    return enemyList;
  }

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
