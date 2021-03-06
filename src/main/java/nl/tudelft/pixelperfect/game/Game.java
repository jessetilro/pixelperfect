package nl.tudelft.pixelperfect.game;

import java.io.IOException;

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
import jmevr.util.VRGuiManager;
import nl.tudelft.pixelperfect.audio.AudioPlayer;
import nl.tudelft.pixelperfect.client.NetworkServerConnectionListener;
import nl.tudelft.pixelperfect.client.NetworkServerMessageListener;
import nl.tudelft.pixelperfect.client.message.DisconnectMessage;
import nl.tudelft.pixelperfect.client.message.EventCompletedMessage;
import nl.tudelft.pixelperfect.client.message.NewGameMessage;
import nl.tudelft.pixelperfect.client.message.PlayerDetailsMessage;
import nl.tudelft.pixelperfect.client.message.RepairMessage;
import nl.tudelft.pixelperfect.client.message.RoleAllocationMessage;
import nl.tudelft.pixelperfect.event.EventScheduler;
import nl.tudelft.pixelperfect.gamestates.GameState;
import nl.tudelft.pixelperfect.gamestates.StartState;
import nl.tudelft.pixelperfect.gui.DebugHeadsUpDisplay;

/**
 * Main class representing an active Game process and creating the JMonkey Environment. Suppressing
 * the too many fields PMD warning because we will refactor this class next iteration.
 * 
 * @author David Alderliesten
 * @author Floris Doolaard
 * @author Dmitry Malarev
 * @author Jesse Tilro
 * @author Wouter Zirkzee
 */
@SuppressWarnings("PMD.TooManyFields")
public class Game extends VRApplication {

  private static Game appGame;
  private Spaceship spaceship;
  private EventScheduler scheduler;
  private static Server server;
  private AudioPlayer audioPlayer;
  private Spatial observer;

  private boolean moveForward;
  private boolean moveBackwards;
  private boolean rotateLeft;
  private boolean rotateRight;
  private boolean startKey;
  private boolean resetKey;
  private boolean debugKeyOn;
  private boolean debugKeyOff;

  private Scene scene;

  private DebugHeadsUpDisplay debugHud;

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
    appGame.preconfigureVRApp(PRECONFIG_PARAMETER.SET_GUI_CURVED_SURFACE, false);
    appGame.preconfigureVRApp(PRECONFIG_PARAMETER.FLIP_EYES, false);

    // Show gui even if it is behind the current timing.
    appGame.preconfigureVRApp(PRECONFIG_PARAMETER.SET_GUI_OVERDRAW, true);

    // Faster VR rendering, requires some vertex shader changes.
    appGame.preconfigureVRApp(PRECONFIG_PARAMETER.INSTANCE_VR_RENDERING, false);
    appGame.preconfigureVRApp(PRECONFIG_PARAMETER.NO_GUI, false);

    // Starting the game.
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

    audioPlayer.loadSounds(Constants.AUDIO_EVENTS, Constants.AUDIO_PATH_NAMES);

    initNetwork();
    initLogic();

