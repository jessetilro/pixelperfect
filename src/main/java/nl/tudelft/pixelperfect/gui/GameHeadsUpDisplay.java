package nl.tudelft.pixelperfect.gui;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.scene.Node;

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

  }

}
