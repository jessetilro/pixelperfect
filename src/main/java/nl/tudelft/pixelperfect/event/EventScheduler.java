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

  private double intensityMin;
  private double intensityMax;
  private double intensityPercentage;
  private ArrayList<EventListener> listeners;
  private EventFactory factory;
  private boolean active;

  /**
   * Construct a new EventScheduler instance with an intensity varying between a given minimum and
   * maximum.
   * 
   * @param intensityMin
   *          The minimum average number of events introduced per second in the game.
   * @param intensityMax
   *          The minimum average number of events introduced per second in the game.
   */
  public EventScheduler(double intensityMin, double intensityMax) {
    this.intensityMin = intensityMin;
    this.intensityMax = intensityMax;
    construct();
  }

  /**
   * Construct a new EventScheduler instance with a fixed intensity.
   * 
   * @param intensity
   *          The average number of events introduced per second in the game.
   */
  public EventScheduler(double intensity) {
    this.intensityMin = intensity;
    this.intensityMax = intensity;
    construct();
  }

  /**
   * Repeated part of the constructors.
   */
  private void construct() {
    this.intensityPercentage = 0;
    this.listeners = new ArrayList<EventListener>();
    this.factory = new EventFactory();
    this.active = false;
  }

  /**
   * Allow the Scheduler to resume scheduling events by making it active.
   */
  public void start() {
    active = true;
  }

  /**
   * Disallow the Scheduler to schedule any event by making it inactive.
   */
  public void stop() {
    active = false;
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
   * Compute the intensity based on the minimum and maximum intensities in combination with the
   * intensity percentage.
   * 
   * @return The current intensity.
   */
  private double getIntensity() {
    return intensityMin + intensityPercentage * (intensityMax - intensityMin);
  }

  /**
   * Update the intensity percentage representing what the current intensity should be with respect
   * to the predefined minimum and maximum intensities. Zero meaning minimum intensity and one
   * hundred meaning maximum intensity.
   * 
   * @param intensityPercentage
   *          A percentage between zero and one hundred percent used to linearly interpolate between
   *          the predefined minimum and maximum intensities.
   */
  public void updateIntensity(double intensityPercentage) {
    if (intensityPercentage > 1) {
      this.intensityPercentage = 1;
    } else if (intensityPercentage < 0) {
      this.intensityPercentage = 0;
    } else {
      this.intensityPercentage = intensityPercentage;
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
    if (!active) {
      return;
    }
    // The random generator should be more efficient and less biased than the Math.random function.
    Random rg = new Random();

    // The tpf is expressed in seconds, therefore intensity times tpf represents the expected value
    // of the probability distribution.
    double mu = getIntensity() * tpf;
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
