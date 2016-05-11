package nl.tudelft.pixelperfect.client;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 * Example message that can be sent between client and server. Note that this exact same class has
 * to be present and registered in the client application as well, including the exact package name.
 * 
 * @author David Alderliesten
 * @author Jesse Tilro
 *
 */
@Serializable
public class HelloMessage extends AbstractMessage {
  private String hello;

  /**
   * The constructor of the hello message.
   * 
   */
  public HelloMessage() {
  }

  /**
   * Changes the hello message.
   * 
   * @param passedValue
   *          the string that will be the new hello message.
   */
  public HelloMessage(String passedValue) {
    hello = passedValue;
  }

  /**
   * Returns the helo message.
   * 
   * @return the message
   */
  public String getSomething() {
    return hello;
  }
}
