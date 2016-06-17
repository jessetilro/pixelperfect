package nl.tudelft.pixelperfect.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.jme3.network.HostedConnection;

import nl.tudelft.pixelperfect.game.Spaceship;

/**
 * Test Suite for the CrewPlayer class.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
@SuppressWarnings("PMD")
public class CrewPlayerTest extends PlayerTest {
  private CrewPlayer player1;
  private CrewPlayer player2;
  private CrewPlayer player3;
  private Spaceship spaceship;
  private HostedConnection mockedConnection1;
  private HostedConnection mockedConnection2;

  /**
   * Creating objects for testing.
   */
  @Before
  public void before() {
    mockedConnection1 = mock(HostedConnection.class);
    mockedConnection2 = mock(HostedConnection.class);
    player1 = new CrewPlayer(mockedConnection1);
    player2 = new CrewPlayer(mockedConnection2);
    player3 = new CrewPlayer(mockedConnection1);
    spaceship = new Spaceship();
  }

  /**
   * Create a CrewPlayer instance as test object.
   */
  @Override
  public CrewPlayer createPlayer(HostedConnection connection) {
    return new CrewPlayer(connection);
  }

  /**
   * Creating the true test for the equals method.
   */
  @Test
  public void testEqualsTrue() {
    assertEquals(player1, player3);
  }

  /**
   * Creating the false test for the equals method.
   */
  @Test
  public void testEqualsFalse() {
    assertNotEquals(player1, player2);
  }

  /**
   * Testing the type in the equals method (branch coverage).
   */
  @Test
  public void testEqualsType() {
    assertNotEquals(player1, spaceship);
  }

}
