package nl.tudelft.pixelperfect.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.jme3.network.HostedConnection;
import com.jme3.network.Server;

import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.game.Spaceship;
import nl.tudelft.pixelperfect.player.PlayerCollection;

/**
 * Test Suite for the ConnectListener class.
 * 
 * @author Jesse Tilro
 *
 */
@SuppressWarnings("PMD")
@edu.umd.cs.findbugs.annotations.SuppressWarnings
public class ConnectListenerTest {

  private Game mockedGame;
  private Spaceship mockedSpaceship;
  private PlayerCollection mockedPlayerCollection;
  private NetworkServerConnectionListener object;

  /**
   * Set up mocked dependencies, stubs and the test object.
   */
  @Before
  public void init() {
    // Mock dependencies
    mockedGame = mock(Game.class);
    mockedSpaceship = mock(Spaceship.class);
    mockedPlayerCollection = mock(PlayerCollection.class);

    // Stub dependencies
    when(mockedGame.getSpaceship()).thenReturn(mockedSpaceship);
    when(mockedSpaceship.getCrew()).thenReturn(mockedPlayerCollection);

    // Set up test object.
    object = new NetworkServerConnectionListener();
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
   * When a connection with a game's server is made, its spaceship's crew should be updated.
   */
  @Test
  public void testConnectionAdded() {
    // Fixtures
    Server mockedServer = mock(Server.class);
    HostedConnection mockedClient = mock(HostedConnection.class);
    Collection<HostedConnection> connections = new ArrayList<HostedConnection>();
    connections.add(mockedClient);

    // Stubbing
    when(mockedServer.getConnections()).thenReturn(connections);

    // Execution
    object.connectionAdded(mockedServer, mockedClient);

    // Verification
    verify(mockedSpaceship).getCrew();
  }

  /**
   * When a connection with a game's server is aborted, its spaceship's crew should be updated.
   */
  @Test
  public void testConnectionRemoved() {
    // Fixtures
    Server mockedServer = mock(Server.class);
    HostedConnection mockedClient = mock(HostedConnection.class);
    Collection<HostedConnection> connections = new ArrayList<HostedConnection>();
    connections.add(mockedClient);

    // Stubbing
    when(mockedServer.getConnections()).thenReturn(connections);

    // Execution
    object.connectionRemoved(mockedServer, mockedClient);

    // Verification
    verify(mockedSpaceship).getCrew();
  }

}
