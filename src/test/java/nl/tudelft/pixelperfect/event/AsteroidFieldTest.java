package nl.tudelft.pixelperfect.event;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by woute on 5/10/2016.
 */
public class AsteroidFieldTest extends EventTest {

    @Override
    public AsteroidField createEvent() {
        return new AsteroidField(1, "TestEvent", "An Event to test the Class.", 42, 42, 99.42);
    }

}
