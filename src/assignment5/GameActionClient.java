package assignment5;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class GameActionClient {

  /**
   * Port used.
   */
  static int port = 2019;

  /**
   * Address to localhost.
   */
  static String host = "127.0.0.1";

  /**
   * Send a GameAction
   *
   * @param action the GameAction to send
   */
  static void send(GameAction action) {
    try {
      var so = new Socket(host, port);
      var oos = new ObjectOutputStream(so.getOutputStream());
      oos.writeObject(action);
      oos.close();
      so.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
