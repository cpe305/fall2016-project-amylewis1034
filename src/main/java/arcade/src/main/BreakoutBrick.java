package arcade.src.main;

import java.awt.Rectangle;

public class BreakoutBrick {
  private BreakoutGrid breakoutGrid;
  private int brickPosX;
  private int brickPosY;
  private boolean active;

  public BreakoutBrick(BreakoutGrid breakoutGrid, int brickPosX, int brickPosY) {
    this.breakoutGrid = breakoutGrid;
    this.brickPosX = brickPosX;
    this.brickPosY = brickPosY;
    active = true;
  }

  public int getBrickPosX() {
    return brickPosX;
  }
  
  public int getBrickPosY() {
    return brickPosY;
  }
  
  public boolean getIsActive() {
    return active;
  }
  
  public void setIsActive(boolean active) {
    this.active = active;
  }
  
  public Rectangle getRectBounds() {
    return new Rectangle(brickPosX, brickPosY, breakoutGrid.getBrickWidth(), breakoutGrid.getBrickHeight());
  }
}
