package pixelperfect.event;

/**
 * A class for storing and defining events, called upon by the event scheduler.
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

	/**
	 * Getter for the event type.
	 * 
	 * @return type of event
	 */
	public int getType() {
		return this.type;
	}

	/**
	 * Getter for the event summary/name.
	 * 
	 * @return summary of event/name
	 */
	public String getSummary() {
		return this.summary;
	}

	/**
	 * Get the description of the event.
	 * 
	 * @return description of event
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Get the starting timestamp of the event.
	 * 
	 * @return starting timestamp of event
	 */
	public double getTimeStamp() {
		return this.timestamp;
	}

	/**
	 * Get the duration of event that is remaining.
	 * 
	 * @return event duration
	 */
	public double getDuration() {
		return this.duration;
	}

	/**
	 * Get the damage done to the ship in the case of event failure.
	 * 
	 * @return event damage done to ship
	 */
	public double getDamage() {
		return this.damage;
	}

	/**
	 * Updates the duration by subtracting the given double from the current
	 * time left.
	 * 
	 * @param timePassed
	 */
	public void updateDuration(double timePassed) {
		this.duration = this.duration - timePassed;
	}
}
