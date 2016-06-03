package nl.tudelft.pixelperfect.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.jme3.network.AbstractMessage;
import com.jme3.network.Filter;
import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.Server;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import nl.tudelft.pixelperfect.client.message.EventCompletedMessage;
import nl.tudelft.pixelperfect.client.message.RoleChosenMessage;
import nl.tudelft.pixelperfect.event.EventLog;
import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.game.Spaceship;

/**
 * Test Suite for the ServerListener class.
 * 
 * @author Jesse Tilro
 *
 */
@SuppressWarnings("PMD")
public class ServerListenerTest {

  private Game mockedGame;
  private ServerListener object;
  private HostedConnection mockedSource;
  private Server mockServer;

  /**
   * Set up mocked dependencies, stubs and the test object.
   */
  @Before
  public void init() {
    // Mock dependencies
    mockedGame = mock(Game.class);
    mockServer = mock(Server.class);
    // Set up test object.
    object = new ServerListener();
    object.setGame(mockedGame);
    object.setServer(mockServer);
    mockedSource = mock(HostedConnection.class);
  }

  /**
   * When calling getGame we expect the Game that we have set earlier using setGame.
   */
  @Test
  public void testGetGame() {
    assertEquals(mockedGame, object.getGame());
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
   * When the Server recieves a RoleChosenMessage, it should send it to other clients.
   * 
   */
  @SuppressWarnings("unchecked")
  @Test
  public void testRoleChosenMessage() {
    RoleChosenMessage message = mock(RoleChosenMessage.class);
    object.messageReceived(mockedSource, message);
    verify(mockServer).broadcast((Filter<? super HostedConnection>) anyObject(),
        (Message) anyObject());
  }
}
