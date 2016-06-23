package nl.tudelft.pixelperfect.gui;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.scene.Node;

import nl.tudelft.pixelperfect.game.Constants;

/**
 * Class responsible for handling the creation and input/output for the won screen of the game.
 * 
 * @author David Alderliesten
 *
 */
public class WonHeadsUpDisplay {
  private AssetManager assetManager;
  private Node guiNodes;
  private float screenWidth;
  private float screenHeight;

  private BitmapFont menuFont;
  private BitmapText wonText;
  private BitmapText instructionText;

  /**
   * Constructor the won/winning screen.
   * 
   * @param passedMan
   * @param passedGuiNode
   * @param width
   * @param height
   */
  public WonHeadsUpDisplay(AssetManager passedMan, Node passedGuiNode, float width, float height) {
    this.assetManager = passedMan;
    this.guiNodes = passedGuiNode;
    this.screenWidth = width;
    this.screenHeight = height;

    // Instantiate the elements and text required for the main menu.
    setupElements();
  }

  /**
   * Sets up the won label and the font required.
   */
  private void setupElements() {
    // Loading the font stored in the javamonkeyengine's default manager.
    menuFont = assetManager.loadFont("Interface/Fonts/Default.fnt");

    // Adding and setting-up the text required.
    wonText = new BitmapText(menuFont, true);
    wonText.setLocalTranslation((screenWidth / 2) - Constants.GUI_WON_LOSS_WIDTH_OFFSET,
        (screenHeight / 2) + Constants.GUI_WON_LOSS_HEIGHT_OFFSET, 0);
    wonText.setText(Constants.GUI_WON_TEXT);

    instructionText = new BitmapText(menuFont, true);
    instructionText.setLocalTranslation((screenWidth / 2) - Constants.GUI_WON_LOSS_WIDTH_OFFSET,
        (screenHeight / 2) - Constants.GUI_WON_LOSS_HEIGHT_OFFSET, 0);
    instructionText.setText(Constants.GUI_RESET_ON_TEXT);

    // Adding the elements to the GUI.
    guiNodes.attachChild(wonText);
    guiNodes.attachChild(instructionText);
  }

  /**
   * Clears and removes the won display elements.
   */
  public void clearMenu() {
    guiNodes.detachChild(wonText);
    guiNodes.detachChild(instructionText);
  }
}
