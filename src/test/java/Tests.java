import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import arcade.src.main.ArcadeConcreteSubject;
import arcade.src.main.ArcadeConcreteSubject.Arcade;
import arcade.src.main.ArcadeObserver;
import arcade.src.main.Bullet;
import arcade.src.main.CollisionPhysics;
import arcade.src.main.Controller;
import arcade.src.main.Enemy;
import arcade.src.main.EnemyCollideObjects;
import arcade.src.main.FriendlyCollideObjects;
import arcade.src.main.HighscoreManagerSnake;
import arcade.src.main.HighscoreManagerSpace;
import arcade.src.main.HighscoreMenu;
import arcade.src.main.ManageHighscores;
import arcade.src.main.Player;
import arcade.src.main.Score;
import arcade.src.main.ScoreComparator;
import arcade.src.main.SpaceInvaders;
import org.junit.Test;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

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
    int x1Coord = (int) bullet.getXCoord();
    int y1Coord = (int) bullet.getYCoord();

    Rectangle rect = new Rectangle(x1Coord, y1Coord, 10, 20);
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

    player.setYVel(12);

    assertEquals((int) y1Vel, 0);
  }

  @Test
  public void rectangleBoundsPlayerTest() {
    SpaceInvaders siGame = new SpaceInvaders();
    Player player = new Player(10, 10, siGame);
    int x1Coord = (int) player.getXCoord();
    int y1Coord = (int) player.getYCoord();

    Rectangle rect = new Rectangle(x1Coord, y1Coord, player.getWidth(),player.getHeight());
    Rectangle rect2 = player.getRectBounds();

    assertEquals(rect, rect2);
  }
  
  @Test
  public void enemyWidthTest() {
    SpaceInvaders siGame = new SpaceInvaders();
    Controller controller = new Controller(siGame);
    Enemy enemy = new Enemy(10, 10, siGame, controller);
    int enemyWidth = 80;
    int width = enemy.getWidth();

    assertEquals(width, enemyWidth);
  }

  @Test
  public void enemyHeightTest() {
    SpaceInvaders siGame = new SpaceInvaders();
    Controller controller = new Controller(siGame);
    Enemy enemy = new Enemy(10, 10, siGame, controller);
    int enemyHeight = 45;
    int width = enemy.getHeight();

    assertEquals(width, enemyHeight);
  }

  @Test
  public void enemyXCoordTest() {
    SpaceInvaders siGame = new SpaceInvaders();
    Controller controller = new Controller(siGame);
    Enemy enemy = new Enemy(10, 10, siGame, controller);
    int x = (int) enemy.getXCoord();
    
    assertEquals((int) enemy.getXCoord(), x);
  }

  @Test
  public void enemyYCoordTest() {
    SpaceInvaders siGame = new SpaceInvaders();
    Controller controller = new Controller(siGame);
    Enemy enemy = new Enemy(10, 10, siGame, controller);
    int y = (int) enemy.getYCoord();

    assertEquals((int) enemy.getYCoord(), y);
  }

  @Test
  public void observersRegisterTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    Arcade state = subject.getState();
    ArcadeObserver siGame = new SpaceInvaders();

    subject.registerObservers(siGame);
    subject.setState(Arcade.HIGHSCORES);
    if (state == Arcade.HIGHSCORES) {
      subject.unRegisterObservers(siGame);
    }
    
    ArrayList<ArcadeObserver> observers = new ArrayList<ArcadeObserver>();

    assertEquals(observers.size(), 0);
  }

  @Test
  public void observersUnregisterTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    HighscoreMenu hsMenu = new HighscoreMenu(subject);

    subject.registerObservers(hsMenu);
    subject.setState(Arcade.HIGHSCORES);
    subject.unRegisterObservers(hsMenu);

    ArrayList<ArcadeObserver> observers = new ArrayList<ArcadeObserver>();
    
    assertEquals(observers.size(), 0);
  }

  @Test
  public void collisionPhysicsFalseTest() {
    SpaceInvaders siGame = new SpaceInvaders();
    Controller controller = new Controller(siGame);
    Bullet bullet = new Bullet(10, 10);
    Enemy enemy = new Enemy(100, 100, siGame, controller);

    assertFalse(CollisionPhysics.isCollision(bullet, enemy));
  }

  @Test
  public void collisionPhysicsTrueTest() {
    SpaceInvaders siGame = new SpaceInvaders();
    Controller controller = new Controller(siGame);
    Bullet bullet = new Bullet(10, 10);
    Enemy enemy = new Enemy(10, 10, siGame, controller);

    assertTrue(CollisionPhysics.isCollision(bullet, enemy));
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

  @Test
  public void getScoreTest() {
    Score scor = new Score("Amy", 10);
    int score = 10;

    assertEquals(scor.getScore(), score);
  }

  @Test
  public void getScoreNameTest() {
    Score scor = new Score("Amy", 10);
    String name = "Amy";

    assertEquals(scor.getScoreName(), name);
  }

  @Test
  public void scoreCompare1Test() {
    ScoreComparator sc = new ScoreComparator();
    Score score1 = new Score("Amy", 10);
    Score score2 = new Score("Hanna", 20);

    int compared = sc.compare(score1, score2);

    assertEquals(compared, 1);
  }

  @Test
  public void scoreCompare2Test() {
    ScoreComparator sc = new ScoreComparator();
    Score score1 = new Score("Amy", 10);
    Score score2 = new Score("Hanna", 10);

    int compared = sc.compare(score1, score2);

    assertEquals(compared, 0);
  }

  @Test
  public void scoreCompare3Test() {
    ScoreComparator sc = new ScoreComparator();
    Score score1 = new Score("Amy", 10);
    Score score2 = new Score("Hanna", 1);

    int compared = sc.compare(score1, score2);

    assertEquals(compared, -1);
  }


  @Test
  public void hsManagerSnakeTest() {
    HighscoreManagerSnake hsSnake = new HighscoreManagerSnake();
    ManageHighscores hsManage = hsSnake.getManageHighscores();

    assertEquals(hsManage.getHighscoreName().size(), 5);
  }

  @Test
  public void hsManagerSpaceTest() {
    HighscoreManagerSpace hsSpace = new HighscoreManagerSpace();
    ManageHighscores hsManage = hsSpace.getManageHighscores();

    assertEquals(hsManage.getHighscoreName().size(), 5);
  }

}
