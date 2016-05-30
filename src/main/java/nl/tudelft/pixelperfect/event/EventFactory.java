package nl.tudelft.pixelperfect.event;

import java.util.Collection;

/**
 * Not a factory as the design pattern, but used for id's, to create a random event, with unique
 * id's.
 * 
 * @author Wouter Zirkzee
 * @author Jesse Tilro
 * @author Floris Doolaard
 * 
 */
public abstract class EventFactory {

  protected EventReader reader;

  /**
   * Constructor for EventFactory.
   */
  public EventFactory() {
    reader = EventReader.getInstance();
  }

  public abstract Event createEvent();

  public abstract Collection<EventParameter> createParameters();
}
