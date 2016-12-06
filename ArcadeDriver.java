package arcade.src.main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class ArcadeDriver {
  /**
   * The method that drives the entire game.
   * 
   * @param args holds any input from the command line
   */
  public static void main(String[] args) {
    SpaceInvaders siGame = new SpaceInvaders();
    int width = siGame.getWidth();
    int height = siGame.getHeight();

    siGame.setMinimumSize(new Dimension(width, height));
    siGame.setMaximumSize(new Dimension(width, height));
    siGame.setPreferredSize(new Dimension(width, height));

    JFrame titleFrame = new JFrame(siGame.getTitle());
    titleFrame.add(siGame);
    titleFrame.pack();
    titleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    titleFrame.setLocationRelativeTo(null);
    titleFrame.setVisible(true);
    titleFrame.setResizable(false);

    siGame.start();
  }
}
