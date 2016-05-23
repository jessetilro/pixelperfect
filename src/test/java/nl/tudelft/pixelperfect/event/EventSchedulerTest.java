package nl.tudelft.pixelperfect.event;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
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
  private static double intensityPercentage = 0.5;
  private double intensityMin;
  private double intensityMax;
  private double percentage;

  private EventListener mockedListener;
  private EventScheduler object;
  private EventScheduler objectVariable;

  /**
   * Construct a new parameterized test suite for the EventScheduler class.
   * 
   * @param intensityMin
   *          The minimum average number of events generated per second by the Poisson process.
   * @param intensityMax
   *          The maximum average number of events generated per second by the Poisson process.
   * @param percentage
   *          The percentage of events the sample mean can deviate from the expected value (after
   *          multiplication with the sample size).
   */
  public EventSchedulerTest(double intensityMin, double intensityMax, double percentage) {
    this.intensityMin = intensityMin;
    this.intensityMax = intensityMax;
    this.percentage = percentage;
  }

  /**
   * Set up test object with mocked dependencies.
   */
  @Before
  public void init() {
    object = new EventScheduler(intensityMin);
    objectVariable = new EventScheduler(intensityMin, intensityMax);
    mockedListener = mock(EventLog.class);

    object.subscribe(mockedListener);
    objectVariable.subscribe(mockedListener);
  }

  /**
   * When the (fixed intensity) scheduler is started, the number of events it will subsequently
   * schedule must be within an expected range. Furthermore updating the intensity on such a
   * scheduler should have no effect.
   */
  @Test
  public void testUpdateStart() {
    // Fixtures
    double expectedValue = intensityMin * duration;
    int expectedEvents = (int) (expectedValue * sampleSize);
    int allowedDeviation = (int) (percentage * expectedEvents);

    // Execution
    object.start();
    object.updateIntensity(0.42);
    execute(object);

    // Verification
    verify(mockedListener, atLeast(expectedEvents - allowedDeviation)).notify(any(Event.class));
    verify(mockedListener, atMost(expectedEvents + allowedDeviation)).notify(any(Event.class));
  }

  /**
   * When the (fixed intensity) scheduler is stopped, the number of events it will subsequently
   * schedule must be 0, regardless of the intensity and time interval.
   */
  @Test
  public void testUpdateStop() {
    // Execution
    object.start();
    object.stop();
    execute(object);

    // Verification
    verify(mockedListener, times(0)).notify(any(Event.class));
  }

  /**
   * When updating the intensity on a variable intensity scheduler with a percentage lower than zero
   * percent, it should use its predefined minimum intensity.
   */
  @Test
  public void testUpdateIntensityLessThanZero() {
    // Fixtures
    double expectedValue = intensityMin * duration;
    int expectedEvents = (int) (expectedValue * sampleSize);
    int allowedDeviation = (int) (percentage * expectedEvents);

    // Execution
    objectVariable.updateIntensity(-0.42);
    objectVariable.start();
    execute(objectVariable);

    // Verification
    verify(mockedListener, atLeast(expectedEvents - allowedDeviation)).notify(any(Event.class));
    verify(mockedListener, atMost(expectedEvents + allowedDeviation)).notify(any(Event.class));
  }

  /**
   * When updating the intensity on a variable intensity scheduler with a percentage greater than
   * one hundred percent, it should use its predefined maximum intensity.
   */
  @Test
  public void testUpdateIntensityGreaterThanOneHundred() {
    // Fixtures
    double expectedValue = intensityMax * duration;
    int expectedEvents = (int) (expectedValue * sampleSize);
    int allowedDeviation = (int) (percentage * expectedEvents);

    // Execution
    objectVariable.updateIntensity(1.42);
    objectVariable.start();
    execute(objectVariable);

    // Verification
    verify(mockedListener, atLeast(expectedEvents - allowedDeviation)).notify(any(Event.class));
    verify(mockedListener, atMost(expectedEvents + allowedDeviation)).notify(any(Event.class));
  }

  /**
   * When updating the intensity on a variable intensity scheduler with a valid percentage between
   * zero and one hundred percent, it should linearly interpolate correctly between minimum and
   * maximum intensity.
   */
  @Test
  public void testUpdateIntensity() {
    // Fixtures
    double targetIntensity = intensityMin + intensityPercentage * (intensityMax - intensityMin);
    double expectedValue = targetIntensity * duration;
    int expectedEvents = (int) (expectedValue * sampleSize);
    int allowedDeviation = (int) (percentage * expectedEvents);

    // Execution
    objectVariable.updateIntensity(intensityPercentage);
    objectVariable.start();
    execute(objectVariable);

    // Verification
    verify(mockedListener, atLeast(expectedEvents - allowedDeviation)).notify(any(Event.class));
    verify(mockedListener, atMost(expectedEvents + allowedDeviation)).notify(any(Event.class));
  }

  /**
   * Execute the code under test.
   * 
   * According to the law of large numbers, as the sample size of a random sample approaches
   * infinity, the deviation of the sample mean from the distribution's expected value will approach
   * 0. That's what we will use to test the Poisson process, while testing it with different
   * parameters.
   */
  private void execute(EventScheduler object) {
    for (int i = 0; i < sampleSize; i++) {
      object.update(duration);
    }
  }

  /**
   * Create the parameters to run the test suite with.
   * 
   * @return A list of parameters.
   */
  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] { { 0, 0, 0 }, { 0.2, 0.4, 0.2 }, { 0.25, 0.5, 0.2 },
        { 0.5, 0.75, 0.2 }, { 0.75, 0.8, 0.2 }, { 0.9, 0.95, 0.2 } });
  }

}