    initGUI();
  }

  /**
   * Method to initialize the network.
   */
  private void initNetwork() {
    try {
      server = Network.createServer(6143);
      Serializer.registerClass(EventCompletedMessage.class);
      Serializer.registerClass(RoleAllocationMessage.class);
      Serializer.registerClass(PlayerDetailsMessage.class);
      Serializer.registerClass(RepairMessage.class);
      Serializer.registerClass(NewGameMessage.class);
      Serializer.registerClass(DisconnectMessage.class);
      server.start();
      NetworkServerMessageListener listen = new NetworkServerMessageListener();
      listen.setGame(this);
      listen.setServer(server);
      server.addMessageListener(listen, EventCompletedMessage.class);
      server.addMessageListener(listen, RoleAllocationMessage.class);
      server.addMessageListener(listen, PlayerDetailsMessage.class);
      server.addMessageListener(listen, RepairMessage.class);
      server.addMessageListener(listen, NewGameMessage.class);
      server.addMessageListener(listen, DisconnectMessage.class);
      NetworkServerConnectionListener connect = new NetworkServerConnectionListener();
      connect.setGame(this);
      server.addConnectionListener(connect);
    } catch (IOException except) {
      except.printStackTrace();
    }
  }

  /**
   * Initialize the game logic.
   */
  public void initLogic() {
    spaceship = new Spaceship();
    scheduler = new EventScheduler(Constants.EVENT_SCHEDULER_INTENSITY_MIN,
        Constants.EVENT_SCHEDULER_INTENSITY_MAX);
    scheduler.subscribe(spaceship.getLog());
    scheduler.start();
  }

  /**
   * Initialize the graphical user interface.
   */
  public void initGUI() {
    debugHud = new DebugHeadsUpDisplay(getAssetManager(), guiNode,
        Constants.DEBUG_ELEMENTS_WIDTH_OFFSET, VRGuiManager.getCanvasSize().getY(), spaceship);

    gameState = new StartState(this);
  }

  /**
   * Get the current game state.
   * 
   * @return The current game state.
   */
  public GameState getState() {
    return gameState;
  }

  /**
   * Tells the clients to start the game.
   */
  public void startGame() {
    server.broadcast(new NewGameMessage());
  }

  /**
   * Really bad way to implement this, will be refactored, but this method resets the game by
   * sending all connected clients a message telling them to disconnect.
   */
  public void resetGame() {
    System.out.println("The game was reset, notifying clients.");
    initLogic();
    server.broadcast(new DisconnectMessage());
  }

  /**
   * Initiate input for the game.
   */
  @SuppressWarnings({ "checkstyle:methodlength", "PMD" })
  private void initInputs() {
    InputManager inputManager = getInputManager();
    int[] keyTriggers = { KeyInput.KEY_W, KeyInput.KEY_S, KeyInput.KEY_A, KeyInput.KEY_D,
        KeyInput.KEY_P, KeyInput.KEY_R, KeyInput.KEY_0, KeyInput.KEY_1 };
    String[] mappings = { "forward", "back", "left", "right", "start", "reset", "debugOn",
        "debugOff" };
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
        } else if (name.equals("reset")) {
          resetKey = keyPressed;
        } else if (name.equals("debugOn")) {
          debugKeyOn = keyPressed;
        } else if (name.equals("debugOff")) {
          debugKeyOff = keyPressed;
        }
      }
    };
    for (String mapping : mappings) {
      inputManager.addListener(acl, mapping);
    }
    inputManager.setCursorVisible(false);
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
   * Check whether the game was reset.
   * 
   * @return Whether the game was reset.
   */
  public boolean isReset() {
    return resetKey;
  }

  /**
   * Getter for the debugKey activator.
   *
   * @return debugKeyOn
   */
  public boolean isDebugOnTrigger() {
    return debugKeyOn;
  }

  /**
   * Getter for the debugKey deactivator.
   *
   * @return debugKeyOff
   */
  public boolean isDebugOffTrigger() {
    return debugKeyOff;
  }

  /**
   * Getter for the debugHud.
   *
   * @return debugHud
   */
  public DebugHeadsUpDisplay getDebugHud() {
    return debugHud;
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
   * Setter for debugDisplay.
   *
   * @param debugDisplay
   *          debugDisplay to be set.
   */
  public void setDebugDisplay(DebugHeadsUpDisplay debugDisplay) {
    this.debugHud = debugDisplay;
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

  /**
   * Getter for the audio player.
   *
   * @return audio player
   */
  public AudioPlayer getAudioPlayer() {
    return audioPlayer;
  }

  /**
   * Getter for the scene.
   *
   * @return scene
   */
  public Scene getScene() {
    return scene;
  }

  /**
   * Returns the size of the x viewport for VR.
   * 
   * @return viewport width.
   */
  public float getViewPortX() {
    return VRGuiManager.getCanvasSize().getX();
  }

  /**
   * Returns the size of the y viewport for VR.
   * 
   * @return viewport height.
   */
  public float getViewPortY() {
    return VRGuiManager.getCanvasSize().getY();
  }
}
