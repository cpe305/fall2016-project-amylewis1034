package arcade.src.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

/**
 * Class that managers high scores for Snake and outputs the top 5.
 * 
 * @author Amy Lewis.
 * @version 11/28/16
 */
public class HighscoreManagerSnake {
  private static final Logger LOGGER = Logger.getLogger(HighscoreManagerSnake.class.toString());
  private ArrayList<Score> scores;
  private static final String HS_FILE_SNAKE = "highscoresSnake.dat";

  ObjectOutputStream outStream = null;
  ObjectInputStream inStream = null;

  /**
   * Constructor that initializes the score list.
   */
  public HighscoreManagerSnake() {
    scores = new ArrayList<Score>();
  }

  /**
   * Method to get and sort the high scores.
   * 
   * @return list of high scores
   */
  public ArrayList<Score> getHighscores() {
    loadScoreFile();
    sort();
    return scores;
  }

  /**
   * Method to sort the scores in order of highest to lowest.
   */
  public void sort() {
    ScoreComparator comparator = new ScoreComparator();
    Collections.sort(scores, comparator);
  }

  /**
   * Method to add a new high score to the list.
   * 
   * @param name name of the user with the new high score
   * @param score score of the user with the new high score
   */
  public void addScore(String name, int score) {
    loadScoreFile();
    scores.add(new Score(name, score));
    updateScoreFile();
  }

  /**
   * Method to lead the file where the high scores are stored.
   */
  @SuppressWarnings("unchecked")
  public void loadScoreFile() {
    try {
      inStream = new ObjectInputStream(new FileInputStream(HS_FILE_SNAKE));
      scores = (ArrayList<Score>) inStream.readObject();
    } catch (ClassNotFoundException ex) {
      LOGGER.log(null, "Class Not Found Error: " + ex.getMessage(), ex);
    } catch (FileNotFoundException ex) {
      LOGGER.log(null, "File Not Found Error: " + ex.getMessage(), ex);
    } catch (IOException ex) {
      LOGGER.log(null, "IO Error: " + ex.getMessage(), ex);
    } finally {
      try {
        if (inStream != null) {
          inStream.close();
        }
      } catch (IOException ex) {
        LOGGER.log(null, "IO Error: " + ex.getMessage(), ex);
      }
    }
  }

  /**
   * Method to update the file where the high scores are stored with the new scores.
   */
  public void updateScoreFile() {
    try {
      outStream = new ObjectOutputStream(new FileOutputStream(HS_FILE_SNAKE));
      outStream.writeObject(scores);
    } catch (FileNotFoundException ex) {
      LOGGER.log(null, "File Not Found Error: " + ex.getMessage(), ex);
    } catch (IOException ex) {
      LOGGER.log(null, "IO Error: " + ex.getMessage(), ex);
    } finally {
      try {
        if (outStream != null) {
          outStream.flush();
          outStream.close();
        }
      } catch (IOException ex) {
        LOGGER.log(null, "IO Error: " + ex.getMessage(), ex);
      }
    }
  }

  /**
   * Method that returns a String of all of the high scores and the names of the users holding those
   * scores.
   * 
   * @return a list of users and high scores
   */
  public ArrayList<String> getHighscoreName() {
    String highscore;
    ArrayList<String> output = new ArrayList<String>();
    final int maxNumScores = 5;

    ArrayList<Score> scores;
    scores = getHighscores();

    int numScores = scores.size();
    if (numScores > maxNumScores) {
      numScores = maxNumScores;
    }
    for (int i = 0; i < numScores; i++) {
      highscore =
          (i + 1) + ".    " + scores.get(i).getScoreName() + "      " + scores.get(i).getScore();
      output.add(highscore);
    }
    return output;
  }
}
