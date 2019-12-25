package assignment5;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.concurrent.locks.Condition;

public class GameActionReceiver implements Runnable {

  Runnable onDetectExit;

  GameLogicHandler handler;

  Condition notifier;

  InputStream stream;

  public GameActionReceiver(Runnable onDetectExit, GameLogicHandler handler,
      Condition notifier,
      InputStream stream) {
    this.handler = handler;
    this.notifier = notifier;
    this.stream = stream;
    this.onDetectExit = onDetectExit;
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
      GameAction action;
      try {
        action = (GameAction) os.readObject();
      } catch (IOException e) {
        e.printStackTrace();
        onDetectExit.run();
        return;
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
        return;
      }
      handler.handleAction(action);
      notifier.notifyAll();
    }

  }
}
