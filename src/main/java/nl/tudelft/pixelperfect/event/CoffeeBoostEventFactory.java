package nl.tudelft.pixelperfect.event;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Factory for CoffeeBoostEvents.
 * 
 * @author Jesse Tilro
 *
 */
public class CoffeeBoostEventFactory extends EventFactory {

  private static EventTypes type = EventTypes.COFFEE_BOOST;

  @Override
  public Event createEvent() {
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
