package nl.tudelft.pixelperfect.game;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.network.serializing.Serializer;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

import jmevr.app.VRApplication;
import nl.tudelft.pixelperfect.audio.AudioPlayer;
import nl.tudelft.pixelperfect.client.ConnectListener;
import nl.tudelft.pixelperfect.client.ServerListener;
import nl.tudelft.pixelperfect.client.message.EventCompletedMessage;
import nl.tudelft.pixelperfect.client.message.EventsMessage;
import nl.tudelft.pixelperfect.client.message.RoleChosenMessage;
import nl.tudelft.pixelperfect.event.EventScheduler;
import nl.tudelft.pixelperfect.gamestates.GameState;
import nl.tudelft.pixelperfect.gamestates.StartState;
import nl.tudelft.pixelperfect.gui.DebugHeadsUpDisplay;

import java.io.IOException;

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

  private static Game appGame;
  private Spaceship spaceship;
  private EventScheduler scheduler;
  private Server server;
  private AudioPlayer audioPlayer;

  private Spatial observer;

  private boolean moveForward;
  private boolean moveBackwards;
  private boolean rotateLeft;
  private boolean rotateRight;
  private boolean startKey;
  private boolean debugKey;

  private Scene scene;

  private DebugHeadsUpDisplay gameHud;

  private GameState gameState;

  /**
   * Main method bootstrapping the process by constructing this class and initializing a
   * jMonkeyEngine Game.
   * 
   * @param args
   *          The parameters passed to the process.
   */
  public static void main(String[] args) {
    appGame = new Game();

    // Use full screen distortion and maximum FOV.
    appGame.preconfigureVRApp(PRECONFIG_PARAMETER.USE_CUSTOM_DISTORTION, false);

    // Runs faster when set to false, but will allow mirroring.
    appGame.preconfigureVRApp(PRECONFIG_PARAMETER.ENABLE_MIRROR_WINDOW, true);

    // Render two eyes, regardless of SteamVR.
    appGame.preconfigureVRApp(PRECONFIG_PARAMETER.FORCE_VR_MODE, false);
    appGame.preconfigureVRApp(PRECONFIG_PARAMETER.SET_GUI_CURVED_SURFACE, true);
    appGame.preconfigureVRApp(PRECONFIG_PARAMETER.FLIP_EYES, false);

    // Show gui even if it is behind the current timing.
    appGame.preconfigureVRApp(PRECONFIG_PARAMETER.SET_GUI_OVERDRAW, true);

    // Faster VR rendering, requires some vertex shader changes.
    appGame.preconfigureVRApp(PRECONFIG_PARAMETER.INSTANCE_VR_RENDERING, true);
    appGame.preconfigureVRApp(PRECONFIG_PARAMETER.NO_GUI, false);

    // Set frustum distances here before app starts.
    appGame.preconfigureFrustrumNearFar(0.1f, 512f);

    appGame.start();
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

    audioPlayer = new AudioPlayer(this);
    // TODO load acutal sounds
    String[] names = {};
    String[] locations = {};
    audioPlayer.loadSounds(names, locations);

    initNetwork();

    spaceship = new Spaceship();
    spaceship.getLog().setServer(server);
    scheduler = new EventScheduler(Constants.EVENT_SCHEDULER_INTENSITY_MIN,
        Constants.EVENT_SCHEDULER_INTENSITY_MAX);
    scheduler.subscribe(spaceship.getLog());
    scheduler.start();

    gameHud = new DebugHeadsUpDisplay(getAssetManager(), guiNode, 200, 200, spaceship);

    gameState = new StartState(this);
  }

  /**
   * Method to initialize the network.
   */
  private void initNetwork() {
    try {
      server = Network.createServer(6143);
      Serializer.registerClass(EventCompletedMessage.class);
      Serializer.registerClass(EventsMessage.class);
      Serializer.registerClass(RoleChosenMessage.class);
      server.start();
      ServerListener listen = new ServerListener();
      listen.setGame(this);
      listen.setServer(server);
      server.addMessageListener(listen, EventCompletedMessage.class);
      server.addMessageListener(listen, EventsMessage.class);
      server.addMessageListener(listen, RoleChosenMessage.class);
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
  @SuppressWarnings({ "checkstyle:methodlength", "PMD" })
  private void initInputs() {
    InputManager inputManager = getInputManager();
    int[] keyTriggers = { KeyInput.KEY_W, KeyInput.KEY_S, KeyInput.KEY_A, KeyInput.KEY_D,
        KeyInput.KEY_P, KeyInput.KEY_0 };
    String[] mappings = { "forward", "back", "left", "right", "start", "debug" };
    for (int i = 0; i < keyTriggers.length; i++) {
      inputManager.addMapping(mappings[i], new KeyTrigger(keyTriggers[i]));
    }
    ActionListener acl = new ActionListener() {

      public void onAction(String name, boolean keyPressed, float tpf) {
        if (name.equals("forward")) {
          moveForward = keyPressed;
        } else if (name.equals("back")) {
          moveBackwards = keyPressed;
        } else if (name.equals("left")) {
          rotateLeft = keyPressed;
        } else if (name.equals("right")) {
          rotateRight = keyPressed;
        } else if (name.equals("start")) {
          startKey = keyPressed;
        } else if (name.equals("debug")) {
          debugKey = keyPressed;
        }
      }
    };
    for (String mapping : mappings) {
      inputManager.addListener(acl, mapping);
    }
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
    gameState.update(tpf);
    gameState = gameState.handleState();
  }

  /**
   * Getter for observer.
   *
   * @return observer
   */
  public Spatial getGameObserver() {
    return observer;
  }

  /**
   * Getter for moveForward.
   *
   * @return moveForward
   */
  public boolean isMoveForward() {
    return moveForward;
  }

  /**
   * Getter for moveBackwards.
   *
   * @return moveBackwards
   */
  public boolean isMoveBackwards() {
    return moveBackwards;
  }

  /**
   * Getter for rotateLeft.
   *
   * @return rotateLeft
   */
  public boolean isRotateLeft() {
    return rotateLeft;
  }

  /**
   * Getter for rotateRight.
   *
   * @return rotateRight
   */
  public boolean isRotateRight() {
    return rotateRight;
  }

  /**
   * Getter for startKey.
   *
   * @return startKey
   */
  public boolean isStartKey() {
    return startKey;
  }

  /**
   * Getter for the debugKey.
   * 
   * @return debugKey
   */
  public boolean isDebugTrigger() {
    return debugKey;
  }

  /**
   * Getter for gameHud.
   *
   * @return gameHud
   */
  public DebugHeadsUpDisplay getGameHud() {
    return gameHud;
  }

  /**
   * Getter for scheduler.
   *
   * @return scheduler
   */
  public EventScheduler getScheduler() {
    return scheduler;
  }

  /**
   * Setter for headsUpDisplay.
   *
   * @param headsUpDisplay
   *          HeadsUpDisplay to be set.
   */
  public void setHeadsUpDisplay(DebugHeadsUpDisplay headsUpDisplay) {
    this.gameHud = headsUpDisplay;
  }

  /**
   * Setter for observer.
   *
   * @param observer
   *          Observer to be set.
   */
  public void setGameObserver(Spatial observer) {
    this.observer = observer;
  }

  /**
   * Setter for scheduler.
   *
   * @param scheduler
   *          Scheduler to be set.
   */
  public void setScheduler(EventScheduler scheduler) {
    this.scheduler = scheduler;
  }

  /**
   * Setter for spaceship.
   *
   * @param spaceship
   *          Spaceship to be set.
   */
  public void setSpaceship(Spaceship spaceship) {
    this.spaceship = spaceship;
  }
}
