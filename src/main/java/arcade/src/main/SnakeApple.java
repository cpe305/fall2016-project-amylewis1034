package arcade.src.main;

public class SnakeApple {

  private SnakeGrid snake;
  private int xposApple;
  private int yposApple;
  private int[] xposTotal;
  private int[] yposTotal;

  public SnakeApple(SnakeGrid snake) {
    this.snake = snake;
    xposTotal = snake.getXposTotal();
    yposTotal = snake.getYposTotal();
  }
  
  public int getXposApple() {
    return xposApple;
  }
  
  public int getYposApple() {
    return yposApple;
  }

  public void appleLocator() {
    xposApple =
        (int) (Math.random() * snake.getGridWidth() / snake.getPointSize()) * snake.getPointSize(); // NOSONAR
    yposApple =
        (int) (Math.random() * snake.getGridHeight() / snake.getPointSize()) * snake.getPointSize(); // NOSONAR
  }

  public void foundApple() {
    if (xposTotal[0] == xposApple && yposTotal[0] == yposApple) {
      snake.setSnakeScore(snake.getSnakeScore() + 1);
      appleLocator();
    }
  }
}
