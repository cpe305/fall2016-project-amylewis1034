/*package arcade.src.main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import arcade.src.main.ArcadeConcreteSubject.Arcade;

public class MinesweeperGrid implements ArcadeObserver {
  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = Logger.getLogger(SpaceInvaders.class.toString());
  private static final int GRID_WIDTH = 1200;
  private static final int GRID_HEIGHT = 1200;
  private final int POSSIBLE_SQUARES = 13;
  private final int SQUARE_SIZE = 15;

  private final int SQUARE_COVER = 10;
  private final int SQUARE_MARK = 10;
  private final int SQUARE_EMPTY = 0;
  private final int SQUARE_MINE = 9;
  private final int SQUARE_MINE_COVERED = SQUARE_MINE + SQUARE_COVER;
  private final int SQUARE_MINE_MARKED = SQUARE_MINE_COVERED + SQUARE_MARK;

  private final int DRAW_SQUARE_MINE = 9;
  private final int DRAW_SQUARE_COVER = 10;
  private final int DRAW_SQUARE_MARK = 11;
  private final int DRAW_WRONG_SQUARE_MARK = 12;

  private final int NUM_SQUARE_MINES = 40;
  private final int NUM_ROWS = 16;
  private final int NUM_COLUMNS = 16;

  private int[] mine_field;
  private boolean isRunning;
  private int remaining_mines;
  private Image[] jImage;

  private int total_squares;
  private JLabel total_complete;
  
  private Arcade state;
  private ArcadeConcreteSubject subject; // NOSONAR

  public SnakeGrid(ArcadeConcreteSubject subject, SpaceInvaders siGame) {
    this.subject = subject;
    state = subject.getState();
    siGame.addKeyListener(new KeyInput(this));
    loadImages();
    init();
    
    this.total_complete = total_complete;

    jImage = new Image[POSSIBLE_SQUARES];

    for (int i = 0; i < POSSIBLE_SQUARES; i++) {
        jImage[i] = (new ImageIcon(i + ".png")).getImage();
    }

    setDoubleBuffered(true);

    addMouseListener(new MinesAdapter());
    newGame();
  }

  public void setSubject(ArcadeConcreteSubject subject) {
    this.subject = subject;
  }
  
  public boolean getRunning() {
    return isRunning;
  }
  
  public void setRunning(boolean isRunning) {
    this.isRunning = isRunning;
  }
*/
  /**
   * Updates observer if the state has been changed.
   */
