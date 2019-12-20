package com.example.hku.comp2396.assignment5;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.concurrent.locks.Condition;

public class GameStateBroadcaster implements Runnable {

  Runnable onDetectExit;

  GameLogicHandler handler;

  Condition notifier;

  OutputStream stream;

  public GameStateBroadcaster(Runnable onDetectExit, GameLogicHandler handler,
      Condition notifier, OutputStream stream) {
    this.onDetectExit = onDetectExit;
    this.handler = handler;
    this.notifier = notifier;
    this.stream = stream;
  }

  @Override
  public void run() {
    ObjectOutputStream os;
    try {
      os = new ObjectOutputStream(stream);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
    while (true) {

      if (handler.isInExitProcedure) {
        return;
      }

      try {
        notifier.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      try {
        os.writeObject(handler.getState());
      } catch (IOException e) {
        e.printStackTrace();
        onDetectExit.run();
        return;
      }


    }
  }
}
