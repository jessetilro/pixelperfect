package nl.tudelft.pixelperfect.gui;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.font.Rectangle;
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
    // Loading the font stored in the javamonkeyengine's default manager.
    menuFont = assetManager.loadFont("Interface/Fonts/Default.fnt");

    // Initializer for the start game button, including font loading, background, and text setting.
    startGameText = new BitmapText(menuFont, true);
    startGameText.setColor(ColorRGBA.White);
    startGameText.setBox(new Rectangle(0, 0, 2, 2));
    startGameText.setLocalTranslation(screenWidth / 2,
        (screenHeight / 2) - Constants.MAIN_MENU_START_HEIGHT_OFFSET, 0);

    // Initializer for the exit game button, including font loading, background, and text setting.
    exitGameText = new BitmapText(menuFont, true);
    exitGameText.setColor(ColorRGBA.White);
    exitGameText.setBox(new Rectangle(0, 0, 2, 2));
    exitGameText.setLocalTranslation(screenWidth / 2,
        (screenHeight / 2) - Constants.MAIN_MENU_EXIT_HEIGHT_OFFSET, 0);
  }

}
