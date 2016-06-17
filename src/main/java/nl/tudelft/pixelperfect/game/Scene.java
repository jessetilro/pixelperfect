package nl.tudelft.pixelperfect.game;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.light.Light;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

/**
 * Class for drawing objects in the game.
 *
 * @author Wouter Zirkzee
 *
 */
public class Scene {

  private static final Vector3f YAXIS = new Vector3f(0, 1, 0);

  /**
   * Get the light object.
   * @return Light object.
   */
  public Light getLight() {
    return light;
  }

  private Light light = new PointLight(new Vector3f(0, 1, 0));

  private Game app;
  private BitmapFont font;
  private BitmapText hostileEventText;
  private BitmapText asteroidEventLabel;
  private BitmapText fireEventLabel;
  private BitmapText plasmaEventLabel;
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
  public BitmapText getPlasmaEventLabel() {
    return plasmaEventLabel;
  }



  /**
   * Constructor for Scene.
   *
   * @param game
   *          The game for which a Scene is built.
   */
  public Scene(Game game) {
    app = game;
    font = app.getAssetManager().loadFont("Interface/Fonts/Default.fnt");
  }

  /**
   * Method that contains all objects for the scene.
   */
  public void createMap() {
    asteroidEventLabel = createLabel(new Vector3f(7, 0.65f, -4f), "ASTEROID WARNING",
        .2f, ColorRGBA.Blue, new Quaternion().fromAngleAxis(-FastMath.HALF_PI * .9f, YAXIS));
    fireEventLabel = createLabel(new Vector3f(-7, 0.7f, -0.5f), "FIRE WARNING",
        .2f, ColorRGBA.Red, new Quaternion().fromAngleAxis(FastMath.HALF_PI, YAXIS));
    plasmaEventLabel = createLabel(new Vector3f(-9, 2.5f, -19), "PLASMA LEAK",
        .5f, ColorRGBA.Green, new Quaternion());
    hostileEventText = createLabel(new Vector3f(1, 4f, 19f), "x: \ny: \n",
        .5f, ColorRGBA.Green, new Quaternion().fromAngleAxis(FastMath.PI, new Vector3f(0, 1, 0)));

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
