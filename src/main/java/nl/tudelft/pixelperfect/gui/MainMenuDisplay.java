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
  private BitmapText startGameText;
  private BitmapText exitGameText;

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
    startGameText = new BitmapText(menuFont, true);
    startGameText.setColor(ColorRGBA.White);
    startGameText.setLocalScale(Constants.MAIN_MENU_TEXT_SCALING);
    startGameText.setLocalTranslation(screenWidth / 2,
        (screenHeight / 2) - Constants.MAIN_MENU_START_HEIGHT_OFFSET, 0);

    // Initializer for the exit game button, including font loading, background, and text setting.
    exitGameText = new BitmapText(menuFont, true);
    exitGameText.setColor(ColorRGBA.White);
    exitGameText.setLocalScale(Constants.MAIN_MENU_TEXT_SCALING);
    exitGameText.setLocalTranslation(screenWidth / 2,
        (screenHeight / 2) - Constants.MAIN_MENU_EXIT_HEIGHT_OFFSET, 0);

    // Append the text required for the menu.
    startGameText.setText(Constants.MAIN_MENU_START_BUTTON_TEXT);
    exitGameText.setText(Constants.MAIN_MENU_EXIT_BUTTON_TEXT);

    // Add the generated bitmaps to the gui node view.
    guiNodes.attachChild(startGameText);
    guiNodes.attachChild(exitGameText);
  }

  /**
   * Clears and removes the main menu elements.
   */
  public void clearMenu() {
    guiNodes.detachChild(startGameText);
    guiNodes.detachChild(exitGameText);
  }

}
