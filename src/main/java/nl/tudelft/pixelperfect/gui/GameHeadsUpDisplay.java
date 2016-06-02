package nl.tudelft.pixelperfect.gui;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;

import nl.tudelft.pixelperfect.event.Event;
import nl.tudelft.pixelperfect.game.Constants;
import nl.tudelft.pixelperfect.game.Spaceship;

import java.util.ArrayList;

/**
 * Class responsible for the display, refreshing, and functionality of the heads-up display (HUD)
 * that is displayed during the active gameplay for the Client. Score and Ship health are displayed
 * constantly, and the current events are shown when they arise.
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
  private BitmapText currentEvents;
  private BitmapText shipHealth;
  private BitmapText teamScore;

  /**
   * Constructor for the heads-up display for in-game utilization and display.
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
  public GameHeadsUpDisplay(AssetManager passedMan, Node passedGuiNode, int passedWid, int passedHi,
      Spaceship passedShip) {
    this.assetManager = passedMan;
    this.guiNodes = passedGuiNode;
    this.screenWidth = passedWid;
    this.screenHeight = passedHi;
    this.spaceship = passedShip;

    // Setting-up the fonts required for the Bitmap display.
    setupFont();
  }

  /**
   * Private class responsible for setting-up the bitmap fonts and attaching the HUD elements to the
   * guiNodes.
   */
  private void setupFont() {
    // Loading the font stored in the jme default manager.
    hudFont = assetManager.loadFont("Interface/Fonts/Default.fnt");

    // Init for the log text, including font loading and text setting.
    currentEvents = new BitmapText(hudFont, true);
    currentEvents.setColor(ColorRGBA.LightGray);
    currentEvents.setLocalTranslation(screenWidth - Constants.GUI_LOG_WIDTH_OFFSET,
        screenHeight - Constants.GUI_LOG_HEIGHT_OFFSET, 0);

    // Init for the health text, including font loading and text setting.
    shipHealth = new BitmapText(hudFont, true);
    shipHealth.setLocalScale(Constants.GUI_HEALTH_TEXT_SIZE_SCALE);
    shipHealth.setColor(ColorRGBA.Red);
    shipHealth.setLocalTranslation(screenWidth - Constants.GUI_HEALTH_WIDTH_OFFSET, screenHeight,
        0);

    // Init for the score text, including font loading and text setting.
    teamScore = new BitmapText(hudFont, true);
    teamScore.setLocalScale(Constants.GUI_SCORE_TEXT_SIZE_SCALE);
    teamScore.setColor(ColorRGBA.Green);
    teamScore.setLocalTranslation(screenWidth, screenHeight, 0);

    // Add the generated bitmaps to the gui node view.
    guiNodes.attachChild(currentEvents);
    guiNodes.attachChild(shipHealth);
    guiNodes.attachChild(teamScore);
  }

  /**
   * Method responsible for the updating of the hud text and displaying the events, along with their
   * remaining time and health, and other HUD elements.
   */
  public void updateHud() {
    // Update the ship's health and team score indicators.
    shipHealth.setText("" + spaceship.getHealth());
    teamScore.setText("" + spaceship.getScore());
    
    // Array lists to store the events.
    ArrayList<Event> currentEventsArray = spaceship.getLog().getEvents();
    ArrayList<String> currentEventsToDisplay = new ArrayList<String>();

    // Fetch all event times for updates and display.
    for (Event current : currentEventsArray) {
      currentEventsToDisplay.add(current.toDebugString());
    }

    // Update the active event log.
    if (currentEventsToDisplay.isEmpty()) {
      currentEvents.setText("");
    } else {
      currentEvents.setText(currentEventsToDisplay.toString());
    }
  }
}
