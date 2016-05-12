package nl.tudelft.pixelperfect;

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
 * Created by woute on 5/12/2016.
 *
 * Class for drawing objects in the game.
 */
public class Scene {

  private Game app;
  private String basicMat;

  /**
   * Constructor for Scene.
   * @param game
   *            game which scene is to be build
   */
  public Scene(Game game) {
    app = game;
    basicMat = "Common/MatDefs/Misc/Unshaded.j3md";
  }

  /**
   * Method that contains all objects for the scene.
   */
  public void createMap() {
    drawDashboard();
    drawFloor();
    drawWalls();
    drawTimer();
  }

  /**
   * Render the dashboard of the scene.
   */
  private void drawDashboard() {
    Box dashboard = new Box(4, 1f, 1);
    Geometry geom = new Geometry("Box", dashboard);
    Material mat = new Material(app.getAssetManager(), basicMat);
    mat.setColor("Blue", ColorRGBA.Blue);
    geom.setMaterial(mat);
    geom.setLocalTranslation(new Vector3f(0, 0, -3));
    app.getRootNode().attachChild(geom);
  }

  /**
   * Render the floor of the scene.
   */
  private void drawFloor() {
    // green floor
    Box floor = new Box(4, 0.01f, 4);
    Geometry g2 = new Geometry("Floor", floor);
    Material mat2 = new Material(app.getAssetManager(), basicMat);
    mat2.setColor("Green", ColorRGBA.Green);
    g2.setMaterial(mat2);
    g2.setLocalTranslation(0, -1, 0);
    app.getRootNode().attachChild(g2);
  }

  /**
   * Render the walls of the scene.
   */
  private void drawWalls() {
    // walls
    Box wallLeft = new Box(0.01f, 4, 4);
    Geometry l1 = new Geometry("leftwall", wallLeft);
    Material mat3 = new Material(app.getAssetManager(), basicMat);
    mat3.setColor("Orange", ColorRGBA.Orange);
    l1.setMaterial(mat3);
    l1.setLocalTranslation(new Vector3f(-4, 3, 0));
    app.getRootNode().attachChild(l1);

    Box wallRight = new Box(0.01f, 4, 4);
    Geometry l2 = new Geometry("rightwall", wallRight);
    l2.setMaterial(mat3);
    l2.setLocalTranslation(new Vector3f(4, 3, 0));
    app.getRootNode().attachChild(l2);

    Box wallFront = new Box(4, 4, 0.01f);
    Geometry l3 = new Geometry("frontwall", wallFront);
    Material mat4 = new Material(app.getAssetManager(), basicMat);
    mat4.setColor("Yellow", ColorRGBA.Yellow);
    l3.setMaterial(mat4);
    l3.setLocalTranslation(new Vector3f(0, 3, -4));
    app.getRootNode().attachChild(l3);

    Box wallBack = new Box(4, 4, 0.01f);
    Geometry l4 = new Geometry("backwall", wallBack);
    l4.setMaterial(mat4);
    l4.setLocalTranslation(new Vector3f(0, 3, 4));
    app.getRootNode().attachChild(l4);
  }

  /**
   * Render placeholder for timer that will be displayed.
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
}
