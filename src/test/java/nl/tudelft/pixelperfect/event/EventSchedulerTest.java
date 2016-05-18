package nl.tudelft.pixelperfect.event;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Test Suite for the EventScheduler class.
 * 
 * @author Jesse Tilro
 *
 */
@SuppressWarnings("PMD")
@RunWith(Parameterized.class)
public class EventSchedulerTest {

  private static int sampleSize = 20000;
  private static float duration = 4;
  private double intensity;
  private double percentage;

  private EventListener mockedListener;
  private EventScheduler object;

  /**
   * Construct a new parameterized test suite for the EventScheduler class.
   * 
   * @param intensity
   *          The average number of events generated per second by the Poisson process.
   * @param percentage
   *          The percentage of events the sample mean can deviate from the expected value (after
   *          multiplication with the sample size).
   */
  public EventSchedulerTest(double intensity, double percentage) {
    this.intensity = intensity;
    this.percentage = percentage;
  }

  /**
   * Set up test object with mocked dependencies.
   */
  @Before
  public void init() {
    object = new EventScheduler(intensity);
    mockedListener = mock(EventLog.class);

    object.subscribe(mockedListener);
  }

  /**
   * According to the law of large numbers, as the sample size of a random sample approaches
   * infinity, the deviation of the sample mean from the distribution's expected value will approach
   * 0. That's what we will use to test the Poisson process, while testing it with different
   * parameters.
   */
  @Test
  public void testUpdate() {
    double expectedValue = intensity * duration;
    int expectedEvents = (int) (expectedValue * sampleSize);
    int allowedDeviation = (int) (percentage * expectedEvents);
    for (int i = 0; i < sampleSize; i++) {
      object.update(duration);
    }
    verify(mockedListener, atLeast(expectedEvents - allowedDeviation)).notify(any(Event.class));
    verify(mockedListener, atMost(expectedEvents + allowedDeviation)).notify(any(Event.class));
  }

  /**
   * Create the parameters to run the test suite with.
   * 
   * @return A list of parameters.
   */
  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] { { 0, 0 }, { 0.2, 0.2 }, { 0.25, 0.2 }, { 0.5, 0.2 },
        { 0.75, 0.2 }, { 0.9, 0.2 } });
  }

}
