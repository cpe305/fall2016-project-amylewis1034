package highscores;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Class that managers high scores for the arcade and outputs the top 10.
 * 
 * @author Amy Lewis.
 * @version 10/31/16
 */
public class HighscoreManager {

  // private ArrayList<Score> scores;

  // private static final String HS_FILE = "scores.dat";

  ObjectOutputStream outStream = null;
  ObjectInputStream inStream = null;

  public HighscoreManager() {
    // scores = new ArrayList<Score>();
  }

  public ArrayList<Score> getScores() {
    // TODO load and return scores
    return null;
  }

  public void sort() {
    // TODO sort scores, probably make private
  }

  public void addScore(String name, int score) {
    // TODO add scores to list
  }

  public void loadScoreFile() {
    // TODO try and catch to load file
  }

  public void updateScoreFile() {
    // TODO instead of reading file it writes arraylist to file
  }

  public String getHighscoreName() {
    // TODO return top 10 player high scores
    return null;
  }
}
