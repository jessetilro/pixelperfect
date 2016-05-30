package nl.tudelft.pixelperfect.event;

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
