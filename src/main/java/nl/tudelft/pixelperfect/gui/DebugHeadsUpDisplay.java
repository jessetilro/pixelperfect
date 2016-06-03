package nl.tudelft.pixelperfect.gui;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.scene.Node;

import nl.tudelft.pixelperfect.event.Event;
import nl.tudelft.pixelperfect.game.Constants;
import nl.tudelft.pixelperfect.game.Spaceship;

import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Class that displays relevant information in the heads-up display during the gameplay when the
 * debug mode has been activated.
 * 
 * @author David Alderliesten
 *
 */
public class DebugHeadsUpDisplay {
  private Spaceship spaceship;

  private AssetManager assetManager;
  private Node guiNodes;
  private int screenWidth;
  private int screenHeight;

  private BitmapFont hudFont;
  private BitmapText captainLog;
  private BitmapText shipHealth;
  private BitmapText teamScore;
  private BitmapText timeLeft;
  private BitmapText playersConnected;
  private BitmapText localAddress;

  /**
   * Constructor for the debug heads-up display for in-game utilization.
   * 
   * @param passedMan
   *          the passed assetmanager.
   * @param passedGuiNode
   *          the passed gui node.
   * @param passedWid
   *          the passed screen width.
   * @param passedHi
   *          the passed screen height.
   * @param passedShip
   *          the passed Spaceship instance.
   */
  public DebugHeadsUpDisplay(AssetManager passedMan, Node passedGuiNode, int passedWid,
      int passedHi, Spaceship passedShip) {
    this.assetManager = passedMan;
    this.guiNodes = passedGuiNode;
    this.screenWidth = passedWid;
    this.screenHeight = passedHi;
    this.spaceship = passedShip;

    // Sets-up the text display using the private method.
    setupTextDisplay();
  }

  /**
   * Sets-up text display for the in-game debug HUD, along with all of its elements.
   */
  private void setupTextDisplay() {
    // Set-up the bitmap text needed for HUD display.
    setUpBitmapText();

    // Add the generated bitmaps to the gui node view.
    guiNodes.attachChild(captainLog);
    guiNodes.attachChild(shipHealth);
    guiNodes.attachChild(teamScore);
    guiNodes.attachChild(timeLeft);
    guiNodes.attachChild(playersConnected);
    guiNodes.attachChild(localAddress);
  }

  /**
   * Set-up the bitmap associations for the debug HUD text.
   */
  private void setUpBitmapText() {
    // Loading the font stored in the jme default manager.
    hudFont = assetManager.loadFont("Interface/Fonts/Default.fnt");

    // Init for the log text, including font loading and text setting.
    captainLog = new BitmapText(hudFont, true);
    captainLog.setLocalTranslation(screenWidth - Constants.DEBUG_LOG_WIDTH_OFFSET,
        screenHeight - Constants.DEBUG_LOG_HEIGHT_OFFSET, 0);

    // Init for the HUD text, including font loading and text setting.
    shipHealth = new BitmapText(hudFont, true);
    shipHealth.setLocalTranslation(screenWidth + 150 - Constants.DEBUG_HEALTH_WIDTH_OFFSET,
        screenHeight - Constants.DEBUG_HEALTH_HEIGHT_OFFSET, 0);

    teamScore = new BitmapText(hudFont, true);
    teamScore.setLocalTranslation(screenWidth + 250 - Constants.DEBUG_SCORE_WIDTH_OFFSET,
        screenHeight - Constants.DEBUG_SCORE_HEIGHT_OFFSET, 0);

    timeLeft = new BitmapText(hudFont, true);
    timeLeft.setLocalTranslation(screenWidth + 250 - Constants.DEBUG_TIME_WIDTH_OFFSET,
        screenHeight - Constants.DEBUG_TIME_HEIGHT_OFFSET, 0);

    playersConnected = new BitmapText(hudFont, true);
    playersConnected.setLocalTranslation(screenWidth + 250 - Constants.DEBUG_CONNECTED_WIDTH_OFFSET,
        screenHeight - Constants.DEBUG_CONNECTED_HEIGHT_OFFSET, 0);

    localAddress = new BitmapText(hudFont, true);
    localAddress.setLocalTranslation(screenWidth + 250 - Constants.DEBUG_IP_WIDTH_OFFSET,
        screenHeight - Constants.DEBUG_IP_HEIGHT_OFFSET, 0);
  }

  /**
   * Method responsible for the updating of the debug hud text and displaying the events, along with
   * their remaining time and health, and other HUD elements.
   */
  public void updateHud() {
    ArrayList<Event> currentEvents = spaceship.getLog().getEvents();
    ArrayList<String> currentEventsToDisplay = new ArrayList<String>();

    // Fetch all event times for updates and display.
    for (Event current : currentEvents) {
      currentEventsToDisplay.add(current.toDebugString());
    }

    // Update the captain's log.
    if (currentEventsToDisplay.isEmpty()) {
      captainLog.setText(Constants.DEBUG_NO_EVENTS_LOG_TEXT);
    } else {
      captainLog.setText(currentEventsToDisplay.toString());
    }

    // Update the ship's health, team score, connected players, and time left indicators.
    shipHealth.setText(Constants.DEBUG_SHIP_HEALTH_LABEL + spaceship.getHealth());
    teamScore.setText(Constants.DEBUG_SHIP_SCORE_LABEL + spaceship.getScore());
    timeLeft.setText(Constants.DEBUG_SHIP_TIME_LABEL + spaceship.getTimer());
    playersConnected.setText(Constants.DEBUG_CONNECTED_LABEL + spaceship.getCrew().size());

    // Printing the server local IP address.
    try {
      localAddress.setText(Constants.DEBUG_IP_LABEL + InetAddress.getLocalHost());
    } catch (Exception error) {
      // Printing the IP error if it occurs.
      System.out.println(error);
    }
  }

  /**
   * Clears the debug display after de-activation of the debug mode.
   */
  public void clearHud() {
    captainLog.setText("");
    shipHealth.setText("");
    teamScore.setText("");
    timeLeft.setText("");
    playersConnected.setText("");
    localAddress.setText("");
  }

}