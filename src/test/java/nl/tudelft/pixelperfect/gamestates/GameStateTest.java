package nl.tudelft.pixelperfect.gamestates;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by woute on 5/24/2016.
 */
public abstract class GameStateTest {

  @Test
  public abstract void update();

  @Test
  public abstract void handleState();

}