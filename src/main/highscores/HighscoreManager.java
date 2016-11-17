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

  public ArrayList<Score> getScores() {
    loadScoreFile();
    sort();
    return scores;
  }

  public void sort() {
    ScoreComparator comparator = new ScoreComparator();
    Collections.sort(scores, comparator);
  }

  public void addScore(String name, int score) {
    loadScoreFile();
    scores.add(new Score(name, score));
    updateScoreFile();
  }

  @SuppressWarnings("unchecked")
  public void loadScoreFile() {
    try {
      inStream = new ObjectInputStream(new FileInputStream(HS_FILE));
      scores = (ArrayList<Score>) inStream.readObject();
    } catch (ClassNotFoundException e) {
      System.out.println("Class Not Found Error: " + e.getMessage());
    } catch (FileNotFoundException e) {
      System.out.println("File Not Found Error: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO Error: " + e.getMessage());
    } finally {
      try {
        if (outStream != null) {
          outStream.flush();
          outStream.close();
        }
      } catch (IOException e) {
        System.out.println("IO Error: " + e.getMessage());
      }
    }
  }

  public void updateScoreFile() {
    try {
      outStream = new ObjectOutputStream(new FileOutputStream(HS_FILE));
      outStream.writeObject(scores);
    } catch (FileNotFoundException e) {
      System.out.println("File Not Found Error: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("IO Error: " + e.getMessage());
    } finally {
      try {
        if (outStream != null) {
          outStream.flush();
          outStream.close();
        }
      } catch (IOException e) {
        System.out.println("[Update] Error: " + e.getMessage());
      }
    }
  }

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
            highscore += (i + 1) + ".\t" + scores.get(i).getScoreName() + "\t\t" + scores.get(i).getScore() + "\n";
        }
        return highscore;
  }
}
