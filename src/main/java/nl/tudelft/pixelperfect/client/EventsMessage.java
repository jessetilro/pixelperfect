package nl.tudelft.pixelperfect.client;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

import nl.tudelft.pixelperfect.event.Event;

import java.util.ArrayList;

/**
 * Massage type that sends the event log.
 * 
 * @author Dmitry
 *
 */
@Serializable
public class EventsMessage extends AbstractMessage {
  
  private ArrayList<Event> eve;

  /**
   * The constructor of the events message.
   * 
   */
  public EventsMessage() {
  }

  /**
   * Changes the log.
   * 
   * @param passedLog
   *          the event list that will be the new log message.
   */
  public EventsMessage(ArrayList<Event> passedLog) {
    eve = passedLog;
  }

  /**
   * Returns the log.
   * 
   * @return the log.
   */
  public ArrayList<Event> getLog() {
    return eve;
  }

}