/*
  public void update() {
    state = subject.getState();
    if (state == Arcade.MINESWEEPER) {
      isRunning = true;
      newGame();
    }
  }

  private void newGame() {

    Random rand;
    int cur_column;

    int i = 0;
    int position = 0;
    int square = 0;

    rand = new Random();
    isRunning = true;
    remaining_mines = NUM_SQUARE_MINES;

    total_squares = NUM_ROWS * NUM_COLUMNS;
    mine_field = new int[total_squares];

    for (i = 0; i < total_squares; i++)
      mine_field[i] = SQUARE_COVER;

    total_complete.setText(Integer.toString(remaining_mines));


    i = 0;
    while (i < NUM_SQUARE_MINES) {

      position = (int) (total_squares * rand.nextDouble());

      if ((position < total_squares) && (mine_field[position] != SQUARE_MINE_COVERED)) {


        cur_column = position % NUM_COLUMNS;
        mine_field[position] = SQUARE_MINE_COVERED;
        i++;

        if (cur_column > 0) {
          square = position - 1 - NUM_COLUMNS;
          if (square >= 0)
            if (mine_field[square] != SQUARE_MINE_COVERED)
              mine_field[square] += 1;
          square = position - 1;
          if (square >= 0)
            if (mine_field[square] != SQUARE_MINE_COVERED)
              mine_field[square] += 1;

          square = position + NUM_COLUMNS - 1;
          if (square < total_squares)
            if (mine_field[square] != SQUARE_MINE_COVERED)
              mine_field[square] += 1;
        }

        square = position - NUM_COLUMNS;
        if (square >= 0)
          if (mine_field[square] != SQUARE_MINE_COVERED)
            mine_field[square] += 1;
        square = position + NUM_COLUMNS;
        if (square < total_squares)
          if (mine_field[square] != SQUARE_MINE_COVERED)
            mine_field[square] += 1;

        if (cur_column < (NUM_COLUMNS - 1)) {
          square = position - NUM_COLUMNS + 1;
          if (square >= 0)
            if (mine_field[square] != SQUARE_MINE_COVERED)
              mine_field[square] += 1;
          square = position + NUM_COLUMNS + 1;
          if (square < total_squares)
            if (mine_field[square] != SQUARE_MINE_COVERED)
              mine_field[square] += 1;
          square = position + 1;
          if (square < total_squares)
            if (mine_field[square] != SQUARE_MINE_COVERED)
              mine_field[square] += 1;
        }
      }
    }
  }


  public void findEmptySquares(int j) {

    int cur_column = j % NUM_COLUMNS;
    int square;

    if (cur_column > 0) {
      square = j - NUM_COLUMNS - 1;
      if (square >= 0)
        if (mine_field[square] > SQUARE_MINE) {
          mine_field[square] -= SQUARE_COVER;
          if (mine_field[square] == SQUARE_EMPTY)
            findEmptySquares(square);
        }

      square = j - 1;
      if (square >= 0)
        if (mine_field[square] > SQUARE_MINE) {
          mine_field[square] -= SQUARE_COVER;
          if (mine_field[square] == SQUARE_EMPTY)
            findEmptySquares(square);
        }

      square = j + NUM_COLUMNS - 1;
      if (square < total_squares)
        if (mine_field[square] > SQUARE_MINE) {
          mine_field[square] -= SQUARE_COVER;
          if (mine_field[square] == SQUARE_EMPTY)
            findEmptySquares(square);
        }
    }

    square = j - NUM_COLUMNS;
    if (square >= 0)
      if (mine_field[square] > SQUARE_MINE) {
        mine_field[square] -= SQUARE_COVER;
        if (mine_field[square] == SQUARE_EMPTY)
          findEmptySquares(square);
      }

    square = j + NUM_COLUMNS;
    if (square < total_squares)
      if (mine_field[square] > SQUARE_MINE) {
        mine_field[square] -= SQUARE_COVER;
        if (mine_field[square] == SQUARE_EMPTY)
          findEmptySquares(square);
      }

    if (cur_column < (NUM_COLUMNS - 1)) {
      square = j - NUM_COLUMNS + 1;
      if (square >= 0)
        if (mine_field[square] > SQUARE_MINE) {
          mine_field[square] -= SQUARE_COVER;
          if (mine_field[square] == SQUARE_EMPTY)
            findEmptySquares(square);
        }

      square = j + NUM_COLUMNS + 1;
      if (square < total_squares)
        if (mine_field[square] > SQUARE_MINE) {
          mine_field[square] -= SQUARE_COVER;
          if (mine_field[square] == SQUARE_EMPTY)
            findEmptySquares(square);
        }

      square = j + 1;
      if (square < total_squares)
        if (mine_field[square] > SQUARE_MINE) {
          mine_field[square] -= SQUARE_COVER;
          if (mine_field[square] == SQUARE_EMPTY)
            findEmptySquares(square);
        }
    }
  }

  @Override
  public void paintComponent(Graphics g) {

    int square = 0;
    int uncover = 0;

    for (int i = 0; i < NUM_ROWS; i++) {
      for (int j = 0; j < NUM_COLUMNS; j++) {

        square = mine_field[(i * NUM_COLUMNS) + j];

        if (isRunning && square == SQUARE_MINE)
          isRunning = false;

        if (!isRunning) {
          if (square == SQUARE_MINE_COVERED) {
            square = DRAW_SQUARE_MINE;
          } else if (square == SQUARE_MINE_MARKED) {
            square = DRAW_SQUARE_MARK;
          } else if (square > SQUARE_MINE_COVERED) {
            square = DRAW_WRONG_SQUARE_MARK;
          } else if (square > SQUARE_MINE) {
            square = DRAW_SQUARE_COVER;
          }


        } else {
          if (square > SQUARE_MINE_COVERED)
            square = DRAW_SQUARE_MARK;
          else if (square > SQUARE_MINE) {
            square = DRAW_SQUARE_COVER;
            uncover++;
          }
        }

        g.drawImage(jImage[square], (j * SQUARE_SIZE), (i * SQUARE_SIZE), this);
      }
    }

    if (uncover == 0 && isRunning) {
      isRunning = false;
      total_complete.setText("Game won");
    } else if (!isRunning)
      total_complete.setText("Game lost");
  }


  class MinesAdapter extends MouseAdapter {

    @Override
    public void mousePressed(MouseEvent e) {

      int x = e.getX();
      int y = e.getY();

      int cCol = x / SQUARE_SIZE;
      int cRow = y / SQUARE_SIZE;

      boolean rep = false;


      if (!isRunning) {
        newGame();
        repaint();
      }


      if ((x < NUM_COLUMNS * SQUARE_SIZE) && (y < NUM_ROWS * SQUARE_SIZE)) {

        if (e.getButton() == MouseEvent.BUTTON3) {

          if (mine_field[(cRow * NUM_COLUMNS) + cCol] > SQUARE_MINE) {
            rep = true;

            if (mine_field[(cRow * NUM_COLUMNS) + cCol] <= SQUARE_MINE_COVERED) {
              if (remaining_mines > 0) {
                mine_field[(cRow * NUM_COLUMNS) + cCol] += SQUARE_MARK;
                remaining_mines--;
                total_complete.setText(Integer.toString(remaining_mines));
              } else
                total_complete.setText("No marks left");
            } else {

              mine_field[(cRow * NUM_COLUMNS) + cCol] -= SQUARE_MARK;
              remaining_mines++;
              total_complete.setText(Integer.toString(remaining_mines));
            }
          }

        } else {

          if (mine_field[(cRow * NUM_COLUMNS) + cCol] > SQUARE_MINE_COVERED) {
            return;
          }

          if ((mine_field[(cRow * NUM_COLUMNS) + cCol] > SQUARE_MINE)
              && (mine_field[(cRow * NUM_COLUMNS) + cCol] < SQUARE_MINE_MARKED)) {

            mine_field[(cRow * NUM_COLUMNS) + cCol] -= SQUARE_COVER;
            rep = true;

            if (mine_field[(cRow * NUM_COLUMNS) + cCol] == SQUARE_MINE)
              isRunning = false;
            if (mine_field[(cRow * NUM_COLUMNS) + cCol] == SQUARE_EMPTY)
              findEmptySquares((cRow * NUM_COLUMNS) + cCol);
          }
        }

        if (rep)
          repaint();

      }
    }
  }
} 
*/
