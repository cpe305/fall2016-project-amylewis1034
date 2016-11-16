import static org.junit.Assert.*;

import org.junit.Test;

import arcade.src.main.Bullet;

public class Tests {
  
  @Test
  public void BulletWidthTest() {
    Bullet bullet = new Bullet(10, 10);
    int bulletWidth = 4;  
    int width = bullet.getWidth();
    
    assertEquals(width, bulletWidth);
  }

  @Test
  public void BulletHeightTest() {
    Bullet bullet = new Bullet(10, 10);
    int bulletHeight = 9;
    int height = bullet.getHeight();
    
    assertEquals(height, bulletHeight);
  }
  
  @Test
  public void BulletSpeedTest() {
    Bullet bullet = new Bullet(10, 10);
    int bulletSpeed = 10;
    int yPosAfterSpeed = (int)bullet.getYCoord() - bulletSpeed;
    
    assertEquals(yPosAfterSpeed, 0);
  }
}
