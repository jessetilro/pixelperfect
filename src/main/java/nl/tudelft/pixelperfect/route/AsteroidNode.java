package nl.tudelft.pixelperfect.route;

/**
 * An asteroid field Node which belongs in a Route. The asteroid field will be a dangerous location
 * to fly in.
 * 
 * @author Floris Doolaard
 *
 */
public class AsteroidNode extends RouteNode {

  /**
   * The constructor for the AsteroidNode.
   * 
   * @param summary
   *          , a summary of the AsteroidNode.
   * @param description
   *          , a description of the AsteroidNode.
   */
  public AsteroidNode(String summary, String description) {
    super(summary, description);
  }
  
  /**
   * String representation of an AsteroidNode.
   * @return A String.
   */
  public String toString() {
    String res = "AsteroidNode";
    return res;
  }

}
