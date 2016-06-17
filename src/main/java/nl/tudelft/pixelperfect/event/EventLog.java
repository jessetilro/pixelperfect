package nl.tudelft.pixelperfect.event;

import java.util.ArrayList;
import java.util.Collection;

import nl.tudelft.pixelperfect.event.parameter.EventParameter;
import nl.tudelft.pixelperfect.event.type.EventTypes;
import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.game.Spaceship;

/**
 * The captain's log of events, which should be subscribed to the event schedulers in the game.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 * @author Wouter Zirkzee
 *
 */
public class EventLog implements EventListener {

  private ArrayList<Event> events;
  private Spaceship spaceship;

  /**
   * Construct a new EventLog instance.
   * 
   * @param spaceship
   *          The spaceship this event log is installed on.
   */
  public EventLog(Spaceship spaceship) {
    this.events = new ArrayList<Event>();
    this.spaceship = spaceship;
  }

  /**
   * Get the current log of events.
   * 
   * @return the list of events.
   */
  public synchronized ArrayList<Event> getEvents() {
    return this.events;
  }

  /**
   * Get all Event of a specific type from the log.
   * 
   * @param type
   *          The type of Event to look for.
   * 
   * @return A collection of Events with the given type.
   */
  private synchronized Collection<Event> getByType(EventTypes type) {
    Collection<Event> result = new ArrayList<Event>();
    for (Event event : events) {
      if (event.getType() == type) {
        result.add(event);
      }
    }
    return result;
  }

  /**
   * Get the spaceship the EvenLog relates to.
   * 
   * @return the log's spaceship.
   */
  public Spaceship getSpaceship() {
    return this.spaceship;
  }

  /**
   * Be notified of a new event by a scheduler.
   * 
   * @param event
   *          The event that the scheduler introduces, to be listed in the log.
   */
  public synchronized void notify(Event event) {
    events.add(event);
    System.out.println("The ship received a new event: " + event.getSummary());
  }

  /**
   * Discard the given event from the list.
   * 
   * @param event
   *          The event to be discarded from the log.
   */
  public synchronized void discard(Event event) {
    events.remove(event);
  }

  /**
   * Replaces the Event Log.
   * 
   * @param log
   *          The log that will replace the old one.
   */
  public synchronized void replace(ArrayList<Event> log) {
    events = log;
    update();
  }

  /**
   * Try to complete an Event in the log that matches a given type and parameters.
   * 
   * @param type
   *          The type of the Event to be completed.
   * @param parameters
   *          The parameters the Event should have.
   * @param game
   *          The current game session.
   * 
   */
  public synchronized void complete(EventTypes type, Collection<EventParameter> parameters,
      Game game) {
    Collection<Event> candidates = getByType(type);

    for (Event event : candidates) {
      if (event.validateParameters(parameters)) {
        event.onComplete(game);

        discard(event);
        spaceship.updateScore(10);
        System.out.println("Event " + event.getId() + " solved!");
        return;
      }
    }

    if (candidates.size() > 0) {
      System.out.println("Wrong task performed: wrong parameters entered");
    } else {
      System.out.println("Wrong task performed: there is no active Event of type "
          + type.toString());
    }

    spaceship.updateHealth(-10);
  }

  /**
   * Evaluate the log to check whether events have expired since the last update.
   */
  public synchronized void update() {
    ArrayList<Event> discardPile = new ArrayList<Event>();
    for (Event event : events) {
      long now = System.currentTimeMillis();
      if (event.isExpired(now)) {
        discardPile.add(event);
      }
    }
    for (Event event : discardPile) {
      spaceship.updateScore(-5);
      event.applyDamage(spaceship);
      discard(event);
    }
  }

  /**
   * Get first event of specified type.
   * 
   * @param type
   *          type to find.
   * @return event of given type
   */
  public Event getFirst(EventTypes type) {
    for (Event event : events) {
      if (event.getType().equals(type)) {
        return event;
      }
    }
    return null;
  }
}
