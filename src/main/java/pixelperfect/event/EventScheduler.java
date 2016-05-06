package pixelperfect.event;

import java.util.ArrayList;
import java.util.Random;

/**
 * Random generator for events, simulating space. The random distribution is modelled as a poisson
 * process.
 * 
 * @author Jesse Tilro
 *
 */
public class EventScheduler {

  private float intensity;
  private ArrayList<EventListener> listeners;
  private boolean tried;

  /**
   * Construct a new EventScheduler instance.
   * 
   * @param intensity
   *          The average number of events introduced per second in the game.
   */
  public EventScheduler(float intensity) {
    this.intensity = intensity;
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
   * Notify all subscriber's by publishing a newly generated Event on the channel.
   * 
   * @param event
   *          The Event to be published.
   */
  private void publish(Event event) {
    for (EventListener subscriber : listeners) {
      subscriber.notify(event);
    }
  }

  /**
   * With a given probability, introduce a new random event for the next step in the game loop. This
   * models a poisson process, with the specified intensity as lambda parameter.
   * 
   * @param tpf
   *          The time per frame in seconds, i.e. how many times per second this draw is performed
   *          and thus the time interval parameter for the poisson process.
   */
  public void update(float tpf) {
    Random rg = new Random();
    float draw = rg.nextFloat();
    // The tpf is expressed in seconds, therefore intensity times tpf represents the expected value
    // of the probability distribution.
    if (draw <= intensity * tpf) {
      long timestamp = System.currentTimeMillis();
      Event evt = new Event(0, "Dummy", "Hello world, I am a dummy event!", timestamp, 4000, 10);
      publish(evt);
    }
  }

}
