package nl.tudelft.pixelperfect.event.factory;

import java.util.ArrayList;
import java.util.Collection;

import nl.tudelft.pixelperfect.event.Event;
import nl.tudelft.pixelperfect.event.EventReader;
import nl.tudelft.pixelperfect.event.parameter.EventParameter;
import nl.tudelft.pixelperfect.event.type.EventTypes;
import nl.tudelft.pixelperfect.event.type.PlasmaLeakEvent;

/**
 * Factory for PlasmaLeakEvents.
 * 
 * @author Jesse Tilro
 *
 */
public class PlasmaLeakEventFactory extends EventFactory {
  
  @Override
  public EventTypes getType() {
    return EventTypes.PLASMA_LEAK;
  }

  @Override
  public Event createEvent() {
    EventReader reader = getReader();
    EventTypes type = getType();
    return new PlasmaLeakEvent(reader.getNextId(), reader.getSummary(type.ordinal()),
        reader.getDescription(type.ordinal()), System.currentTimeMillis(),
        reader.getDuration(type.ordinal()), reader.getDamage(type.ordinal()));
  }

  @Override
  public Collection<EventParameter> createParameters() {
    Collection<EventParameter> collection = new ArrayList<EventParameter>();
    return collection;
  }

}
