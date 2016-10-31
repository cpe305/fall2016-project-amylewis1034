package arcade.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends Collidable implements CollideObjectB {
  public static final int ENEMY_WIDTH = 80;
  public static final int ENEMY_HEIGHT = 45;
  public static final int RAND_VAR = 4;

  private double x1Coord;
  private double y1Coord;
  private BufferedImage enemy;
  private SpaceInvaders siGame;

  Random randNum = new Random();
  private int enemySpeed = (randNum.nextInt(RAND_VAR) + 1);

  public Enemy(double x1Coord, double y1Coord, SpaceInvaders siGame) {
    super(x1Coord, y1Coord);
    this.siGame = siGame;

    BufferedImageLoader buffLoader = new BufferedImageLoader();
    try {
      enemy = buffLoader.loadImage("/enemy.png");
      enemy = buffLoader.createResizedCopy(enemy, ENEMY_WIDTH, ENEMY_HEIGHT);
    } catch (Exception ex) {
      ex.printStackTrace();
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

  public void tick() {
    y1Coord += enemySpeed;

    if (y1Coord > siGame.getHeight()) {
      y1Coord = 0;
      x1Coord = randNum.nextInt(siGame.getWidth());
      enemySpeed = (randNum.nextInt(RAND_VAR) + 1);
    }
  }

  public void render(Graphics graphics) {
    graphics.drawImage(enemy, (int) x1Coord, (int) y1Coord, null);
  }
}
