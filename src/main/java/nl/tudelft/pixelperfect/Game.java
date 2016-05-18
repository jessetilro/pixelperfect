package nl.tudelft.pixelperfect;

import java.io.IOException;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.network.serializing.Serializer;
import com.jme3.post.FilterPostProcessor;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

import nl.tudelft.pixelperfect.client.ConnectListener;
import nl.tudelft.pixelperfect.client.EventCompletedMessage;
import nl.tudelft.pixelperfect.client.EventsMessage;
import nl.tudelft.pixelperfect.client.ServerListener;
import nl.tudelft.pixelperfect.event.Event;
import nl.tudelft.pixelperfect.event.EventScheduler;
import jmevr.input.OpenVR;
import jmevr.app.VRApplication;
import jmevr.post.CartoonSSAO;
import jmevr.util.VRGuiManager;
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
  private Scene scene;

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
	  observer = new Node("observer");
	  observer.setLocalTranslation(new Vector3f(0.0f, 2.0f, 0.0f));
	  VRApplication.setObserver(observer);
	  rootNode.attachChild(observer);

    initInputs();
    
    scene = new Scene(this);
    scene.createMap();

    initNetwork();

    spaceship = new Spaceship();
    spaceship.getLog().setServer(server);
    scheduler = new EventScheduler(0.5);
    scheduler.subscribe(spaceship.getLog());
  }

  private void initNetwork() {
    try {
      server = Network.createServer(6143);
      Serializer.registerClass(EventCompletedMessage.class);
      Serializer.registerClass(EventsMessage.class);
      server.start();
      ServerListener listen = new ServerListener();
      listen.setGame(this);
      server.addMessageListener(listen, EventCompletedMessage.class);
      server.addMessageListener(listen, EventsMessage.class);
      ConnectListener connect = new ConnectListener();
      connect.setGame(this);
      server.addConnectionListener(connect);
    } catch (IOException except) {
      except.printStackTrace();
    }
  }

  /**
   * Initiate input for the game.
   */
  @SuppressWarnings({"checkstyle:methodlength", "PMD"})
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
          // move them into VR processing (won't do anything if not in VR mode)
          VRApplication.moveScreenProcessingToVR();
        }
        if (name.equals("toggle")) {
          VRGuiManager.positionGui();
        }
        if (name.equals("forward")) {
          moveForward = keyPressed;
        } else if (name.equals("back")) {
          moveBackwards = keyPressed;
        } else if (name.equals("dumpImages")) {
            OpenVR.getCompositor().CompositorDumpImages.apply();
        } else if (name.equals("left")) {
            rotateLeft = keyPressed;
        } else if (name.equals("right")) {
            rotateRight = keyPressed;
        }
      }
    };
    inputManager.addListener(acl, "forward"); inputManager.addListener(acl, "back");
    inputManager.addListener(acl, "left"); inputManager.addListener(acl, "right");
    inputManager.addListener(acl, "toggle"); inputManager.addListener(acl, "incShift");
    inputManager.addListener(acl, "decShift"); inputManager.addListener(acl, "filter");
    inputManager.addListener(acl, "dumpImages");
  }

  /**
   * Get the spaceship for reference purposes.
   * 
   * @return The spaceship.
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
    
    for (Event event : spaceship.getLog().getEvents()) {
      event.notification(scene);
    }
    
    if (spaceship.isVictorious()) {
      System.out.println("Well played, you have completed the game!");
      this.stop();
    }
  }
}
