package nl.tudelft.pixelperfect.game;

import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Dome;
import com.jme3.scene.shape.Torus;
import com.jme3.texture.Texture;

/**
 * Class for drawing objects in the game.
 *
 * @author Wouter Zirkzee
 *
 */
public class Scene {

  private Game app;
  private String basicMat;
  private final String colorMapStr = "ColorMap";

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

    material.setTexture(colorMapStr, texture);
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

    domeMat.setTexture(colorMapStr, window);
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

    torusGeo.setLocalRotation(new Quaternion().fromAngleAxis(FastMath.HALF_PI, new Vector3f(1, 0, 0)));
    app.getRootNode().attachChild(torusGeo);
  }
}
