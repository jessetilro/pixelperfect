package nl.tudelft.pixelperfect.event;

import java.util.Collection;

/**
 * Factory for AsteroidImpactEvents.
 * 
 * @author Jesse Tilro
 *
 */
public class AsteroidImpactEventFactory extends EventFactory {

  private static EventTypes type = EventTypes.ASTEROID_IMPACT;

  /**
   * Construct a new AsteroidImpactEventFactory instance.
   */
  public AsteroidImpactEventFactory() {
    super();
  }

  /**
   * Create a new AsteroidImpactEvent.
   * 
   * @return An AsteroidImpactEvent.
   */
  @Override
  public AsteroidImpactEvent createEvent() {
    return new AsteroidImpactEvent(reader.getNextId(), reader.getSummary(type.ordinal()),
        reader.getDescription(type.ordinal()), System.currentTimeMillis(),
        reader.getDuration(type.ordinal()), reader.getDamage(type.ordinal()));
  }

  @Override
  public Collection<EventParameter> createParameters() {
    // Create parameters for an AsteroidFieldEvent.
    return null;
  }

}
