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

  public static final int GUI_SCORE_WIDTH_OFFSET = 400;
  public static final int GUI_SCORE_HEIGHT_OFFSET = 150;

  public static final int GUI_TIME_WIDTH_OFFSET = 400;
  public static final int GUI_TIME_HEIGHT_OFFSET = 0;

  // Gui text for the in-game HUd.
  public static final String NO_EVENTS_LOG_TEXT = "Everything looks clear, cap'n!";
  public static final String SHIP_HEALTH_LABEL = "Health: ";
  public static final String SHIP_SCORE_LABEL = "Score: ";
  public static final String SHIP_TIME_LABEL = "Time: ";

  // Target intensity for the Event Scheduler.
  public static final double EVENT_SCHEDULER_INTENSITY_MIN = 0.125;
  public static final double EVENT_SCHEDULER_INTENSITY_MAX = 0.5;

  // Path to the Events data file on the file system.
  public static final String EVENT_DATA_FILE = "src/main/resources/data/events.json";
}
