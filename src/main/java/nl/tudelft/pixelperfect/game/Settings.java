package nl.tudelft.pixelperfect.game;

/**
 * A singleton data class for settings tracking that are used across the game.
 * 
 * @author David Alderliesten
 *
 */
public final class Settings {
  private static volatile Settings instance;

  private static boolean isDebug = false;

  /**
   * Gets the current instance of the settings class.
   * 
   * @return the settings instance
   */
  public static Settings getInstance() {
    if (instance == null) {
      synchronized (Settings.class) {
        instance = new Settings();
      }
    }
    return instance;
  }

  /**
   * Constructor for the Settings class.
   */
  private Settings() {
  }

  /**
   * Get the isDebug state.
   * 
   * @return isDebug
   */
  public static boolean getIsDebug() {
    return isDebug;
  }

  /**
   * Flips the isDebug state to the on state of the boolean.
   */
  public static void isDebugOn() {
    isDebug = true;
  }
  
  /**
   * Flips the isDebug state to the on state of the boolean.
   */
  public static void isDebugOff() {
    isDebug = false;
  }

}
