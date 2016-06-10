package nl.tudelft.pixelperfect.client;

import static org.junit.Assert.assertEquals;

import nl.tudelft.pixelperfect.client.message.RepairMessage;

import org.junit.Test;

/**
 * Test case for the RepairMessage Class.
 * 
 * @author Dmitry
 *
 */
public class RepairMessageTest {

  /**
   * Tests the getAmount method.
   * 
   */
  @Test
  public void testRepairMessage() {
    RepairMessage toTest = new RepairMessage();
    assertEquals(2, toTest.getAmount());
  }

}
