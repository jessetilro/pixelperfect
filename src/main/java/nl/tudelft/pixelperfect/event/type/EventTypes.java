package nl.tudelft.pixelperfect.event.type;

import nl.tudelft.pixelperfect.event.factory.AsteroidImpactEventFactory;
import nl.tudelft.pixelperfect.event.factory.CoffeeBoostEventFactory;
import nl.tudelft.pixelperfect.event.factory.EventFactory;
import nl.tudelft.pixelperfect.event.factory.FireOutbreakEventFactory;
import nl.tudelft.pixelperfect.event.factory.HostileShipEventFactory;
import nl.tudelft.pixelperfect.event.factory.PlasmaLeakEventFactory;

/**
 * Enumeration of the different types of Events in the game. The ordinals, i.e. the indices of the
 * types in this enumeration, correspond to the numeric type identifiers of the Event types
 * described in the Event Data File. Keep that in mind when updating this enumeration!
 * 
 * @author Jesse Tilro
 *
 */
public enum EventTypes {
  FIRE_OUTBREAK {
    @Override
    public EventFactory getFactory() {
      return new FireOutbreakEventFactory();
    }
  },
  PLASMA_LEAK {
    @Override
    public EventFactory getFactory() {
      return new PlasmaLeakEventFactory();
    }
  },
  ASTEROID_IMPACT {
    @Override
    public EventFactory getFactory() {
      return new AsteroidImpactEventFactory();
    }
  },
  HOSTILE_SHIP {
    @Override
    public EventFactory getFactory() {
      return new HostileShipEventFactory();
    }
  },
  COFFEE_BOOST {
    @Override
    public EventFactory getFactory() {
      return new CoffeeBoostEventFactory();
    }
  };

  /**
   * Get the Factory to be used for constructing the specific type of Event.
   * 
   * @return An EventFactory.
   */
  public abstract EventFactory getFactory();
}
