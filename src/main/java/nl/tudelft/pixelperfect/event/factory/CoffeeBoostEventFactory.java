package nl.tudelft.pixelperfect.event.factory;

import java.util.ArrayList;
import java.util.Collection;

import nl.tudelft.pixelperfect.event.EventReader;
import nl.tudelft.pixelperfect.event.parameter.EventParameter;
import nl.tudelft.pixelperfect.event.type.CoffeeBoostEvent;
import nl.tudelft.pixelperfect.event.type.Event;
import nl.tudelft.pixelperfect.event.type.EventTypes;

/**
 * Factory for CoffeeBoostEvents.
 * 
 * @author Jesse Tilro
 *
 */
public class CoffeeBoostEventFactory extends EventFactory {
  
  @Override
  public EventTypes getType() {
    return EventTypes.COFFEE_BOOST;
  }

  @Override
  public Event createEvent() {
    EventReader reader = getReader();
    EventTypes type = getType();
    return new CoffeeBoostEvent(reader.getNextId(), reader.getSummary(type.ordinal()),
        reader.getDescription(type.ordinal()), System.currentTimeMillis(),
        reader.getDuration(type.ordinal()), reader.getDamage(type.ordinal()));
  }

  @Override
  public Collection<EventParameter> createParameters() {
    Collection<EventParameter> collection = new ArrayList<EventParameter>();
    return collection;
  }

}
