package nl.tudelft.pixelperfect.event.parameter;

import com.jme3.network.serializing.Serializable;

/**
 * Enumeration of possible specific values for an EventParameter.
 * 
 * @author Jesse Tilro
 *
 */
@Serializable
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
   * 
   * @return A String representation of the value.
   */
  public abstract String toString();
}
