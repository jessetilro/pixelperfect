package nl.tudelft.pixelperfect.event;

/**
 * Created by woute on 5/10/2016.
 */
public class PlasmaLeakTest extends EventTest {

    /**
     * Factory method for testing.
     * @return PlasmaLeak class
     */
    public PlasmaLeak createEvent() {
        return new PlasmaLeak(1, "TestEvent", "An Event to test the Class.", 42, 42, 99.42);
    }

}