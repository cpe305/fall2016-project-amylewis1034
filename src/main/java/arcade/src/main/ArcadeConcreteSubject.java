package arcade.src.main;

import java.util.ArrayList;

/**
 * Class that creates the concrete subject that can be observed in the Arcade.
 * 
 * @author Amy Lewis.
 * @version 11/28/16
 */
public class ArcadeConcreteSubject extends ArcadeSubject {
  
  /**
   * enum that controls the state of the subject.
   */
  public enum Arcade {
    STARTMENU, 
    SPACEINVADERS, 
    SNAKE,
    HIGHSCORES, 
    ENDSPACEGAMEMENU,
    ENDSNAKEMENU;
  }

  private Arcade state = Arcade.STARTMENU;

  private ArrayList<ArcadeObserver> observers = new ArrayList<ArcadeObserver>();

  /**
   * Adds observers to observer list.
   */
  @Override
  public void registerObservers(ArcadeObserver observer) {
    observers.add(observer);
  }

  /**
   * Removes observers from observer list.
   */
  @Override
  public void unRegisterObservers(ArcadeObserver observer) {
    observers.remove(observer);
  }

  /**
   * Method to return the state of the subject.
   * 
   * @return the state of the subject
   */
  public Arcade getState() {
    return state;
  }

  /**
   * Method that sets the state of the subject.
   * @param state the new state for the subject
   */
  public void setState(Arcade state) {
    this.state = state;
  }

  /**
   * Method that updates all observers of the subject.
   */
  @Override
  public void notifyObservers() {
    for (int i = 0; i < observers.size(); i++) {
      observers.get(i).update();
    }
  }
}
