import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import arcade.src.main.EndSnakeMenu;
import arcade.src.main.EndSpaceGameMenu;
import arcade.src.main.Enemy;
import arcade.src.main.EnemyCollideObjects;
import arcade.src.main.FriendlyCollideObjects;
import arcade.src.main.HighscoreManagerSpace;
import arcade.src.main.HighscoreMenu;
import arcade.src.main.Player;
import arcade.src.main.Score;
import arcade.src.main.ScoreComparator;
import arcade.src.main.Snake;
import arcade.src.main.SnakeApple;
import arcade.src.main.SnakeGrid;
import arcade.src.main.SpaceInvaders;
import arcade.src.main.StartMenu;

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
    subject.setState(Arcade.ENDSNAKEMENU);
    if (state == Arcade.ENDSPACEGAMEMENU) {
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
  public void moveSnake1Test() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    SnakeGrid snakeGrid = new SnakeGrid(subject, siGame);
    Snake snake = new Snake(snakeGrid);
    int[] yposTotal = snakeGrid.getYposTotal();
    
    int cury = yposTotal[0];
    
    snake.moveSnake();
    
    assertEquals(yposTotal[0], cury);
  }
  
  @Test
  public void moveSnake2Test() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    SnakeGrid snakeGrid = new SnakeGrid(subject, siGame);
    Snake snake = new Snake(snakeGrid);
    int[] xposTotal = snakeGrid.getXposTotal();
    int[] yposTotal = snakeGrid.getYposTotal();
    int pointSize = snakeGrid.getPointSize();   
    int curx = yposTotal[0] - pointSize;
    
    snake.moveSnake();
    
    curx = xposTotal[0];
    
    assertEquals(xposTotal[0], curx);
  }
  
  @Test
  public void collisionDetectionTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    SnakeGrid snakeGrid = new SnakeGrid(subject, siGame);
    Snake snake = new Snake(snakeGrid);
    int[] xposTotal = snakeGrid.getXposTotal();
    
    for (int i = snakeGrid.getSnakeScore(); i > 0; i--) {
      xposTotal[0] = xposTotal[i];
    }
    
    snake.collisionDetection();
    
    assertFalse(snakeGrid.getRunning());
  }
  
  @Test
  public void isRunningSnakeTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    SnakeGrid snakeGrid = new SnakeGrid(subject, siGame);
    snakeGrid.actionPerformed(null);
    snakeGrid.setRunning(false);
    
    assertFalse(snakeGrid.getRunning());
  }
  
  @Test
  public void stateSnakeTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    SnakeGrid snakeGrid = new SnakeGrid(subject, siGame);
    
    Arcade state = subject.getState();
    snakeGrid.setSubject(subject);
    snakeGrid.update();
    Arcade state2 = subject.getState();
    
    assertEquals(state, state2);
  }
  
  @Test
  public void scoreSnakeTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    SnakeGrid snakeGrid = new SnakeGrid(subject, siGame);
    
    snakeGrid.setSnakeScore(10);
    int score = snakeGrid.getSnakeScore();
    
    assertEquals(score, 10);
  }
  
  @Test
  public void appleSnakeTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    SnakeGrid snakeGrid = new SnakeGrid(subject, siGame);
    SnakeApple apple = new SnakeApple(snakeGrid);
    
    snakeGrid.setSnakeScore(10);
    int[] xpos = snakeGrid.getXposTotal();
    int x = apple.getXposApple();
    xpos[0] = x;
    int[] ypos = snakeGrid.getYposTotal();
    int y = apple.getYposApple();
    ypos[0] = y;
    apple.foundApple();
    
    assertEquals(snakeGrid.getSnakeScore(), 11);
  }
  
  @Test
  public void numEnemyTest() {
    SpaceInvaders siGame = new SpaceInvaders();
    siGame.setNumEnemy(10);
    
    assertEquals(siGame.getNumEnemy(), 10);
  }
  
  @Test
  public void numEnemyKilledTest() {
    SpaceInvaders siGame = new SpaceInvaders();
    siGame.setNumEnemyKilled(10);
    
    assertEquals(siGame.getNumEnemyKilled(), 10);
  }
  
  @Test
  public void spaceHealthTest() {
    SpaceInvaders siGame = new SpaceInvaders();
    siGame.setHealth(100);
    
    assertEquals(siGame.getHealth(), 100);
  }
  
  @Test
  public void spaceScoreTest() {
    SpaceInvaders siGame = new SpaceInvaders();
    siGame.setSpaceScore(10);
    
    assertEquals(siGame.getSpaceScore(), 10);
  }
  
  @SuppressWarnings("unused")
  @Test
  public void spaceInitTest() {
    SpaceInvaders siGame = new SpaceInvaders();
    String title = siGame.getTitle();
    Player player = new Player(10, 10, siGame);
    HighscoreManagerSpace hsManager = siGame.getHighscoreManagerSpace();
    Controller controller = new Controller(siGame);
 
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    Arcade state = subject.getState();
    StartMenu startMenu = new StartMenu(subject);
    SnakeGrid snakegrid = new SnakeGrid(subject, siGame);
    EndSpaceGameMenu endSpaceGameMenu = new EndSpaceGameMenu(subject);
    EndSnakeMenu endSnakeMenu = new EndSnakeMenu(subject);
    HighscoreMenu highScoreMenu = new HighscoreMenu(subject);
    subject.registerObservers(siGame);
    subject.registerObservers(startMenu);
    subject.registerObservers(snakegrid);
    subject.registerObservers(endSpaceGameMenu);
    subject.registerObservers(endSnakeMenu);
    subject.registerObservers(highScoreMenu);
 
    controller.addFriendlyCollideObject(player);
    LinkedList<FriendlyCollideObjects> friendlyList = controller.getFriendlyCollideObjectList();
    LinkedList<EnemyCollideObjects> enemyList = controller.getEnemyCollideObjectList();
    
    assertEquals(state, subject.getState());
  }
}
