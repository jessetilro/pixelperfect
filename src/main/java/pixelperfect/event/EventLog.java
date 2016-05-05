package pixelperfect.event;

import java.util.ArrayList;

/**
 * The captain's log of events, which should be subscribed to the event schedulers in the game.
 * 
 * @author Jesse Tilro
 *
 */
public class EventLog implements EventListener {

  private ArrayList<Event> events;

  /**
   * Construct a new EventLog instance.
   */
  public EventLog() {
    this.events = new ArrayList<Event>();
  }

  /**
   * Be notified of a new event by a scheduler.
   * 
   * @param event
   *          The event that the scheduler introduces, to be listed in the log.
   */
  public void notify(Event event) {
    events.add(event);
  }

  /**
   * Discard the given event from the list.
   * 
   * @param event
   *          The event to be discarded from the log.
   */
  public void discard(Event event) {
    events.remove(event);
  }

}
