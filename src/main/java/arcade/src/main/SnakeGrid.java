package arcade.src.main;

import arcade.src.main.ArcadeConcreteSubject.Arcade;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGrid extends JPanel implements ActionListener, ArcadeObserver {
  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = Logger.getLogger(SpaceInvaders.class.toString());
  private static final int GRID_WIDTH = 1200;
  private static final int GRID_HEIGHT = 1200;
  private static final int POINT_SIZE = 40;
  private static final int TOTAL_POINTS = GRID_WIDTH * GRID_HEIGHT / (POINT_SIZE * POINT_SIZE);
  private static final int DELAY = 140;
  private static final int INITIAL_POSITIONS = 80;
  private static final int SCORE_POSITION = 300;

  private transient BufferedImage apple = null;
  private transient BufferedImage head = null;
  private transient BufferedImage body = null;

  private int score;
  private int points;
  private boolean isRunning = false;
  private Timer timer;
  private transient HighscoreManagerSnake hsManager;

  private int xposApple;
  private int yposApple;
  private int[] xposTotal = new int[TOTAL_POINTS];
  private int[] yposTotal = new int[TOTAL_POINTS];

  private boolean goingUp;
  private boolean goingDown;
  private boolean goingLeft;
  private boolean goingRight;

  private Arcade state;
  private ArcadeConcreteSubject subject; //NOSONAR

  public Rectangle mapGrid = new Rectangle(0, 0, GRID_WIDTH, GRID_HEIGHT);

  Random randNum = new Random();
  
  /**
   * Constructor for Snake game.
   * 
   * @param subject is a reference to the subject for the observer Snake
   * @param siGame is a reference to Space Invaders to add a Key Listener
   */
  public SnakeGrid(ArcadeConcreteSubject subject, SpaceInvaders siGame) {
    this.subject = subject;
    state = subject.getState();
    siGame.addKeyListener(new KeyInput(this));
    loadImages();
    init();
  }

  public void setSubject(ArcadeConcreteSubject subject) {
    this.subject = subject;
  }

  /**
   * Updates observer if the state has been changed.
   */
  public void update() {
    state = subject.getState();
    if (state == Arcade.SNAKE) {
      isRunning = true;
      newGame();
    }
  }

  public int getSnakeScore() {
    return score;
  }

  public void setSnakeScore(int score) {
    this.score = score;
  }

  private void loadImages() {
    BufferedImageLoader buffLoader = new BufferedImageLoader();
    try {
      apple = buffLoader.loadImage("/apple.png");
      apple = buffLoader.createResizedCopy(apple, POINT_SIZE, POINT_SIZE);
      head = buffLoader.loadImage("/snake_head.png");
      head = buffLoader.createResizedCopy(head, POINT_SIZE, POINT_SIZE);
      body = buffLoader.loadImage("/snake_body.png");
      body = buffLoader.createResizedCopy(body, POINT_SIZE, POINT_SIZE);
    } catch (Exception ex) {
      LOGGER.log(null, "Could not load images.", ex);
    }
  }

  private void newGame() {
    goingUp = false;
    goingDown = false;
    goingLeft = false;
    goingRight = true;
    score = 3;

    int iter = 0;

    while (iter < score) {
      xposTotal[iter] = INITIAL_POSITIONS - iter * 10;
      yposTotal[iter++] = INITIAL_POSITIONS;
    }

    appleLocator();
  }

  private void init() {
    newGame();

    hsManager = new HighscoreManagerSnake();
    timer = new Timer(DELAY, this);
    timer.start();
  }

  private void foundApple() {
    if (xposTotal[0] == xposApple && yposTotal[0] == yposApple) {
      score++;
      appleLocator();
    }
  }

  private void moveSnake() {
    for (int i = score; i > 0; i--) {
      xposTotal[i] = xposTotal[i - 1];
      yposTotal[i] = yposTotal[i - 1];
    }

    if (goingUp) {
      yposTotal[0] -= POINT_SIZE;
    } else if (goingDown) {
      yposTotal[0] += POINT_SIZE;
    } else if (goingLeft) {
      xposTotal[0] -= POINT_SIZE;
    } else if (goingRight) {
      xposTotal[0] += POINT_SIZE;
    }
  }

  private void collisionDetection() {
    for (int i = score; i > 0; i--) {
      if (xposTotal[0] == xposTotal[i] && yposTotal[0] == yposTotal[i]) {
        isRunning = false;
      }
    }

    if ((xposTotal[0] >= GRID_WIDTH) || (xposTotal[0] < 0) || (yposTotal[0] >= GRID_HEIGHT)
        || (yposTotal[0] < 0)) {
      isRunning = false;
    }
  }

  private void appleLocator() {
    xposApple = (int) (Math.random() * GRID_WIDTH / POINT_SIZE) * POINT_SIZE; //NOSONAR
    yposApple = (int) (Math.random() * GRID_HEIGHT / POINT_SIZE) * POINT_SIZE; //NOSONAR
  }

  /**
   * Draws the images for Snake.
   * 
   * @param graphics is a reference to the Java graphics class
   */
  public void render(Graphics graphics) {
    if (state == Arcade.SNAKE) {
      if (isRunning) {
        graphics.setColor(Color.BLACK);

        graphics.drawImage(apple, xposApple, yposApple, this);

        ((Graphics2D) graphics).draw(mapGrid);

        for (int i = 0; i < score; i++) {
          if (i == 0) {
            graphics.drawImage(head, xposTotal[i], yposTotal[i], this);
          } else {
            graphics.drawImage(body, xposTotal[i], yposTotal[i], this);
          }
        }

        Font fnt = new Font("arial", Font.BOLD, 50);
        graphics.setFont(fnt);
        graphics.setColor(Color.YELLOW);
        points = score - 3;
        graphics.drawString("Score: ", SpaceInvaders.WIDTH * 2 - SCORE_POSITION, 60);
        graphics.drawString(((Integer) points).toString(), SpaceInvaders.WIDTH * 2 - 100, 60);

        Toolkit.getDefaultToolkit().sync();
      } else {
        ArrayList<Score> highscores = hsManager.getHighscores();
        if (highscores.size() < 5 || score > highscores.get(4).getScore()) {

          String name = JOptionPane
              .showInputDialog("Congratulations! You set a new highscore! Please enter your name.");
          hsManager.addScore(name, score);
        }
        subject.setState(Arcade.ENDSNAKEMENU);
        subject.notifyObservers();
      }
    }
  }

  /**
   * If Snake is running the apple should be randomly placed, collision detection should be
   * activated, and the snake should be able to move.
   */
  public void actionPerformed(ActionEvent event) {
    if (isRunning) {
      foundApple();
      collisionDetection();
      moveSnake();
    }
    repaint();
  }

  /**
   * Allows the user to control the player with certain keys on the keyboard.
   * 
   * @param event the key that was pressed
   */
  public void keyPressed(KeyEvent event) {
    int keyPressed = event.getKeyCode();
    if ((keyPressed == KeyEvent.VK_LEFT) && (!goingRight)) {
      goingLeft = true;
      goingUp = false;
      goingDown = false;
    }

    if ((keyPressed == KeyEvent.VK_RIGHT) && (!goingLeft)) {
      goingRight = true;
      goingUp = false;
      goingDown = false;
    }

    if ((keyPressed == KeyEvent.VK_UP) && (!goingDown)) {
      goingRight = false;
      goingLeft = false;
      goingUp = true;
    }

    if ((keyPressed == KeyEvent.VK_DOWN) && (!goingUp)) {
      goingRight = false;
      goingLeft = false;
      goingDown = true;
    }
  }
}
