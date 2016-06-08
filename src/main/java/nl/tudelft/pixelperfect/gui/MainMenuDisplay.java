package nl.tudelft.pixelperfect.gui;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.scene.Node;

/**
 * Class responsible for handling the creation and input/output for the main menu of the game.
 * 
 * @author David Alderliesten
 *
 */
public class MainMenuDisplay {

  private AssetManager assetManager;
  private Node guiNodes;
  private int screenWidth;
  private int screenHeight;

  private BitmapFont menuFont;
  private BitmapText startGameText;
  private BitmapText exitGameText;

  /**
   * Constructor for the main menu display.
   * 
   * @param passedMan
   *          the assetManager.
   * @param passedGuiNode
   *          the GUI node manager.
   * @param passedWid
   *          the screen width.
   * @param passedHi
   *          the screen height.
   */
  public MainMenuDisplay(AssetManager passedMan, Node passedGuiNode, int passedWid, int passedHi) {
    this.assetManager = passedMan;
    this.guiNodes = passedGuiNode;
    this.screenWidth = passedWid;
    this.screenHeight = passedHi;

    // Instantiate the elements and text required for the main menu.
    setupElements();
  }

  /**
   * Class aimed at instantiating the elements of the main menu.
   */
  private void setupElements() {

  }

}
