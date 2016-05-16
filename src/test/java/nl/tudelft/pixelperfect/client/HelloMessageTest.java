package nl.tudelft.pixelperfect.client;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Suite for the (temporary!) Hello Message class.
 * 
 * @author Jesse Tilro
 *
 */
public class HelloMessageTest {

  private HelloMessage object;

  /**
   * Set up a test object.
   */
  @Before
  public void init() {
    object = new HelloMessage("Hello World!");
  }

  /**
   * We can also construct a HelloMessage without a message.
   */
  @Test
  public void testEmptyConstructor() {
    HelloMessage obj = new HelloMessage();
    assertThat(obj.getSomething(), is(nullValue()));
  }

  /**
   * When calling the temporary getSomething method we expect to retrieve the original message.
   */
  @Test
  public void testGetSomething() {
    assertThat(object.getSomething(), is("Hello World!"));
  }

}
