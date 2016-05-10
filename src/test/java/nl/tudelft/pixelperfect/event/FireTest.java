package nl.tudelft.pixelperfect.event;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by woute on 5/10/2016.
 */
public class FireTest extends EventTest{

    @Override
    public Fire createEvent() {
        return new Fire(1, "TestEvent", "An Event to test the Class.", 42, 42, 99.42);
    }

}