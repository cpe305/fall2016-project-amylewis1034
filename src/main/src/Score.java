package arcade.src.main;

import java.io.Serializable;

public class Score implements Serializable {
  private static final long serialVersionUID = 1L;
  private int score;
  private String scoreName;

  public int getScore() {
    return score;
  }

  public String getScoreName() {
    return scoreName;
  }

  public Score(String name, int score) {
    this.score = score;
    this.scoreName = name;
  }
}
