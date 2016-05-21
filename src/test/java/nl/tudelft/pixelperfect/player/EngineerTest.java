package nl.tudelft.pixelperfect.player;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import nl.tudelft.pixelperfect.Spaceship;

import org.junit.Before;
import org.junit.Test;

public class EngineerTest extends CrewPlayerTest {
  private Engineer player1;
  private Engineer player2;
  private Engineer player3;
  private Spaceship spaceship;

  /**
   * Creating objects for testing.
   */
  @Before
  public void before() {
    player1 = new Engineer("Player 1");
    player2 = new Engineer("Player 2");
    player3 = new Engineer("Player 1");
    spaceship = new Spaceship();
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
  
  /**
   * Two different player instances should have different hash codes. Since the hash code method is
   * not implemented yet however, this test case is stubbed.
   */
  @Test
  public void testHashCode() {
    assertThat(player1.hashCode(), equalTo(42));
  }
}
