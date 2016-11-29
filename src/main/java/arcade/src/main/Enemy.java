package arcade.src.main;

import arcade.src.main.ArcadeConcreteSubject.Arcade;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 * Class to create enemies for the game, Space Invaders. The number of enemies increases as the
 * Player advances in the game.
 * 
 * @author Amy Lewis.
 * @see Enemy
 * @version 10/30/16
 */
public class Enemy extends Collidable implements CollideObjectB {
  public static final int ENEMY_WIDTH = 80;
  public static final int ENEMY_HEIGHT = 45;
  public static final int RAND_VAR = 4;
  public static final int ENEMY_POSITION = -10;
  private static final Logger logger = Logger.getLogger(SpaceInvaders.class.toString());

  private BufferedImage enemy;
  private SpaceInvaders siGame;
  private Controller controller;

  Random randNum = new Random();
  private int enemySpeed = (randNum.nextInt(RAND_VAR) + 1);

  /**
   * Constructor for Enemy class that loads an image for an enemy and puts it in a random initial
   * position.
   * 
   * @param x1Coord the random initial x position for the player
   * @param y1Coord the random initial y position for the player
   * @param siGame a reference to the Space Invaders game
   * @param controller a reference to the controller for the game
   */
  public Enemy(double x1Coord, double y1Coord, SpaceInvaders siGame, Controller controller) {
    super(x1Coord, y1Coord);
    this.siGame = siGame;
    this.controller = controller;

    BufferedImageLoader buffLoader = new BufferedImageLoader();
    try {
      enemy = buffLoader.loadImage("/enemy.png");
      enemy = buffLoader.createResizedCopy(enemy, ENEMY_WIDTH, ENEMY_HEIGHT);
    } catch (Exception ex) {
      logger.warning(ex.toString());
    }
  }

  public int getWidth() {
    return ENEMY_WIDTH;
  }

  public int getHeight() {
    return ENEMY_HEIGHT;
  }

  public double getXCoord() {
    return x1Coord;
  }

  public double getYCoord() {
    return y1Coord;
  }

  public Rectangle getRectBounds() {
    return new Rectangle((int) x1Coord, (int) y1Coord, ENEMY_WIDTH, ENEMY_HEIGHT);
  }

  /**
   * Method that gives each enemy a random speed and x coordinate. It also resets the enemy at the
   * top of the screen with a new speed and position if that enemy was not killed by the Player.
   */
  public void tick() {
    y1Coord += enemySpeed;

    if (y1Coord > siGame.getHeight()) {
      y1Coord = 0;
      x1Coord = randNum.nextInt(siGame.getWidth() - ENEMY_WIDTH);
      enemySpeed = (randNum.nextInt(RAND_VAR) + 1);
    }

    for (int i = 0; i < siGame.friendlyList.size(); i++) {
      CollideObjectA tmpObjA = siGame.friendlyList.get(i);

      if (CollisionPhysics.isCollision(tmpObjA, this)) {
        if (tmpObjA instanceof Player) {
          controller.removeCollideObjectB(this);
          siGame.setHealth(siGame.getHealth() - 10);
          if (siGame.getHealth() == 0) {
            ArrayList<Score> highscores = siGame.getHighscoreManagerSpace().getHighscores();
            if (highscores.size() < 5 || siGame.getSpaceScore() > highscores.get(4).getScore()) {

              String name = JOptionPane.showInputDialog(
                  "Congratulations! You set a new highscore! Please enter your name.");
              siGame.getHighscoreManagerSpace().addScore(name, siGame.getSpaceScore());
            }
            SpaceInvaders.getSubject().setState(Arcade.ENDSPACEGAMEMENU);
            SpaceInvaders.getSubject().notifyObservers();
            siGame.enemyList.clear();
            siGame.newGame();
          } else {
            siGame.setNumEnemyKilled(siGame.getNumEnemyKilled() + 1);
          }
        } else {
          controller.removeCollideObjectB(this);
          controller.removeCollideObjectA(tmpObjA);
          siGame.setSpaceScore(siGame.getSpaceScore() + 1);
          siGame.setNumEnemyKilled(siGame.getNumEnemyKilled() + 1);
        }
      }
    }
  }

  public void render(Graphics graphics) {
    graphics.drawImage(enemy, (int) x1Coord, (int) y1Coord, null);
  }
}
