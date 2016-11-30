import static org.junit.Assert.assertEquals;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.junit.Test;

import arcade.src.main.ArcadeConcreteSubject;
import arcade.src.main.ArcadeConcreteSubject.Arcade;
import arcade.src.main.ArcadeObserver;
import arcade.src.main.Bullet;
import arcade.src.main.SpaceInvaders;

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
  
  @Test
  public void rectangleBoundsTest() {
    Bullet bullet = new Bullet(10, 10);
    int xCoord = (int)bullet.getXCoord();
    int yCoord = (int)bullet.getYCoord();
    
    Rectangle rect = new Rectangle(xCoord, yCoord, 10, 20);
    Rectangle rect2 = bullet.getRectBounds(10, 20);
    
    assertEquals(rect, rect2);
  }
  
  @Test
  public void observersTest() {
    ArcadeConcreteSubject subject = new ArcadeConcreteSubject();
    ArrayList<ArcadeObserver> observers = new ArrayList<ArcadeObserver>();
    Arcade state = subject.getState();
    ArcadeObserver siGame = new SpaceInvaders();
    
    subject.registerObservers(siGame);
    subject.setState(Arcade.HIGHSCORES);
    subject.unRegisterObservers(siGame);
    
    assertEquals(observers.size(), 0);
  }
}
