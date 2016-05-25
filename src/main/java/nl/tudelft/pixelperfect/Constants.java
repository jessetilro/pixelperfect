package nl.tudelft.pixelperfect;

/**
 * A data class for storing game-wide items that can all be altered in a single sweep.
 * 
 * @author David Alderliesten
 *
 */
public final class Constants {

  /**
   * Empty private constructor, since this is a utility class.
   */
  private Constants() {
  }

  // Gui-styling related constants for the in-game HUD.
  public static final int GUI_LOG_WIDTH_OFFSET = 150;
  public static final int GUI_LOG_HEIGHT_OFFSET = 50;

  public static final int GUI_HEALTH_WIDTH_OFFSET = 300;
  public static final int GUI_HEALTH_HEIGHT_OFFSET = 100;

  // Gui text for the in-game HUd.
  public static final String NO_EVENTS_LOG_TEXT = "Everything looks clear, cap'n!";
  public static final String SHIP_HEALTH_LABEL = "Health: ";

  // Target intensity for the Event Scheduler.
  public static final double EVENT_SCHEDULER_INTENSITY_MIN = 0.125;
  public static final double EVENT_SCHEDULER_INTENSITY_MAX = 0.5;
}
