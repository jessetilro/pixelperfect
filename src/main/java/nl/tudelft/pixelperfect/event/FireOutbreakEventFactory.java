package nl.tudelft.pixelperfect.event;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Factory for FireOutbreakEvents.
 * 
 * @author Jesse Tilro
 *
 */
public class FireOutbreakEventFactory extends EventFactory {

  private static EventTypes type = EventTypes.FIRE_OUTBREAK;

  @Override
  public Event createEvent() {
    return new FireOutbreakEvent(reader.getNextId(), reader.getSummary(type.ordinal()),
        reader.getDescription(type.ordinal()), System.currentTimeMillis(),
        reader.getDuration(type.ordinal()), reader.getDamage(type.ordinal()));
  }

  @Override
  public Collection<EventParameter> createParameters() {
    Collection<EventParameter> collection = new ArrayList<EventParameter>();
    return collection;
  }

}
