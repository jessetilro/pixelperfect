package nl.tudelft.pixelperfect.event;

import com.jme3.network.Server;

import nl.tudelft.pixelperfect.Spaceship;
import nl.tudelft.pixelperfect.client.EventsMessage;

import java.util.ArrayList;

/**
 * The captain's log of events, which should be subscribed to the event schedulers in the game.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public class EventLog implements EventListener {

  private ArrayList<Event> events;
  private Spaceship spaceship;
  private Server server;

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
   * Sets the server for reference purposes.
   * 
   * @param server The server.
   */
  public synchronized void setServer(Server server) {
    this.server = server;
  }
  
  /**
   * Get the current log of events.
   * 
   * @return the list of events.
   */
  public ArrayList<Event> getEvents() {
    return this.events;
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
    System.out.println("The ship received a new event: " + event.getDescription());
    String type = event.getClass().getSimpleName();
    EventsMessage message = 
        new EventsMessage(event.getId(), type, event.getTimestamp(), event.getDuration());
    server.broadcast(message);
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
   * Removes a completed event from the list before it is expired.
   *  
   * @param identity 
   *           The id used to find the event in the log.
   */
  public synchronized void complete(int identity) {
    for (int t = 0; t < events.size(); t++) {
      if (events.get(t).getId() == identity) {
        events.remove(t);
        spaceship.updateScore(10);
        return;
      }
    }
    //For now hardcoded
    System.out.println("You pressed wrong button, DAMAGE will be done to the ship.");
    spaceship.updateHealth(-10);
    //Manual Testing
    System.out.println("New ship HEALTH: " + spaceship.getHealth());
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
}
