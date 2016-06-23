package nl.tudelft.pixelperfect.player;

import java.util.Arrays;
import java.util.List;

import nl.tudelft.pixelperfect.event.type.EventTypes;

/**
 * Enums representing the roles in the game.
 * 
 * @author Floris Doolaard
 * @author Jesse Tilro
 */
public enum PlayerRoles {
  GUNNER {
    public List<EventTypes> getEventTypesSolved() {
      return Arrays.asList(new EventTypes[] { EventTypes.HOSTILE_SHIP, EventTypes.FIRE_OUTBREAK });
    }
  },
  ENGINEER {
    public List<EventTypes> getEventTypesSolved() {
      return Arrays.asList(new EventTypes[] { EventTypes.ASTEROID_IMPACT, EventTypes.FIRE_OUTBREAK });
    }
  },
  SCIENTIST {
    public List<EventTypes> getEventTypesSolved() {
      return Arrays.asList(new EventTypes[] { EventTypes.PLASMA_LEAK, EventTypes.FIRE_OUTBREAK });
    }
  },
  JANITOR {
    public List<EventTypes> getEventTypesSolved() {
      return Arrays.asList(new EventTypes[] { EventTypes.COFFEE_BOOST, EventTypes.FIRE_OUTBREAK });
    }
  };

  /**
   * Get a list over EventTypes a player with this role can solve.
   * 
   * @return A list of EventTypes this role can solve.s
   */
  public abstract List<EventTypes> getEventTypesSolved();

  /**
   * Check whether this role is able to solve a given Event type.
   * 
   * @param eventType
   *          The type of Event to be solved.
   * @return Whether this role can solve the Event.
   */
  public boolean canSolveEventType(EventTypes eventType) {
    return getEventTypesSolved().contains(eventType);
  }
}
