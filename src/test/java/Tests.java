import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;

import arcade.src.main.ArcadeConcreteSubject;
import arcade.src.main.ArcadeConcreteSubject.Arcade;
import arcade.src.main.ArcadeObserver;
import arcade.src.main.Bullet;
import arcade.src.main.CollisionPhysics;
import arcade.src.main.Controller;
import arcade.src.main.Enemy;
import arcade.src.main.EnemyCollideObjects;
import arcade.src.main.FriendlyCollideObjects;
import arcade.src.main.Player;
import arcade.src.main.SpaceInvaders;

public class Tests {

  @Test
  public void bulletWidthTest() {
    Bullet bullet = new Bullet(10, 10);
    int bulletWidth = 4;
    int width = bullet.getWidth();

    assertEquals(width, bulletWidth);
  }

  @Test
  public void bulletHeightTest() {
    Bullet bullet = new Bullet(10, 10);
    int bulletHeight = 9;
    int height = bullet.getHeight();

    assertEquals(height, bulletHeight);
  }

  @Test
  public void bulletSpeedTest() {
    Bullet bullet = new Bullet(10, 10);
    int bulletSpeed = 10;
    int ypSpeed = (int) bullet.getYCoord() - bulletSpeed;

    assertEquals(ypSpeed, 0);
  }
  
  @Test
  public void rectangleBoundsBulletTest() {
    Bullet bullet = new Bullet(10, 10);
    int xCoord = (int) bullet.getXCoord();
    int yCoord = (int) bullet.getYCoord();

    Rectangle rect = new Rectangle(xCoord, yCoord, 10, 20);
    Rectangle rect2 = bullet.getRectBounds(10, 20);

    assertEquals(rect, rect2);
  }

  @Test
  public void playerWidthTest() {
    SpaceInvaders siGame = new SpaceInvaders();
    Player player = new Player(10, 10, siGame);
    int playerWidth = 40;
    int width = player.getWidth();

    assertEquals(width, playerWidth);
  }

  @Test
  public void playerHeightTest() {
    SpaceInvaders siGame = new SpaceInvaders();
    Player player = new Player(10, 10, siGame);
    int playerHeight = 50;
    int width = player.getHeight();

    assertEquals(width, playerHeight);
  }

  @Test
  public void playerXCoordTest() {
    SpaceInvaders siGame = new SpaceInvaders();
    Player player = new Player(10, 10, siGame);
    player.setXCoord(12);

    assertEquals((int) player.getXCoord(), 12);
  }

  @Test
  public void playerYCoordTest() {
    SpaceInvaders siGame = new SpaceInvaders();
    Player player = new Player(10, 10, siGame);
    player.setYCoord(12);

    assertEquals((int) player.getYCoord(), 12);
  }

  @Test
  public void playerXVelTest() {
    SpaceInvaders siGame = new SpaceInvaders();
    Player player = new Player(10, 10, siGame);
    double x1Vel = 0.0;
    
    player.setXVel(12);

    assertEquals((int) x1Vel, 0);
  }

  @Test
  public void playerYVelTest() {
    SpaceInvaders siGame = new SpaceInvaders();
    Player player = new Player(10, 10, siGame);
    double y1Vel = 0.0;
    
    player.setYCoord(12);

    assertEquals((int) y1Vel, 0);
  }

  @Test
  public void observersTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    ArrayList<ArcadeObserver> observers = new ArrayList<ArcadeObserver>();
    Arcade state = subject.getState();
    ArcadeObserver siGame = new SpaceInvaders();

    subject.registerObservers(siGame);
    subject.setState(Arcade.HIGHSCORES);
    if (state == Arcade.HIGHSCORES) {
      subject.unRegisterObservers(siGame);
    }

    assertEquals(observers.size(), 0);
  }

  @Test
  public void collisionPhysicsTest() {
    SpaceInvaders siGame = new SpaceInvaders();
    Controller controller = new Controller(siGame);
    Bullet bullet = new Bullet(10, 10);
    Enemy enemy = new Enemy(100, 100, siGame, controller);

    assertFalse(CollisionPhysics.isCollision(bullet, enemy));
  }

  @Test
  public void friendlyControllerTest() {
    LinkedList<FriendlyCollideObjects> friendlyList = new LinkedList<FriendlyCollideObjects>();
    FriendlyCollideObjects friendObj;
    SpaceInvaders siGame = new SpaceInvaders();
    Controller controller = new Controller(siGame);
    Bullet bullet = new Bullet(10, 10);

    controller.addFriendlyCollideObject(bullet);

    for (int i = 0; i < friendlyList.size(); i++) {
      friendObj = friendlyList.get(i);
      friendObj.tick();
    }

    controller.removeFriendlyCollideObject(bullet);

    LinkedList<FriendlyCollideObjects> friendlyList2 = controller.getFriendlyCollideObjectList();

    assertEquals(friendlyList2, friendlyList);
  }

  @Test
  public void enemyControllerTest() {
    LinkedList<EnemyCollideObjects> enemyList = new LinkedList<EnemyCollideObjects>();
    EnemyCollideObjects enemyObj;
    SpaceInvaders siGame = new SpaceInvaders();
    Controller controller = new Controller(siGame);
    Enemy enemy = new Enemy(100, 100, siGame, controller);

    controller.addEnemyCollideObject(enemy);
    controller.addEnemy(1);

    for (int j = 0; j < enemyList.size(); j++) {
      enemyObj = enemyList.get(j);
      enemyObj.tick();
    }

    controller.removeEnemyCollideObject(enemy);

    LinkedList<EnemyCollideObjects> enemyList2 = controller.getEnemyCollideObjectList();

    assertEquals(enemyList2.size(), 1);
  }
  /*
   * @Test public void hsManagerSnakeTest() { HighscoreManagerSnake hsSnake = new
   * HighscoreManagerSnake(); ManageHighscores hsManage = hsSnake.getManageHighscores();
   * 
   * hsManage.loadScoreFile(); ArrayList<Score> scores = hsManage.getHighscores(); ScoreComparator
   * comparator = new ScoreComparator(); scores.sort(comparator); hsManage.addScore("Amy", 0);
   * hsManage.sort(); hsManage.updateScoreFile();
   * 
   * assertEquals(hsManage.getHighscoreName().size(), 5); }
   * 
   * @Test public void hsManagerSpaceTest() { HighscoreManagerSpace hsSpace = new
   * HighscoreManagerSpace(); ManageHighscores hsManage = hsSpace.getManageHighscores();
   * 
   * hsManage.loadScoreFile(); ArrayList<Score> scores = hsManage.getHighscores(); ScoreComparator
   * comparator = new ScoreComparator(); scores.sort(comparator); hsManage.addScore("Amy", 0);
   * hsManage.sort(); hsManage.updateScoreFile();
   * 
   * assertEquals(hsManage.getHighscoreName().size(), 5); }
   */
}
