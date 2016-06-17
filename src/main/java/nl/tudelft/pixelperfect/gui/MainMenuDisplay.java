package nl.tudelft.pixelperfect.gui;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;

import nl.tudelft.pixelperfect.game.Constants;

/**
 * Class responsible for handling the creation and input/output for the main menu of the game.
 * 
 * @author David Alderliesten
 *
 */
public class MainMenuDisplay {

  private AssetManager assetManager;
  private Node guiNodes;
  private float screenWidth;
  private float screenHeight;

  private BitmapFont menuFont;
  private BitmapText instructionText;

  /**
   * Constructor for the main menu display.
   * 
   * @param passedMan
   *          the assetManager.
   * @param passedGuiNode
   *          the GUI node manager.
   * @param width
   *          the screen width.
   * @param height
   *          the screen height.
   */
  public MainMenuDisplay(AssetManager passedMan, Node passedGuiNode, float width, float height) {
    this.assetManager = passedMan;
    this.guiNodes = passedGuiNode;
    this.screenWidth = width;
    this.screenHeight = height;

    // Instantiate the elements and text required for the main menu.
    setupElements();
  }

  /**
   * Class aimed at instantiating the elements of the main menu.
   */
  private void setupElements() {
    // Loading the font stored in the javamonkeyengine's default manager.
    menuFont = assetManager.loadFont("Interface/Fonts/Default.fnt");

    // Initializer for the start game button, including font loading, background, and text setting.
    instructionText = new BitmapText(menuFont, true);
    instructionText.setColor(ColorRGBA.White);
    instructionText.setLocalScale(Constants.MAIN_MENU_TEXT_SCALING);
    instructionText.setLocalTranslation(screenWidth / 2, (screenHeight / 2), 0);

    // Add the generated bitmaps to the GUI node view.
    guiNodes.attachChild(instructionText);
  }

  /**
   * Clears and removes the main menu elements.
   */
  public void clearMenu() {
    guiNodes.detachChild(instructionText);
  }

}
