package nl.tudelft.pixelperfect.event;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by woute on 5/10/2016.
 */
public class HostileShipTest extends EventTest{

    public HostileShip createEvent() {
      return new HostileShip(1, "TestEvent", "An Event to test the Class.", 42, 42, 99.42);
    }

}
