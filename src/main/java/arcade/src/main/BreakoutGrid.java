package arcade.src.main;

import arcade.src.main.ArcadeConcreteSubject.Arcade;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Class that runs the game, Breakout.
 * 
 * @author Amy Lewis.
 * @version 12/7/16
 */
public class BreakoutGrid extends JPanel implements ActionListener, ArcadeObserver {
  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = Logger.getLogger(SpaceInvaders.class.toString());
  private static final int DELAY = 1000;
  private static final int GRID_WIDTH = 1200;
  private static final int GRID_HEIGHT = 1200;
  private static final int BOTTOM_OF_GRID = 1100;
  private static final int NUM_BRICKS = 120;
  private static final int BRICK_WIDTH = 50;
  private static final int BRICK_HEIGHT = 30;
  private static final int BALL_DIAMETER = 20;
  private static final int BALL_VELOCITY = 10;
  private static final int PADDLE_VELOCITY = 9;
  private static final int PADDLE_WIDTH = 100;
  private static final int PADDLE_HEIGHT = 30;
  private static final int SCORE_POSITION = 300;

  private transient BufferedImage breakBallImg = null;
  private transient BufferedImage breakPaddleImg = null;
  private transient BufferedImage breakBrickImg = null;

  private int[] xposTotal = new int[NUM_BRICKS];
  private int[] yposTotal = new int[NUM_BRICKS];

  private Rectangle breakGrid = new Rectangle(0, 0, GRID_WIDTH, GRID_HEIGHT);

  private transient HighscoreManagerBreakout hsManager;
  private transient BreakoutBall breakBall;
  private transient BreakoutPaddle breakPaddle;
  private transient BreakoutBrick[] breakBricks = new BreakoutBrick[NUM_BRICKS];

  private boolean isRunning = false;
  private Timer timer;
  private int score;

  private Arcade state;
  private ArcadeConcreteSubject subject; // NOSONAR

  /**
   * Constructor for Breakout game.
   * 
   * @param subject is a reference to the subject for the observer Breakout
   * @param siGame is a reference to Space Invaders to add a Key Listener
   */
  public BreakoutGrid(ArcadeConcreteSubject subject, SpaceInvaders siGame) {
    this.subject = subject;
    state = subject.getState();
    siGame.addKeyListener(new KeyInput(this));
    loadImages();
    init();
  }

  public int getGridWidth() {
    return GRID_WIDTH;
  }

  public int[] getXposTotal() {
    return xposTotal;
  }

  public int[] getYposTotal() {
    return yposTotal;
  }

  public int getBallDiameter() {
    return BALL_DIAMETER;
  }

  public int getBrickWidth() {
    return BRICK_WIDTH;
  }

  public int getBrickHeight() {
    return BRICK_HEIGHT;
  }

  public int getPaddleWidth() {
    return PADDLE_WIDTH;
  }

  public int getPaddleHeight() {
    return PADDLE_HEIGHT;
  }

  public int getBreakoutScore() {
    return score;
  }

  public void setBreakoutScore(int score) {
    this.score = score;
  }

  private void loadImages() {
    BufferedImageLoader buffLoader = new BufferedImageLoader();
    try {
      breakBallImg = buffLoader.loadImage("/breakball.png");
      breakBallImg = buffLoader.createResizedCopy(breakBallImg, BALL_DIAMETER, BALL_DIAMETER);
      breakPaddleImg = buffLoader.loadImage("/paddle.png");
      breakPaddleImg = buffLoader.createResizedCopy(breakPaddleImg, PADDLE_WIDTH, PADDLE_HEIGHT);
      breakBrickImg = buffLoader.loadImage("/breakbrick.png");
      breakBrickImg = buffLoader.createResizedCopy(breakBrickImg, BRICK_WIDTH, BRICK_HEIGHT);
    } catch (Exception ex) {
      LOGGER.log(null, "Could not load images.", ex);
    }
  }

  private void newGame() {
    breakBall = new BreakoutBall(this);
    breakPaddle = new BreakoutPaddle(this);
    score = 0;

    int iter = 0;
    int x1Pos = 0;
    int y1Pos = 0;
    while (iter < NUM_BRICKS) {
      xposTotal[iter] = x1Pos * 50;
      yposTotal[iter] = y1Pos * 30;
      breakBricks[iter] = new BreakoutBrick(this, xposTotal[iter], yposTotal[iter++]);
      x1Pos++;
      if (x1Pos == 24) {
        x1Pos = 0;
        y1Pos++;
      }
    }
  }

  private void init() {
    newGame();

    hsManager = new HighscoreManagerBreakout();
    timer = new Timer(DELAY, this);
    timer.start();
  }
  
  /**
   * If Breakout is running the apple should be randomly placed, collision detection should be
   * activated, and the snake should be able to move.
   * 
   * @param event is the action performed by the class
   */
  public void actionPerformed(ActionEvent event) {
    repaint();
  }

