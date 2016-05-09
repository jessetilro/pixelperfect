package nl.tudelft.pixelperfect.client;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

@Serializable
/**
 * Example message that can be sent between client and server. Note that this exact same class has
 * to be present and registered in the client application as well, including the exact package name.
 * 
 * @author Jesse Tilro
 *
 */
public class HelloMessage extends AbstractMessage {
  private String hello; // custom message data

  public HelloMessage() {
  }

  public HelloMessage(String s) {
    hello = s;
  }

  public String getSomething() {
    return hello;
  }
}
