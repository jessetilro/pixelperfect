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
import com.jme3.texture.Texture;

/**
 * Class for drawing objects in the game.
 *
 * @author Wouter Zirkzee
 *
 */
public class Scene {

  private static final Vector3f XAXIS = new Vector3f(1, 0, 0);
  private static final Vector3f YAXIS = new Vector3f(0, 1, 0);
//  private static final Vector3f ZAXIS = new Vector3f(0, 0, 1);

  /**
   * Get the light object.
   * @return Light object.
   */
  public Light getLight() {
    return light;
  }

  private Light light = new PointLight(new Vector3f(0, 1, 0));

  private Game app;
  private String basicMat;
  private BitmapFont font;
  private BitmapText hostileEventText;
  private BitmapText asteroidEventLabel;
  private BitmapText fireEventLabel;
  private BitmapText plasmaEventlabel;
  /**
   * Get the label used for asteroid events.
   * @return BitmapText
   */
  public BitmapText getAsteroidEventLabel() {
    return asteroidEventLabel;
  }

  /**
   * Get the label used for fire events.
   * @return BitmapText
   */
  public BitmapText getFireEventLabel() {
    return fireEventLabel;
  }


  /**
   * Get the label used for plasma events.
   * @return BitmapText
   */
  public BitmapText getPlasmaEventlabel() {
    return plasmaEventlabel;
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
    font = app.getAssetManager().loadFont("Interface/Fonts/Default.fnt");
  }

  /**
   * Method that contains all objects for the scene.
   */
  public void createMap() {
    asteroidEventLabel = createLabel(new Vector3f(7, 0.65f, -3.5f), "ASTEROID WARNING",
        .2f, ColorRGBA.Blue, new Quaternion().fromAngleAxis(-FastMath.HALF_PI, YAXIS));
    fireEventLabel = createLabel(new Vector3f(-7, 0.6f, -1f), "FIRE WARNING",
        .2f, ColorRGBA.Red, new Quaternion().fromAngleAxis(FastMath.HALF_PI, YAXIS));
    plasmaEventlabel = createLabel(new Vector3f(-9, 2.5f, -19), "PLASMA LEAK",
        .5f, ColorRGBA.Green, new Quaternion());
    hostileEventText = createLabel(new Vector3f(-1, 2.5f, 12f), "x: \ny: \n",
        .6f, ColorRGBA.Green, new Quaternion().fromAngleAxis(FastMath.PI, new Vector3f(0, 1, 0)));
    Geometry radar = createBoxObject(new Box(0.6f, 0.4f, 0.01f),
        new Vector3f(-1.3f, 0.4f, 12.5f), "Textures/radar.jpg");
    radar.setLocalRotation(new Quaternion().fromAngleAxis(FastMath.PI / 4, XAXIS));


    Spatial spaceship = app.getAssetManager().loadModel("Models/spaceship/spaceship_no_light.j3o");
    spaceship.scale(2f);
    app.getRootNode().attachChild(spaceship);
    app.getRootNode().addLight(light);
    Spatial pipes = app.getAssetManager().loadModel("Models/Pipe/Pipe.j3o");
    pipes.scale(0.04f);
    pipes.setLocalTranslation(-8, 5, -22);
    pipes.setLocalRotation(new Quaternion().fromAngleAxis(FastMath.PI * 0.3f, YAXIS));
    app.getRootNode().attachChild(pipes);
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
  private Geometry createBoxObject(Box box, Vector3f translation, String textureLocation) {
    Box newBox = box;
    Geometry geometry = new Geometry("Box", newBox);
    Material material = new Material(app.getAssetManager(), basicMat);
    Texture texture = app.getAssetManager().loadTexture(textureLocation);
    material.setTexture("ColorMap", texture);
    geometry.setMaterial(material);
    geometry.setLocalTranslation(translation);

    app.getRootNode().attachChild(geometry);

    return geometry;
  }

  /**
   * Create a BitmapText and return it.
   * @param location
   *              Location of the BitmapText.
   * @param text
   *              Text to be set on the BitmapText.
   * @param size
   *              Size of the BitmapText.
   * @param color
   *              Color of the text.
   * @param rotation
   *              Rotation of the BitmapText.
   * @return The new BitmapText.
   */
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
   * Get the BitmapText for the Hostile events.
   * @return BitmapText
   */
  public BitmapText getHostileEventText() {
    return hostileEventText;
  }

}
