package nl.tudelft.pixelperfect;

import java.io.IOException;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.network.serializing.Serializer;
import com.jme3.post.FilterPostProcessor;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import jmevr.input.OpenVR;
import jmevr.app.VRApplication;
import jmevr.post.CartoonSSAO;
import jmevr.util.VRGuiManager;
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
  private Spatial observer;
  private boolean moveForward, moveBackwards, rotateLeft, rotateRight;

  /**
   * Main method bootstrapping the process by constructing this class and initializing a
   * jMonkeyEngine Game.
   * 
   * @param args
   *          The parameters passed to the process.
   */
  public static void main(String[] args) {
    Game app = new Game();
    //VR SETTINGS
    // use full screen distortion, maximum FOV, possibly quicker (not compatible with instancing)
    app.preconfigureVRApp(PRECONFIG_PARAMETER.USE_CUSTOM_DISTORTION, false);
    // runs faster when set to false, but will allow mirroring
    app.preconfigureVRApp(PRECONFIG_PARAMETER.ENABLE_MIRROR_WINDOW, true);
    // render two eyes, regardless of SteamVR
    app.preconfigureVRApp(PRECONFIG_PARAMETER.FORCE_VR_MODE, false);
    app.preconfigureVRApp(PRECONFIG_PARAMETER.SET_GUI_CURVED_SURFACE, true);
    app.preconfigureVRApp(PRECONFIG_PARAMETER.FLIP_EYES, false);
    // show gui even if it is behind things
    app.preconfigureVRApp(PRECONFIG_PARAMETER.SET_GUI_OVERDRAW, true);
    // faster VR rendering, requires some vertex shader changes (see jmevr/shaders/Unshaded.j3md)
    app.preconfigureVRApp(PRECONFIG_PARAMETER.INSTANCE_VR_RENDERING, true);
    app.preconfigureVRApp(PRECONFIG_PARAMETER.NO_GUI, false);
    // set frustum distances here before app starts
    app.preconfigureFrustrumNearFar(0.1f, 512f);
    //app.preconfigureResolutionMultiplier(0.666f); // you can downsample for performance reasons
    app.start();
  }

  /**
   * Method initializing the game (e.g. setting up the scenegraph and/or the main menu).
   */
  @Override
  public void simpleInitApp() {
    initInputs();
    createMap();
    initNetwork();

    observer = new Node("observer");
    observer.setLocalTranslation(new Vector3f(0.0f, 0.0f, 0.0f));
    VRApplication.setObserver(observer);
    rootNode.attachChild(observer);

    spaceship = new Spaceship();
    spaceship.getLog().setServer(server);
    scheduler = new EventScheduler(0.5);
    scheduler.subscribe(spaceship.getLog());
  }

  private void initNetwork() {
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
  }

  private void initInputs() {
    InputManager inputManager = getInputManager();
    inputManager.addMapping("toggle", new KeyTrigger(KeyInput.KEY_SPACE));
    inputManager.addMapping("incShift", new KeyTrigger(KeyInput.KEY_Q));
    inputManager.addMapping("decShift", new KeyTrigger(KeyInput.KEY_E));
    inputManager.addMapping("forward", new KeyTrigger(KeyInput.KEY_W));
    inputManager.addMapping("back", new KeyTrigger(KeyInput.KEY_S));
    inputManager.addMapping("left", new KeyTrigger(KeyInput.KEY_A));
    inputManager.addMapping("right", new KeyTrigger(KeyInput.KEY_D));
    inputManager.addMapping("filter", new KeyTrigger(KeyInput.KEY_F));
    inputManager.addMapping("dumpImages", new KeyTrigger(KeyInput.KEY_I));
    ActionListener acl = new ActionListener() {

      public void onAction(String name, boolean keyPressed, float tpf) {
        if (name.equals("incShift") && keyPressed) {
          VRGuiManager.adjustGuiDistance(-0.1f);
        } else if (name.equals("decShift") && keyPressed) {
          VRGuiManager.adjustGuiDistance(0.1f);
        } else if (name.equals("filter") && keyPressed) {
          // adding filters in realtime
          CartoonSSAO cartfilt = new CartoonSSAO();
          FilterPostProcessor fpp = new FilterPostProcessor(getAssetManager());
          fpp.addFilter(cartfilt);
          getViewPort().addProcessor(fpp);
          // filters added to main viewport during runtime,
          // move them into VR processing
          // (won't do anything if not in VR mode)
          VRApplication.moveScreenProcessingToVR();
        }
        if (name.equals("toggle")) {
          VRGuiManager.positionGui();
        }
        if (name.equals("forward")) {
          if (keyPressed) {
            moveForward = true;
          } else {
            moveForward = false;
          }
        } else if (name.equals("back")) {
          if (keyPressed) {
            moveBackwards = true;
          } else {
            moveBackwards = false;
          }
        } else if (name.equals("dumpImages")) {
            OpenVR.getCompositor().CompositorDumpImages.apply();
        } else if (name.equals("left")) {
            if (keyPressed) {
              rotateLeft = true;
            } else {
              rotateLeft = false;
            }
        } else if (name.equals("right")) {
            if (keyPressed) {
              rotateRight = true;
            } else {
              rotateRight = false;
            }
        }
      }

    };
    inputManager.addListener(acl, "forward");
    inputManager.addListener(acl, "back");
    inputManager.addListener(acl, "left");
    inputManager.addListener(acl, "right");
    inputManager.addListener(acl, "toggle");
    inputManager.addListener(acl, "incShift");
    inputManager.addListener(acl, "decShift");
    inputManager.addListener(acl, "filter");
    inputManager.addListener(acl, "dumpImages");
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
    guiNode.detachAllChildren();
    BitmapFont guiFont = getAssetManager().loadFont("Interface/Fonts/Default.fnt");
    BitmapText timer = new BitmapText(guiFont, false);
    // timer.setSize(1);
    timer.setText("mm:ss");
    timer.setLocalTranslation(2.5f, 5, 3.9f);
    timer.setLocalScale(0.1f);
    timer.setLocalRotation(
        new Quaternion().fromAngleAxis(180 * FastMath.DEG_TO_RAD, new Vector3f(0, 1, 0)));
    rootNode.attachChild(timer);
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
    if (moveForward) {
      observer.move(VRApplication.getFinalObserverRotation().getRotationColumn(2).mult(tpf * 8f));
    }
    if (moveBackwards) {
      observer.move(VRApplication.getFinalObserverRotation().getRotationColumn(2).mult(-tpf * 8f));
    }
    if (rotateLeft) {
      observer.rotate(0, 0.75f * tpf, 0);
    }
    if (rotateRight) {
      observer.rotate(0, -0.75f * tpf, 0);
    }

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
