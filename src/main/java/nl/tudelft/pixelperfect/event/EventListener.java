package nl.tudelft.pixelperfect.event;

import java.util.ArrayList;
import java.util.Collection;

import nl.tudelft.pixelperfect.event.parameter.EventParameter;
import nl.tudelft.pixelperfect.event.type.EventTypes;
import nl.tudelft.pixelperfect.game.Game;

/**
 * Interface for classes that can subscribe themselves to an EventScheduler.
 * 
 * @author Jesse Tilro
 * @author Wouter Zirkzee
 *
 */
public interface EventListener {

	/**
	 * Get notified of a new events by an EventScheduler subscribed to.
	 * 
	 * @param event
	 *            The event that is introduced by the scheduler.
	 */
	void notify(Event event);

	/**
	 * Discard a given event from this listener's active queue.
	 * 
	 * @param event
	 *            Event to be discarded.
	 */
	void discard(Event event);

	/**
	 * Update the event listener.
	 */
	void update();

	/**
	 * Removes a completed event from the list before it is expired, after
	 * validating whether there is such an Event with the specified type and
	 * parameters.
	 * 
	 * @param type
	 *            The type of Event completed.
	 * @param parameters
	 *            The submitted parameters.
	 * @param game
	 *            The passed game.
	 */
	void complete(EventTypes type, Collection<EventParameter> parameters, Game game);

	/**
	 * Get first event of specified type.
	 * 
	 * @param type
	 *            type to find.
	 * @return event of given type
	 */
	Event getFirst(EventTypes type);

	/**
	 * Get all the events that are in the log.
	 * 
	 * @return All events in the log.
	 */
	ArrayList<Event> getEvents();
}
