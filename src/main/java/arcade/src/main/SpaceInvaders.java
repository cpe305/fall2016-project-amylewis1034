package arcade.src.main;

import arcade.src.main.ArcadeConcreteSubject.Arcade;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * Class that runs the game, Space Invaders.
 * 
 * @author Amy Lewis.
 * @version 11/28/16
 */
public class SpaceInvaders extends Canvas implements Runnable, ArcadeObserver {
  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = Logger.getLogger(SpaceInvaders.class.toString());
  public static final String TITLE = "Dad's Arcade";
  public static final int WIDTH = 1000;
  public static final int HEIGHT = 800;
  public static final int SCALE = 2;
  public static final int BILLION = 1000000000;
  public static final int BACK_POSITION = -50;
  public static final int BACK_REWIDTH = 2500;
  public static final int BACK_REHEIGHT = 1600;
  public static final int BACK_SHIFT = 450;
  public static final int X_PLAYER_POSITION = 1000;
  public static final int Y_PLAYER_POSITION = 1200;
  public static final int X_BULLET_POSITION = 19;
  public static final int Y_BULLET_POSITION = 5;
  private static final int SCORE_POSITION = 300;
  public static final double TICKS = 60.0;

  private transient Thread thread;
  private transient BufferedImage imageBuffer =
      new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
  private transient BufferedImage spaceBackground = null;
  private transient BufferedImage arcadeBackground = null;
  private transient BufferedImage snakeBackground = null;
  private transient BufferedImage breakBackground = null;
  private transient BufferedImage highscoreBackground = null;
  private transient StartMenu startMenu;
  private transient EndSpaceGameMenu endSpaceGameMenu;
  private transient EndSnakeMenu endSnakeMenu;
  private transient EndBreakoutMenu endBreakoutMenu;
  private transient HighscoreMenu highScoreMenu;
  private transient HelpMenu helpMenu;
  private transient HighscoreManagerSpace hsManager;
  private transient Player player;
  private transient Controller controller;

  private boolean isRunning = false;
  private boolean isShooting = false;
  private SnakeGrid snakeGrid;
  private BreakoutGrid breakoutGrid;
  private Arcade state;

  private int numEnemy;
  private int numEnemyKilled;
  private int health;
  private int score;

  public static ArcadeConcreteSubject subject; // NOSONAR

  public LinkedList<FriendlyCollideObjects> friendlyList; // NOSONAR
  public LinkedList<EnemyCollideObjects> enemyList; // NOSONAR

  public static ArcadeConcreteSubject getSubject() {
    return subject;
  }

  public void update() {
    state = subject.getState();
  }

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

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public int getSpaceScore() {
    return score;
  }

  public void setSpaceScore(int score) {
    this.score = score;
  }

  public HighscoreManagerSpace getHighscoreManagerSpace() {
    return hsManager;
  }

  /**
   * Any time there is a new game, these default values should be reset.
   */
  public void newGame() {
    numEnemy = 1;
    numEnemyKilled = 0;
    health = 200;
    score = 0;

    player.setXVel(0);
    player.setYVel(0);
    player.setXCoord(X_PLAYER_POSITION);
    player.setYCoord(Y_PLAYER_POSITION);

    controller.addEnemy(numEnemy);
  }

