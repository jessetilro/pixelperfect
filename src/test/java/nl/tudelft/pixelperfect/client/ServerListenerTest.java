package nl.tudelft.pixelperfect.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.jme3.network.AbstractMessage;
import com.jme3.network.HostedConnection;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import nl.tudelft.pixelperfect.Game;
import nl.tudelft.pixelperfect.Spaceship;
import nl.tudelft.pixelperfect.event.EventLog;

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
   * When calling getGame we expect the Game that we have set earlier using setGame.
   */
  @Test
  public void testGetGame() {
    assertEquals(mockedGame, object.getGame());
  }

  /**
   * When the ServerListener receives a HelloMessage, it should do something with its contents.
   */
  @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT")
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
   * When the ServerListener receives an CompletedEventMessage, it should do something with its contents.
   */
  @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT")
  @Test
  public void testCompletedEventMessageReceived() {
    // Fixtures
    HostedConnection mockedSource = mock(HostedConnection.class);
    EventCompletedMessage mockedMessage = mock(EventCompletedMessage.class);
    Spaceship mockedShip = mock(Spaceship.class);
    EventLog mockedLog = mock(EventLog.class);
    

    // Stubbing
    when(mockedMessage.getCompletedEvent()).thenReturn("1");
    when(mockedGame.getSpaceship()).thenReturn(mockedShip);
    when(mockedShip.getLog()).thenReturn(mockedLog);
    

    // Execution
    object.messageReceived(mockedSource, mockedMessage);

    // Verification
    verify(mockedLog).complete("1");
  }

}
