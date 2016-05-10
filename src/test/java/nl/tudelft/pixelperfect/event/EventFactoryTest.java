package nl.tudelft.pixelperfect.event;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by woute on 5/10/2016.
 */
public class EventFactoryTest {

  private EventFactory factory;

  @Before
  public void initialize() {
    factory = new EventFactory();
  }

  @Test
  public void testUniqueId() {
    Event e1 = factory.create(0);
    assertEquals(e1.getId(), 0);
    Event e2 = factory.create(0);
    assertEquals(e2.getId(), 1);
  }

  @Test
  public void testCreateAsteroidEvent() {
    Event e1 = factory.create(0);
    assertTrue(e1 instanceof AsteroidField);
  }

  @Test
  public void testCreateFireEvent() {
    Event e1 = factory.create(1);
    assertTrue(e1 instanceof Fire);
  }

  @Test
  public void testCreateHostileShipEvent() {
    Event e1 = factory.create(2);
    assertTrue(e1 instanceof HostileShip);
  }

  @Test
  public void testCreatePlasmaLeakEvent() {
    Event e1 = factory.create(3);
    assertTrue(e1 instanceof PlasmaLeak);
  }

}