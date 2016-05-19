package nl.tudelft.pixelperfect.gui;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.scene.Node;

/**
 * Class that displays relevant information in the heads-up display during the gameplay.
 * 
 * @author David Alderliesten
 *
 */
public class GameHeadsUpDisplay {
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
  public GameHeadsUpDisplay(AssetManager passedMan, Node passedGuiNode, int passedWid,
      int passedHi) {
    this.assetManager = passedMan;
    this.guiNodes = passedGuiNode;
    this.screenWidth = passedWid;
    this.screenHeight = passedHi;

    // Sets-up the text display using the private method.
    setupTextDisplay();
  }

  /**
   * Sets-up text display for the in-game HUD.
   */
  private void setupTextDisplay() {
    // Loading the font stored in the jme default manager.
    hudFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
    
    // Init for the log text, including font loading.
    captainLog = new BitmapText(hudFont, true);
  }
}
