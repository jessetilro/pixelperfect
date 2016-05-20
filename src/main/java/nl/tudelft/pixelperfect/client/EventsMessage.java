package nl.tudelft.pixelperfect.client;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;


/**
 * Message type that sends the event to the client.
 * 
 * @author Dmitry Malarev
 *
 */
@Serializable
public class EventsMessage extends AbstractMessage {

  private int id;
  private String type;
  private Long timestamp;
  private Long duration;

  /**
   * Constructs a new EventsMessage instance.
   * 
   */
  public EventsMessage() {
  }

  /**
   * Changes the content of the message.
   *
   * @param id
   *          The id of the event.
   * @param type
   *          The type of the event.
   * @param timestamp
   *          The timestamp of the event.
   * @param duration
   *          The time limit of the event.
   */
  public EventsMessage(int id, String type, Long timestamp, Long duration) {
    this.id = id;
    this.type = type;
    this.timestamp = timestamp;
    this.duration = duration;
  }

  /**
   * Returns the event id to send to the client.
   *
   * @return The event id.
   */
  public int getId() {
    return id;
  }

  /**
   * Returns the event type to send to the client.
   *
   * @return The event type.
   */
  public String getType() {
    return type;
  }

  /**
   * Returns the event timestamp to send to the client.
   *
   * @return The event timestamp.
   */
  public Long getTime() {
    return timestamp;
  }

  /**
   * Returns the event time limit to send to the client.
   *
   * @return The event time limit.
   */
  public Long getDuration() {
    return duration;
  }
}