  /**
   * Initializes the background of space invaders game, the player, the enemies, and the
   * collide-able objects.
   */
  public void init() {
    this.requestFocus();
    this.addKeyListener(new KeyInput(this));
    this.addMouseListener(new MouseClickInput());

    BufferedImageLoader buffLoader = new BufferedImageLoader();
    try {
      spaceBackground = buffLoader.loadImage("/space_background.png");
      spaceBackground = buffLoader.createResizedCopy(spaceBackground, BACK_REWIDTH, BACK_REHEIGHT);
      arcadeBackground = buffLoader.loadImage("/arcade.png");
      arcadeBackground =
          buffLoader.createResizedCopy(arcadeBackground, BACK_REWIDTH, BACK_REHEIGHT);
      snakeBackground = buffLoader.loadImage("/snake_background.png");
      snakeBackground =
          buffLoader.createResizedCopy(snakeBackground, BACK_REWIDTH - BACK_SHIFT, BACK_REHEIGHT);
      breakBackground = buffLoader.loadImage("/break_background.png");
      breakBackground =
          buffLoader.createResizedCopy(breakBackground, BACK_REWIDTH + BACK_SHIFT, BACK_REHEIGHT);
      highscoreBackground = buffLoader.loadImage("/highscore_background.png");
      highscoreBackground =
          buffLoader.createResizedCopy(highscoreBackground, BACK_REWIDTH, BACK_REHEIGHT);
    } catch (Exception ex) {
      LOGGER.log(null, "Could not load images.", ex);
    }

    player = new Player(X_PLAYER_POSITION, Y_PLAYER_POSITION, this);
    hsManager = new HighscoreManagerSpace();
    controller = new Controller(this);

    newGame();

    subject = new ArcadeConcreteSubject();
    state = subject.getState();
    startMenu = new StartMenu(subject);
    snakeGrid = new SnakeGrid(subject, this);
    breakoutGrid = new BreakoutGrid(subject, this);
    endSpaceGameMenu = new EndSpaceGameMenu(subject);
    endSnakeMenu = new EndSnakeMenu(subject);
    endBreakoutMenu = new EndBreakoutMenu(subject);
    highScoreMenu = new HighscoreMenu(subject);
    helpMenu = new HelpMenu(subject);
    subject.registerObservers(this);
    subject.registerObservers(startMenu);
    subject.registerObservers(snakeGrid);
    subject.registerObservers(breakoutGrid);
    subject.registerObservers(endSpaceGameMenu);
    subject.registerObservers(endSnakeMenu);
    subject.registerObservers(endBreakoutMenu);
    subject.registerObservers(highScoreMenu);
    subject.registerObservers(helpMenu);

    controller.addFriendlyCollideObject(player);
    friendlyList = controller.getFriendlyCollideObjectList();
    enemyList = controller.getEnemyCollideObjectList();
  }

  /**
   * Allows the user to control the player with certain keys on the keyboard.
   * 
   * @param event the key that was pressed
   */
  public void keyPressed(KeyEvent event) {
    int keyPressed = event.getKeyCode();
    if (state == Arcade.SPACEINVADERS) {
      if (keyPressed == KeyEvent.VK_UP) {
        player.setYVel(-3);
      } else if (keyPressed == KeyEvent.VK_DOWN) {
        player.setYVel(3);
      } else if (keyPressed == KeyEvent.VK_LEFT) {
        player.setXVel(-3);
      } else if (keyPressed == KeyEvent.VK_RIGHT) {
        player.setXVel(3);
      } else if (keyPressed == KeyEvent.VK_SPACE && isShooting == false) {
        controller.addFriendlyCollideObject(new Bullet(player.getXCoord() + X_BULLET_POSITION,
            player.getYCoord() + Y_BULLET_POSITION));
        isShooting = true;
      }
    }
  }

  /**
   * Makes it so when a key is released, it no longer has an effect on the game.
   * 
   * @param event the key that was released
   */
  public void keyReleased(KeyEvent event) {
    int keyPressed = event.getKeyCode();
    if (state == Arcade.SPACEINVADERS) {
      if (keyPressed == KeyEvent.VK_UP || keyPressed == KeyEvent.VK_DOWN) {
        player.setYVel(0);
      } else if (keyPressed == KeyEvent.VK_LEFT || keyPressed == KeyEvent.VK_RIGHT) {
        player.setXVel(0);
      } else if (keyPressed == KeyEvent.VK_SPACE) {
        isShooting = false;
      }
    }
  }

