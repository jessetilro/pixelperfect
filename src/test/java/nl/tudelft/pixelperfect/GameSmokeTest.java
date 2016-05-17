package nl.tudelft.pixelperfect;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Test Suite for the Game class.
 * 
 * @author Jesse Tilro
 *
 */

@SuppressWarnings("PMD")
public class GameSmokeTest {

  /**
   * Increase the test coverage by running the Game class methods inside a test case. There is
   * little functionality to test here, just integration with the jMonkeyEngine.
   * 
   * Added the nice threading in order to be able to kill the application.
   * 
   * @throws InterruptedException
   *           Since we're sleeping in this test.
   * @throws AWTException
   *           Since we're using the Robot.
   */
  @Ignore
  @Test
  public void increaseTestCoverage() throws InterruptedException, AWTException {
    Thread t = new Thread() {
      public void run() {
        String[] args = {};
        Game.main(args);
      }
    };
    Robot robot = new Robot();
    t.start();
    Thread.sleep(500);
    robot.keyPress(KeyEvent.VK_ENTER);
    Thread.sleep(1500);
    Thread runTest = t;
    t = null;
    runTest.interrupt();
  }

}
