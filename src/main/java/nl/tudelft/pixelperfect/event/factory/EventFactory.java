package nl.tudelft.pixelperfect.event.factory;

import java.util.Collection;
import java.util.Map;

import nl.tudelft.pixelperfect.event.Event;
import nl.tudelft.pixelperfect.event.EventReader;
import nl.tudelft.pixelperfect.event.parameter.EventParameter;
import nl.tudelft.pixelperfect.event.type.EventTypes;

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
   * Return the Type of the Events this factory creates.
   * 
   * @return An EventType.
   */
  protected abstract EventTypes getType();

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

  /**
   * Uses the createParameters method to create parameters for the type of Event this factory
   * produces, and subsequently provides the created parameters with values read from the data file.
   * 
   * @return A random collection of valued parameters.
   */
  public Collection<EventParameter> createParametersValues() {
    Collection<EventParameter> parameters = createParameters();
    Map<String, String> summaries = reader.getParameters(getType().ordinal());
    int index = 0;
    for (EventParameter param : parameters) {
      param.setSummary(summaries.get(param.getKey()));
      param.setValueDescription(
          reader.getParameterValue(getType().ordinal(), index, param.getValue()));
      index++;
    }
    return parameters;
  }
}
