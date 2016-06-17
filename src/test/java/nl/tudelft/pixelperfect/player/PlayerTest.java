package nl.tudelft.pixelperfect.player;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.jme3.network.HostedConnection;

/**
 * Class for the automated testing of the Player class.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public abstract class PlayerTest {

  private Player testObject;
  private HostedConnection mockedConnection1;
  private HostedConnection mockedConnection2;

  /**
   * Setting up the Player class as a test object.
   */
  @Before
  public void initialize() {
    mockedConnection1 = mock(HostedConnection.class);
    mockedConnection2 = mock(HostedConnection.class);
    testObject = createPlayer(mockedConnection1);
    testObject.setName("Lorem Ipsum");
  }

  /**
   * Factory method for testing.
   * 
   * @param connection
   *          Connection to the player.
   * @return The object to be tested.
   */
  public abstract Player createPlayer(HostedConnection connection);

  /**
   * When calling getName the correct player name must be returned.
   */
  @Test
  public void testGetName() {
    assertEquals(testObject.getName(), "Lorem Ipsum");
  }

  /**
   * After assigning a role to a player, getting the role should yield that same role.
   */
  @Test
  public void testAssignRole() {
    testObject.assignRole(PlayerRoles.ENGINEER);
    assertEquals(PlayerRoles.ENGINEER, testObject.getRole());
  }

  /**
   * Two different player instances should have different hash codes. Since the hash code method is
   * not implemented yet however, this test case is stubbed.
   */
  @Test
  public void testHashCode() {
    assertThat(testObject.hashCode(), equalTo(42));
  }

}
