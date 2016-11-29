package arcade.src.main;

/**
 * Interface for an observer for the Observer Pattern.
 * 
 * @author Amy Lewis.
 * @version 11/28/16
 */
public interface ArcadeObserver {

  /**
   * Method that updates all of the observers if the subject changes.
   */
  public void update();
}
