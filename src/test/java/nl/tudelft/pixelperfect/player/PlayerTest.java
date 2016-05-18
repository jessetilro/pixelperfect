package nl.tudelft.pixelperfect.player;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Class for the automated testing of the Player class.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public abstract class PlayerTest {

  private Player testObject;

  /**
   * Setting up the Player class as a test object.
   */
  @Before
  public void initialize() {
    testObject = createPlayer("Lorem Ipsum");
  }

  /**
   * Factory method for testing.
   * 
   * @param name
   *          , the name of the Player.
   * @return The object to be tested.
   */
  public abstract Player createPlayer(String name);

  /**
   * When calling getName the correct player name must be returned.
   */
  @Test
  public void testGetName() {
    assertEquals(testObject.getName(), "Lorem Ipsum");
  }

  /**
   * Two players with the same name should be considered equal.
   */
  @Test
  public void testEqualsTrue() {
    Player otherTest = createPlayer("Lorem Ipsum");
    assertEquals(testObject, otherTest);
  }

  /**
   * Two players with a different name should not be considered equal.
   */
  @Test
  public void testEqualsFalseName() {
    Player otherTest = createPlayer("Foo Bar");
    assertFalse(testObject.equals(otherTest));
  }

  /**
   * An object that is not of the type Player cannot be considered equal to a player.
   */
  @Test
  public void testEqualsFalseType() {
    ArrayList<Player> otherTest = new ArrayList<Player>();
    assertFalse(testObject.equals(otherTest));
  }

  /**
   * Two different player instances should have different hash codes. Since the hash code method is
   * not implemented yet however, this test case is stubbed.
   */
  @Test
  public void testHashCode() {
    assertThat(testObject.hashCode(), equalTo(42));
  }

}
