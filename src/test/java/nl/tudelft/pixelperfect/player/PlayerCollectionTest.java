package nl.tudelft.pixelperfect.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import com.jme3.network.HostedConnection;
import org.junit.Test;

/**
 * The test class for the Player collection class.
 * 
 * @author Dmitry
 *
 */
public class PlayerCollectionTest {

  private Player newPlayer;
  
  /**
   * Tests the add method.
   * 
   */
  @Test
  public void testAdd() {
    PlayerCollection test = new PlayerCollection();
    newPlayer = mock(Player.class);
    test.addPlayer(newPlayer);
    assertEquals(1, test.size());
  }

  /**
   * tests the get by connection method.
   * 
   */
  @Test
  public void testGet() {
    PlayerCollection test = new PlayerCollection();
    HostedConnection mockedHost = mock(HostedConnection.class);
    newPlayer = new CrewPlayer(mockedHost);
    test.addPlayer(newPlayer);
    assertEquals(newPlayer, test.getPlayerByConnection(mockedHost));
  }
  
  /**
   * tests the remove by connection method.
   * 
   */
  @Test
  public void testRemove() {
    PlayerCollection test = new PlayerCollection();
    HostedConnection mockedHost = mock(HostedConnection.class);
    newPlayer = new CrewPlayer(mockedHost);
    test.addPlayer(newPlayer);
    assertEquals(1, test.size());
    test.removePlayerByConnection(mockedHost);
    assertEquals(0, test.size());
  }
  
  /**
   * tests the get by role method in the case that there is one.
   * 
   */
  @Test
  public void testGetByRoleTrue() {
    PlayerCollection test = new PlayerCollection();
    HostedConnection mockedHost = mock(HostedConnection.class);
    newPlayer = new CrewPlayer(mockedHost);
    newPlayer.assignRole(PlayerRoles.GUNNER);
    test.addPlayer(newPlayer);
    assertEquals(newPlayer, test.getPlayerByRole(PlayerRoles.GUNNER));
  }
  
  /**
   * tests the get by role method in the case that there isn't one.
   * 
   */
  @Test
  public void testGetByRoleFalse() {
    PlayerCollection test = new PlayerCollection();
    HostedConnection mockedHost = mock(HostedConnection.class);
    newPlayer = new CrewPlayer(mockedHost);
    newPlayer.assignRole(PlayerRoles.GUNNER);
    test.addPlayer(newPlayer);
    assertNull(test.getPlayerByRole(PlayerRoles.JANITOR));
  }
  
  /**
   * tests the has role method in the case that there isn't one.
   * 
   */
  @Test
  public void testHasRoleFalse() {
    PlayerCollection test = new PlayerCollection();
    HostedConnection mockedHost = mock(HostedConnection.class);
    newPlayer = new CrewPlayer(mockedHost);
    newPlayer.assignRole(PlayerRoles.GUNNER);
    test.addPlayer(newPlayer);
    assertFalse(test.hasPlayerWithRole(PlayerRoles.JANITOR));
  }
  
  
  /**
   * tests the has role method in the case that there is one.
   * 
   */
  @Test
  public void testHasRoleTrue() {
    PlayerCollection test = new PlayerCollection();
    HostedConnection mockedHost = mock(HostedConnection.class);
    newPlayer = new CrewPlayer(mockedHost);
    newPlayer.assignRole(PlayerRoles.GUNNER);
    test.addPlayer(newPlayer);
    assertTrue(test.hasPlayerWithRole(PlayerRoles.GUNNER));
  }
}
