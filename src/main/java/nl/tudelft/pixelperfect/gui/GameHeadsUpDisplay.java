package nl.tudelft.pixelperfect.gui;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;

import nl.tudelft.pixelperfect.game.Constants;
import nl.tudelft.pixelperfect.game.Spaceship;

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
  private float screenWidth;
  private float screenHeight;

  private BitmapFont hudFont;
  private BitmapText shipHealth;
  private BitmapText teamScore;

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
  }

  /**
   * Initializer for the health display, including font loading and text setting.
   */
  private void attachHealth() {
    shipHealth = new BitmapText(hudFont, true);
    shipHealth.setLocalScale(Constants.GUI_HEALTH_TEXT_SIZE_SCALE);
    shipHealth.setColor(ColorRGBA.Red);
    shipHealth.setLocalTranslation(Constants.GUI_ELEMENTS_WIDTH_OFFSET,
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
    shipHealth.setText("" + spaceship.getHealth());
    teamScore.setText("" + spaceship.getScore());
  }

  /**
   * Clears the heads-up display and detaches all the GUI elements. Should only be called when the
   * game is done and reset.
   */
  public void clearHud() {
    guiNodes.detachChild(shipHealth);
    guiNodes.detachChild(teamScore);
  }
}
