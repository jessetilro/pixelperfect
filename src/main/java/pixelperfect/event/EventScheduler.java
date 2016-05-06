package pixelperfect.event;

import java.util.ArrayList;

/**
 * Random generator for events, simulating space.
 * 
 * @author Jesse Tilro
 *
 */
public class EventScheduler {

  private float probability;
  private ArrayList<EventListener> listeners;

  /**
   * Construct a new EventScheduler instance.
   * 
   * @param probability
   *          The probability of introducing a new event each step in the game.
   */
  public EventScheduler(float probability) {
    this.probability = probability;
    this.listeners = new ArrayList<EventListener>();
  }

  /**
   * Subscribe a new event listener to this scheduler.
   * 
   * @param listener
   *          An event listener.
   */
  public void subscribe(EventListener listener) {
    this.listeners.add(listener);
  }

  /**
   * With a given probability, introduce a new random event for the next step in the game loop.
   */
  public void update() {
    // with the given probability update subscribers with a new randomly generated event.
  }

}
