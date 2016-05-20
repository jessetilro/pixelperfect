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
  public static final int GUI_WIDTH_OFFSET = 150;
  public static final int GUI_HEIGHT_OFFSET = 50;

  // Gui text for the in-game HUd.
  public static final String NO_EVENTS_LOG_TEXT = "Everything looks clear, cap'n!";
}
