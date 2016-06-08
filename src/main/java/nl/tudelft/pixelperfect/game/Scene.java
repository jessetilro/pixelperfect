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
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;
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
  private ArrayList<Geometry> plasmaEventObjects;
  private ArrayList<Geometry> astroidEventObjects;
  private ArrayList<Geometry> fireEventObjects;
  private ArrayList<Geometry> coffeeEventObjects;

  public ArrayList<BitmapText> getHostileEventObjects() {
    return hostileEventObjects;
  }

  private ArrayList<BitmapText> hostileEventObjects;

  public ArrayList<Geometry> getFireEventObjects() {
    return fireEventObjects;
  }


  /**
   * Constructor for Scene.
   *
   * @param game
   *          The game for which a Scene is built.
   */
  public Scene(Game game) {
    app = game;
    basicMat = "Common/MatDefs/Misc/Unshaded.j3md";
  }

  /**
   * Method that contains all objects for the scene.
   */
  public void createMap() {
    createDashboard();
    createWindow();
    createBoxObject(new Box(10, 10, 0.01f), new Vector3f(0, 0, 0),
            "Textures/rusting_metal.JPG");
    createBoxObject(new Box(2, 4, 0.01f), new Vector3f(0, 4, 0),
             "Textures/metal_door.JPG");
    createBoxObject(new Box(10, 0.01f, 10), new Vector3f(0, 0, 0),
            "Textures/wood.JPG");

    coffeeEventObjects = addButtons(new Vector3f(0, 1, 7.9f), -.5f * FastMath.HALF_PI,
        new Vector3f(0, 1, 0), 1, 1);
    astroidEventObjects = addButtons(new Vector3f(0, 1, 7.9f), .5f * FastMath.HALF_PI,
        new Vector3f(0, 1, 0), 2, 1);
    fireEventObjects = addButtons(new Vector3f(4, 0, -6), FastMath.HALF_PI,
        new Vector3f(1, 0, 0), 2, 2);
    plasmaEventObjects = addTubes(new Vector3f(-6, 0, 0.5f), FastMath.HALF_PI,
        new Vector3f(1, 0, 0), 4);
    app.getGuiNode().detachAllChildren();

    BitmapText hostileEventText = new BitmapText(
        app.getAssetManager().loadFont("Interface/Fonts/Default.fnt"), false);
    hostileEventText.setColor(ColorRGBA.White);
    hostileEventText.setLocalTranslation(0, 4, 4);
    hostileEventText.setSize(20f);
    hostileEventText.setText("TestTest");
    app.getGuiNode().attachChild(hostileEventText);
//    hostileEventObjects.add(hostileEventText);

  }

  /**
   * Create a box object.
   *
   * @param box
   *          New box with the dimensions of the box.
   * @param translation
   *          Translation of the box.
   * @param textureLocation
   *          Texture of the box.
     */
  private void createBoxObject(Box box, Vector3f translation,
                               String textureLocation) {
    Box newBox = box;
    Geometry geometry = new Geometry("Box", newBox);
    Material material = new Material(app.getAssetManager(), basicMat);
    Texture texture = app.getAssetManager().loadTexture(textureLocation);

    material.setTexture("ColorMap", texture);
    geometry.setMaterial(material);

    geometry.setLocalTranslation(translation);

    app.getRootNode().attachChild(geometry);
  }

  /**
   * Create the windows of the spaceship.
   */
  private void createWindow() {
    Dome dome = new Dome(new Vector3f(0, 0, 0), 10, 10, 10, true);
    Geometry domeGeo = new Geometry("Dome", dome);
    Material domeMat = new Material(app.getAssetManager(), basicMat);
    Texture window = app.getAssetManager().loadTexture("Textures/Sky/Bright/spheremap.png");

    domeMat.setTexture("ColorMap", window);
    domeGeo.setMaterial(domeMat);

    domeMat.getAdditionalRenderState().setBlendMode(
            RenderState.BlendMode.Alpha);
    domeGeo.setQueueBucket(RenderQueue.Bucket.Transparent);
    domeGeo.setLocalRotation(new Quaternion().fromAngleAxis(
            FastMath.HALF_PI, new Vector3f(1, 0, 0)));
    app.getRootNode().attachChild(domeGeo);
  }

  /**
   * Create the dashboard of the cockpit.
   */
  private void createDashboard() {
    Torus torus = new Torus(10, 10, 2, 10);
    Geometry torusGeo = new Geometry("Torus", torus);
    Material torusMat = new Material(app.getAssetManager(), basicMat);
    Texture metal2 = app.getAssetManager().loadTexture("Textures/metal.JPG");

    torusMat.setTexture("ColorMap", metal2);
    torusGeo.setMaterial(torusMat);

    torusGeo.setLocalRotation(new Quaternion().fromAngleAxis(
            FastMath.HALF_PI, new Vector3f(1, 0, 0)));
    app.getRootNode().attachChild(torusGeo);
  }

  /**
   * Render a group of small buttons.
   *
   * @param location
   *          Location of the top left button.
   * @param col
   *          Amount of rows of buttons.
   * @param row
   *          Amount of collums of buttons.
   */
  private ArrayList<Geometry> addButtons(Vector3f location, float rotation,
                                         Vector3f rotationAxis, int col, int row) {
    ArrayList<Geometry> buttons = new ArrayList<Geometry>();
    Node pivot = new Node("pivot");
    float distance = 1f;
    for (int x = 0; x < col; x++) {
      for (int z = 0; z < row; z++) {
        Box box = new Box(1f, 1f, 1f);
        Geometry button = new Geometry("Box", box);
        button.setLocalScale(0.2f);
        Material buttonMat = new Material(app.getAssetManager(), basicMat);
        buttonMat.setColor("Color", ColorRGBA.Red);
        button.setMaterial(buttonMat);
        button.setLocalTranslation(new Vector3f(location.getX() + x * distance, location.getY(),
            location.getZ() + z * distance));
        pivot.attachChild(button);
        buttons.add(button);
      }
    }
    pivot.rotate(new Quaternion().fromAngleAxis(
        rotation, rotationAxis));
    app.getRootNode().attachChild(pivot);
    return buttons;
  }

  private ArrayList<Geometry> addTubes(Vector3f location, float rotation, Vector3f rotationAxis, int row) {
    ArrayList<Geometry> pipes = new ArrayList<Geometry>();
    float distance = 1f;
    for (int x = 0; x < row; x++) {
      Cylinder cylinder = new Cylinder(20, 20, 0.2f, 20);
      Geometry pipe = new Geometry("Cyclinder", cylinder);
      Material pipeMat = new Material(app.getAssetManager(), basicMat);
      pipeMat.setColor("Color", ColorRGBA.Blue);
      pipe.setMaterial(pipeMat);
      pipe.setLocalRotation(new Quaternion().fromAngleAxis(
          rotation, rotationAxis));
      pipe.setLocalTranslation(new Vector3f(location.getX()  + x * distance, location.getY(),
          location.getZ()));
      pipes.add(pipe);
      app.getRootNode().attachChild(pipe);
    }
    return pipes;
  }

  public ArrayList<Geometry> getPlasmaEventObjects() {
    return plasmaEventObjects;
  }

  public ArrayList<Geometry> getAstroidEventObjects() {
    return astroidEventObjects;
  }

  public ArrayList<Geometry> getCoffeeEventObjects() {
    return coffeeEventObjects;
  }
}
