package nl.tudelft.pixelperfect.client;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

import nl.tudelft.pixelperfect.event.Event;

import java.util.ArrayList;

/**
 * Message type that sends the event log.
 * 
 * @author Dmitry Malarev
 *
 */
@Serializable
public class EventsMessage extends AbstractMessage {

  private String eve;

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
  public EventsMessage(String passedLog) {
    eve = passedLog;
  }

  /**
   * Returns the log.
   * 
   * @return The log.
   */
  public String getLog() {
    return eve;
  }

}
