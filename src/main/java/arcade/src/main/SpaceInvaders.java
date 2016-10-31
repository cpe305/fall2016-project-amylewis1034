package arcade.src.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JFrame;

/**
 * Class that runs the game, Space Invaders.
 * 
 * @author Amy Lewis.
 * @see SpaceInvaders
 * @version 10/30/16
 */

public class SpaceInvaders extends Canvas implements Runnable {

  private static final long serialVersionUID = 1L;
  public static final String TITLE = "Space Invaders";
  public static final int HEIGHT = 480;
  public static final int WIDTH = 640;
  public static final int SCALE = 2;
  public static final int BILLION = 1000000000;
  public static final int BACK_POSITION = -50;
  public static final int BACK_REWIDTH = 1400;
  public static final int BACK_REHEIGHT = 1100;
  public static final int X_PLAYER_POSITION = 640;
  public static final int Y_PLAYER_POSITION = 700;
  public static final int X_BULLET_POSITION = 19;
  public static final int Y_BULLET_POSITION = 5;
  public static final double TICKS = 60.0;

  private Thread thread;
  private boolean isRunning = false;
  private boolean isShooting = false;
  private BufferedImage imageBuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
  private BufferedImage spaceBackground = null;

  private Player player;
  private Controller controller;

  private int numEnemy = 1;
  private int numEnemyKilled = 0;

  public LinkedList<CollideObjectA> friendlyList;
  public LinkedList<CollideObjectB> enemyList;

  public int getNumEnemy() {
    return numEnemy;
  }

  public void setNumEnemy(int numEnemy) {
    this.numEnemy = numEnemy;
  }

  public int getNumEnemyKilled() {
    return numEnemyKilled;
  }

  public void setNumEnemyKilled(int numEnemyKilled) {
    this.numEnemyKilled = numEnemyKilled;
  }

  public String getTitle() {
    return TITLE;
  }

  public int getWidth() {
    return WIDTH * SCALE;
  }

  public int getHeight() {
    return HEIGHT * SCALE;
  }

  /**
   * Initializes the background of space invaders game, the player, the enemies, and the
   * collide-able objects.
   */
  public void init() {
    requestFocus();
    addKeyListener(new KeyInput(this));

    BufferedImageLoader buffLoader = new BufferedImageLoader();
    try {
      spaceBackground = buffLoader.loadImage("/space_background.png");
      spaceBackground = buffLoader.createResizedCopy(spaceBackground, BACK_REWIDTH, BACK_REHEIGHT);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    player = new Player(X_PLAYER_POSITION, Y_PLAYER_POSITION, this);
    controller = new Controller(this);
    controller.addEnemy(numEnemy);

    friendlyList = controller.getCollideObjectAList();
    enemyList = controller.getCollideObjectBList();
  }

  /**
   * Allows the user to control the player with certain keys on the keyboard.
   * 
   * @param event the key that was pressed
   */
  public void keyPressed(KeyEvent event) {
    int keyPressed = event.getKeyCode();

    if (keyPressed == KeyEvent.VK_UP) {
      player.setYVel(-5);
    } else if (keyPressed == KeyEvent.VK_DOWN) {
      player.setYVel(5);
    } else if (keyPressed == KeyEvent.VK_LEFT) {
      player.setXVel(-5);
    } else if (keyPressed == KeyEvent.VK_RIGHT) {
      player.setXVel(5);
    } else if (keyPressed == KeyEvent.VK_SPACE && isShooting == false) {
      controller.addCollideObjectA(new Bullet(player.getXCoord() + X_BULLET_POSITION,
          player.getYCoord() + Y_BULLET_POSITION, this));
      isShooting = true;
    }
  }

  /**
   * Makes it so when a key is released, it no longer has an effect on the game.
   * 
   * @param event the key that was released
   */
  public void keyReleased(KeyEvent event) {
    int keyPressed = event.getKeyCode();

    if (keyPressed == KeyEvent.VK_UP) {
      player.setYVel(0);
    } else if (keyPressed == KeyEvent.VK_DOWN) {
      player.setYVel(0);
    } else if (keyPressed == KeyEvent.VK_LEFT) {
      player.setXVel(0);
    } else if (keyPressed == KeyEvent.VK_RIGHT) {
      player.setXVel(0);
    } else if (keyPressed == KeyEvent.VK_SPACE) {
      isShooting = false;
    }
  }

  private synchronized void start() {
    if (isRunning) {
      return;
    }

    isRunning = true;

    thread = new Thread(this);
    thread.start();
  }

  private synchronized void stop() {
    if (isRunning == false) {
      return;
    }

    int status = 0;
    isRunning = false;

    try {
      thread.join();
      status = 1;
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    System.exit(status);

  }

  private void tick() {
    player.tick();
    controller.tick();
  }

  private void render() {
    BufferStrategy buffStrategy = this.getBufferStrategy();
    if (buffStrategy == null) {
      createBufferStrategy(3);
      return;
    }

    Graphics graphics = buffStrategy.getDrawGraphics();

    graphics.drawImage(imageBuffer, 0, 0, getWidth(), getHeight(), this);
    graphics.drawImage(spaceBackground, BACK_POSITION, 0, this);
    player.render(graphics);
    controller.render(graphics);

    graphics.dispose();
    buffStrategy.show();
  }

  /**
   * Initiates, renders, and stops the program based on time passed.
   */
  public void run() {
    init();

    long prevTime = System.nanoTime();
    final double ticks = TICKS;
    double nanoSecs = BILLION / ticks;
    double timePassed = 0;

    while (isRunning) {
      long currentTime = System.nanoTime();
      timePassed += (currentTime - prevTime) / nanoSecs;
      prevTime = currentTime;

      if (timePassed >= 1) {
        timePassed--;
        tick();
      }

      render();
    }
    stop();
  }

  /**
   * The method that drives the entire game.
   * 
   * @param args holds any input from the command line
   */
  public static void main(String[] args) {
    SpaceInvaders siGame = new SpaceInvaders();

    siGame.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
    siGame.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
    siGame.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

    JFrame titleFrame = new JFrame(siGame.getTitle());
    titleFrame.add(siGame);
    titleFrame.pack();
    titleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    titleFrame.setLocationRelativeTo(null);
    titleFrame.setVisible(true);
    titleFrame.setResizable(false);

    siGame.start();
  }
}
