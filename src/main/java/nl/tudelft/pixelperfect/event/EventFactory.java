package nl.tudelft.pixelperfect.event;

/**
 * Created by woute on 5/10/2016.
 */

import java.util.Random;

/**
 * Not a factory as the design pattern, but used for id's.
 * <p>
 * Create a random event, with unique id's.
 */
public class EventFactory {

  private int id;
  private Random random;

  /**
   * Constructor for EventFactory.
   */
  public EventFactory() {
    id = 0;
    random = new Random();
  }

  /**
   * Generate a random event.
   *
   * @return an Event.
   */
  public Event randomEvent() {
    int x = random.nextInt(4);
    return create(x);
  }

  /**
   * Create an event based on the parameter.
   *
   * @param x Defines which event is to be created.
   * @return an Event.
   */
  public Event create(int x) {
    Event tbr;
    switch (x) {
      default:
        tbr = null;
        break;
      case 0:
        tbr = new AsteroidFieldEvent(id, "Asteroid Field",
            "Watch out, you are approaching an asteroid field!",
            System.currentTimeMillis(), 4000, 10);
        break;
      case 1:
        tbr = new FireEvent(id, "Fire",
            "Alert! Faulty wiring caused a fire!",
            System.currentTimeMillis(), 4000, 10);
        break;
      case 2:
        tbr = new HostileShipEvent(id, "Hostile Ship",
            "A hostile spaceship is near, prepare to defend yourself!",
            System.currentTimeMillis(), 4000, 10);
        break;
      case 3:
        tbr = new PlasmaLeakEvent(id, "Plasma Leak",
            "Plasma pressure is dropping, there must be a leak!",
            System.currentTimeMillis(), 4000, 10);
        break;
    }
    id++;
    return tbr;
  }
}
