package nl.tudelft.pixelperfect.event;

import java.util.Arrays;
import java.util.Collection;

import nl.tudelft.pixelperfect.event.parameter.EventParameter;
import nl.tudelft.pixelperfect.event.parameter.EventParameterCollection;
import nl.tudelft.pixelperfect.event.type.EventTypes;
import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.game.Scene;
import nl.tudelft.pixelperfect.game.Spaceship;

/**
 * A class for storing and defining events, called upon by the event scheduler.
 *
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public abstract class Event {
	private String summary;
	private String description;
	private int id;
	private long timestamp;
	private long duration;
	private double damage;
	private EventParameterCollection parameters;

	/**
	 * Constructor for the event class, taking parameters for the type of event,
	 * a summary of the event/name, a description of the event, a timestamp to
	 * start, a duration, and a damage if the event is failed.
	 *
	 * @param id
	 *            The unique id of the event.
	 * @param summary
	 *            Summary/name of the event.
	 * @param description
	 *            A description of the event.
	 * @param timestamp
	 *            The timestamp of start of the event.
	 * @param duration
	 *            The time to live milliseconds until the event expires.
	 * @param damage
	 *            The damage done to the ship on even failure.
	 */
	public Event(int id, String summary, String description, long timestamp, long duration, double damage) {
		this.id = id;
		this.summary = summary;
		this.description = description;
		this.timestamp = timestamp;
		this.duration = duration;
		this.damage = damage;
		this.parameters = new EventParameterCollection();
	}

	/**
	 * Get the type of this Event.
	 *
	 * @return The EventType.
	 */
	public abstract EventTypes getType();

	/**
	 * Getter for the event id.
	 *
	 * @return The id of event.
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Getter for the event summary/name.
	 *
	 * @return Summary/name of the event.
	 */
	public String getSummary() {
		return this.summary;
	}

	/**
	 * Get the description of the event.
	 *
	 * @return Description of event.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Get the timestamp of the event.
	 *
	 * @return Timestamp of event.
	 */
	public Long getTimestamp() {
		return this.timestamp;
	}

	/**
	 * Get the duration of the event.
	 *
	 * @return Duration of event.
	 */
	public Long getDuration() {
		return this.duration;
	}

	/**
	 * Get the time remaining of the event.
	 *
	 * @param currentTime
	 *            the current time.
	 *
	 * @return The time remaining.
	 */
	public String getTimeLeft(long currentTime) {
		return Long.toString((((this.timestamp + this.duration) - currentTime) / 1000));
	}

	/**
	 * Check whether the event is expired at a given moment in time.
	 *
	 * @param time
	 *            The moment in time at which to check the expiration.
	 * @return Whether the event is expired.
	 */
	public boolean isExpired(long time) {
		return (time > (timestamp + duration));
	}

	/**
	 * Apply damage to a given Spaceship.
	 *
	 * @param spaceship
	 *            The Spaceship to apply damage to.
	 */
	public void applyDamage(Spaceship spaceship) {
		spaceship.updateHealth(-1 * damage);
	}

	/**
	 * Get the damage that will be done to the ship in the case of event
	 * failure.
	 *
	 * @return The event's damage value.
	 */
	public double getDamage() {
		return this.damage;
	}

	/**
	 * Update this Event's parameter collection to consist of the parameters in
	 * the given collection.
	 *
	 * @param collection
	 *            A Collection of EventParameters.
	 */
	public void setParameters(Collection<EventParameter> collection) {
		parameters = new EventParameterCollection(collection);
	}

	/**
	 * Check whether the given collection of parameters matches this Event's
	 * parameters.
	 *
	 * @param collection
	 *            A Collection of EventParameters.
	 * @return Whether the parameters are valid.
	 */
	public boolean validateParameters(Collection<EventParameter> collection) {
		return parameters.validate(collection);
	}

	/**
	 * Generate a String representation of this Event and its parameters.
	 *
	 * @return A String representation of this Event and its parameters.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Collection<String> components = Arrays.asList(new String[] { summary, ": ", description,
				" Take note of the following details: ", parameters.toString(), "." });
		for (String component : components) {
			sb.append(component);
		}
		return sb.toString();
	}

	/**
	 * Generate a debug string with only timing and parameters.
	 *
	 * @return debug string.
	 */
	public String toDebugString() {
		StringBuilder sb = new StringBuilder();
		Collection<String> components = Arrays.asList(new String[] { summary, " (",
				getTimeLeft(System.currentTimeMillis()), "), Param: (", parameters.toDebugString() + ")" });
		for (String component : components) {
			sb.append(component);
		}
		return sb.toString();
	}

	/**
	 * Allow events to render notifications to the players.
	 *
	 * @param game
	 *            The current game.
	 * @param scene
	 *            The scene in which the notification must appear.
	 */
	public abstract void notification(Game game, Scene scene);

	/**
	 * Get parameters for this event.
	 *
	 * @return EventParamterCollection containing parameters.
	 */
	public EventParameterCollection getParameters() {
		return parameters;
	}

	/**
	 * Method responsible for handling event completion actions that are not
	 * related to game logic.
	 */
	public abstract void onComplete(Game game);

}
