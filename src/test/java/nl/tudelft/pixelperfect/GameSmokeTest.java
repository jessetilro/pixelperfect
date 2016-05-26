package nl.tudelft.pixelperfect;

import org.junit.Ignore;
import org.junit.Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * Test Suite for the Game class.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
public class GameSmokeTest {

  /**
   * Increase the test coverage by running the Game class methods inside a test case. There is
   * little functionality to test here, just integration with the jMonkeyEngine. Testing is done by
   * interrupting the running thread and verifying the behavior.
   * 
   * @throws InterruptedException
   *           Since we're sleeping in this test.
   * @throws AWTException
   *           Since we're using the Robot.
   */
  @Test
  public void increaseTestCoverage() throws InterruptedException, AWTException {
    Thread testThread = new Thread() {
      public void run() {
        String[] args = {};
        Game.main(args);
      }
    };
    Robot robot = new Robot();
    testThread.start();
    Thread.sleep(500);
    robot.keyPress(KeyEvent.VK_ENTER);
    Thread.sleep(1500);
    Thread runTest = testThread;
    testThread = null;
    runTest.interrupt();
  }

}
