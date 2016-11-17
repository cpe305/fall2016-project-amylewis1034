import static org.junit.Assert.assertEquals;

import arcade.src.main.Bullet;
import org.junit.Test;

public class Tests {
  
  @Test
  public void bulletWidthTest() {
    Bullet bullet = new Bullet(10, 10);
    int bulletWidth = 4;  
    int width = bullet.getWidth();
    
    assertEquals(width, bulletWidth);
  }

  @Test
  public void bulletHeightTest() {
    Bullet bullet = new Bullet(10, 10);
    int bulletHeight = 9;
    int height = bullet.getHeight();
    
    assertEquals(height, bulletHeight);
  }
  
  @Test
  public void bulletSpeedTest() {
    Bullet bullet = new Bullet(10, 10);
    int bulletSpeed = 10;
    int ypSpeed = (int)bullet.getYCoord() - bulletSpeed;
    
    assertEquals(ypSpeed, 0);
  }
}
