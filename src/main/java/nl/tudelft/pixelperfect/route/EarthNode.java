package nl.tudelft.pixelperfect.route;

/**
 * A planet Node called Earth which belongs in a Route.
 * 
 * @author Floris Doolaard
 *
 */
public class EarthNode extends RouteNode {

  /**
   * The constructor for the EarthNode.
   * 
   * @param summary
   *          A summary of the EarthNode.
   * @param description
   *          A description of the EarthNode.
   */
  public EarthNode(String summary, String description) {
    super(summary, description);
  }

  /**
   * String representation of an EarthNode.
   * 
   * @return A String.
   */
  public String toString() {
    String res = "EarthNode";
    return res;
  }
}