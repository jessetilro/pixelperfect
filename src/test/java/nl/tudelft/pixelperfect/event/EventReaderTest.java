package nl.tudelft.pixelperfect.event;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the EventReader class.
 * 
 * @author Jesse Tilro
 *
 */
public class EventReaderTest {

  private EventReader object;

  @Before
  public void init() {
    object = EventReader.getInstance();
  }

  @Test
  public void testReadTypesFromFileNonExistentFile() {
    assertFalse(object.readTypesFromFile("nonexistent_file.json"));
  }

  @Test
  public void testReadTypesFromFileGood() {
    assertTrue(object.readTypesFromFile("src/test/resources/data/events.json"));
  }

}
