package nl.tudelft.pixelperfect;

import java.util.ArrayList;
import java.util.Collection;

import com.jme3.network.HostedConnection;

import nl.tudelft.pixelperfect.event.EventListener;
import nl.tudelft.pixelperfect.event.EventLog;
import nl.tudelft.pixelperfect.player.CrewPlayer;
import nl.tudelft.pixelperfect.player.Player;
import nl.tudelft.pixelperfect.route.Route;
import nl.tudelft.pixelperfect.route.RouteGenerator;

/**
 * The spaceship the players are controlling and guiding along a given route through space.
 * 
 * @author Jesse Tilro
 * @author Floris Doolaard
 *
 */
public class Spaceship {

  private static final int STARTING_HEALTH = 100;

  private int score;
  private double health;
  private Route route;
  private EventListener log;
  private float timer;
  private boolean victorious;
  private ArrayList<Player> crew;

  /**
   * Construct a new Spaceship instance.
   */
  public Spaceship() {
    this.score = 0;
    this.health = STARTING_HEALTH;
    this.victorious = false;
    RouteGenerator rg = RouteGenerator.getInstance();
    this.route = rg.generateRoute();
    this.log = new EventLog(this);
    this.timer = 0;
    this.crew = new ArrayList<Player>();
  }

  /**
   * Use the given EventListener as the captain's log.
   * 
   * @param log
   *          The EventListener to use.
   */
  public void useLog(EventListener log) {
    this.log = log;
  }

  /**
   * Get the spaceship's log of events.
   * 
   * @return An EventListener.
   */
  public EventListener getLog() {
    return log;
  }

  /**
   * Get the spaceship's list of crew members.
   * 
   * @return A list of Players.
   */
  public ArrayList<Player> getCrew() {
    return crew;
  }

  /**
   * Let the ship pursue a given Route.
   * 
   * @param route
   *          The route the ship must follow.
   */
  public void followRoute(Route route) {
    this.route = route;
  }

  /**
   * Update the ship's health status.
   * 
   * @param delta
   *          The amount to increment or decrement the health value with.
   */
  public void updateHealth(double delta) {
    this.health += delta;
    if (this.health < 0) {
      this.health = 0;
    }
  }

  /**
   * Get the ship's health.
   * 
   * @return The ship's current health value.
   */
  public double getHealth() {
    return this.health;
  }

  /**
   * Checks if the ship has 0 health.
   * 
   * @return A boolean stating whether the ship is dead or not.
   */
  public boolean isDead() {
    return (this.health == 0);
  }

  /**
   * Checks if the ship has reached the end of the Route.
   * 
   * @return A Boolean pointing out Victory.
   */
  public boolean isVictorious() {
    return victorious;
  }

  /**
   * Get the ship's timer.
   * 
   * @return The time the ship has been on its way.
   */
  public float getTimer() {
    return this.timer;
  }

  /**
   * Update the Spaceship and related model classes for the step in the game loop.
   * 
   * @param tpf
   *          Time per frame.
   */
  public void update(float tpf) {
    this.timer += tpf;
    long now = System.currentTimeMillis();
    if (route.isCompleted(now)) {
      this.victorious = true;
    }
    log.update();
  }

  /**
   * Update the crew in the spaceship based on the people connected.
   * 
   * @param clients
   *          The clients connected to the server.
   */
  public void updateCrew(Collection<HostedConnection> clients) {
    ArrayList<Player> temp = new ArrayList<Player>();
    for (HostedConnection hc : clients) {
      Player play = new CrewPlayer(Integer.toString(hc.getId()));
      temp.add(play);
    }
    if (!crew.equals(temp)) {
      crew.clear();
      crew.addAll(temp);
    }
  }
}
