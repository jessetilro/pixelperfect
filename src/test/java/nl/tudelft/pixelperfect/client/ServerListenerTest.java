package nl.tudelft.pixelperfect.client;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.jme3.network.AbstractMessage;
import com.jme3.network.HostedConnection;
import com.jme3.network.Server;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import nl.tudelft.pixelperfect.client.message.EventCompletedMessage;
import nl.tudelft.pixelperfect.client.message.RepairMessage;
import nl.tudelft.pixelperfect.client.message.RoleAllocationMessage;
import nl.tudelft.pixelperfect.event.EventLog;
import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.game.Spaceship;
import nl.tudelft.pixelperfect.player.PlayerCollection;
import nl.tudelft.pixelperfect.player.PlayerRoles;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;



/**
 * Test Suite for the ServerListener class.
 * 
 * @author Jesse Tilro
 *
 */
@SuppressWarnings("PMD")
public class ServerListenerTest {

  private Game mockedGame;
  private NetworkServerMessageListener object;
  private HostedConnection mockedSource;
  private Server mockServer;
  private NetworkServerConnectionListener listen;

  /**
   * Set up mocked dependencies, stubs and the test object.
   */
  @Before
  public void init() {
    // Mock dependencies
    mockedGame = mock(Game.class);
    mockServer = mock(Server.class);
    // Set up test object.
    object = new NetworkServerMessageListener();
    object.setGame(mockedGame);
    object.setServer(mockServer);
    mockedSource = mock(HostedConnection.class);
    listen = new NetworkServerConnectionListener();
    listen.setGame(mockedGame);
  }

  /**
   * When the ServerListener receives a Message, it should do something with its contents.
   */
  @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT")
  @Test
  public void testMessageReceived() {
    // Fixtures
    EventCompletedMessage mockedMessage = mock(EventCompletedMessage.class);
    Spaceship mockedShip = mock(Spaceship.class);
    EventLog mockedLog = mock(EventLog.class);

    // Execution
    when(mockedGame.getSpaceship()).thenReturn(mockedShip);
    when(mockedShip.getLog()).thenReturn(mockedLog);

    object.messageReceived(mockedSource, mockedMessage);
  }

  /**
   * When the ServerListener receives some message it does not recognize, it should do nothing with
   * it.
   */
  @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT")
  @Test
  public void testHelloMessageReceivedNull() {
    // Fixtures
    HostedConnection mockedSource = mock(HostedConnection.class);
    AbstractMessage mockedMessage = mock(AbstractMessage.class);

    // Execution
    object.messageReceived(mockedSource, null);

    // Verification
    verifyNoMoreInteractions(mockedMessage);
  }
  
  /**
   * Tests what the listenert does if it receives a RoleChosenMessage.
   * The crew does not have the requested role.
   * 
   */
  @Test
  public void testRoleChosenNoParam() {
    RoleAllocationMessage mockedMessage = mock(RoleAllocationMessage.class);
    Spaceship mockedShip = mock(Spaceship.class);
    PlayerCollection crew = new PlayerCollection();
    when(mockedMessage.getRole()).thenReturn(PlayerRoles.ENGINEER);
    when(mockedGame.getSpaceship()).thenReturn(mockedShip);
    when(mockedShip.getCrew()).thenReturn(crew);
    listen.connectionAdded(mockServer, mockedSource);
    object.messageReceived(mockedSource, mockedMessage);
  }
  
  /**
   * Tests what the listenert does if it receives a RoleChosenMessage.
   * The crew has already the requested role.
   * 
   */
  @Test
  public void testRoleChosen() {
    RoleAllocationMessage mockedMessage = mock(RoleAllocationMessage.class);
    Spaceship mockedShip = mock(Spaceship.class);
    PlayerCollection crew = new PlayerCollection();
    when(mockedMessage.getRole()).thenReturn(PlayerRoles.ENGINEER);
    when(mockedGame.getSpaceship()).thenReturn(mockedShip);
    when(mockedShip.getCrew()).thenReturn(crew);
    listen.connectionAdded(mockServer, mockedSource);
    HostedConnection mockHost2 = mock(HostedConnection.class);
    listen.connectionAdded(mockServer, mockHost2);
    crew.getPlayerByConnection(mockHost2).assignRole(PlayerRoles.ENGINEER);
    object.messageReceived(mockedSource, mockedMessage);
  }

  /**
   * Tests what the Listener does if an event with parameters is sent.
   * 
   */
  @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT")
  @Test
  public void testEventCompletedParameters() {
    Spaceship mockedShip = mock(Spaceship.class);
    final EventLog mockedLog = mock(EventLog.class);

    final HostedConnection mockedSource = mock(HostedConnection.class);
    EventCompletedMessage message = new EventCompletedMessage(2);
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    map.put("test", 42);
    message.setParameters(map);

    when(mockedGame.getSpaceship()).thenReturn(mockedShip);
    when(mockedShip.getLog()).thenReturn(mockedLog);

    object.messageReceived(mockedSource, message);

  }

  /**
   * When the Server receives a RepairMessage, it should update the ship's health.
   * 
   */
  @Test
  public void testRepairMessage() {
    Spaceship mockedShip = mock(Spaceship.class);
    when(mockedGame.getSpaceship()).thenReturn(mockedShip);
    RepairMessage message = mock(RepairMessage.class);
    object.messageReceived(mockedSource, message);
    verify(mockedShip).updateHealth(anyDouble());
    
  }
}
