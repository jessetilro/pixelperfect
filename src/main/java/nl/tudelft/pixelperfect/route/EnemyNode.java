package nl.tudelft.pixelperfect.route;

/**
 * An enemy ship Node which belongs in a Route. The captain will have to work together with the crew
 * to eliminate the enemy.
 * 
 * @author Floris Doolaard
 *
 */
public class EnemyNode extends RouteNode {

  /**
   * The constructor for the EnemyNode.
   * 
   * @param summary
   *          a summary of the EnemyNode.
   * @param description
   *          a description of the EnemyNode.
   */
  public EnemyNode(String summary, String description) {
    super(summary, description);
  }
}