  private void checkCollision() {

    if (breakBall.getRectBounds().getMaxY() > BOTTOM_OF_GRID) {
      isRunning = false;
    }

    for (int i = 0, j = 0; i < NUM_BRICKS; i++) {

      if (!breakBricks[i].getIsActive()) {
        j++;
      }

      if (j == NUM_BRICKS) {
        // win
      }
    }

    if ((breakBall.getRectBounds()).intersects(breakPaddle.getRectBounds())) {

      int curPosPaddle = (int) breakPaddle.getRectBounds().getMinX();
      int curPosBall = (int) breakBall.getRectBounds().getMinX();

      int firstQuarter = curPosPaddle + 25;
      int secondQuarter = curPosPaddle + 50;

      if (curPosBall < firstQuarter) {
        breakBall.setBallVelocityX(-BALL_VELOCITY);
        breakBall.setBallVelocityY(-BALL_VELOCITY);
      }

      if (curPosBall >= firstQuarter && curPosBall < secondQuarter) {
        breakBall.setBallVelocityX(-BALL_VELOCITY);
        breakBall.setBallVelocityY(-1 * breakBall.getBallVelocityY());
      }

      int thirdQuarter = curPosPaddle + 75;
      int fourthQuarter = curPosPaddle + 100;

      if (curPosBall >= secondQuarter && curPosBall < thirdQuarter) {
        breakBall.setBallVelocityX(0);
        breakBall.setBallVelocityY(-BALL_VELOCITY);
      }

      if (curPosBall >= thirdQuarter && curPosBall < fourthQuarter) {
        breakBall.setBallVelocityX(0);
        breakBall.setBallVelocityY(-1 * breakBall.getBallVelocityY());
      }

      if (curPosBall > fourthQuarter) {
        breakBall.setBallVelocityX(BALL_VELOCITY);
        breakBall.setBallVelocityY(-BALL_VELOCITY);
      }
    }

    for (int i = 0; i < NUM_BRICKS; i++) {

      if ((breakBall.getRectBounds()).intersects(breakBricks[i].getRectBounds())) {

        int ballLeft = (int) breakBall.getRectBounds().getMinX();
        int ballTop = (int) breakBall.getRectBounds().getMinY();

        Point rightContactPoint = new Point(ballLeft + BALL_DIAMETER + 1, ballTop);
        Point leftContactPoint = new Point(ballLeft - 1, ballTop);
        Point topContactPoint = new Point(ballLeft, ballTop - 1);
        Point bottomContactPoint = new Point(ballLeft, ballTop + BALL_DIAMETER + 1);

        if (breakBricks[i].getIsActive()) {
          if (breakBricks[i].getRectBounds().contains(rightContactPoint)) {
            breakBall.setBallVelocityX(-BALL_VELOCITY);
          } else if (breakBricks[i].getRectBounds().contains(leftContactPoint)) {
            breakBall.setBallVelocityX(BALL_VELOCITY);
          }

          if (breakBricks[i].getRectBounds().contains(topContactPoint)) {
            breakBall.setBallVelocityY(BALL_VELOCITY);
          } else if (breakBricks[i].getRectBounds().contains(bottomContactPoint)) {
            breakBall.setBallVelocityY(-BALL_VELOCITY);
          }
          
          breakBricks[i].setIsActive(false);
          score++;
        }
      }
    }
  }

  /**
   * Draws the images for Breakout.
   * 
   * @param graphics is a reference to the Java graphics class
   */
  public void render(Graphics graphics) {
    if (isRunning) {
      ((Graphics2D) graphics).draw(breakGrid);

      graphics.drawImage(breakPaddleImg, breakPaddle.getPaddlePosX(), breakPaddle.getPaddlePosY(),
          this);

      graphics.drawImage(breakBallImg, breakBall.getBallPosX(), breakBall.getBallPosY(), this);

      for (int i = 0; i < NUM_BRICKS; i++) {
        if (breakBricks[i].getIsActive()) {
          graphics.drawImage(breakBrickImg, breakBricks[i].getBrickPosX(),
              breakBricks[i].getBrickPosY(), this);
        }
      }
      
      breakBall.ballMove();
      breakPaddle.paddleMove();
      checkCollision();

      Font fnt = new Font("arial", Font.BOLD, 50);
      graphics.setFont(fnt);
      graphics.setColor(Color.YELLOW);
      graphics.drawString("Score: ", SpaceInvaders.WIDTH * 2 - SCORE_POSITION, 60);
      graphics.drawString(((Integer) score).toString(), SpaceInvaders.WIDTH * 2 - 100, 60);

      Toolkit.getDefaultToolkit().sync();
    } else {
      ArrayList<Score> highscores = hsManager.getManageHighscores().getHighscores();
      if (highscores.size() < 5 || score > highscores.get(4).getScore()) {
        String name = JOptionPane
            .showInputDialog("Congratulations! You set a new highscore! Please enter your name.");
        hsManager.getManageHighscores().addScore(name, score);
      }
      subject.setState(Arcade.ENDBREAKOUTMENU);
      subject.notifyObservers();
    }
  }

  /**
   * Allows the user to control the player with certain keys on the keyboard.
   * 
   * @param event the key that was pressed
   */
  public void keyPressed(KeyEvent event) {
    int key = event.getKeyCode();

    if (key == KeyEvent.VK_LEFT) {
      breakPaddle.setPaddleVelocity(-PADDLE_VELOCITY);
    }

    if (key == KeyEvent.VK_RIGHT) {
      breakPaddle.setPaddleVelocity(PADDLE_VELOCITY);
    }

    if (key == KeyEvent.VK_UP && breakBall.getBallVelocityY() == 0) {
      breakBall.setBallVelocityY(-BALL_VELOCITY);
    }
  }

  /**
   * Makes it so when a key is released, it no longer has an effect on the game.
   * 
   * @param event the key that was released
   */
  public void keyReleased(KeyEvent event) {
    int key = event.getKeyCode();

    if (key == KeyEvent.VK_LEFT) {
      breakPaddle.setPaddleVelocity(0);
    }

    if (key == KeyEvent.VK_RIGHT) {
      breakPaddle.setPaddleVelocity(0);
    }
  }

  /**
   * Updates observer if the state has been changed.
   */
  public void update() {
    state = subject.getState();
    if (state == Arcade.BREAKOUT) {
      isRunning = true;
      newGame();
    }
  }
}
