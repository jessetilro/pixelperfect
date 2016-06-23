package nl.tudelft.pixelperfect.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;

import nl.tudelft.pixelperfect.game.Constants;
import nl.tudelft.pixelperfect.game.Spaceship;
import nl.tudelft.pixelperfect.player.Player;

/**
 * Class responsible for the display, refreshing, and functionality of the heads-up display (HUD)
 * that is displayed during the active gameplay for the Client. Score and Ship health are displayed
 * constantly, and the current events are shown when they arise. Crew members are displayes as the
 * join and leave.
 * 
 * @author David Alderliesten
 *
 */
public class GameHeadsUpDisplay {
  private Spaceship spaceship;

  private AssetManager assetManager;
  private Node guiNodes;
  private float screenWidth;
  private float screenHeight;

  private BitmapFont hudFont;
  private BitmapText shipHealth;
  private BitmapText teamScore;
  private List<BitmapText> crewList;

  /**
   * Constructor for the heads-up display for in-game utilization and display.
   * 
   * @param passedMan
   *          the passed assetmanager.
   * @param passedGuiNode
   *          the passed gui node.
   * @param width
   *          the passed screen width.
   * @param height
   *          the passed screen height.
   * @param passedShip
   *          the passed Spaceship instance.
   */
  public GameHeadsUpDisplay(AssetManager passedMan, Node passedGuiNode, float width, float height,
      Spaceship passedShip) {
    this.assetManager = passedMan;
    this.guiNodes = passedGuiNode;
    this.screenWidth = width;
    this.screenHeight = height;
    this.spaceship = passedShip;

    crewList = new ArrayList<BitmapText>();

    // Setting-up the fonts required for the Bitmap display.
    setupElements();
  }

  /**
   * Private class responsible for setting-up the bitmap fonts and attaching the HUD elements to the
   * guiNodes.
   */
  private void setupElements() {
    // Loading the font stored in the javamonkeyengine's default manager.
    hudFont = assetManager.loadFont("Interface/Fonts/Default.fnt");

    // Attaching the elements and preparing them.
    attachHealth();
    attachScore();
    attachCrewList();
  }

  /**
   * Initializer for the crew list display, including font loading and text setting.
   */
  private void attachCrewList() {
    for (int i = 0; i < 4; i++) {
      BitmapText text = new BitmapText(hudFont, true);
      text.setLocalScale(1.5f);
      text.setColor(ColorRGBA.White);
      text.setLocalTranslation(Constants.GUI_ELEMENTS_WIDTH_OFFSET - 40,
          screenHeight - Constants.GUI_ELEMENTS_HEIGHT_OFFSET - 128 - (i * 48), 0);

      crewList.add(text);
      guiNodes.attachChild(text);
    }
  }

  /**
   * Initializer for the health display, including font loading and text setting.
   */
  private void attachHealth() {
    shipHealth = new BitmapText(hudFont, true);
    shipHealth.setLocalScale(Constants.GUI_HEALTH_TEXT_SIZE_SCALE);
    shipHealth.setColor(ColorRGBA.Red);
    shipHealth.setLocalTranslation(Constants.GUI_ELEMENTS_WIDTH_OFFSET - 40,
        screenHeight - Constants.GUI_ELEMENTS_HEIGHT_OFFSET, 0);

    guiNodes.attachChild(shipHealth);
  }

  /**
   * Initializer for the score display, including font loading and text setting.
   */
  private void attachScore() {
    teamScore = new BitmapText(hudFont, true);
    teamScore.setLocalScale(Constants.GUI_SCORE_TEXT_SIZE_SCALE);
    teamScore.setColor(ColorRGBA.Green);
    teamScore.setLocalTranslation(screenWidth - Constants.GUI_ELEMENTS_WIDTH_OFFSET,
        screenHeight - Constants.GUI_ELEMENTS_HEIGHT_OFFSET, 0);

    guiNodes.attachChild(teamScore);
  }

  /**
   * Method responsible for the updating of the hud text and displaying the events, along with their
   * remaining time and health, and other HUD elements.
   */
  public void updateHud() {
    // Update the ship's health and team score indicators.
    shipHealth.setText("" + (int) spaceship.getHealth());
    teamScore.setText("" + spaceship.getScore());
    // Update Crew List
    Iterator<Player> iterator = spaceship.getCrew().getAll().iterator();
    for (int i = 0; i < 4; i++) {
      if (iterator.hasNext()) {
        crewList.get(i).setText(iterator.next().toString());
      } else {
        crewList.get(i).setText("-");
      }
    }
  }
}
