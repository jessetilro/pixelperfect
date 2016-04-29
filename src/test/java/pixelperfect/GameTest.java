package pixelperfect;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Class containing test cases for the Game/launches class.
 * 
 * @author David Alderliesten
 *
 */
public class GameTest {
	@Test
	public void test() {
		Game toTest = new Game();
		toTest.destroy();
	}
}
