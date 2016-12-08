package arcade.src.main;

import java.awt.Rectangle;

/**
 * Class that creates the bricks for the game, Breakout.
 * 
 * @author Amy Lewis.
 * @version 11/28/16
 */
public class BreakoutPaddle {
  private static final int PADDLE_INIT_X = 600;
  private static final int PADDLE_INIT_Y = 1000;

  private BreakoutGrid breakoutGrid;
  private int paddlePosX;
  private int paddlePosY;
  private int paddleVelocity;

  /**
   * Constructor for the paddle to be used in the game Breakout.
   * 
   * @param breakoutGrid represents the game Breakout
   */
  public BreakoutPaddle(BreakoutGrid breakoutGrid) {
    this.breakoutGrid = breakoutGrid;
    paddleVelocity = 0;
    init();
  }

  private void init() {
    paddlePosX = PADDLE_INIT_X;
    paddlePosY = PADDLE_INIT_Y;
  }

  public int getPaddlePosX() {
    return paddlePosX;
  }

  public int getPaddlePosY() {
    return paddlePosY;
  }

  public void setPaddleVelocity(int paddleVelocity) {
    this.paddleVelocity = paddleVelocity;
  }

  public Rectangle getRectBounds() {
    return new Rectangle(paddlePosX, paddlePosY, breakoutGrid.getPaddleWidth(),
        breakoutGrid.getPaddleHeight());
  }

  /**
   * Method that controls how the paddle moves in the game Breakout.
   */
  public void paddleMove() {

    paddlePosX += paddleVelocity;

    if (paddlePosX <= 0) {
      paddlePosX = 0;
    }

    if (paddlePosX >= breakoutGrid.getWidth() - breakoutGrid.getPaddleWidth()) {
      paddlePosX = breakoutGrid.getWidth() - breakoutGrid.getPaddleWidth();
    }
  }
}
