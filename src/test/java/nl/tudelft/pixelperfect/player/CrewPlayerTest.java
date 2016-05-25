package nl.tudelft.pixelperfect.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import nl.tudelft.pixelperfect.Spaceship;

import org.junit.Before;
import org.junit.Test;
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

  /**
   * Creating objects for testing.
   */
  @Before
  public void before() {
    player1 = new CrewPlayer("Player 1");
    player2 = new CrewPlayer("Player 2");
    player3 = new CrewPlayer("Player 1");
    spaceship = new Spaceship();
  }
  
  /**
   * Create a CrewPlayer instance as test object.
   */
  @Override
  public CrewPlayer createPlayer(String name) {
    return new CrewPlayer(name);
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
