package nl.tudelft.pixelperfect.event;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the EventReader class.
 * 
 * @author Jesse Tilro
 *
 */
@SuppressWarnings("PMD")
public class EventReaderTest {

  private static String fileGood = "src/test/resources/data/events.json";
  private static String fileBad = "src/test/resources/data/events_invalid.json";
  private EventReader object;

  /**
   * Set up the test object.
   */
  @Before
  public void init() {
    object = EventReader.getInstance();
    object.clear();
  }

  /**
   * When loading a nonexistent file, the readFromFile method should fail and return false.
   */
  @Test
  public void testReadFromFileNonExistent() {
    assertFalse(object.readFromFile("nonexistent_file.json"));
  }

  /**
   * When loading a file that is not properly JSON encoded (e.g. containing syntax errors), the
   * readFromFile method should fail and throw an exception.
   */
  @Test(expected = JSONException.class)
  public void testReadFromFileInvalidSyntax() {
    assertFalse(object.readFromFile(fileBad));
  }

  /**
   * When loading a properly JSON encoded file, the readFromFile method should succeed and return
   * true.
   */
  @Test
  public void testReadFromFileGood() {
    assertTrue(object.readFromFile(fileGood));
  }

  /**
   * When reading a String that is not properly JSON encoded, the readFromString method should fail
   * and throw an exception.
   */
  @Test(expected = JSONException.class)
  public void testReadFromStringInvalidSyntax() {
    object.readFromString("}{");
  }

  /**
   * When reading a String that is properly JSON encoded, the readFromString method should succeed.
   */
  @Test
  public void testReadFromStringGood() {
    object.readFromString("[{\"type\": 1}]");
    assertTrue(object.has(1));
  }

  /**
   * When calling the getSummary method, it should return the correct summary of the Event type with
   * the specified type identifier.
   */
  @Test
  public void testGetSummary() {
    object.readFromFile(fileGood);
    assertEquals("Banana", object.getSummary(1));
  }

  /**
   * When calling the getDescription, it should return the correct summary of the Event type with
   * the specified type identifier.
   */
  @Test
  public void testGetDescription() {
    object.readFromFile(fileGood);
    assertEquals("Swims in the ocean.", object.getDescription(2));
  }

  /**
   * When calling some getString dependent method with an invalid type identifier, it should yield
   * null.
   */
  @Test
  public void testGetStringOfInvalidType() {
    object.readFromFile(fileGood);
    assertThat(object.getSummary(-1), is(nullValue()));
  }

  /**
   * When calling the getDamage, it should return the correct summary of the Event type with the
   * specified type identifier.
   */
  @Test
  public void testGetDamage() {
    object.readFromFile(fileGood);
    assertEquals(42.0, object.getDamage(1), 0.0);
  }

  /**
   * When calling the getDamage method with an invalid type identifier, it should yield 0.
   */
  @Test
  public void testGetDamageInvalid() {
    object.readFromFile(fileGood);
    assertEquals(0.0, object.getDamage(-1), 0.0);
  }

  /**
   * When calling the getDuration, it should return the correct summary of the Event type with the
   * specified type identifier.
   */
  @Test
  public void testGetDuration() {
    object.readFromFile(fileGood);
    assertEquals(1337, object.getDuration(2));
  }

  /**
   * When calling the getDuration method with an invalid type identifier, it should yield 0.
   */
  @Test
  public void testGetDurationInvalid() {
    object.readFromFile(fileGood);
    assertEquals(0, object.getDuration(-1));
  }

}
