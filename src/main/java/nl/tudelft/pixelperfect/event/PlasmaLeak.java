package nl.tudelft.pixelperfect.event;

/**
 * Created by woute on 5/10/2016.
 */
public class PlasmaLeak extends Event {

    /**
     * Constructor for the PlasmaLeak event.
     * @param type
     *          the desired type
     * @param summary
     *          summary/name of the event
     * @param description
     *          a description of the event
     * @param timestamp
     *          the timestamp of start of the event
     * @param duration
     *          The time to live milliseconds until the event expires.
     * @param damage
     *          the damage done to the ship on even failure
     */
    public PlasmaLeak(int type, String summary, String description,
                      long timestamp, long duration, double damage) {
        super(type, summary, description, timestamp, duration, damage);
    }
}
