package pixelperfect.route;

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
   *          , a summary of the AllyNode.
   * @param description
   *          , a description of the AllyNode.
   */
  public AllyNode(String summary, String description) {
    super(summary, description);
  }

}
