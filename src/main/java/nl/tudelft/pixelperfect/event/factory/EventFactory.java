package nl.tudelft.pixelperfect.event.factory;

import java.util.Collection;

import nl.tudelft.pixelperfect.event.EventReader;
import nl.tudelft.pixelperfect.event.parameter.EventParameter;
import nl.tudelft.pixelperfect.event.type.Event;

/**
 * An abstract factory for creating a specific type of Event and its parameters, according to the
 * Abstract Factory design pattern.
 * 
 * @author Jesse Tilro
 * 
 */
public abstract class EventFactory {

  private EventReader reader;

  /**
   * Constructor for EventFactory.
   */
  public EventFactory() {
    reader = EventReader.getInstance();
  }

  /**
   * Get the EventReader used by this Factory.
   * 
   * @return The EventReader.
   */
  protected EventReader getReader() {
    return reader;
  }

  /**
   * Create a specific type of Event.
   * 
   * @return A specific type of Event.
   */
  public abstract Event createEvent();

  /**
   * Create a random collection of parameters for the type of Event created by this Factory.
   * 
   * @return A random collection of parameters.
   */
  public abstract Collection<EventParameter> createParameters();
}
