package arcade.src.main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import arcade.src.main.ArcadeConcreteSubject.Arcade;

public class Snake extends JFrame implements Runnable, ArcadeObserver {
  private Arcade state;

  public static ArcadeConcreteSubject subject;

  public Snake(ArcadeConcreteSubject subject) {
    this.subject = subject;
    state = subject.getState();
    setResizable(false);
    pack();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static ArcadeConcreteSubject getSubject() {
    return subject;
  }
  
  public void setSubject(ArcadeConcreteSubject subject) {
    this.subject = subject;
  }
  
  public void update() {
    state = subject.getState();
  }

  public void run() {
    JFrame ex = new Snake(subject);
    ex.setVisible(true);
    
  }

} 
/*
public class Snake extends JFrame {

  public Snake() {

      add(new SnakeGrid());
      
      setResizable(false);
      pack();
      
      setTitle("Snake");
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  

  public static void main(String[] args) {
      
      EventQueue.invokeLater(new Runnable() {
          public void run() {                
              JFrame ex = new Snake();
              ex.setVisible(true);                
          }
      });
  }
}*/

