package nl.tudelft.pixelperfect.client;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.jme3.network.HostedConnection;
import com.jme3.network.Server;

import nl.tudelft.pixelperfect.Game;
import nl.tudelft.pixelperfect.Spaceship;

/**
 * Test Suite for the ConnectListener class.
 * 
 * @author Jesse Tilro
 *
 */
public class ConnectListenerTest {

  private Game mockedGame;
  private Spaceship mockedSpaceship;
  private ConnectListener object;

  @Before
  public void init() {
    // Mock dependencies
    mockedGame = mock(Game.class);
    mockedSpaceship = mock(Spaceship.class);

    // Stub dependencies
    when(mockedGame.getSpaceship()).thenReturn(mockedSpaceship);

    // Set up test object.
    object = new ConnectListener();
    object.setGame(mockedGame);
  }

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
    verify(mockedSpaceship).updateCrew(connections);
    verify(mockedServer).getConnections();
  }

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
    verify(mockedSpaceship).updateCrew(connections);
    verify(mockedServer).getConnections();
  }

}
