package pixelperfect.spaceship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test Suite for the Spaceship class.
 * 
 * @author Jesse Tilro
 *
 */
public class SpaceshipTest {

	@Test
	public void updateTest() {
		Spaceship ship = new Spaceship();
		assertEquals(100.0, ship.getHealth(), 0.0);
	}
	
	@Test
	public void falseDeathTest() {
		Spaceship ship = new Spaceship();
		assertFalse(ship.isDead());
	}
	
	@Test
	public void trueDeathTest() {
		Spaceship ship = new Spaceship();
		ship.updateHealth(-100.0);
		assertTrue(ship.isDead());
	}
}
