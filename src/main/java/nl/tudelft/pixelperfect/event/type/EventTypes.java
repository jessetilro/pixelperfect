package nl.tudelft.pixelperfect.event.type;

import com.jme3.math.ColorRGBA;
import nl.tudelft.pixelperfect.event.factory.AsteroidImpactEventFactory;
import nl.tudelft.pixelperfect.event.factory.CoffeeBoostEventFactory;
import nl.tudelft.pixelperfect.event.factory.EventFactory;
import nl.tudelft.pixelperfect.event.factory.FireOutbreakEventFactory;
import nl.tudelft.pixelperfect.event.factory.HostileShipEventFactory;
import nl.tudelft.pixelperfect.event.factory.PlasmaLeakEventFactory;
import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.game.Scene;

/**
 * Enumeration of the different types of Events in the game. The ordinals, i.e. the indices of the
 * types in this enumeration, correspond to the numeric type identifiers of the Event types
 * described in the Event Data File. Keep that in mind when updating this enumeration!
 * 
 * @author Jesse Tilro
 *
 */
public enum EventTypes {
  FIRE_OUTBREAK {
    @Override
    public EventFactory getFactory() {
      return new FireOutbreakEventFactory();
    }

    @Override
    public void resetNotification(Game game) {
      game.getScene().getFireEventLabel().setText("");
      game.getAudioPlayer().stopSound("FireEvent");
    }
  },
  PLASMA_LEAK {
    @Override
    public EventFactory getFactory() {
      return new PlasmaLeakEventFactory();
    }

    @Override
    public void resetNotification(Game game) {
      game.getScene().getPlasmaEventLabel().setText("");
      game.getAudioPlayer().stopSound("PlasmaEvent");
    }
  },
  ASTEROID_IMPACT {
    @Override
    public EventFactory getFactory() {
      return new AsteroidImpactEventFactory();
    }

    @Override
    public void resetNotification(Game game) {
      game.getScene().getAsteroidEventLabel().setText("");
      game.getAudioPlayer().stopSound("AsteroidEvent");
    }
  },
  HOSTILE_SHIP {
    @Override
    public EventFactory getFactory() {
      return new HostileShipEventFactory();
    }

    @Override
    public void resetNotification(Game game) {
      game.getScene().getHostileEventText().setText("x: " + "\ny: " + "\n");
      game.getAudioPlayer().stopSound("HostileEvent");
    }
  },
  COFFEE_BOOST {
    @Override
    public EventFactory getFactory() {
      return new CoffeeBoostEventFactory();
    }

    @Override
    public void resetNotification(Game game) {
      game.getScene().getLight().setColor(ColorRGBA.White);
      game.getAudioPlayer().stopSound("CoffeeEvent");
    }
  };

  /**
   * Get the Factory to be used for constructing the specific type of Event.
   * 
   * @return An EventFactory.
   */
  public abstract EventFactory getFactory();

  /**
   * Method to reset the notification.
   *
   * @param game
   *            Game in which to reset the notification.
   */
  public abstract void resetNotification(Game game);
}