  /**
   * Method that starts the Java application.
   */
  public synchronized void start() {
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
      LOGGER.log(null, "Could not join thread.", ex);
    }

    System.exit(status); // NOSONAR

  }

  private void tick() {
    if (state == Arcade.SPACEINVADERS) {
      controller.tick();
      player.tick();

      if (numEnemyKilled >= numEnemy) {
        numEnemyKilled = 0;
        numEnemy++;
        controller.addEnemy(numEnemy);
      }
    }
  }

  private void render() {
    BufferStrategy buffStrategy = this.getBufferStrategy();
    if (buffStrategy == null) {
      createBufferStrategy(3);
      return;
    }

    Graphics graphics = buffStrategy.getDrawGraphics();

    if (state == Arcade.SPACEINVADERS) {
      graphics.drawImage(imageBuffer, 0, 0, getWidth(), getHeight(), this);
      graphics.drawImage(spaceBackground, BACK_POSITION, 0, this);

      graphics.setColor(Color.GRAY);
      graphics.fillRect(5, 5, 200, 50);

      graphics.setColor(Color.GREEN);
      graphics.fillRect(5, 5, health, 50);

      graphics.setColor(Color.WHITE);
      graphics.drawRect(5, 5, 200, 50);

      Font fnt = new Font("arial", Font.BOLD, 50);
      graphics.setFont(fnt);
      graphics.setColor(Color.YELLOW);
      graphics.drawString("Score: ", SpaceInvaders.WIDTH * 2 - SCORE_POSITION, 60);
      graphics.drawString(((Integer) score).toString(), SpaceInvaders.WIDTH * 2 - 100, 60);

      player.render(graphics);
      controller.render(graphics);
    } else if (state == Arcade.SNAKE) {
      graphics.drawImage(imageBuffer, 0, 0, getWidth(), getHeight(), this);
      graphics.drawImage(snakeBackground, BACK_POSITION, 0, this);
      snakeGrid.render(graphics);
    } else if (state == Arcade.BREAKOUT) {
      graphics.drawImage(imageBuffer, 0, 0, getWidth(), getHeight(), this);
      graphics.drawImage(breakBackground, BACK_POSITION, 0, this);
      breakoutGrid.render(graphics);
    } else if (state == Arcade.STARTMENU) {
      graphics.drawImage(imageBuffer, 0, 0, getWidth(), getHeight(), this);
      graphics.drawImage(arcadeBackground, BACK_POSITION, 0, this);
      startMenu.render(graphics);
    } else if (state == Arcade.HIGHSCORES) {
      graphics.drawImage(imageBuffer, 0, 0, getWidth(), getHeight(), this);
      graphics.drawImage(highscoreBackground, BACK_POSITION, 0, this);
      highScoreMenu.render(graphics);
    } else if (state == Arcade.ENDSPACEGAMEMENU) {
      graphics.drawImage(imageBuffer, 0, 0, getWidth(), getHeight(), this);
      graphics.drawImage(spaceBackground, BACK_POSITION, 0, this);
      endSpaceGameMenu.render(graphics);
    } else if (state == Arcade.ENDSNAKEMENU) {
      graphics.drawImage(imageBuffer, 0, 0, getWidth(), getHeight(), this);
      graphics.drawImage(snakeBackground, BACK_POSITION, 0, this);
      endSnakeMenu.render(graphics);
    } else if (state == Arcade.ENDBREAKOUTMENU) {
      graphics.drawImage(imageBuffer, 0, 0, getWidth(), getHeight(), this);
      graphics.drawImage(breakBackground, BACK_POSITION, 0, this);
      endBreakoutMenu.render(graphics);
    } else if (state == Arcade.HELP) {
      graphics.drawImage(imageBuffer, 0, 0, getWidth(), getHeight(), this);
      graphics.drawImage(highscoreBackground, BACK_POSITION, 0, this);
      helpMenu.render(graphics);
    }

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
}
