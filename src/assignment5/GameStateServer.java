package assignment5;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class GameStateServer implements Runnable {

  /**
   * Port used.
   */
  static int port = 8964;

  /**
   * Handler used.
   */
  GameLogicHandler handler;

  /**
   * Exit detected.
   */
  Runnable onDetectExit;

  private Set<Socket> sockets = ConcurrentHashMap.newKeySet();

  private Set<ObjectOutputStream> objectOutputStreams = ConcurrentHashMap
      .newKeySet();

  public GameStateServer(GameLogicHandler handler, Runnable onDetectExit) {
    this.handler = handler;
    this.onDetectExit = onDetectExit;
  }

  /**
   * Broadcast the state to clients
   */
  public synchronized void broadcast() {
    for (var objectOutputStream : objectOutputStreams) {
      try {
        objectOutputStream.reset();
        objectOutputStream.writeUnshared(handler.getState());
      } catch (Exception e) {
        e.printStackTrace();
        onDetectExit.run();
      }
    }
  }

  /**
   * Remove all existing connections.
   */
  public void halt() {
    sockets = ConcurrentHashMap.newKeySet();
    objectOutputStreams = ConcurrentHashMap.newKeySet();
  }

  /**
   * Run the server
   */
  @Override
  public void run() {
    try {
      var listener = new ServerSocket(port);
      while (true) {

        var so = listener.accept();
        sockets.add(so);
        objectOutputStreams.add(new ObjectOutputStream(so.getOutputStream()));
        broadcast();

      }
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }
  }


}
