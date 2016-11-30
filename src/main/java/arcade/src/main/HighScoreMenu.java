package arcade.src.main;

import arcade.src.main.ArcadeConcreteSubject.Arcade;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Class that creates the High Score Menu for the arcade and is the first thing the user sees.
 * 
 * @author Amy Lewis.
 * @see StartMenu
 * @version 11/17/16
 */
public class HighScoreMenu implements ArcadeObserver {

  private static final int X_BUTTON_POS = SpaceInvaders.WIDTH + 100;
  private static final int Y_BUTTON_POS = 1150;
  private static final int BUTTON_WIDTH = 100;
  private static final int BUTTON_HEIGHT = 50;
  private ArcadeConcreteSubject subject;
  private Arcade state;
  private HighscoreManagerSpace hsManagerSpace;
  private ArrayList<String> highscoresSpace;
  private HighscoreManagerSnake hsManagerSnake;
  private ArrayList<String> highscoresSnake;

  private Rectangle startMenuButton =
      new Rectangle(X_BUTTON_POS, Y_BUTTON_POS + BUTTON_WIDTH, BUTTON_WIDTH * 3, BUTTON_HEIGHT);
  private Rectangle exitButton =
      new Rectangle(X_BUTTON_POS, Y_BUTTON_POS + 2 * BUTTON_WIDTH, BUTTON_WIDTH, BUTTON_HEIGHT);

  /**
   * Constructor for Highscore Menu that creates a new Highscore manager.
   * 
   * @param subject Highscore Menu is an observer so it takes a subject
   */
  public HighScoreMenu(ArcadeConcreteSubject subject) {
    this.subject = subject;
    state = subject.getState();
    hsManagerSnake = new HighscoreManagerSnake();
    hsManagerSpace = new HighscoreManagerSpace();
    
  }

  /**
   * Method that calls draws the Start Menu for the arcade.
   * 
   * @param graphics is a reference to the Java graphics class
   */
  public void render(Graphics graphics) {
    if (state == Arcade.HIGHSCORES) {
      Font fnt = new Font("arial", Font.BOLD, 200);
      graphics.setFont(fnt);
      graphics.setColor(Color.ORANGE);
      graphics.drawString("High Scores", SpaceInvaders.WIDTH / 2, 200);

      Font fntButton = new Font("arial", Font.BOLD, 30);
      graphics.setFont(fntButton);
      graphics.drawString("Space Invaders", X_BUTTON_POS + BUTTON_WIDTH, 660);
      graphics.drawString("Name    Score", X_BUTTON_POS + BUTTON_WIDTH, 700);
      for (int i = 0; i < highscoresSpace.size(); i++) {
        graphics.drawString(highscoresSpace.get(i), X_BUTTON_POS + 50, 740 + i * 40);
      }
      
      graphics.drawString("Snake", X_BUTTON_POS  + BUTTON_WIDTH, 960);
      graphics.drawString("Name    Score", X_BUTTON_POS  + BUTTON_WIDTH, 1000);
      for (int j = 0; j < highscoresSnake.size(); j++) {
        graphics.drawString(highscoresSnake.get(j), X_BUTTON_POS + 50, 1040 + j * 40);
      }  
      
      graphics.drawString("Go To Start Menu", startMenuButton.x + 10, startMenuButton.y + 35);
      ((Graphics2D) graphics).draw(startMenuButton);
      graphics.drawString("Exit", exitButton.x + 15, exitButton.y + 35);
      ((Graphics2D) graphics).draw(exitButton);
    }
  }

  /**
   * Updates observer if the state has been changed.
   */
  public void update() {
    state = subject.getState();
    if (state == Arcade.HIGHSCORES) {
      highscoresSpace = hsManagerSpace.getManageHighscores().getHighscoreName();
      highscoresSnake = hsManagerSnake.getManageHighscores().getHighscoreName();
    }
  }
}
