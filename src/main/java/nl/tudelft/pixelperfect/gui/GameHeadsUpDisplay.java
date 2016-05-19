package nl.tudelft.pixelperfect.gui;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.scene.Node;

import nl.tudelft.pixelperfect.Constants;
import nl.tudelft.pixelperfect.Spaceship;
import nl.tudelft.pixelperfect.event.Event;
import nl.tudelft.pixelperfect.event.EventLog;

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
   * Sets-up text display for the in-game HUD.
   */
  private void setupTextDisplay() {
    // Loading the font stored in the jme default manager.
    hudFont = assetManager.loadFont("Interface/Fonts/Default.fnt");

    // Init for the log text, including font loading and text setting.
    captainLog = new BitmapText(hudFont, true);
    captainLog.setLocalTranslation(screenWidth - Constants.guiWidthOffset,
        screenHeight - Constants.guiHeightOffset, 0);

    // Attach the log to the gui nodes.
    guiNodes.attachChild(captainLog);
  }

  /**
   * Method responsible for the updating of the hud text and displaying the events, along with their
   * remaining time.
   */
  public void updateHud() {
    ArrayList<Event> currentEvents = spaceship.getLog().getEvents();
    ArrayList<String> currentEventsToDisplay = new ArrayList<String>();

    for (Event current : currentEvents) {
      currentEventsToDisplay.add(current.getSummary() + " (" + current.getDuration() / 1000 + ")");
    }

    if (currentEventsToDisplay.isEmpty()) {
      captainLog.setText(Constants.noEventsLogText);
    } else {
      captainLog.setText(currentEventsToDisplay.toString());
    }
  }
}
