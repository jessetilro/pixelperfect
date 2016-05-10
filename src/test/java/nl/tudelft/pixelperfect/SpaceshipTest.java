package nl.tudelft.pixelperfect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import nl.tudelft.pixelperfect.Spaceship;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the Spaceship class.
 * 
 * @author Jesse Tilro
 *
 */
public class SpaceshipTest {

  private Spaceship ship;

  /**
   * Initialize ship object.
   */
  @Before
  public void before() {
    ship = new Spaceship();
  }

  /**
   * Test the get health functionality by verifying full health.
   */
  @Test
  public void testGetHealth() {
    assertEquals(100.0, ship.getHealth(), 0.0);
  }

  /**
   * Test the update health method by dealing 25 damage.
   */
  @Test
  public void testUpdate() {
    assertEquals(100.0, ship.getHealth(), 0.0);
    ship.updateHealth(-25.0);
    assertEquals(75.0, ship.getHealth(), 0.0);
  }

  /**
   * Test the isDead method on a false case.
   * 
   */
  @Test
  public void testFalseDeath() {
    assertFalse(ship.isDead());
  }

  /**
   * Test the isDead method on a true case.
   * 
   */
  @Test
  public void testTrueDeath() {
    ship.updateHealth(-100.0);
    assertTrue(ship.isDead());
  }
}
