package nl.tudelft.pixelperfect.client;

import java.util.ArrayList;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

import nl.tudelft.pixelperfect.event.Event;

/**
 * Message type that sends the event log.
 * 
 * @author Dmitry Malarev
 *
 */
@Serializable
public class EventsMessage extends AbstractMessage {

  private ArrayList<Event> eve;

  /**
   * Constructs a new EventsMessage instance.
   * 
   */
  public EventsMessage() {
  }

  /**
   * Changes the log.
   * 
   * @param passedLog
   *          The list of events that will be the new log message.
   */
  public EventsMessage(ArrayList<Event> passedLog) {
    eve = passedLog;
  }

  /**
   * Returns the log.
   * 
   * @return The log.
   */
  public ArrayList<Event> getLog() {
    return eve;
  }

}
