package nl.tudelft.pixelperfect.gui;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.scene.Node;

import nl.tudelft.pixelperfect.Constants;
import nl.tudelft.pixelperfect.Spaceship;
import nl.tudelft.pixelperfect.event.Event;

import java.util.ArrayList;

/**
 * Class that displays relevant information in the heads-up display during the gameplay.
 * 
 * @author David Alderliesten
 *
 */
public class GameHeadsUpDisplay {
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

  /**
   * Constructor for the heads-up display for in-game utiliztion.
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
   *          the passed Sapceship instance.
   */
  public GameHeadsUpDisplay(AssetManager passedMan, Node passedGuiNode, int passedWid, int passedHi,
      Spaceship passedShip) {
    this.assetManager = passedMan;
    this.guiNodes = passedGuiNode;
    this.screenWidth = passedWid;
    this.screenHeight = passedHi;
    this.spaceship = passedShip;

    // Sets-up the text display using the private method.
    setupTextDisplay();
  }

  /**
   * Sets-up text display for the in-game HUD, along with all of its elements.
   */
  private void setupTextDisplay() {
    // Set-up the bitmap text needed for HUD display.
    setUpBitmapText();

    // Attach the elements to the gui nodes.
    guiNodes.attachChild(captainLog);
    guiNodes.attachChild(shipHealth);
    guiNodes.attachChild(teamScore);
    guiNodes.attachChild(timeLeft);
  }

  /**
   * Set-up the bitmap associations for the HUD text.
   */
  private void setUpBitmapText() {
    // Loading the font stored in the jme default manager.
    hudFont = assetManager.loadFont("Interface/Fonts/Default.fnt");

    // Init for the log text, including font loading and text setting.
    captainLog = new BitmapText(hudFont, true);
    captainLog.setLocalTranslation(screenWidth - Constants.GUI_LOG_WIDTH_OFFSET,
        screenHeight - Constants.GUI_LOG_HEIGHT_OFFSET, 0);

    // Init for the HUD text, including font loading and text setting.
    shipHealth = new BitmapText(hudFont, true);
    shipHealth.setLocalTranslation(screenWidth + 150 - Constants.GUI_HEALTH_WIDTH_OFFSET,
        screenHeight - Constants.GUI_HEALTH_HEIGHT_OFFSET, 0);

    teamScore = new BitmapText(hudFont, true);
    teamScore.setLocalTranslation(screenWidth + 250 - Constants.GUI_SCORE_WIDTH_OFFSET,
        screenHeight - Constants.GUI_SCORE_HEIGHT_OFFSET, 0);

    timeLeft = new BitmapText(hudFont, true);
    timeLeft.setLocalTranslation(screenWidth + 250 - Constants.GUI_TIME_WIDTH_OFFSET,
        screenHeight - Constants.GUI_TIME_HEIGHT_OFFSET, 0);
  }

  /**
   * Method responsible for the updating of the hud text and displaying the events, along with their
   * remaining time and health, and other HUD elements.
   */
  public void updateHud() {
    ArrayList<Event> currentEvents = spaceship.getLog().getEvents();
    ArrayList<String> currentEventsToDisplay = new ArrayList<String>();

    // Fetch all event times for updates and display.
    for (Event current : currentEvents) {
      currentEventsToDisplay.add(current.getSummary() + " ("
          + current.getTimeLeft(System.currentTimeMillis()) / 1000 + ")");
    }

    // Update the captain's log.
    if (currentEventsToDisplay.isEmpty()) {
      captainLog.setText(Constants.NO_EVENTS_LOG_TEXT);
    } else {
      captainLog.setText(currentEventsToDisplay.toString());
    }

    // Update the ship's health, team score, and time left indicators.
    shipHealth.setText(Constants.SHIP_HEALTH_LABEL + spaceship.getHealth());
    teamScore.setText(Constants.SHIP_SCORE_LABEL + spaceship.getScore());
    timeLeft.setText(Constants.SHIP_TIME_LABEL + spaceship.getTimer());
  }
}
