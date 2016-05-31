package nl.tudelft.pixelperfect.event.parameter;

/**
 * Enumeration of possible specific values for an EventParameter.
 * 
 * @author Jesse Tilro
 *
 */
public enum EventParameterValues {
  GENERIC {
    @Override
    public String toString() {
      return "Number";
    }
  },
  HOSTILE_SHIP_ARMOR_ENERGY_SHIELD {
    @Override
    public String toString() {
      return "Energy Shield";
    }
  },
  HOSTILE_SHIP_ARMOR_TITANIUM {
    @Override
    public String toString() {
      return "Titanium";
    }
  };

  /**
   * Returns the String representation of this parameter value.
   */
  public abstract String toString();
}
