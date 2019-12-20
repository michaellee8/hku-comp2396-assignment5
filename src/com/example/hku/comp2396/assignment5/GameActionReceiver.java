package com.example.hku.comp2396.assignment5;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.concurrent.locks.Condition;

public class GameActionReceiver implements Runnable {

  GameLogicHandler handler;

  Condition notifier;

  InputStream stream;

  public GameActionReceiver(GameLogicHandler handler, Condition notifier,
      InputStream stream) {
    this.handler = handler;
    this.notifier = notifier;
    this.stream = stream;
  }

  @Override
  public void run() {

    ObjectInputStream os;
    try {
      os = new ObjectInputStream(stream);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    while (true) {
      try {
        var action = (GameAction) os.readObject();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }

  }
}
