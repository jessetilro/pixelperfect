package pixelperfect;

import java.io.IOException;

import com.jme3.app.SimpleApplication;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.system.JmeContext;


public class GameServer extends SimpleApplication {

  /**
   * main method to start a server.
   * 
   * @param args parameters passed through
   */
  public static void main(String[] args) {
    GameServer app = new GameServer();
    app.start(JmeContext.Type.Headless);
  }
  /**
  * Used to set up a server.
  * 
  */
  
  @Override
  public void simpleInitApp() {
    Server myServer;
    try {
      myServer = Network.createServer(6143);
      myServer.start();
    } catch (IOException exception) {
      exception.printStackTrace();
    }

    
  }

}
