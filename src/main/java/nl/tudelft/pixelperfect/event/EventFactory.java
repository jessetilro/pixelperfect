package nl.tudelft.pixelperfect.event;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Not a factory as the design pattern, but used for id's, to create a random event, with unique
 * id's.
 * 
 * @author Wouter Zirkzee
 * @author Jesse Tilro
 * @author Floris Doolaard
 * 
 */
public class EventFactory {

  private static String dataFile = "src/main/resources/data/events.json";

  private int id;
  private Random random;
  private EventReader reader;
  private Map<Integer, Class<? extends Event>> associations;

  /**
   * Constructor for EventFactory.
   */
  public EventFactory() {
    id = 1;
    reader = EventReader.getInstance();
    reader.readFromFile(dataFile);
    random = new Random();

    associations = new HashMap<Integer, Class<? extends Event>>();
    associations.put(1, AsteroidFieldEvent.class);
    associations.put(2, FireEvent.class);
    associations.put(3, HostileShipEvent.class);
    associations.put(4, PlasmaLeakEvent.class);
  }

  /**
   * Generate a random event.
   *
   * @return an Event.
   */
  public Event randomEvent() {
    int rand = 1 + random.nextInt(4);
    return create(rand);
  }

  /**
   * Create an event based on the parameter.
   *
   * @param type
   *          Defines which event is to be created.
   * 
   * @return an Event.
   */
  public Event create(int type) {
    Class<? extends Event> eventClass = associations.get(type);
    if (eventClass != null) {
      Constructor<? extends Event>[] constructors = (Constructor<? extends Event>[]) eventClass
          .getConstructors();
      Constructor<? extends Event> constructor = constructors[0];
      try {
        return constructor.newInstance(id++, reader.getSummary(type), reader.getDescription(type),
            System.currentTimeMillis(), reader.getDuration(type), reader.getDamage(type));
      } catch (InstantiationException e) {
        return null;
      } catch (IllegalAccessException e) {
        return null;
      } catch (IllegalArgumentException e) {
        return null;
      } catch (InvocationTargetException e) {
        return null;
      }
    }
    return null;
  }
}
