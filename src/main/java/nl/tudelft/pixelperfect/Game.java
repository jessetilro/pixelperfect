package nl.tudelft.pixelperfect;

import java.io.IOException;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.network.serializing.Serializer;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

import jmevr.app.VRApplication;
import nl.tudelft.pixelperfect.client.ConnectListener;
import nl.tudelft.pixelperfect.client.EventsMessage;
import nl.tudelft.pixelperfect.client.HelloMessage;
import nl.tudelft.pixelperfect.client.ServerListener;
import nl.tudelft.pixelperfect.event.EventScheduler;

/**
 * Main class representing an active Game process and creating the JMonkey Environment.
 * 
 * @author David Alderliesten
 * @author Floris Doolaard
 * @author Dmitry Malarev
 * @author Jesse Tilro
 * @author Wouter Zirkzee
 *
 */
public class Game extends VRApplication {

  private Spaceship spaceship;
  private EventScheduler scheduler;
  private Server server;

  /**
   * Main method bootstrapping the process by constructing this class and initializing a
   * jMonkeyEngine Game.
   * 
   * @param args
   *          The parameters passed to the process.
   */
  public static void main(String[] args) {
    Game app = new Game();
    app.start();
  }

  /**
   * Method initializing the game (e.g. setting up the scenegraph and/or the main menu).
   */
  @Override
  public void simpleInitApp() {
    // increase movement speed
    //flyCam.setMoveSpeed(50);
    createMap();
    // Spatial map = assetManager.loadModel("assets/Models/cockpit1/cockpit1.j3o");
    try {
      server = Network.createServer(6143);
      Serializer.registerClass(HelloMessage.class);
      server.start();
      ServerListener listen = new ServerListener();
      listen.setGame(this);
      server.addMessageListener(listen, HelloMessage.class);
      server.addMessageListener(listen, EventsMessage.class);
      ConnectListener connect = new ConnectListener();
      connect.setGame(this);
      server.addConnectionListener(connect);

    } catch (IOException except) {
      except.printStackTrace();
    }
    spaceship = new Spaceship();
    spaceship.getLog().setServer(server);
    scheduler = new EventScheduler(0.5);

    scheduler.subscribe(spaceship.getLog());
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
  public void drawDashboard() {
    Box dashboard = new Box(4, 1f, 1);
    Geometry geom = new Geometry("Box", dashboard);
    Material mat = new Material(getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
    mat.setColor("Color", ColorRGBA.Blue);
    geom.setMaterial(mat);
    geom.setLocalTranslation(new Vector3f(0, 0, -3));
    rootNode.attachChild(geom);
  }

  /**
   * Render the floor of the scene.
   */
  public void drawFloor() {
    // green floor
    Box floor = new Box(4, 0.01f, 4);
    Geometry g2 = new Geometry("Floor", floor);
    Material mat2 = new Material(getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
    mat2.setColor("Color", ColorRGBA.Green);
    g2.setMaterial(mat2);
    g2.setLocalTranslation(0, -1, 0);
    rootNode.attachChild(g2);
  }

  /**
   * Render the walls of the scene.
   */
  public void drawWalls() {
    // walls
    Box wallLeft = new Box(0.01f, 4, 4);
    Geometry l1 = new Geometry("leftwall", wallLeft);
    Material mat3 = new Material(getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
    mat3.setColor("Color", ColorRGBA.Orange);
    l1.setMaterial(mat3);
    l1.setLocalTranslation(new Vector3f(-4, 3, 0));
    rootNode.attachChild(l1);

    Box wallRight = new Box(0.01f, 4, 4);
    Geometry l2 = new Geometry("rightwall", wallRight);
    l2.setMaterial(mat3);
    l2.setLocalTranslation(new Vector3f(4, 3, 0));
    rootNode.attachChild(l2);

    Box wallFront = new Box(4, 4, 0.01f);
    Geometry l3 = new Geometry("frontwall", wallFront);
    Material mat4 = new Material(getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
    mat4.setColor("Color", ColorRGBA.Yellow);
    l3.setMaterial(mat4);
    l3.setLocalTranslation(new Vector3f(0, 3, -4));
    rootNode.attachChild(l3);

    Box wallBack = new Box(4, 4, 0.01f);
    Geometry l4 = new Geometry("backwall", wallBack);
    l4.setMaterial(mat4);
    l4.setLocalTranslation(new Vector3f(0, 3, 4));
    rootNode.attachChild(l4);
  }

  /**
   * Render placeholder for timer that will be displayed.
   */
  public void drawTimer() {
//    guiNode.detachAllChildren();
//    guiFont = getAssetManager().loadFont("Interface/Fonts/Default.fnt");
//    BitmapText timer = new BitmapText(guiFont, false);
//    // timer.setSize(1);
//    timer.setText("mm:ss");
//    timer.setLocalTranslation(2.5f, 5, 3.9f);
//    timer.setLocalScale(0.1f);
//    timer.setLocalRotation(
//        new Quaternion().fromAngleAxis(180 * FastMath.DEG_TO_RAD, new Vector3f(0, 1, 0)));
//    rootNode.attachChild(timer);
  }
  
  /**
   * Shows the spaceship for reference purposes.
   * 
   * @return the spaceship.
   */
  public Spaceship getSpaceship() {
    return spaceship;
  }

  /**
   * Main update loop for the game.
   */
  @Override
  public void simpleUpdate(float tpf) {
    scheduler.update(tpf);
    spaceship.update(tpf);

    if (spaceship.isDead()) {
      this.stop();
    }

    if (spaceship.isVictorious()) {
      System.out.println("Well played, you have completed the game!");
      this.stop();
    }
  }
}
