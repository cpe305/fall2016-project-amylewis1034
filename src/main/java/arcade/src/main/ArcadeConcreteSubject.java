package arcade.src.main;

import java.util.ArrayList;

public class ArcadeConcreteSubject extends ArcadeSubject {
  public enum Arcade {
    STARTMENU,
    SPACEINVADERS,
    HIGHSCORES,
    ENDGAMEMENU;
  }

  private Arcade state = Arcade.STARTMENU;
  
  public ArrayList<ArcadeObserver> observers = new ArrayList<ArcadeObserver>();

  public void registerObservers(ArcadeObserver observer) {
    observers.add(observer);
  }
  
  public void unRegisterObservers(ArcadeObserver observer) {
    observers.remove(observer);
  }
  
  public Arcade getState()  {
    return state;
  }
  
  public void setState(Arcade state) {
    this.state = state;
  }
  
  /**
   * Method that updates all observers of the subject.
   */
  public void notifyObservers() {
    for (int i = 0; i < observers.size(); i++) {
      observers.get(i).update();
    }
  }
}
