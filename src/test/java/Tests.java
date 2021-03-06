import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import arcade.src.main.ArcadeConcreteSubject;
import arcade.src.main.ArcadeConcreteSubject.Arcade;
import arcade.src.main.ArcadeObserver;
import arcade.src.main.BreakoutBall;
import arcade.src.main.BreakoutBrick;
import arcade.src.main.BreakoutGrid;
import arcade.src.main.BreakoutPaddle;
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

    Rectangle rect = new Rectangle(x1Coord, y1Coord, player.getWidth(), player.getHeight());
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
    int x1 = (int) enemy.getXCoord();

    assertEquals((int) enemy.getXCoord(), x1);
  }

  @Test
  public void enemyYCoordTest() {
    SpaceInvaders siGame = new SpaceInvaders();
    Controller controller = new Controller(siGame);
    Enemy enemy = new Enemy(10, 10, siGame, controller);
    int y1 = (int) enemy.getYCoord();

    assertEquals((int) enemy.getYCoord(), y1);
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
    int x1 = apple.getXposApple();
    xpos[0] = x1;
    int[] ypos = snakeGrid.getYposTotal();
    int y1 = apple.getYposApple();
    ypos[0] = y1;
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
    HighscoreManagerSpace hsManager = siGame.getHighscoreManagerSpace();

    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
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

    Controller controller = new Controller(siGame);
    Player player = new Player(10, 10, siGame);
    controller.addFriendlyCollideObject(player);
    LinkedList<FriendlyCollideObjects> friendlyList = controller.getFriendlyCollideObjectList();
    LinkedList<EnemyCollideObjects> enemyList = controller.getEnemyCollideObjectList();
    Arcade state = subject.getState();

    assertEquals(state, subject.getState());
  }

  @Test
  public void spaceHeightTest() {
    SpaceInvaders siGame = new SpaceInvaders();

    assertEquals(siGame.getHeight(), 1600);
  }

  @Test
  public void breakBallPosXTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);
    BreakoutBall ball = new BreakoutBall(grid);

    assertEquals(ball.getBallPosX(), 590);
  }

  @Test
  public void breakBallPosYTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);
    BreakoutBall ball = new BreakoutBall(grid);

    assertEquals(ball.getBallPosY(), 990);
  }

  @Test
  public void breakBallVelXTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);
    BreakoutBall ball = new BreakoutBall(grid);

    assertEquals(ball.getBallVelocityX(), 0);
  }

  @Test
  public void breakBallVelYTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);
    BreakoutBall ball = new BreakoutBall(grid);

    assertEquals(ball.getBallVelocityY(), 0);
  }

  @Test
  public void ballMoveZTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);
    BreakoutBall ball = new BreakoutBall(grid);

    ball.setBallPosX(0);
    ball.ballMove();
    assertEquals(ball.getBallVelocityX(), 10);
  }

  @Test
  public void ballMoveWTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);
    BreakoutBall ball = new BreakoutBall(grid);

    ball.setBallPosX(grid.getGridWidth() - grid.getBallDiameter());
    ball.ballMove();
    assertEquals(ball.getBallVelocityX(), -10);
  }

  @Test
  public void ballMoveYTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);
    BreakoutBall ball = new BreakoutBall(grid);

    ball.setBallPosY(0);
    ball.ballMove();
    assertEquals(ball.getBallVelocityY(), 10);
  }

  @Test
  public void breakPaddlePosXTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);
    BreakoutPaddle paddle = new BreakoutPaddle(grid);

    assertEquals(paddle.getPaddlePosX(), 550);
  }

  @Test
  public void breakPaddlePosYTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);
    BreakoutPaddle paddle = new BreakoutPaddle(grid);

    assertEquals(paddle.getPaddlePosY(), 1010);
  }

  @Test
  public void paddleMoveZTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);
    BreakoutPaddle paddle = new BreakoutPaddle(grid);

    paddle.setPaddlePosX(0);
    paddle.paddleMove();

    assertEquals(paddle.getPaddlePosX(), 0);
  }

  @Test
  public void paddleMoveWTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);
    BreakoutPaddle paddle = new BreakoutPaddle(grid);

    paddle.setPaddlePosX(grid.getGridWidth() - grid.getPaddleWidth());
    paddle.paddleMove();

    assertEquals(paddle.getPaddlePosX(), grid.getGridWidth() - grid.getPaddleWidth());
  }

  @Test
  public void breakBrickPosXTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);
    BreakoutBrick brick = new BreakoutBrick(grid, 10, 10);

    assertEquals(brick.getBrickPosX(), 10);
  }

  @Test
  public void breakBrickPosYTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);
    BreakoutBrick brick = new BreakoutBrick(grid, 10, 10);

    assertEquals(brick.getBrickPosY(), 10);
  }

  @Test
  public void breakBrickActiveTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);
    BreakoutBrick brick = new BreakoutBrick(grid, 10, 10);

    brick.setIsActive(false);

    assertFalse(brick.getIsActive());
  }

  @Test
  public void breakPosXTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);

    int[] temp = new int[120];
    int[] posX = grid.getXposTotal();

    assertEquals(temp.length, posX.length);
  }

  @Test
  public void breakPosYTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);

    int[] temp = new int[120];
    int[] posY = grid.getYposTotal();

    assertEquals(temp.length, posY.length);
  }

  @Test
  public void breakBrickWidthTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);

    int width = grid.getBrickWidth();

    assertEquals(width, 50);
  }

  @Test
  public void breakBrickHeightTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);

    int height = grid.getBrickHeight();

    assertEquals(height, 30);
  }

  @Test
  public void breakPaddleWidthTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);

    int width = grid.getPaddleWidth();

    assertEquals(width, 100);
  }

  @Test
  public void breakPaddleHeightTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);

    int height = grid.getPaddleHeight();

    assertEquals(height, 30);
  }

  @Test
  public void breakScoreTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    SpaceInvaders siGame = new SpaceInvaders();
    BreakoutGrid grid = new BreakoutGrid(subject, siGame);

    grid.setBreakoutScore(10);

    assertEquals(grid.getBreakoutScore(), 10);
  }
}
