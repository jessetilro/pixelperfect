package nl.tudelft.pixelperfect.game;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.light.Light;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;
import com.jme3.texture.Texture;

import java.util.ArrayList;

/**
 * Class for drawing objects in the game.
 *
 * @author Wouter Zirkzee
 *
 */
public class Scene {

  private static final Vector3f XAXIS = new Vector3f(1, 0, 0);
  private static final Vector3f YAXIS = new Vector3f(0, 1, 0);
  private static final Vector3f ZAXIS = new Vector3f(0, 0, 1);

  private Light getLight() {
    return light;
  }

  public Light light = new PointLight(new Vector3f(0, 1, 0));

  private Game app;
  private String basicMat;
  private BitmapFont font;
  private ArrayList<Geometry> plasmaEventObjects;
  private ArrayList<Geometry> asteroidEventObjects;
  private ArrayList<Geometry> fireEventObjects;
  private ArrayList<Geometry> coffeeEventObjects;
  private BitmapText hostileEventText;
  private BitmapText asteroidEventLabel;

  public BitmapText getAsteroidEventLabel() {
    return asteroidEventLabel;
  }

  public BitmapText getFireEventLabel() {
    return fireEventLabel;
  }

  private BitmapText fireEventLabel;


  /**
   * Constructor for Scene.
   *
   * @param game
   *          The game for which a Scene is built.
   */
  public Scene(Game game) {
    app = game;
    basicMat = "Common/MatDefs/Misc/Unshaded.j3md";
    font = app.getAssetManager().loadFont("Interface/Fonts/Default.fnt");
  }

