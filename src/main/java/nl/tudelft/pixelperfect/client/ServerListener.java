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
import nl.tudelft.pixelperfect.client.message.RepairMessage;
import nl.tudelft.pixelperfect.client.message.RoleChosenMessage;
import nl.tudelft.pixelperfect.event.parameter.EventParameter;
import nl.tudelft.pixelperfect.event.type.EventTypes;
import nl.tudelft.pixelperfect.game.Game;
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
public class ServerListener implements MessageListener<HostedConnection> {

  private Game app;
  private Server server;

  /**
   * Sets the game whose server to listen for.
   * 
   * @param game
   *          The game.
   */
  public void setGame(Game game) {
    app = game;
  }

  /**
   * Returns the game for reference purposes.
   * 
   * @return The game.
   */
  public Game getGame() {
    return app;
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
    } else if (message instanceof RoleChosenMessage) {
      RoleChosenMessage roleChosen = (RoleChosenMessage) message;
      processRoleChosen(source, roleChosen);
    } else if (message instanceof RepairMessage) {
      RepairMessage repairMessage = (RepairMessage) message;
      processRepairs(repairMessage);
    }
  }

  /**
   * Process a role chosen message.
   */
  public synchronized void processRoleChosen(HostedConnection source, RoleChosenMessage message) {
    PlayerRoles role = message.getRole();
    PlayerCollection crew = app.getSpaceship().getCrew();
    if (crew.hasPlayerWithRole(role)) {
      server.broadcast(Filters.equalTo(source), new RoleChosenMessage(role, false));
      System.out.println("Role " + role.toString() + " requested, denied.");
    } else {
      crew.getPlayerByConnection(source).assignRole(role);
      server.broadcast(Filters.equalTo(source), new RoleChosenMessage(role, true));
      System.out.println("Role " + role.toString() + " requested, granted.");
    }
  }

  /**
   * Process a recieved RepairMessage.
   * 
   * @param message
   *          , The received RepairMessage.
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
    app.getSpaceship().getLog().complete(type, parameters);
  }

  /**
   * Convert type attribute of message from serializable to more abstract.
   * 
   * @param The
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
