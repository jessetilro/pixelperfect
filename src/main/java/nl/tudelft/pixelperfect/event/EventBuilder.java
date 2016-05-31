package nl.tudelft.pixelperfect.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import nl.tudelft.pixelperfect.event.factory.EventFactory;
import nl.tudelft.pixelperfect.event.parameter.EventParameter;
import nl.tudelft.pixelperfect.event.type.Event;
import nl.tudelft.pixelperfect.event.type.EventTypes;

/**
 * Builder for Events. Uses an abstract factory for construction of an Event and its parameters.
 * 
 * @author Jesse Tilro
 *
 */
public class EventBuilder {

  private EventFactory factory;
  private Collection<EventParameter> parameters;
  private Random random;

  /**
   * Construct a new EventBuilder instance.
   */
  public EventBuilder() {
    random = new Random();
    parameters = new ArrayList<EventParameter>();
  }

  /**
   * Set the type of Event to be created.
   * 
   * @param type
   *          The type of Event to be created.
   */
  public void setType(EventTypes type) {
    // For efficiency reasons storing the factory instead of type.
    this.factory = type.getFactory();
  }

  /**
   * Set the parameters to be assigned to the Event.
   * 
   * @param parameters
   *          The EventParamters to be assigned.
   */
  public void setParameters(Collection<EventParameter> parameters) {
    this.parameters = parameters;
  }

  /**
   * Build an Event of the set type with the set parameters.
   * 
   * @return A new Event.
   */
  public Event buildEvent() {
    if (factory != null) {
      Event event = factory.createEvent();
      event.setParameters(parameters);
      return event;
    }
    return null;
  }

  /**
   * Build a completely random Event.
   * 
   * @return A random Event.
   */
  public Event buildRandomEvent() {
    EventTypes type = getRandomEventType();
    setType(type);
    setParameters(factory.createParameters());
    return buildEvent();
  }

  /**
   * Get a random Event Type.
   * 
   * @return A random Event Type.
   */
  private EventTypes getRandomEventType() {
    List<EventTypes> types = Arrays.asList(EventTypes.values());
    return types.get(random.nextInt(types.size()));
  }

}
