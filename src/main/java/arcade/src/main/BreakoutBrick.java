package arcade.src.main;

import java.awt.Rectangle;

/**
 * Class that creates the bricks for the game, Breakout.
 * 
 * @author Amy Lewis.
 * @version 11/28/16
 */
public class BreakoutBrick {
  private BreakoutGrid breakoutGrid;
  private int brickPosX;
  private int brickPosY;
  private boolean active;

  /**
   * Constructor to create bricks for the game Breakout.
   * 
   * @param breakoutGrid represents the game Breakout
   * @param brickPosX represents the x position for a brick
   * @param brickPosY represents the y position for a brick
   */
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
    return new Rectangle(brickPosX, brickPosY, breakoutGrid.getBrickWidth(),
        breakoutGrid.getBrickHeight());
  }
}
