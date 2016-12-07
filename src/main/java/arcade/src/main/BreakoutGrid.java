package arcade.src.main;

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

import arcade.src.main.ArcadeConcreteSubject.Arcade;

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
  private static final int PADDLE_WIDTH = 100;
  private static final int PADDLE_HEIGHT = 30;
  private static final int SCORE_POSITION = 300;

  private transient BufferedImage breakBallImg = null;
  private transient BufferedImage breakPaddleImg = null;
  private transient BufferedImage breakBrickImg = null;

  private int[] xposTotal = new int[NUM_BRICKS];
  private int[] yposTotal = new int[NUM_BRICKS];
  
  private Rectangle breakGrid = new Rectangle(0, 0, GRID_WIDTH, GRID_HEIGHT);

  private int score;
  private transient HighscoreManagerBreakout hsManager;
  private Timer timer;
  private BreakoutBall breakBall;
  private BreakoutPaddle breakPaddle;
  private BreakoutBrick[] breakBricks = new BreakoutBrick[NUM_BRICKS];
  private boolean isRunning = false;

  private Arcade state;
  private ArcadeConcreteSubject subject; // NOSONAR

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

    int iter, i, j;
    iter = i = j = 0;
    while (iter < NUM_BRICKS) {
      xposTotal[iter] = i * 50;
      yposTotal[iter] = j * 30;
      breakBricks[iter] = new BreakoutBrick(this, xposTotal[iter], yposTotal[iter++]);
      i++;
      if (i == 24) {
        i = 0;
        j++;
      }
    }
  }

  private void init() {
    newGame();

    hsManager = new HighscoreManagerBreakout();
    timer = new Timer(DELAY, this);
    timer.start();
  }

  public void actionPerformed(ActionEvent event) {
    if (isRunning) {
      breakBall.ballMove();
      breakPaddle.paddleMove();
      checkCollision();
    }
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

      int paddleLPos = (int) breakPaddle.getRectBounds().getMinX();
      int ballLPos = (int) breakBall.getRectBounds().getMinX();

      int first = paddleLPos + 8;
      int second = paddleLPos + 16;
      int third = paddleLPos + 24;
      int fourth = paddleLPos + 32;

      if (ballLPos < first) {
        breakBall.setBallVelocityX(-10);
        breakBall.setBallVelocityY(-10);
      }

      if (ballLPos >= first && ballLPos < second) {
        breakBall.setBallVelocityX(-10);
        breakBall.setBallVelocityY(-1 * breakBall.getBallVelocityY());
      }

      if (ballLPos >= second && ballLPos < third) {
        breakBall.setBallVelocityX(0);
        breakBall.setBallVelocityY(-10);
      }

      if (ballLPos >= third && ballLPos < fourth) {
        breakBall.setBallVelocityX(0);
        breakBall.setBallVelocityY(-1 * breakBall.getBallVelocityY());
      }

      if (ballLPos > fourth) {
        breakBall.setBallVelocityX(10);
        breakBall.setBallVelocityY(-10);
      }
    }

    for (int i = 0; i < NUM_BRICKS; i++) {

      if ((breakBall.getRectBounds()).intersects(breakBricks[i].getRectBounds())) {

        int ballLeft = (int) breakBall.getRectBounds().getMinX();
        int ballTop = (int) breakBall.getRectBounds().getMinY();

        Point pointRight = new Point(ballLeft + BALL_DIAMETER + 1, ballTop);
        Point pointLeft = new Point(ballLeft - 1, ballTop);
        Point pointTop = new Point(ballLeft, ballTop - 1);
        Point pointBottom = new Point(ballLeft, ballTop + BALL_DIAMETER + 1);

        if (breakBricks[i].getIsActive()) {
          if (breakBricks[i].getRectBounds().contains(pointRight)) {
            breakBall.setBallVelocityX(-10);
          } else if (breakBricks[i].getRectBounds().contains(pointLeft)) {
            breakBall.setBallVelocityX(10);
          }

          if (breakBricks[i].getRectBounds().contains(pointTop)) {
            breakBall.setBallVelocityY(10);
          } else if (breakBricks[i].getRectBounds().contains(pointBottom)) {
            breakBall.setBallVelocityY(-10);
          }
          breakBricks[i].setIsActive(false);
          score++;
        }
      }
    }
  }

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
      
      Font fnt = new Font("arial", Font.BOLD, 50);
      graphics.setFont(fnt);
      graphics.setColor(Color.YELLOW);
      graphics.drawString("Score: ", SpaceInvaders.WIDTH * 2 - SCORE_POSITION, 60);
      graphics.drawString(((Integer) score).toString(), SpaceInvaders.WIDTH * 2 - 100, 60);


      breakBall.ballMove();
      breakPaddle.paddleMove();
      checkCollision();

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

  public void keyPressed(KeyEvent event) {

    int key = event.getKeyCode();

    if (key == KeyEvent.VK_LEFT) {
      breakPaddle.setPaddleVelocity(-10);
    }

    if (key == KeyEvent.VK_RIGHT) {
      breakPaddle.setPaddleVelocity(10);
    }

    if (key == KeyEvent.VK_UP && breakBall.getBallVelocityY() == 0) {
      breakBall.setBallVelocityY(-10);
    }
  }

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
