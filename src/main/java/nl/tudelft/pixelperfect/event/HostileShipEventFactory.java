package nl.tudelft.pixelperfect.event;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Factory for HostileShipEvent.
 * 
 * @author Jesse Tilro
 *
 */
public class HostileShipEventFactory extends EventFactory {

  private static EventTypes type = EventTypes.HOSTILE_SHIP;

  @Override
  public Event createEvent() {
    EventReader reader = getReader();
    return new HostileShipEvent(reader.getNextId(), reader.getSummary(type.ordinal()),
        reader.getDescription(type.ordinal()), System.currentTimeMillis(),
        reader.getDuration(type.ordinal()), reader.getDamage(type.ordinal()));
  }

  @Override
  public Collection<EventParameter> createParameters() {
    Collection<EventParameter> collection = new ArrayList<EventParameter>();
    collection.add(new EventParameter("position-x", 1));
    collection.add(new EventParameter("position-y", 2));
    collection
        .add(new EventParameter("armor", EventParameterValues.HOSTILE_SHIP_ARMOR_ENERGY_SHIELD));
    return collection;
  }

}
