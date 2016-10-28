package arcade.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bullet extends Collidable implements CollideObjectA {
  public static final int BULLET_WIDTH = 4;
  public static final int BULLET_HEIGHT = 9;
  public static final int BULLET_SPEED = 10;
  
  private SpaceInvaders siGame;
  private double x1Coord;
  private double y1Coord;
  private BufferedImage bullet;

  public Bullet(double x1Coord, double y1Coord, SpaceInvaders siGame) {
    super(x1Coord, y1Coord);
    this.x1Coord = x1Coord;
    this.y1Coord = y1Coord;
    this.siGame = siGame; 
    
    BufferedImageLoader buffLoader = new BufferedImageLoader();
    try {
      bullet = buffLoader.loadImage("/bullet.png");
      bullet = buffLoader.createResizedCopy(bullet, BULLET_WIDTH, BULLET_HEIGHT);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
  
  public int getWidth() {
    return BULLET_WIDTH;
  }
  
  public int getHeight() {
    return BULLET_HEIGHT;
  }

  public double getXCoord() {
    return x1Coord;
  }

  public double getYCoord() {
    return y1Coord;
  }

  public Rectangle getRectBounds() {
    return new Rectangle((int) x1Coord, (int) y1Coord, BULLET_WIDTH, BULLET_HEIGHT);
  } 
  
  public void tick() {
    y1Coord -= BULLET_SPEED;
    
    if (CollisionPhysics.Collision(this, siGame.enemyList)) {
      System.out.println("COLLISION");
    }
  }

  public void render(Graphics graphics) {
    graphics.drawImage(bullet, (int) x1Coord, (int) y1Coord, null);
  }
}
