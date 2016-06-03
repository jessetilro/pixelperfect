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
import com.jme3.scene.shape.StripBox;
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
  private final String colorStr = "Color";
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

    domeMat.setTexture(colorMapStr, glass_wire);
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

    backWallMat.setTexture(colorMapStr, metal_rust);
    backWallGeo.setMaterial(backWallMat);

    app.getRootNode().attachChild(backWallGeo);


    Box door = new Box(2, 4, 0.01f);
    Geometry doorGeo = new Geometry("door", door);
    Material doorMat = new Material(app.getAssetManager(), basicMat);
    Texture doorTexture = app.getAssetManager().loadTexture("Textures/metal_door.JPG");

    doorMat.setTexture(colorMapStr, doorTexture);
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
  }
}
