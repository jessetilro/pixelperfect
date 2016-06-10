package nl.tudelft.pixelperfect.client.message;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 * A message Class used when the repair mini-game is complete.
 * 
 * @author Dmitry
 *
 */
@Serializable
public class RepairMessage extends AbstractMessage {
  
  private int amount;

  /**
   * The empty constructor for the message.
   */
  public RepairMessage() {
    amount = 2;
  }

  /**
   * Returns the amount of health the ship should repair.
   * 
   * @return , the amount of health.
   */
  public int getAmount() {
    return amount;
  }

}
