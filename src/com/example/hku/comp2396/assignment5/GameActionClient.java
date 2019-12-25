package com.example.hku.comp2396.assignment5;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class GameActionClient {

  static int port = 2019;
  static String host = "127.0.0.1";

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
