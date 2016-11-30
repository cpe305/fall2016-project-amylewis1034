package arcade.src.main;

/**
 * Class that managers high scores for Snake and outputs the top 5.
 * 
 * @author Amy Lewis.
 * @version 11/28/16
 */
public class HighscoreManagerSnake {
  private static final String HS_FILE_SNAKE = "highscoresSnake.dat";
  private ManageHighscores hsManage;

  /**
   * Constructor that creates a new highscore manager for Snake.
   */
  public HighscoreManagerSnake() {
    hsManage = new ManageHighscores(HS_FILE_SNAKE);
  }

  public ManageHighscores getManageHighscores() {
    return hsManage;
  }
}
