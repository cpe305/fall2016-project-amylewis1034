package arcade.src.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

import arcade.src.main.ArcadeConcreteSubject.Arcade;

public class SnakeGrid extends JPanel implements ActionListener, ArcadeObserver {
  private static final int GRID_WIDTH = 1200;
  private static final int GRID_HEIGHT = 1200;
  private static final int POINT_SIZE = 40;
  private static final int TOTAL_POINTS = GRID_WIDTH * GRID_HEIGHT / (POINT_SIZE * POINT_SIZE);
  private static final int RAND_POINT_POS = 40;
  private static final int DELAY = 140;
  private static final int INITIAL_POSITIONS = 80;

  private BufferedImage apple;
  private BufferedImage head;
  private BufferedImage body;

  private int points;
  private boolean isRunning = false;
  private Timer timer;

  private int xApplePosition;
  private int yApplePosition;
  private int[] xPositions = new int[TOTAL_POINTS];
  private int[] yPositions = new int[TOTAL_POINTS];

  private boolean goingUp = false;
  private boolean goingDown = false;
  private boolean goingLeft = false;
  private boolean goingRight = true;
  
  private Arcade state;
  private ArcadeConcreteSubject subject;
  
  public Rectangle mapGrid =
      new Rectangle(0, 0, GRID_WIDTH, GRID_HEIGHT);

  public SnakeGrid(ArcadeConcreteSubject subject, SpaceInvaders siGame) {
    this.subject = subject;
    state = subject.getState();
    siGame.addKeyListener(new KeyInput(this));
    loadImages();
    init();
  }
  
  private ArcadeConcreteSubject getSubject() {
    return subject;
  }
  
  public void setSubject(ArcadeConcreteSubject subject) {
    this.subject = subject;
  }
  
  public void update() {
    state = subject.getState();
    if (state == Arcade.SNAKE) {
      isRunning = true;
    }
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
      ex.printStackTrace();
    }
  }

  private void init() {
    points = 3;
    int i = 0;

    while (i < points) {
      xPositions[i] = INITIAL_POSITIONS - i * 10;
      yPositions[i++] = INITIAL_POSITIONS;
    }

    timer = new Timer(DELAY, this);
    timer.start();

    appleLocator();
  }

  private void foundApple() {
    if (xPositions[0] == xApplePosition && yPositions[0] == yApplePosition) {
      points++;
      appleLocator();
    }
  }

  private void moveSnake() {
    for (int i = points; i > 0; i--) {
      xPositions[i] = xPositions[i - 1];
      yPositions[i] = yPositions[i - 1];
    }

    if (goingUp) {
      yPositions[0] -= POINT_SIZE;
    } else if (goingDown) {
      yPositions[0] += POINT_SIZE;
    } else if (goingLeft) {
      xPositions[0] -= POINT_SIZE;
    } else if (goingRight) {
      xPositions[0] += POINT_SIZE;
    }
  }

  private void collisionDetection() {
    for (int i = points; i > 0; i--) {
      if (xPositions[0] == xPositions[i] && yPositions[0] == yPositions[i]) {
        isRunning = false;
      }
    }

    if ((xPositions[0] >= GRID_WIDTH) || (xPositions[0] < 0) || (yPositions[0] >= GRID_HEIGHT)
        || (yPositions[0] < 0)) {
      isRunning = false;
    }
  }

  private void appleLocator() {
    xApplePosition = (int)(Math.random() * GRID_WIDTH / POINT_SIZE) * POINT_SIZE;
    yApplePosition = (int)(Math.random() * GRID_HEIGHT / POINT_SIZE) * POINT_SIZE;
  }

  public void render(Graphics graphics) {
    if (isRunning) {
      graphics.setColor(Color.BLACK);
      
      graphics.drawImage(apple, xApplePosition, yApplePosition, this);

      ((Graphics2D) graphics).draw(mapGrid);
      
      for (int i = 0; i < points; i++) {
        if (i == 0) {
          graphics.drawImage(head, xPositions[i], yPositions[i], this);
        } else {
          graphics.drawImage(body, xPositions[i], yPositions[i], this);
        }
      }
      

      Toolkit.getDefaultToolkit().sync();
    } else {
     //  Snake.getSubject().setState(Arcade.ENDSNAKEMENU);
    }
  }

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
