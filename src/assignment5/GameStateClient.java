package assignment5;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.function.Consumer;

public class GameStateClient implements Runnable {

  static int port = 8964;
  static String host = "127.0.0.1";

  Consumer<GameState> onReceiveState;
  Runnable onDetectBrokenConnection;

  public GameStateClient(Consumer<GameState> onReceiveState,
      Runnable onDetectBrokenConnection) {
    this.onReceiveState = onReceiveState;
    this.onDetectBrokenConnection = onDetectBrokenConnection;
  }

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
