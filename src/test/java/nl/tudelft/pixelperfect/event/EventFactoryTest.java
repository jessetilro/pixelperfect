package nl.tudelft.pixelperfect.event;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by woute on 5/10/2016.
 */
public class EventFactoryTest {

  private EventFactory factory;

  /**
   * Setting up the EventFactory class for the test.
   */
  @Before
  public void initialize() {
    factory = new EventFactory();
  }

  /**
   * Test if Id increases with each event.
   */
  @Test
  public void testUniqueId() {
    Event e1 = factory.create(0);
    assertEquals(e1.getId(), 0);
    Event e2 = factory.create(0);
    assertEquals(e2.getId(), 1);
  }

  /**
   * Test if an Astroid Field event is created.
   */
  @Test
  public void testCreateAsteroidEvent() {
    Event e1 = factory.create(0);
    assertEquals(e1.getSummary(), "Asteroid Field");
  }

  /**
   * Test if an FireEvent event is created.
   */
  @Test
  public void testCreateFireEvent() {
    Event e1 = factory.create(1);
    assertEquals(e1.getSummary(), "Fire");
  }

  /**
   * Test if an Hostile Ship event is created.
   */
  @Test
  public void testCreateHostileShipEvent() {
    Event e1 = factory.create(2);
    assertEquals(e1.getSummary(), "Hostile Ship");
  }

  /**
   * Test if an Plasma Leak event is created.
   */
  @Test
  public void testCreatePlasmaLeakEvent() {
    Event e1 = factory.create(3);
    assertEquals(e1.getSummary(), "Plasma Leak");
  }

}