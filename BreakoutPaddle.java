package arcade.src.main;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class BreakoutPaddle {
  private static final int PADDLE_INIT_X = 600;
  private static final int PADDLE_INIT_Y = 1000;
  
  private BreakoutGrid breakoutGrid;
  private int paddlePosX;
  private int paddlePosY;
  private int paddleVelocity;

  public BreakoutPaddle(BreakoutGrid breakoutGrid) {
    this.breakoutGrid = breakoutGrid;
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
    return new Rectangle(paddlePosX, paddlePosY, breakoutGrid.getPaddleWidth(), breakoutGrid.getPaddleHeight());
  }
  
  public void paddleMove() {

    paddlePosX += paddleVelocity;

    if (paddlePosX <= 0) {
      paddlePosX = 0;
    }

    if (paddlePosX >= breakoutGrid.getWidth() - breakoutGrid.getPaddleWidth()) {
      paddlePosX = breakoutGrid.getWidth() -breakoutGrid.getPaddleWidth();
    }
  }
}
