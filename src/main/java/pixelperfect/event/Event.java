package pixelperfect.event;

/**
 * A class for storing and defining events.
 * 
 * @author David Alderliesten
 *
 */
public class Event {
	private String name;
	private int priority;
	
	/**
	 * Constructor for the event class, sets the name and priority.
	 * 
	 * @param passedName
	 * @param passedPriority
	 */
	public Event(String passedName, int passedPriority) {
		this.name = passedName;
		this.priority = passedPriority;
	}
}
