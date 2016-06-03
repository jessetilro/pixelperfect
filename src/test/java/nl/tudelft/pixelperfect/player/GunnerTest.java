package nl.tudelft.pixelperfect.player;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.pixelperfect.game.Spaceship;

/**
 * Test suite for the Gunner class.
 * 
 * @author Floris Doolaard
 */
public class GunnerTest extends CrewPlayerTest {
  private Gunner player1;
  private Gunner player2;
  private Gunner player3;
  private Spaceship spaceship;

  /**
   * Creating objects for testing.
   */
  @Before
  public void before() {
    player1 = new Gunner("Player 1");
    player2 = new Gunner("Player 2");
    player3 = new Gunner("Player 1");
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
