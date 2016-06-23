package nl.tudelft.pixelperfect.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.jme3.network.Filters;
import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.Server;

import nl.tudelft.pixelperfect.client.message.EventCompletedMessage;
import nl.tudelft.pixelperfect.client.message.NewGameMessage;
import nl.tudelft.pixelperfect.client.message.PlayerDetailsMessage;
import nl.tudelft.pixelperfect.client.message.RepairMessage;
import nl.tudelft.pixelperfect.client.message.RoleAllocationMessage;
import nl.tudelft.pixelperfect.event.parameter.EventParameter;
import nl.tudelft.pixelperfect.event.type.EventTypes;
import nl.tudelft.pixelperfect.game.Game;
import nl.tudelft.pixelperfect.player.Player;
import nl.tudelft.pixelperfect.player.PlayerCollection;
import nl.tudelft.pixelperfect.player.PlayerRoles;

/**
 * Listener for the Game's server, which handles incoming messages.
 * 
 * @author David Alderliesten
 * @author Dmitry Malarev
 * @author Jesse Tilro
 *
 */
public class NetworkServerMessageListener implements MessageListener<HostedConnection> {

  private Game app;
  private Server server;

  /**
   * Sets the game whose server to listen for.
   * 
   * @param game
   *          The game.
   */
  public synchronized void setGame(Game game) {
    app = game;
  }

  /**
   * Uses the server of the game to broadcast messages that has come in here.
   * 
   * @param server
   *          The server of the game.
   */
  public synchronized void setServer(Server server) {
    this.server = server;
  }

  /**
   * Functionality for server behavior upon receiving a message.
   * 
   * @param source
   *          The client that sent the message.
   * @param message
   *          The message sent.
   * 
   */
  public synchronized void messageReceived(HostedConnection source, Message message) {
    if (message instanceof EventCompletedMessage) {
      EventCompletedMessage completedMessage = (EventCompletedMessage) message;
      processEventCompletedMessage(completedMessage);
    } else if (message instanceof RoleAllocationMessage) {
      RoleAllocationMessage roleChosen = (RoleAllocationMessage) message;
      processRoleChosen(source, roleChosen);
    } else if (message instanceof PlayerDetailsMessage) {
      PlayerDetailsMessage details = (PlayerDetailsMessage) message;
      processPlayerDetailsMessage(source, details);
    } else if (message instanceof RepairMessage) {
      RepairMessage repairMessage = (RepairMessage) message;
      processRepairs(repairMessage);
    } else if (message instanceof NewGameMessage) {
      NewGameMessage newGameMessage = (NewGameMessage) message;
      processNewGame(source, newGameMessage);
    }
  }

  /**
   * Process a received PlayerDetailsMessage.
   * 
   * @param message
   *          A received PlayerDetailsMessage.
   */
  public synchronized void processPlayerDetailsMessage(HostedConnection connection,
      PlayerDetailsMessage message) {
    Player player = app.getSpaceship().getCrew().getPlayerByConnection(connection);
    player.setName(message.getName());
  }

  /**
   * Process a role chosen message.
   * 
   * @param source
   *          The client that sent the message.
   * 
   * @param message
   *          The message received.
   */
  public synchronized void processRoleChosen(HostedConnection source,
      RoleAllocationMessage message) {
    if (message.isAllocated()) {
      processRoleChosenFree(source, message);
    } else {
      processRoleChosenAssign(source, message);
    }
  }

  /**
   * The received RoleChosenMessage has as purpose to communicate the intent of claiming a player
   * role.
   * 
   * @param source
   *          The connection source.
   * @param message
   *          The RoleChosenMessage.
   */
  public synchronized void processRoleChosenAssign(HostedConnection source,
      RoleAllocationMessage message) {
    PlayerRoles role = message.getRole();
    PlayerCollection crew = app.getSpaceship().getCrew();
    if (crew.hasPlayerWithRole(role)) {
      server.broadcast(Filters.equalTo(source), new RoleAllocationMessage(role, false));
      System.out.println("Role " + role.toString() + " requested, denied.");
    } else {
      crew.getPlayerByConnection(source).assignRole(role);
      server.broadcast(Filters.equalTo(source), new RoleAllocationMessage(role, true));
      System.out.println("Role " + role.toString() + " requested, granted.");
    }
  }

  /**
   * The received RoleChosenMessage has as purpose to communicate the act of freeing a role, making
   * it available to other players again.
   * 
   * @param source
   *          The connection source.
   * @param message
   *          The RoleChosenMessage.
   */
  public synchronized void processRoleChosenFree(HostedConnection source,
      RoleAllocationMessage message) {
    Player player = app.getSpaceship().getCrew().getPlayerByConnection(source);
    PlayerRoles role = player.getRole();
    if (role != null) {
      player.assignRole(null);
      System.out.println("Role " + role.toString() + " was made available again.");
    }
  }

  /**
   * Process the request for an update on whether the game is already in progress.
   * 
   * @param source
   *          The connection requesting the update.
   * @param message
   *          The message send as request.
   */
  public synchronized void processNewGame(HostedConnection source, NewGameMessage message) {
    System.out.println("Received new game request.");
    if (app.getState().isRunning()) {
      System.out.println("Notifying that game has started.");
      server.broadcast(Filters.equalTo(source), new NewGameMessage());
    }
  }

  /**
   * Process a received RepairMessage.
   * 
   * @param message
   *          The received RepairMessage.
   */
  public synchronized void processRepairs(RepairMessage message) {
    System.out.println("Activating repairs.");
    app.getSpaceship().updateHealth(message.getAmount());
  }

  /**
   * Process a received EventCompletedMessage.
   * 
   * @param message
   *          A received EventCompletedMessage.
   */
  public synchronized void processEventCompletedMessage(EventCompletedMessage message) {
    EventTypes type = getType(message);
    Collection<EventParameter> parameters = getParameters(message);
    System.out.println("Received a completed event: " + type.toString());
    app.getSpaceship().getLog().complete(type, parameters, app);
  }

  /**
   * Convert type attribute of message from serializable to more abstract.
   * 
   * @param message
   *          message to extract the attribute from.
   * 
   * @return An Event Type.
   */
  private EventTypes getType(EventCompletedMessage message) {
    return EventTypes.values()[message.getType()];
  }

  /**
   * Convert parameters attribute of message from serializable to more abstract.
   * 
   * @param message
   *          The message to extract the attribute from.
   * 
   * @return A collection of EventParameters.
   */
  private Collection<EventParameter> getParameters(EventCompletedMessage message) {
    Map<String, Integer> structureFrom = message.getParameters();
    Collection<EventParameter> structureTo = new ArrayList<EventParameter>();
    for (Map.Entry<String, Integer> param : structureFrom.entrySet()) {
      structureTo.add(new EventParameter(param.getKey(), param.getValue()));
    }
    return structureTo;
  }
}
