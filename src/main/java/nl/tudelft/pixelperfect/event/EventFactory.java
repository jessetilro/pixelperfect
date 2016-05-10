package nl.tudelft.pixelperfect.event;

/**
 * Created by woute on 5/10/2016.
 */

/**
 * Not a factory as the design pattern, but used for id's.
 *
 * Create a random event, with unique id's.
 */
public class EventFactory {


    private int id;

    /**
     * Constructor for EventFactory.
     */
    public EventFactory() {
        id = 0;
    }

    /**
     * Create a pseudo random event.
     *
     * @return Event
     */
    public Event create() {
        int x = (int) Math.floor(Math.random()) % 4;
        Event tbr = null;
        switch (x) {
            case 0: tbr = new AsteroidField(id, "Asteroid", "Hello world, I am a dummy Asteroid event!", System.currentTimeMillis(), 4000, 10);
                break;
            case 1: tbr = new Fire(id, "Fire", "Hello world, I am a dummy Fire event!", System.currentTimeMillis(), 4000, 10);
                break;
            case 2: tbr = new HostileShip(id, "HostileShip", "Hello world, I am a dummy HostileShip event!", System.currentTimeMillis(), 4000, 10);
                break;
            case 3: tbr = new PlasmaLeak(id, "PlasmaLeak", "Hello world, I am a dummy PlasmaLeak event!", System.currentTimeMillis(), 4000, 10);
                break;
        }
        id++;
        return tbr;
    }
}
