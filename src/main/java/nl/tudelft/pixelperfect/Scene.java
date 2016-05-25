package nl.tudelft.pixelperfect;

import java.util.ArrayList;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 * Class for drawing objects in the game.
 * 
 * @author Wouter Zirkzee
 * 
 */
public class Scene {

  private Game app;
  private String basicMat;
  private String colorStr = "Color";
  private ArrayList<Geometry> buttons = new ArrayList<Geometry>();

  /**
   * Constructor for Scene.
   * 
   * @param game
   *          The game for which a Scene is built.
   */
  public Scene(Game game) {
    app = game;
    basicMat = "jmevr/shaders/Unshaded.j3md";
  }

  /**
   * Method that contains all objects for the scene.
   */
  public void createMap() {  	
    drawDashboard();
    //floor
    drawPane(new Vector3f(0f, 0f, 0f), ColorRGBA.Yellow, new Quaternion());
    //walls
    drawPane(new Vector3f(-8, 7, 0), ColorRGBA.Orange, new Quaternion()
        .fromAngleAxis(90 * FastMath.DEG_TO_RAD, new Vector3f(0, 0, 1)));
    drawPane(new Vector3f(8, 7, 0), ColorRGBA.Orange, new Quaternion()
        .fromAngleAxis(90 * FastMath.DEG_TO_RAD, new Vector3f(0, 0, 1)));
    drawPane(new Vector3f(0, 7, -8), ColorRGBA.Green, new Quaternion()
        .fromAngleAxis(90 * FastMath.DEG_TO_RAD, new Vector3f(1, 0, 0)));
    drawPane(new Vector3f(0, 7, 8), ColorRGBA.Green, new Quaternion()
        .fromAngleAxis(90 * FastMath.DEG_TO_RAD, new Vector3f(1, 0, 0)));
    drawTimer();
    addButton(new Vector3f(0, 1, 7), 2, 2);
  }

  /**
   * Render the dashboard of the scene.
   */
  private void drawDashboard() {
    Box dashboard = new Box(8, 1f, 1);
    Geometry geometry = new Geometry("Box", dashboard);
    Material material = new Material(app.getAssetManager(), basicMat);
    material.setColor(colorStr, ColorRGBA.Blue);
    geometry.setMaterial(material);
    geometry.setLocalTranslation(new Vector3f(0, 0, 7));
    app.getRootNode().attachChild(geometry);
  }

  /**
   * Render a flat pane.
   * @param location
   *                The location of the center.
   * @param rotation
   *                The rotation of the pane.
   * @param color
   *                Color of the pane.
   */
  private void drawPane(Vector3f location, ColorRGBA color, Quaternion rotation) {
    Box pane = new Box(8, 0.01f, 8);
    Geometry geometry = new Geometry("Pane", pane);
    Material material = new Material(app.getAssetManager(), basicMat);
    material.setColor(colorStr, color);
    geometry.setMaterial(material);
    geometry.setLocalTranslation(location);
    geometry.setLocalRotation(rotation);
    app.getRootNode().attachChild(geometry);
  }

  /**
   * Render placeholder for the timer that will be displayed.
   */
  private void drawTimer() {
    app.getGuiNode().detachAllChildren();
    BitmapFont guiFont = app.getAssetManager().loadFont("Interface/Fonts/Default.fnt");
    BitmapText timer = new BitmapText(guiFont, false);
    // timer.setSize(1);
    timer.setText("mm:ss");
    timer.setLocalTranslation(2.5f, 5, 3.9f);
    timer.setLocalScale(0.1f);
    timer.setLocalRotation(
        new Quaternion().fromAngleAxis(180 * FastMath.DEG_TO_RAD, new Vector3f(0, 1, 0)));
    app.getRootNode().attachChild(timer);
  }
  
  /**
   * Render a group of small buttons.
   * @param location
   * 							Location of the top left button.
   * @param row
   * 						Amount of rows of buttons.
   * @param col
   * 						Amount of collums of buttons.
   */
  private void addButton(Vector3f location, int row, int col) {
  	float distance = 1f;
  	for (int x = 0; x < row; x++) {
  		for (int z = 0; z < col; z++) {
  			Box box = new Box(1f, 1f, 1f);
  	    Geometry button = new Geometry("Box", box);
  	    button.setLocalScale(0.2f);
  	  	Material buttonMat = new Material(app.getAssetManager(), basicMat);
  	  	buttonMat.setColor("Color", ColorRGBA.Red);
  	    button.setMaterial(buttonMat);
  	    button.setLocalTranslation(
  	    		new Vector3f(location.getX() + x * distance,
  	    				location.getY(), location.getZ() + z * distance));
  	    app.getRootNode().attachChild(button);
  	    buttons.add(button);
  		}
  	}
  }
}
