package nl.tudelft.pixelperfect.client;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.jme3.network.HostedConnection;

import nl.tudelft.pixelperfect.Game;

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

  /**
   * Set up mocked dependencies, stubs and the test object.
   */
  @Before
  public void init() {
    // Mock dependencies
    mockedGame = mock(Game.class);

    // Set up test object.
    object = new ServerListener();
    object.setGame(mockedGame);
  }

  /**
   * When the ServerListener retrieves a HelloMessage, it should do something with its contents.
   */
  @Test
  public void testMessageReceived() {
    // Fixtures
    HostedConnection mockedSource = mock(HostedConnection.class);
    HelloMessage mockedMessage = mock(HelloMessage.class);

    // Stubbing
    when(mockedMessage.getSomething()).thenReturn("Hello World!");

    // Execution
    object.messageReceived(mockedSource, mockedMessage);

    // Verification
    verify(mockedMessage).getSomething();
  }

}
