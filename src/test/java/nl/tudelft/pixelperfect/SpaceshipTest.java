package nl.tudelft.pixelperfect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.pixelperfect.Spaceship;

/**
 * Test Suite for the Spaceship class.
 * 
 * @author Jesse Tilro
 *
 */
public class SpaceshipTest {
  
  private Spaceship ship;
  
  /**
   * Initialize objects.
   */
  @Before
  public void before() {
    ship = new Spaceship();
  }
  
  /**
   * Test the udate health method.
   */
  @Test
  public void testUpdate() {
    assertEquals(100.0, ship.getHealth(), 0.0);
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
