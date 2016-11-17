package highscores;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that managers high scores for the arcade and outputs the top 10.
 * 
 * @author Amy Lewis.
 * @version 10/31/16
 */
public class HighscoreManager {

  private ArrayList<Score> scores;

  private static final String HS_FILE = "scores.dat";

  ObjectOutputStream outStream = null;
  ObjectInputStream inStream = null;

  public HighscoreManager() {
    scores = new ArrayList<Score>();
  }

  /**
   * Method to get and sort the high scores.
   * 
   * @return list of high scores
   */
  public ArrayList<Score> getScores() {
    loadScoreFile();
    sort();
    return scores;
  }

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
      inStream = new ObjectInputStream(new FileInputStream(HS_FILE));
      scores = (ArrayList<Score>) inStream.readObject();
    } catch (ClassNotFoundException ex) {
      System.out.println("Class Not Found Error: " + ex.getMessage());
    } catch (FileNotFoundException ex) {
      System.out.println("File Not Found Error: " + ex.getMessage());
    } catch (IOException ex) {
      System.out.println("IO Error: " + ex.getMessage());
    } finally {
      try {
        if (outStream != null) {
          outStream.flush();
          outStream.close();
        }
      } catch (IOException ex) {
        System.out.println("IO Error: " + ex.getMessage());
      }
    }
  }

  /**
   * Method to update the file where the high scores are stored with the new scores.
   */
  public void updateScoreFile() {
    try {
      outStream = new ObjectOutputStream(new FileOutputStream(HS_FILE));
      outStream.writeObject(scores);
    } catch (FileNotFoundException ex) {
      System.out.println("File Not Found Error: " + ex.getMessage());
    } catch (IOException ex) {
      System.out.println("IO Error: " + ex.getMessage());
    } finally {
      try {
        if (outStream != null) {
          outStream.flush();
          outStream.close();
        }
      } catch (IOException ex) {
        System.out.println("[Update] Error: " + ex.getMessage());
      }
    }
  }

  /**
   * Method that returns a String of all of the high scores and the names of the users
   * holding those scores.
   * 
   * @return String of users and high scores
   */
  public String getHighscoreName() {
    String highscore = "";
    final int maxNumScores = 10;

    ArrayList<Score> scores;
    scores = getScores();

    int numScores = scores.size();
    if (numScores > maxNumScores) {
      numScores = maxNumScores;
    }
    for (int i = 0; i < numScores; i++) {
      highscore +=
          (i + 1) + ".\t" + scores.get(i).getScoreName() + "\t\t" + scores.get(i).getScore() + "\n";
    }
    return highscore;
  }
}
