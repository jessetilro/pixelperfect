package pixelperfect.event;

/**
 * A class for storing and defining events.
 * 
 * @author David Alderliesten
 *
 */
public class Event {
	private String summary, description;
	private int type, priority;
	private double timestamp, duration, damage;

	/**
	 * Constructor for the event class, taking parameters for the type of event,
	 * a summary of the event/name, a description of the event, a timestamp to
	 * start, a duration, and a damage if the event is failed.
	 * 
	 * @param type
	 * @param summary
	 * @param description
	 * @param timestamp
	 * @param duration
	 * @param damage
	 */
	public Event(int type, String summary, String description, double timestamp, double duration, double damage) {
		this.type = type;
		this.summary = summary;
		this.description = description;
		this.timestamp = timestamp;
		this.duration = duration;
		this.damage = damage;
	}
}
