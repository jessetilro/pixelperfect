package nl.tudelft.pixelperfect.gui;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;

import nl.tudelft.pixelperfect.game.Constants;

/**
 * Class responsible for handling the creation and input/output for the main/start menu of the game.
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
  private BitmapText rowOne;
  private BitmapText rowTwo;

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

    // Initializer for the main menu elements.
    rowOne = new BitmapText(menuFont, true);
    rowOne.setColor(ColorRGBA.White);
    rowOne.setLocalScale(Constants.MAIN_MENU_TEXT_SCALING);
    rowOne.setLocalTranslation((screenWidth / 2) - Constants.MAIN_MENU_WIDTH_SPACING,
        (screenHeight / 2) + Constants.MAIN_MENU_HEIGHT_SPACING, 0);

    rowTwo = new BitmapText(menuFont, true);
    rowTwo.setColor(ColorRGBA.White);
    rowTwo.setLocalScale(Constants.MAIN_MENU_TEXT_SCALING);
    rowTwo.setLocalTranslation((screenWidth / 2) - Constants.MAIN_MENU_WIDTH_SPACING,
        (screenHeight / 2) - Constants.MAIN_MENU_HEIGHT_SPACING, 0);

    // Setting the text to the element.
    rowOne.setText(Constants.MAIN_MENU_FIRST_ROW_TEXT);
    rowTwo.setText(Constants.MAIN_MENU_SECOND_ROW_TEXT);

    // Add the generated bitmaps to the GUI node view.
    guiNodes.attachChild(rowOne);
    guiNodes.attachChild(rowTwo);
  }

  /**
   * Clears and removes the main menu elements.
   */
  public void clearMenu() {
    guiNodes.detachChild(rowOne);
    guiNodes.detachChild(rowTwo);
  }

}
