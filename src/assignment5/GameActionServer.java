package assignment5;

import java.io.ObjectInputStream;
import java.net.ServerSocket;

public class GameActionServer implements Runnable {

  static int port = 2019;

  GameLogicHandler handler;
  Runnable onMessageProcessed;

  public GameActionServer(GameLogicHandler handler,
      Runnable onMessageProcessed) {
    this.handler = handler;
    this.onMessageProcessed = onMessageProcessed;
  }

  @Override
  public void run() {
    try {
      var serverSocket = new ServerSocket(port);
      while (true) {
        var so = serverSocket.accept();
        var ois = new ObjectInputStream(so.getInputStream());
        var action = (GameAction) ois.readObject();
        handler.handleAction(action);
        onMessageProcessed.run();
        ois.close();
        so.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }
  }
}
