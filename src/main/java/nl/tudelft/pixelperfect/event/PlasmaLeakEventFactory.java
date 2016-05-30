package nl.tudelft.pixelperfect.event;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Factory for PlasmaLeakEvents.
 * 
 * @author Jesse Tilro
 *
 */
public class PlasmaLeakEventFactory extends EventFactory {

  private static EventTypes type = EventTypes.PLASMA_LEAK;

  @Override
  public Event createEvent() {
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
