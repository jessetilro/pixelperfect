package nl.tudelft.pixelperfect.event;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the EventFactory class.
 * 
 * @author Wouter Zirkzee
 * @author Jesse Tilro
 * 
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
   * When creating an event with index 0, it should yield an event of type Asteroid Field.
   */
  @Test
  public void testCreateAsteroidEvent() {
    Event e1 = factory.create(0);
    assertEquals(e1.getSummary(), "Asteroid Field");
  }

  /**
   * When creating an event with index 1, it should yield an event of type Fire.
   */
  @Test
  public void testCreateFireEvent() {
    Event e1 = factory.create(1);
    assertEquals(e1.getSummary(), "Fire");
  }

  /**
   * When creating an event with index 2, it should yield an event of type Hostile Ship.
   */
  @Test
  public void testCreateHostileShipEvent() {
    Event e1 = factory.create(2);
    assertEquals(e1.getSummary(), "Hostile Ship");
  }

  /**
   * When creating an event with index 3, it should yield an event of type Plasma Leak.
   */
  @Test
  public void testCreatePlasmaLeakEvent() {
    Event e1 = factory.create(3);
    assertEquals(e1.getSummary(), "Plasma Leak");
  }

  /**
   * When creating an event with an invalid index, it should yield a null reference.
   */
  @Test
  public void testCreateInvalidIndex() {
    Event e1 = factory.create(-1);
    assertThat(e1, is(nullValue()));
  }

}