package nl.tudelft.pixelperfect.event;

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
    return new HostileShipEvent(reader.getNextId(), reader.getSummary(type.ordinal()),
        reader.getDescription(type.ordinal()), System.currentTimeMillis(),
        reader.getDuration(type.ordinal()), reader.getDamage(type.ordinal()));
  }

  @Override
  public Collection<EventParameter> createParameters() {
    // Create parameters for an Event.
    return null;
  }

}
