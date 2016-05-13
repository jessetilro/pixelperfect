package nl.tudelft.pixelperfect.route;

/**
 * A utility class to create tuples mainly or RouteNodes. This will be used for the two-choice that
 * will be made in the game.
 * 
 * @author Floris Doolaard
 *
 */
public class Tuple {

  private RouteNode left;
  private RouteNode right;

  /**
   * The constructor for a Tuple. It takes two RouteNodes which represent a two-choice.
   * 
   * @param left
   *          , the first RouteNode choice.
   * @param right
   *          , the second RouteNode choice.
   */
  public Tuple(RouteNode left, RouteNode right) {
    this.left = left;
    this.right = right;
  }

  /**
   * Get the first element of the Tuple.
   * 
   * @return a RouteNode.
   */
  public RouteNode getLeft() {
    return left;
  }

  /**
   * Get the second element of the Tuple.
   * 
   * @return a RouteNode.
   */
  public RouteNode getRight() {
    return right;
  }

  /**
   * The String representation of the Tuple.
   * 
   * @return A String.
   */
  public String toString() {
    String res = "(";
    res = res + left.toString() + ", " + right.toString() + ")";
    return res;
  }
}
