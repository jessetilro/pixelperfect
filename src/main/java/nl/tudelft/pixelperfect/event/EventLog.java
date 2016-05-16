package nl.tudelft.pixelperfect.event;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import com.jme3.network.Server;
import com.jme3.network.serializing.serializers.CollectionSerializer;
import com.jme3.network.serializing.serializers.FieldSerializer;

import nl.tudelft.pixelperfect.Spaceship;
import nl.tudelft.pixelperfect.client.EventsMessage;

/**
 * The captain's log of events, which should be subscribed to the event schedulers in the game.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public class EventLog implements EventListener {

  private Server serve;
  private ArrayList<Event> events;
  private Spaceship spaceship;
  private ByteBuffer bite = ByteBuffer.allocate(1500);
  private FieldSerializer colli;

  /**
   * Construct a new EventLog instance.
   * 
   * @param spaceship
   *          The spaceship this event log is installed on.
   */
  public EventLog(Spaceship spaceship) {
    this.events = new ArrayList<Event>();
    this.spaceship = spaceship;
    this.colli = new FieldSerializer();
    FieldSerializer.setReadOnly(false);
    FieldSerializer.registerClass(nl.tudelft.pixelperfect.event.AsteroidFieldEvent.class);
    FieldSerializer.registerClass(nl.tudelft.pixelperfect.event.FireEvent.class);
    FieldSerializer.registerClass(nl.tudelft.pixelperfect.event.HostileShipEvent.class);
    FieldSerializer.registerClass(nl.tudelft.pixelperfect.event.PlasmaLeakEvent.class);
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
    String id = Integer.toString(event.getId());
    String type = event.getClass().getSimpleName();
    String time = Long.toString(event.getTimestamp());
    String dur = Long.toString(event.getDuration());
    EventsMessage eve =new EventsMessage(id + " " + type + " " + time + " " + dur);
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
      event.applyDamage(spaceship);
      discard(event);
    }
    // serve.broadcast(new EventsMessage(events));
  }

  /**
   * Sets the server for reference.
   * 
   * @param server
   *          The server to refer to.
   * 
   */
  public void setServer(Server server) {
    serve = server;

  }
}
