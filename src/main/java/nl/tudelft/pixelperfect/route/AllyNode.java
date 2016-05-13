package nl.tudelft.pixelperfect.route;

/**
 * An allied ship Node which belongs in a Route. The AllyNode represents an allied ship in need of
 * help.
 * 
 * @author Floris Doolaard
 *
 */
public class AllyNode extends RouteNode {

  /**
   * The constructor for the AllyNode.
   * 
   * @param summary
   *          A summary of the AllyNode.
   * @param description
   *          A description of the AllyNode.
   */
  public AllyNode(String summary, String description) {
    super(summary, description);
  }

  /**
   * String representation of an AllyNode.
   * 
   * @return A String.
   */
  public String toString() {
    String res = "AllyNode";
    return res;
  }
}
