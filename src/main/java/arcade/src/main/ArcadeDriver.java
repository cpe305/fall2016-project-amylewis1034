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
    final int WIDTH = 1000;
    final int HEIGHT = 800;
    final int SCALE = 2;
    
    SpaceInvaders siGame = new SpaceInvaders();

    siGame.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
    siGame.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
    siGame.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

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
