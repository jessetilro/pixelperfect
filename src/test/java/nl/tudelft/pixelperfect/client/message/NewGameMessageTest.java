package nl.tudelft.pixelperfect.client.message;

import org.junit.Test;
import static org.junit.Assert.assertNotEquals;

/**
 * The test class for the the NewGameMessage.
 * 
 * @author Floris Doolaard
 *
 */
public class NewGameMessageTest {
  
  /**
   * Tests the constructor to be not null as it has no other parameters.
   */
  @Test
  public void testNewGameMessage() {
    NewGameMessage message = new NewGameMessage();
    assertNotEquals(null, message);
  }
}
