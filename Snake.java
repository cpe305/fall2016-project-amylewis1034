package arcade.src.main;

public class Snake {
  private SnakeGrid snake;
  private int[] xposTotal;
  private int[] yposTotal;
  private int pointSize;

  /**
   * Constructor for snake to be used for the game Snake.
   * 
   * @param snake represents the game Snake
   */
  public Snake(SnakeGrid snake) {
    this.snake = snake;
    xposTotal = snake.getXposTotal();
    yposTotal = snake.getYposTotal();
    pointSize = snake.getPointSize();
  }

  /**
   * Method that moves the snake after the user uses key events.
   */
  public void moveSnake() {
    for (int i = snake.getSnakeScore(); i > 0; i--) {
      xposTotal[i] = xposTotal[i - 1];
      yposTotal[i] = yposTotal[i - 1];
    }

    if (snake.isGoingUp()) {
      yposTotal[0] -= pointSize;
    } else if (snake.isGoingDown()) {
      yposTotal[0] += pointSize;
    } else if (snake.isGoingLeft()) {
      xposTotal[0] -= pointSize;
    } else if (snake.isGoingRight()) {
      xposTotal[0] += pointSize;
    }
  }

  /**
   * Method to detect if the snake collided with a wall.
   */
  public void collisionDetection() {
    for (int i = snake.getSnakeScore(); i > 0; i--) {
      if (xposTotal[0] == xposTotal[i] && yposTotal[0] == yposTotal[i]) {
        snake.setRunning(false);
      }
    }

    if ((xposTotal[0] >= snake.getGridWidth()) || (xposTotal[0] < 0)
        || (yposTotal[0] >= snake.getGridHeight()) || (yposTotal[0] < 0)) {
      snake.setRunning(false);
    }
  }

}
