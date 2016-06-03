package nl.tudelft.pixelperfect.game;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Dome;
import com.jme3.scene.shape.Torus;
import com.jme3.texture.Texture;

import java.util.ArrayList;

/**
 * Class for drawing objects in the game.
 * 
 * @author Wouter Zirkzee
 * 
 */
public class Scene {

  private Game app;
  private String basicMat;
  private String lightingMat;
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
//    basicMat = "jmevr/shaders/Unshaded.j3md";
    basicMat = "Common/MatDefs/Misc/Unshaded.j3md";
    lightingMat = "Common/MatDefs/Light/Lighting.j3md";
  }

  /**
   * Method that contains all objects for the scene.
   */
  public void createMap() {
    Dome dome = new Dome(new Vector3f(0,0,0), 10, 10, 10, true);
    Geometry domeGeo = new Geometry("Dome", dome);
    Material domeMat = new Material(app.getAssetManager(), basicMat);
    Texture glass_wire = app.getAssetManager().loadTexture("Textures/Sky/Bright/spheremap.png");

    domeMat.setTexture("ColorMap", glass_wire);
    domeGeo.setMaterial(domeMat);

    domeMat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
    domeGeo.setQueueBucket(RenderQueue.Bucket.Transparent);
    domeGeo.setLocalRotation(new Quaternion().fromAngleAxis(FastMath.HALF_PI, new Vector3f(1, 0, 0)));
    app.getRootNode().attachChild(domeGeo);
//

    Box backwall = new Box(10, 10, 0.01f);
    Geometry backWallGeo = new Geometry("backwall", backwall);
    Material backWallMat = new Material(app.getAssetManager(), basicMat);
    Texture metal_rust = app.getAssetManager().loadTexture("Textures/rusting_metal.JPG");

    backWallMat.setTexture("ColorMap", metal_rust);
    backWallGeo.setMaterial(backWallMat);

    app.getRootNode().attachChild(backWallGeo);


    Box door = new Box(2, 4, 0.01f);
    Geometry doorGeo = new Geometry("door", door);
    Material doorMat = new Material(app.getAssetManager(), basicMat);
    Texture doorTexture = app.getAssetManager().loadTexture("Textures/metal_door.JPG");

    doorMat.setTexture("ColorMap", doorTexture);
    doorGeo.setMaterial(doorMat);

    doorGeo.setLocalTranslation(new Vector3f(0, 4, 0));
    app.getRootNode().attachChild(doorGeo);


    Box floor = new Box(10, 0.01f, 10);
    Geometry floorGeo = new Geometry("Pane", floor);
    Material floorMat = new Material(app.getAssetManager(), basicMat);
    Texture metal = app.getAssetManager().loadTexture("Textures/wood.JPG");

    floorMat.setTexture("ColorMap", metal);
    floorGeo.setMaterial(floorMat);

    app.getRootNode().attachChild(floorGeo);


    Torus torus = new Torus(10, 10, 2, 10);
    Geometry torusGeo = new Geometry("Torus", torus);
    Material torusMat = new Material(app.getAssetManager(), basicMat);
    Texture metal2 = app.getAssetManager().loadTexture("Textures/metal.JPG");

    torusMat.setTexture("ColorMap", metal2);
    torusGeo.setMaterial(torusMat);

    torusGeo.setLocalRotation(new Quaternion().fromAngleAxis(FastMath.HALF_PI, new Vector3f(1, 0, 0)));
    app.getRootNode().attachChild(torusGeo);

//    drawDashboard();
//     floor
//    drawPane(new Vector3f(0f, 0f, 0f), ColorRGBA.Yellow, new Quaternion());
//    // walls
//    drawPane(new Vector3f(-8, 7, 0), ColorRGBA.Orange,
//        new Quaternion().fromAngleAxis(90 * FastMath.DEG_TO_RAD, new Vector3f(0, 0, 1)));
//    drawPane(new Vector3f(8, 7, 0), ColorRGBA.Orange,
//        new Quaternion().fromAngleAxis(90 * FastMath.DEG_TO_RAD, new Vector3f(0, 0, 1)));
//    drawPane(new Vector3f(0, 7, -8), ColorRGBA.Green,
//        new Quaternion().fromAngleAxis(90 * FastMath.DEG_TO_RAD, new Vector3f(1, 0, 0)));
//    drawPane(new Vector3f(0, 7, 8), ColorRGBA.Green,
//        new Quaternion().fromAngleAxis(90 * FastMath.DEG_TO_RAD, new Vector3f(1, 0, 0)));
//    drawTimer();
//    addButton(new Vector3f(0, 1, 7), 2, 2);
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
   * 
   * @param location
   *          The location of the center.
   * @param rotation
   *          The rotation of the pane.
   * @param color
   *          Color of the pane.
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
   * 
   * @param location
   *          Location of the top left button.
   * @param row
   *          Amount of rows of buttons.
   * @param col
   *          Amount of collums of buttons.
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
        button.setLocalTranslation(new Vector3f(location.getX() + x * distance, location.getY(),
            location.getZ() + z * distance));
        app.getRootNode().attachChild(button);
        buttons.add(button);
      }
    }
  }
}
