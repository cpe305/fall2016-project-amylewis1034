/* package arcade.src.main;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class Mines extends JFrame {

    private final int FRAME_WIDTH = 250;
    private final int FRAME_HEIGHT = 290;

    private final JLabel total_complete;
    
    public Mines() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Minesweeper");

        total_complete = new JLabel("");
        add(total_complete, BorderLayout.SOUTH);

        add(new MinesweeperGrid(total_complete));

        setResizable(false);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
        
            public void run() {                
                JFrame ex = new Mines();
                ex.setVisible(true);                
            }
        });
    }
}*/