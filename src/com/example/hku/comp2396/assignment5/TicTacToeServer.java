package com.example.hku.comp2396.assignment5;

import java.net.ServerSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class TicTacToeServer {

  static final int port = 8964;

  public static void main(String[] args) {
    try (ServerSocket listener = new ServerSocket(port)) {
      System.out.println("TicTacToeServer is running in " + port);
      var handler = new GameLogicHandler();
      Runnable onDetectExit = () -> {
      };
      var receiverPool = Executors.newCachedThreadPool();
      var broadcasterPool = Executors.newCachedThreadPool();
      var lock = new ReentrantLock();
      var notifier = lock.newCondition();
      while (true) {
        try {
          var so = listener.accept();
          System.out.println("TicTacToeServer accepted " + so);
          receiverPool.execute(
              new GameActionReceiver(onDetectExit, handler, notifier,
                  so.getInputStream()));
          broadcasterPool.execute(
              new GameStateBroadcaster(onDetectExit, handler, notifier,
                  so.getOutputStream()));
        } catch (Exception e) {
          e.printStackTrace();
          return;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
