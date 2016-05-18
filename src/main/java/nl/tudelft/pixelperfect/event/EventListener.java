package nl.tudelft.pixelperfect.event;

import java.util.ArrayList;

/**
 * Interface for classes that can subscribe themselves to an EventScheduler.
 * 
 * @author Jesse Tilro
 *
 */
public interface EventListener {

  /**
   * Get notified of a new events by an EventScheduler subscribed to.
   * 
   * @param event
   *          The event that is introduced by the scheduler.
   */
  void notify(Event event);

  /**
   * Discard a given event from this listener's active queue.
   * 
   * @param event
   *          Event to be discarded.
   */
  void discard(Event event);

  /**
   * Update the event listener.
   */
  void update();

  /**
   * Removes a completed event from the list before it is expired.
   *  
   * @param id
   *           The id used to find the event in the log.
   */
  void complete(String id);
  
  /**
   * Get all the events that are in the log.
   * 
   * @return
   * 				All events in the log.
   */
  ArrayList<Event> getEvents();
}
