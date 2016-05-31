package nl.tudelft.pixelperfect.event.factory;

import java.util.ArrayList;
import java.util.Collection;

import nl.tudelft.pixelperfect.event.EventReader;
import nl.tudelft.pixelperfect.event.parameter.EventParameter;
import nl.tudelft.pixelperfect.event.parameter.EventParameterValues;
import nl.tudelft.pixelperfect.event.type.Event;
import nl.tudelft.pixelperfect.event.type.EventTypes;
import nl.tudelft.pixelperfect.event.type.HostileShipEvent;

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
