package nl.tudelft.pixelperfect;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import nl.tudelft.pixelperfect.event.EventListener;
import nl.tudelft.pixelperfect.event.EventLog;
import nl.tudelft.pixelperfect.game.Route;
import nl.tudelft.pixelperfect.game.Spaceship;

/**
 * Test Suite for the Spaceship class.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
@SuppressWarnings("PMD")
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
   * Tests the getScore method.
   */
  @Test
  public void testGetScore() {
    assertEquals(0, ship.getScore());
  }

  /**
   * Updates the score to a positive value and verifies it.
   */
  @Test
  public void testChangeScorePositive() {
    ship.updateScore(25);

    assertEquals(25, ship.getScore());
  }

  /**
   * Updates the score to a negative value and verifies it.
   * 
   */
  @Test
  public void testChangeScoreNegative() {
    ship.updateScore(-50);

    assertEquals(-50, ship.getScore());
  }

  /**
   * Tests the getTimer method.
   */
  @Test
  public void testGetTimer() {
    assertEquals(0, ship.getTimer(), 0);
  }

  /**
   * Initiallly the ship's health value should be set to 100.
   */
  @Test
  public void testGetHealth() {
    assertEquals(100.0, ship.getHealth(), 0.0);
  }

  /**
   * When the ship is dealt damage less than its current health value, its health value must be
   * decremented with the given damage.
   */
  @Test
  public void testUpdateHealthLessDamage() {
    ship.updateHealth(-25.0);
    assertEquals(75.0, ship.getHealth(), 0.0);
  }

  /**
   * When the ship is dealt damage equal to or greater than its current health value, its health
   * value must be set to zero.
   */
  @Test
  public void testUpdateHealthGreaterDamage() {
    ship.updateHealth(-101.0);
    assertEquals(0.0, ship.getHealth(), 0.0);
  }

  /**
   * Test the isDead method on a false case.
   */
  @Test
  public void testFalseIsDead() {
    assertFalse(ship.isDead());
  }

  /**
   * Test the isDead method on a true case.
   */
  @Test
  public void testTrueIsDead() {
    ship.updateHealth(-100.0);
    assertTrue(ship.isDead());
  }

  /**
   * Initially the ship has not traversed its Route, so it is not yet victorious.
   */
  @Test
  public void testInitialVictoryFalse() {
    assertFalse(ship.isVictorious());
  }

  /**
   * When not enough time has passed, ship is not yet victorious after an update.
   */
  @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT")
  @Test
  public void testUpdateVictoryFalse() {
    // Mock and stub route dependency to force victory and inject it in test object.
    Route mockedRoute = mock(Route.class);
    when(mockedRoute.isCompleted(anyLong())).thenReturn(false);
    ship.followRoute(mockedRoute);

    // Execute
    ship.update(1);

    verify(mockedRoute).isCompleted(anyLong());
    assertFalse(ship.isVictorious());
  }

  /**
   * When enough time has passed, the route is traversed and the ship is victorious.
   */
  @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT")
  @Test
  public void testUpdateVictoryTrue() {
    // Mock and stub route dependency to force victory and inject it in test object.
    Route mockedRoute = mock(Route.class);
    when(mockedRoute.isCompleted(anyLong())).thenReturn(true);
    ship.followRoute(mockedRoute);

    // Execute
    ship.update(1);

    verify(mockedRoute).isCompleted(anyLong());
    assertTrue(ship.isVictorious());
  }

  /**
   * When updating the ship, the captain's log is updated as well.
   */
  @Test
  public void testUpdateLog() {
    // Mock and stub log dependency.
    EventListener mockedLog = mock(EventLog.class);

    ship.useLog(mockedLog);
    assertEquals(mockedLog, ship.getLog());

    ship.update(1);
    verify(mockedLog).update();
  }
}
