package nl.tudelft.pixelperfect.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import nl.tudelft.pixelperfect.event.parameter.EventParameter;
import nl.tudelft.pixelperfect.event.type.AsteroidImpactEvent;
import nl.tudelft.pixelperfect.event.type.EventTypes;
import nl.tudelft.pixelperfect.event.type.FireOutbreakEvent;
import nl.tudelft.pixelperfect.game.Spaceship;

/**
 * Test Suite for the EventLog class.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
@SuppressWarnings("PMD")
public class EventLogTest extends EventListenerTest {
  private EventLog object;
  private Spaceship mockedSpaceship;

  /**
   * Setting test object and mocked dependencies.
   */
  @Before
  public void initialise() {
    mockedSpaceship = mock(Spaceship.class);
    object = new EventLog(mockedSpaceship);
  }

  /**
   * Initially the log should be empty.
   */
  @Test
  public void testGetEventsEmpty() {
    assertTrue(object.getEvents().isEmpty());
  }

  /**
   * Test the spaceship getter, that it is equal to the given spaceship at the start.
   */
  @Test
  public void testGetSpaceship() {
    assertEquals(mockedSpaceship, object.getSpaceship());
  }

  /**
   * When the log is notified of an event, it should add it to its list of events.
   */
  @Test
  public void testNotify() {
    Event evt1 = new FireOutbreakEvent(0, "Lorem", "Ipsum", 0, 0, 50);
    object.notify(evt1);
    assertTrue(object.getEvents().contains(evt1));
  }

  /**
   * Checks the complete method of an existing event in the log.
   * 
   */
  @Test
  public void testCompleteExisting() {
    Event evt1 = new FireOutbreakEvent(0, "Lorem", "Ipsum", 0, 0, 50);
    object.getEvents().add(evt1);
    object.complete(EventTypes.FIRE_OUTBREAK, new ArrayList<EventParameter>());
    assertEquals(0, object.getEvents().size());
  }

  /**
   * Checks the complete method of an non-existing event in the log.
   * 
   */
  @Test
  public void testCompleteMissing() {
    Event evt1 = new FireOutbreakEvent(0, "Lorem", "Ipsum", 0, 0, 50);
    object.getEvents().add(evt1);
    object.complete(EventTypes.ASTEROID_IMPACT, new ArrayList<EventParameter>());
    assertEquals(1, object.getEvents().size());
  }

  /**
   * When an Event is discarded from the log, it should be removed from the list of active events
   * without damaging the ship.
   */
  @Test
  public void testDiscard() {
    Event evt1 = new FireOutbreakEvent(0, "Apple", "Banana", 0, 0, 50);
    object.notify(evt1);
    object.discard(evt1);
    assertFalse(object.getEvents().contains(evt1));
    verify(mockedSpaceship, never()).updateHealth(anyDouble());
  }

  /**
   * Expired events must be discarded from the log, whereas events that have not expired yet must
   * remain active.
   */
  @Test
  public void testUpdate() {
    Event evt1 = new FireOutbreakEvent(0, "Whale", "Shark", 0, 0, 50);
    Event evt2 = new AsteroidImpactEvent(1, "Pie", "Cake", System.currentTimeMillis(), 99999999,
        50);
    object.notify(evt1);
    object.notify(evt2);

    object.update();
    ArrayList<Event> log = object.getEvents();

    assertFalse(log.contains(evt1));
    assertTrue(log.contains(evt2));
    verify(mockedSpaceship).updateHealth(-50);
  }

  /**
   * When the log's list of events is replaced, the list is updated and only Events from the
   * replacement that have not expired yet will be in the list of active Events.
   */
  @Test
  public void testReplace() {
    Event evt1 = new FireOutbreakEvent(0, "Mango", "Pineapple", 0, 0, 50);
    Event evt2 = new AsteroidImpactEvent(1, "Pear", "Kiwi Fruit", System.currentTimeMillis(),
        99999999, 50);
    ArrayList<Event> events = new ArrayList<Event>();
    events.add(evt1);
    events.add(evt2);

    object.replace(events);
    ArrayList<Event> log = object.getEvents();

    assertFalse(log.contains(evt1));
    assertTrue(log.contains(evt2));
    verify(mockedSpaceship).updateHealth(-50);
  }
}
