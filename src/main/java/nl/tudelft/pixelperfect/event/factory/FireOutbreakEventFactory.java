package nl.tudelft.pixelperfect.event.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import nl.tudelft.pixelperfect.event.Event;
import nl.tudelft.pixelperfect.event.EventReader;
import nl.tudelft.pixelperfect.event.parameter.EventParameter;
import nl.tudelft.pixelperfect.event.type.EventTypes;
import nl.tudelft.pixelperfect.event.type.FireOutbreakEvent;

/**
 * Factory for FireOutbreakEvents.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public class FireOutbreakEventFactory extends EventFactory {

  @Override
  public EventTypes getType() {
    return EventTypes.FIRE_OUTBREAK;
  }

  @Override
  public Event createEvent() {
    EventReader reader = getReader();
    EventTypes type = getType();
    return new FireOutbreakEvent(reader.getNextId(), reader.getSummary(type.ordinal()),
        reader.getDescription(type.ordinal()), System.currentTimeMillis(),
        reader.getDuration(type.ordinal()), reader.getDamage(type.ordinal()));
  }

  @Override
  public Collection<EventParameter> createParameters() {
    Random random = new Random();
    Collection<EventParameter> collection = new ArrayList<EventParameter>();
    collection.add(new EventParameter("location", random.nextInt(3)));
    return collection;
  }

}
