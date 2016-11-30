package arcade.src.main;

/**
 * Class that managers high scores for Snake and outputs the top 5.
 * 
 * @author Amy Lewis.
 * @version 11/28/16
 */
public class HighscoreManagerSpace {
  private static final String HS_FILE_SPACE = "highscoresSpace.dat";
  private ManageHighscores hsManage;

  /**
   * Constructor that creates a new highscore manager for Snake.
   */
  public HighscoreManagerSpace() {
    hsManage = new ManageHighscores(HS_FILE_SPACE);
  }

  public ManageHighscores getManageHighscores() {
    return hsManage;
  }
}
