package nl.tudelft.pixelperfect.gui;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.scene.Node;

import nl.tudelft.pixelperfect.game.Constants;

/**
 * Class responsible for handling the creation and input/output for the loss screen of the game.
 * 
 * @author David Alderliesten
 *
 */
public class LossHeadsUpDisplay {
  private AssetManager assetManager;
  private Node guiNodes;
  private float screenWidth;
  private float screenHeight;

  private BitmapFont menuFont;
  private BitmapText lossText;
  private BitmapText instructionText;

  /**
   * Constructor the loss/lost screen.
   * 
   * @param passedMan
   * @param passedGuiNode
   * @param width
   * @param height
   */
  public LossHeadsUpDisplay(AssetManager passedMan, Node passedGuiNode, float width, float height) {
    this.assetManager = passedMan;
    this.guiNodes = passedGuiNode;
    this.screenWidth = width;
    this.screenHeight = height;

    // Instantiate the elements and text required for the loss menu.
    setupElements();
  }

  /**
   * Sets up the loss labels and the font required.
   */
  private void setupElements() {
    // Loading the font stored in the javamonkeyengine's default manager.
    menuFont = assetManager.loadFont("Interface/Fonts/Default.fnt");

    // Adding and setting-up the text required.
    lossText = new BitmapText(menuFont, true);
    lossText.setLocalTranslation((screenWidth / 2) - Constants.GUI_WON_LOSS_WIDTH_OFFSET,
        (screenHeight / 2) + Constants.GUI_WON_LOSS_HEIGHT_OFFSET, 0);
    lossText.setText(Constants.GUI_LOST_TEXT);

    instructionText = new BitmapText(menuFont, true);
    instructionText.setLocalTranslation((screenWidth / 2) - Constants.GUI_WON_LOSS_WIDTH_OFFSET,
        (screenHeight / 2) - Constants.GUI_WON_LOSS_HEIGHT_OFFSET, 0);
    instructionText.setText(Constants.GUI_RESET_ON_TEXT);

    // Adding the elements to the GUI.
    guiNodes.attachChild(lossText);
    guiNodes.attachChild(instructionText);
  }
}
