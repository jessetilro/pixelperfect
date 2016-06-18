package nl.tudelft.pixelperfect.player;

import java.util.HashMap;
import java.util.Map;

import com.jme3.network.HostedConnection;

import nl.tudelft.pixelperfect.event.Event;

/**
 * Aggregates the (crew) players currently playing the game.
 * 
 * @author Jesse Tilro
 *
 */
public class PlayerCollection {

  private Map<HostedConnection, Player> map;

  /**
   * Construct a new collection of Players.
   */
  public PlayerCollection() {
    map = new HashMap<HostedConnection, Player>();
  }

  /**
   * Add a player to the collection.
   * 
   * @param player
   *          The player to add.
   */
  public synchronized void addPlayer(Player player) {
    map.put(player.getConnection(), player);
  }

  /**
   * Get the player from the collection that is connected with the game by the given connection.
   * 
   * @param connection
   *          The connection through which the player is connected to the game.
   * 
   * @return The Player with the given connection.
   */
  public synchronized Player getPlayerByConnection(HostedConnection connection) {
    return map.get(connection);
  }

  /**
   * Remove a Player from the collection by the connection it used to join the game.
   * 
   * @param connection
   *          The HostedConnection through which the player was connected.
   */
  public synchronized void removePlayerByConnection(HostedConnection connection) {
    map.remove(connection);
  }

  /**
   * Get a Player from the collection that has the given role assigned.
   * 
   * @param role
   *          The role to look for.
   * @return The Player with the given role, or null if there is no such Player in the collection.
   */
  public synchronized Player getPlayerByRole(PlayerRoles role) {
    for (Player player : map.values()) {
      if (player.getRole() == role) {
        return player;
      }
    }
    return null;
  }

  /**
   * Check whether the collection contains a Player that has a given role assigned.
   * 
   * @param role
   *          The role to look for.
   * @return Whether there is a Player with the given role.
   */
  public synchronized boolean hasPlayerWithRole(PlayerRoles role) {
    return (getPlayerByRole(role) != null);
  }

  /**
   * Check whether this collection contains a Player who can solve a given Event.
   * 
   * @param event
   *          The Event to be solved.
   * @return Whether there is a Player who can solve the Event.
   */
  public synchronized boolean hasPlayerWhoCanSolveEvent(Event event) {
    for (Player player : map.values()) {
      if (player.canSolveEvent(event)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Get the number of players the collection consists of.
   * 
   * @return The number of players.
   */
  public int size() {
    return map.size();
  }

}
