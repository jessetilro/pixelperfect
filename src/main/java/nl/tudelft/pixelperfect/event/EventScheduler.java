package nl.tudelft.pixelperfect.event;

import java.util.ArrayList;
import java.util.Random;

/**
 * Random generator for events, simulating space. The random distribution is modelled as a poisson
 * process.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public class EventScheduler {

  private double intensity;
  private ArrayList<EventListener> listeners;
  private EventFactory factory;

  /**
   * Construct a new EventScheduler instance.
   * 
   * @param intensity
   *          The average number of events introduced per second in the game.
   */
  public EventScheduler(double intensity) {
    this.intensity = intensity;
    this.listeners = new ArrayList<EventListener>();
    this.factory = new EventFactory();
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
   *          The time per frame in seconds, i.e. the time interval parameter for the poisson
   *          process.
   */
  public void update(float tpf) {
    // The random generator should be more efficient and less biased than the Math.random function.
    Random rg = new Random();

    // The tpf is expressed in seconds, therefore intensity times tpf represents the expected value
    // of the probability distribution.
    double mu = intensity * tpf;
    double pzero = Math.exp(-mu);
    int kvalue = 0;
    double pvalue = 1.0;

    do {
      kvalue++;
      pvalue *= rg.nextDouble();
    } while (pvalue > pzero);
    kvalue--;

    for (int i = 0; i < kvalue; i++) {
      Event evt = factory.randomEvent();
      publish(evt);
    }
  }
}
