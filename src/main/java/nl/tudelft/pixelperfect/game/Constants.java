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

  // Audio path information and events.
  protected static final String[] AUDIO_EVENTS = { "CoffeeEvent", "AsteroidEvent", "FireEvent",
      "HostileEvent", "PlasmaEvent", "CompleteAsteroidEvent", "CompleteCoffeeEvent",
      "CompleteFireEvent", "CompleteHostileEvent", "CompletePlasmaEvent" };
  protected static final String[] AUDIO_PATH_NAMES = { "yawn.wav", "impact.wav", "fire_alarm.wav",
      "sonar_x.wav", "bubbling1.wav", "cannon_x.wav", "drink.wav", "fire_ext.wav",
      "explosion_x.wav", "hammer.wav" };

  // Position related constants for the main menu.
  public static final int MAIN_MENU_START_HEIGHT_OFFSET = -150;
  public static final int MAIN_MENU_EXIT_HEIGHT_OFFSET = 150;

  public static final int MAIN_MENU_TEXT_SCALING = 3;

  // Text constants for the main menu.
  public static final String MAIN_MENU_FIRST_ROW_TEXT = "Allow your crew members to select their roles.";
  public static final String MAIN_MENU_SECOND_ROW_TEXT = "Once this is done, press the 'p' button to start.";

  // Positioning related constants for the in-game debug HUD.
  public static final int DEBUG_HEALTH_HEIGHT_OFFSET = 500;
  public static final int DEBUG_SCORE_HEIGHT_OFFSET = 550;
  public static final int DEBUG_TIME_HEIGHT_OFFSET = 600;
  public static final int DEBUG_CONNECTED_HEIGHT_OFFSET = 650;
  public static final int DEBUG_IP_HEIGHT_OFFSET = 700;
  public static final int DEBUG_LOG_HEIGHT_OFFSET = 750;

  public static final int DEBUG_ELEMENTS_WIDTH_OFFSET = 300;

  // Text constants for the in-game debug HUD.
  public static final String DEBUG_NO_EVENTS_LOG_TEXT = "No events currently active";
  public static final String DEBUG_SHIP_HEALTH_LABEL = "Health: ";
  public static final String DEBUG_SHIP_SCORE_LABEL = "Score: ";
  public static final String DEBUG_SHIP_TIME_LABEL = "Time: ";
  public static final String DEBUG_CONNECTED_LABEL = "Players Connected: ";
  public static final String DEBUG_IP_LABEL = "Connection Address: ";

  // Positioning and scaling related constants for the in-game HUD.
  public static final int GUI_ELEMENTS_WIDTH_OFFSET = 330;
  public static final int GUI_ELEMENTS_HEIGHT_OFFSET = 340;

  public static final int GUI_HEALTH_TEXT_SIZE_SCALE = 2;
  public static final int GUI_SCORE_TEXT_SIZE_SCALE = 2;

  // Path to the Events data file on the file system.
  public static final String EVENT_DATA_FILE = "src/main/resources/data/events.json";

  /*
   * Gameplay Variables
   */
  // Total game time
  public static final long GAME_TIME = 60000;

  // Target intensity for the Event Scheduler.
  public static final double EVENT_SCHEDULER_INTENSITY_MIN = 0.125;
  public static final double EVENT_SCHEDULER_INTENSITY_MAX = 0.5;

}
