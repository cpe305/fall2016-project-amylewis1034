package arcade.src.main;

/**
 * Class that managers high scores for Breakout and outputs the top 5.
 * 
 * @author Amy Lewis.
 * @version 11/28/16
 */
public class HighscoreManagerBreakout {
  private static final String HS_FILE_BREAKOUT = "highscoresBreakout.dat";
  private ManageHighscores hsManage;

  /**
   * Constructor that creates a new highscore manager for Snake.
   */
  public HighscoreManagerBreakout() {
    hsManage = new ManageHighscores(HS_FILE_BREAKOUT);
  }

  public ManageHighscores getManageHighscores() {
    return hsManage;
  }
}
