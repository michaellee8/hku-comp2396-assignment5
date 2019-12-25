package assignment5;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.function.Consumer;

public class GameStateClient implements Runnable {

  /**
   * Port used.
   */
  static int port = 8964;

  /**
   * Address to localhost
   */
  static String host = "127.0.0.1";

  /**
   * What to do when receive state
   */
  Consumer<GameState> onReceiveState;

  /**
   * What to do when broken detection is detected
   */
  Runnable onDetectBrokenConnection;

  /**
   * Construct new GameStateClient
   *
   * @param onReceiveState           new state handler
   * @param onDetectBrokenConnection broken connection handler
   */
  public GameStateClient(Consumer<GameState> onReceiveState,
      Runnable onDetectBrokenConnection) {
    this.onReceiveState = onReceiveState;
    this.onDetectBrokenConnection = onDetectBrokenConnection;
  }

  /**
   * Server to be ran
   */
  @Override
  public void run() {
    try {
      var socket = new Socket(host, port);
      var objectInputStream = new ObjectInputStream(socket.getInputStream());
      while (true) {
        var state = (GameState) objectInputStream.readUnshared();
        onReceiveState.accept(state);
      }
    } catch (Exception e) {
      e.printStackTrace();
      onDetectBrokenConnection.run();
      return;
    }
  }
}