  /**
   * Method that contains all objects for the scene.
   */
  public void createMap() {
    asteroidEventLabel = createLabel(new Vector3f(7, 1f, -2.5f), "ASTEROID WARNING", .2f, ColorRGBA.Blue,
            new Quaternion().fromAngleAxis(-FastMath.HALF_PI, YAXIS));
    fireEventLabel = createLabel(new Vector3f(-7, 1, -1f), "FIRE WARNING", .2f, ColorRGBA.Red,
            new Quaternion().fromAngleAxis(FastMath.HALF_PI, YAXIS));

    plasmaEventObjects = addTubes(new Vector3f(0, 4, -4), FastMath.PI / 2, YAXIS, 4);
//    createLabel(new Vector3f())

    hostileEventText = createLabel(new Vector3f(-1, 1f, 12f), "x: \ny: \n", .2f, ColorRGBA.Green,
            new Quaternion().fromAngleAxis(FastMath.PI, YAXIS));
//    hostileEventText.setLocalRotation(new Quaternion().fromAngleAxis(FastMath.PI / 4, XAXIS));
    Spatial spaceship = app.getAssetManager().loadModel("Models/spaceship/spaceship_no_light.j3o");
    spaceship.scale(2f);
    app.getRootNode().addLight(light);
    app.getRootNode().attachChild(spaceship);
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
  private Box createBoxObject(Box box, Vector3f translation, String textureLocation) {
    Box newBox = box;
    Geometry geometry = new Geometry("Box", newBox);
    Material material = new Material(app.getAssetManager(), basicMat);
    Texture texture = app.getAssetManager().loadTexture(textureLocation);
    material.setTexture("ColorMap", texture);
    geometry.setMaterial(material);
    geometry.setLocalTranslation(translation);

    app.getRootNode().attachChild(geometry);

    return newBox;
  }
//
//  /**
//   * Create the windows of the spaceship.
//   */
//  private void createWindow() {
//    Dome dome = new Dome(new Vector3f(0, 0, 0), 10, 10, 10, true);
//    Geometry domeGeo = new Geometry("Dome", dome);
//    Material domeMat = new Material(app.getAssetManager(), basicMat);
//    Texture window = app.getAssetManager().loadTexture("Textures/Sky/Bright/spheremap.png");
//
//    domeMat.setTexture("ColorMap", window);
//    domeGeo.setMaterial(domeMat);
//
//    domeMat.getAdditionalRenderState().setBlendMode(
//            RenderState.BlendMode.Alpha);
//    domeGeo.setQueueBucket(RenderQueue.Bucket.Transparent);
//    domeGeo.setLocalRotation(new Quaternion().fromAngleAxis(
//            FastMath.HALF_PI, new Vector3f(1, 0, 0)));
//    app.getRootNode().attachChild(domeGeo);
//  }
//
//  /**
//   * Create the dashboard of the cockpit.
//   */
//  private void createDashboard() {
//    Torus torus = new Torus(10, 10, 2, 10);
//    Geometry torusGeo = new Geometry("Torus", torus);
//    Material torusMat = new Material(app.getAssetManager(), basicMat);
//    Texture metal2 = app.getAssetManager().loadTexture("Textures/metal.JPG");
//
//    torusMat.setTexture("ColorMap", metal2);
//    torusGeo.setMaterial(torusMat);
//
//    torusGeo.setLocalRotation(new Quaternion().fromAngleAxis(
//            FastMath.HALF_PI, new Vector3f(1, 0, 0)));
//    app.getRootNode().attachChild(torusGeo);
//  }

  /**
   * Render a group of small buttons.
   *
   * @param location
   *            Location of the top left button.
   * @param rotation
   *            Rotation of the tubes.
   * @param rotationAxis
   *            Axis around which to rotate.
   * @param col
   *            Amount of rows of buttons.
   * @param row
   *            Amount of collums of buttons.
   * @return ArrayList containing the buttons.
   */
//  private ArrayList<Geometry> addButtons(Vector3f location, float rotation,
//                                         Vector3f rotationAxis, int col, int row) {
//    ArrayList<Geometry> buttons = new ArrayList<Geometry>();
//    Node pivot = new Node("pivot");
//    float distance = 1f;
//    for (int x = 0; x < col; x++) {
//      for (int z = 0; z < row; z++) {
//        Box box = new Box(1f, 1f, 1f);
//        Geometry button = new Geometry("Box", box);
//        button.setLocalScale(0.2f);
//        Material buttonMat = new Material(app.getAssetManager(), basicMat);
//        buttonMat.setColor("Color", ColorRGBA.Red);
//        button.setMaterial(buttonMat);
//        button.setLocalTranslation(new Vector3f(location.getX() + x * distance, location.getY(),
//            location.getZ() + z * distance));
//        pivot.attachChild(button);
//        buttons.add(button);
//      }
//    }
//    pivot.rotate(new Quaternion().fromAngleAxis(
//        rotation, rotationAxis));
//    app.getRootNode().attachChild(pivot);
//    return buttons;
//  }

  /**
   * Create a group of tubes.
   *
   * @param location
   *            Location of the tubes.
   * @param rotation
   *            Rotation of the tubes.
   * @param rotationAxis
   *            Axis around which to rotate.
   * @param row
   *            Amount of rows of pipes.
   * @return  Arraylist containing the pipes.
   */
  private ArrayList<Geometry> addTubes(Vector3f location, float rotation,
                                       Vector3f rotationAxis, int row) {
    ArrayList<Geometry> pipes = new ArrayList<Geometry>();
    float distance = 1f;
    for (int z = 0; z < row; z++) {
      Cylinder cylinder = new Cylinder(20, 20, 0.2f, 20);
      Geometry pipe = new Geometry("Cyclinder", cylinder);
      Material pipeMat = new Material(app.getAssetManager(), basicMat);
      pipeMat.setColor("Color", ColorRGBA.Blue);
      pipe.setMaterial(pipeMat);
      pipe.setLocalRotation(new Quaternion().fromAngleAxis(
          rotation, rotationAxis));
      pipe.setLocalTranslation(new Vector3f(location.getX(), location.getY(),
          location.getZ() + z * distance));
      pipes.add(pipe);
      app.getRootNode().attachChild(pipe);
    }
    return pipes;
  }

//  /**
//   * Create the radar and a BitmmapText to display information.
//   */
//  private void createHostileRadar() {
//    createBoxObject(new Box(0.8f, 0.8f, 0.01f), new Vector3f(0, 4.7f, 9.5f), "Textures/radar.jpg");
//
//    hostileEventText = new BitmapText(font, false);
//    hostileEventText.setColor(ColorRGBA.Green);
//    hostileEventText.setLocalTranslation(1, 4, 7);
//    hostileEventText.setSize(.5f);
//    hostileEventText.setLocalRotation(
//        new Quaternion().fromAngleAxis(180 * FastMath.DEG_TO_RAD, new Vector3f(0, 1, 0)));
//    hostileEventText.setText("x:\n" + "y:\n" + "");
//    app.getRootNode().attachChild(hostileEventText);
//  }

  private BitmapText createLabel(Vector3f location, String text, float size,
                                 ColorRGBA color, Quaternion rotation) {
    BitmapText label = new BitmapText(font, false);
    label.setText(text);
    label.setColor(color);
    label.setSize(size);
    label.setLocalTranslation(location);
    label.setLocalRotation(rotation);
    app.getRootNode().attachChild(label);
    return label;
  }

  /**
   * Get the objects for the plasma objects.
   * @return Arraylist containing the objects.
   */
  public ArrayList<Geometry> getPlasmaEventObjects() {
    return plasmaEventObjects;
  }

//  /**
//   * Get the objects for the Asteroid objects.
//   * @return Arraylist containing the objects.
//   */
//  public ArrayList<Geometry> getAsteroidEventObjects() {
//    return asteroidEventObjects;
//  }
//
//  /**
//   * Get the objects for the Coffee objects.
//   * @return Arraylist containing the objects.
//   */
//  public ArrayList<Geometry> getCoffeeEventObjects() {
//    return coffeeEventObjects;
//  }

  /**
   * Get the BitmapText for the Hostile events.
   * @return BitmapText
   */
  public BitmapText getHostileEventText() {
    return hostileEventText;
  }

//  /**
//   * Get the objects for the Fire objects.
//   * @return Arraylist containing the objects.
//   */
//  public ArrayList<Geometry> getFireEventObjects() {
//    return fireEventObjects;
//  }

}
