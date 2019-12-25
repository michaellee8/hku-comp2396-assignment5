package assignment5;

import java.io.ObjectInputStream;
import java.net.ServerSocket;

public class GameActionServer implements Runnable {

  /**
   * Port used
   */
  static int port = 2019;

  /**
   * The handler used for this instance.
   */
  GameLogicHandler handler;

  /**
   * Action to do when new message comes
   */
  Runnable onMessageProcessed;

  /**
   * Construct a GameActionServer
   *
   * @param handler            the handler to use
   * @param onMessageProcessed what to do after processing new message.
   */
  public GameActionServer(GameLogicHandler handler,
      Runnable onMessageProcessed) {
    this.handler = handler;
    this.onMessageProcessed = onMessageProcessed;
  }

  /**
   * Run the server
   */
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
