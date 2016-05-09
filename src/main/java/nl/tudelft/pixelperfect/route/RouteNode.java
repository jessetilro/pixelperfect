package nl.tudelft.pixelperfect.route;

/**
 * Node along a route through space.
 * 
 * @author Jesse Tilro
 * @author Floris Doolaard
 *
 */
public class RouteNode {

  private String summary;
  private String description;

  /**
   * A constructor for the RouteNode.
   * 
   * @param summary
   *          , a summary about the RouteNode.
   * @param description
   *          , a description about the RouteNode.
   */
  public RouteNode(String summary, String description) {
    this.summary = summary;
    this.description = description;
  }

  /**
   * Retrieves the summary of the Node.
   * 
   * @return a summary
   */
  public String getSummary() {
    return this.summary;
  }

  /**
   * Retrieves the description of the Node.
   * 
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }
}
