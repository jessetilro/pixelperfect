package nl.tudelft.pixelperfect.game;

/**
 * A data class for storing game-wide items that can all be altered in a single sweep.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public final class Constants {

  /**
   * Empty private constructor, since this is a data/utility class.
   */
  private Constants() {
  }

  // Positioning related constants for the in-game debug HUD.
  public static final int DEBUG_LOG_WIDTH_OFFSET = 150;
  public static final int DEBUG_LOG_HEIGHT_OFFSET = 50;

  public static final int DEBUG_HEALTH_WIDTH_OFFSET = 300;
  public static final int DEBUG_HEALTH_HEIGHT_OFFSET = 100;

  public static final int DEBUG_SCORE_WIDTH_OFFSET = 400;
  public static final int DEBUG_SCORE_HEIGHT_OFFSET = 150;

  public static final int DEBUG_TIME_WIDTH_OFFSET = 400;
  public static final int DEBUG_TIME_HEIGHT_OFFSET = 0;

  // Text constants for the in-game debug HUD.
  public static final String DEBUG_NO_EVENTS_LOG_TEXT = "No events currently active";
  public static final String DEBUG_SHIP_HEALTH_LABEL = "Health: ";
  public static final String DEBUG_SHIP_SCORE_LABEL = "Score: ";
  public static final String DEBUG_SHIP_TIME_LABEL = "Time: ";

  // Positioning and scaling related constants for the in-game HUD.
  public static final int GUI_LOG_WIDTH_OFFSET = 1000;
  public static final int GUI_HEALTH_WIDTH_OFFSET = 1700;
  
  public static final int GUI_HEALTH_TEXT_SIZE_SCALE = 2;
  public static final int GUI_SCORE_TEXT_SIZE_SCALE = 2;

  // Target intensity for the Event Scheduler.
  public static final double EVENT_SCHEDULER_INTENSITY_MIN = 0.125;
  public static final double EVENT_SCHEDULER_INTENSITY_MAX = 0.5;

  // Path to the Events data file on the file system.
  public static final String EVENT_DATA_FILE = "src/main/resources/data/events.json";
}
