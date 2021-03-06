package arcade.src.main;

import java.awt.Rectangle;

/**
 * Class that creates the ball for the game, Breakout.
 * 
 * @author Amy Lewis.
 * @version 11/28/16
 */
public class BreakoutBall {
  private static final int BALL_INIT_X = 590;
  private static final int BALL_INIT_Y = 990;

  private BreakoutGrid breakoutGrid;
  private int ballPosX;
  private int ballPosY;
  private int ballVelocityX;
  private int ballVelocityY;

  /**
   * Constructor for the ball to be used in the game Breakout.
   * 
   * @param breakoutGrid represents the game Breakout
   */
  public BreakoutBall(BreakoutGrid breakoutGrid) {
    this.breakoutGrid = breakoutGrid;
    ballVelocityX = 0;
    ballVelocityY = 0;
    init();
  }

  private void init() {
    ballPosX = BALL_INIT_X;
    ballPosY = BALL_INIT_Y;
  }

  public int getBallPosX() {
    return ballPosX;
  }
  
  public void setBallPosX(int ballPosX) {
    this.ballPosX = ballPosX;
  }

  public int getBallPosY() {
    return ballPosY;
  }
  
  public void setBallPosY(int ballPosY) {
    this.ballPosY = ballPosY;
  }

  public int getBallVelocityX() {
    return ballVelocityX;
  }

  public void setBallVelocityX(int ballVelocityX) {
    this.ballVelocityX = ballVelocityX;
  }

  public int getBallVelocityY() {
    return ballVelocityY;
  }

  public void setBallVelocityY(int ballVelocityY) {
    this.ballVelocityY = ballVelocityY;
  }

  public Rectangle getRectBounds() {
    return new Rectangle(ballPosX, ballPosY, breakoutGrid.getBallDiameter(),
        breakoutGrid.getBallDiameter());
  }

  /**
   * Method that controls how the ball moves in the game Breakout.
   */
  public void ballMove() {
    ballPosX += ballVelocityX;
    ballPosY += ballVelocityY;

    if (ballPosY == 0) {
      setBallVelocityY(10);
    }

    if (ballPosX == 0) {
      setBallVelocityX(10);
    }

    if (ballPosX == breakoutGrid.getGridWidth() - breakoutGrid.getBallDiameter()) {
      setBallVelocityX(-10);
    }
  }
}
