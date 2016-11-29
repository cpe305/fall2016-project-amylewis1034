package arcade.src.main;

import java.util.Comparator;

/**
 * Class that compares inputed scores.
 * 
 * @author Amy Lewis.
 * @version 10/31/16
 */
public class ScoreComparator implements Comparator<Score> {
  
  /**
   * Compares the two inputed scores to see which holds greater value.
   * 
   * @param score1 the first inputed score
   * @param score2 the second inputed score
   */
  public int compare(Score score1, Score score2) {
    int first = score1.getScore();
    int second = score2.getScore();

    if (first > second) {
      return -1;
    } else if (first < second) {
      return 1;
    } else {
      return 0;
    }
  }
}
