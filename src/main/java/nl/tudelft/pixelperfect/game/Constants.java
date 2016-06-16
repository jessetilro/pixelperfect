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

  // Position related constants for the main menu.
  public static final int MAIN_MENU_START_HEIGHT_OFFSET = -150;
  public static final int MAIN_MENU_EXIT_HEIGHT_OFFSET = 150;

  public static final int MAIN_MENU_TEXT_SCALING = 3;

  // Text constants for the main menu.
  public static final String MAIN_MENU_START_BUTTON_TEXT = "Start Game";
  public static final String MAIN_MENU_EXIT_BUTTON_TEXT = "Exit Game";

  // Positioning related constants for the in-game debug HUD.
  public static final int DEBUG_LOG_HEIGHT_OFFSET = 300;
  public static final int DEBUG_HEALTH_HEIGHT_OFFSET = 350;
  public static final int DEBUG_SCORE_HEIGHT_OFFSET = 400;
  public static final int DEBUG_TIME_HEIGHT_OFFSET = 450;
  public static final int DEBUG_CONNECTED_HEIGHT_OFFSET = 500;
  public static final int DEBUG_IP_HEIGHT_OFFSET = 550;

  public static final int DEBUG_ELEMENTS_WIDTH_OFFSET = 100;

  // Text constants for the in-game debug HUD.
  public static final String DEBUG_NO_EVENTS_LOG_TEXT = "No events currently active";
  public static final String DEBUG_SHIP_HEALTH_LABEL = "Health: ";
  public static final String DEBUG_SHIP_SCORE_LABEL = "Score: ";
  public static final String DEBUG_SHIP_TIME_LABEL = "Time: ";
  public static final String DEBUG_CONNECTED_LABEL = "Players Connected: ";
  public static final String DEBUG_IP_LABEL = "Connection Address: ";

  // Positioning and scaling related constants for the in-game HUD.
  public static final int GUI_ELEMENTS_WIDTH_OFFSET = 80;
  public static final int GUI_ELEMENTS_HEIGHT_OFFSET = 20;

  public static final int GUI_HEALTH_TEXT_SIZE_SCALE = 2;
  public static final int GUI_SCORE_TEXT_SIZE_SCALE = 2;

  // Target intensity for the Event Scheduler.
  public static final double EVENT_SCHEDULER_INTENSITY_MIN = 0.125;
  public static final double EVENT_SCHEDULER_INTENSITY_MAX = 0.5;

  // Path to the Events data file on the file system.
  public static final String EVENT_DATA_FILE = "src/main/resources/data/events.json";
}
