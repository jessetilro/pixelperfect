package nl.tudelft.pixelperfect.event.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import nl.tudelft.pixelperfect.event.EventReader;
import nl.tudelft.pixelperfect.event.parameter.EventParameter;
import nl.tudelft.pixelperfect.event.type.AsteroidImpactEvent;
import nl.tudelft.pixelperfect.event.type.EventTypes;

/**
 * Factory for AsteroidImpactEvents.
 * 
 * @author Jesse Tilro
 *
 */
public class AsteroidImpactEventFactory extends EventFactory {
  
  @Override
  public EventTypes getType() {
    return EventTypes.ASTEROID_IMPACT;
  }
  
  @Override
  public AsteroidImpactEvent createEvent() {
    EventReader reader = getReader();
    EventTypes type = getType();
    return new AsteroidImpactEvent(reader.getNextId(), reader.getSummary(type.ordinal()),
        reader.getDescription(type.ordinal()), System.currentTimeMillis(),
        reader.getDuration(type.ordinal()), reader.getDamage(type.ordinal()));
  }

  @Override
  public Collection<EventParameter> createParameters() {
    Collection<EventParameter> collection = new ArrayList<EventParameter>();
    Random random = new Random();    
    collection.add(new EventParameter("Asteroid Impact", random.nextInt(2)));
    return collection;
  }

}
