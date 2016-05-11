package nl.tudelft.pixelperfect.event;

/**
 * Created by woute on 5/10/2016.
 */
public class FireEventTest extends EventTest {

    @Override
    public FireEvent createEvent() {
        return new FireEvent(1, "TestEvent", "An Event to test the Class.", 42, 42, 99.42);
    }

}