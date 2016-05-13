package nl.tudelft.pixelperfect.route;

/**
 * A planet Node called Mars which belongs in a Route.
 * 
 * @author Floris Doolaard
 *
 */
public class MarsNode extends RouteNode {

  /**
   * The constructor for the MarsNode.
   * 
   * @param summary
   *          A summary of the MarsNode.
   * @param description
   *          A description of the MarsNode.
   */
  public MarsNode(String summary, String description) {
    super(summary, description);
  }

  /**
   * String representation of a MarsNode.
   * 
   * @return A String.
   */
  public String toString() {
    String res = "MarsNode";
    return res;
  }
}
