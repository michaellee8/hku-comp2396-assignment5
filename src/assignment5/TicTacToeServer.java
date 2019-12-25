package assignment5;

import assignment5.GameState.PlayerSymbol;
import java.net.ServerSocket;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class TicTacToeServer {

  public static void main(String[] args) {
    try {
      var handler = new GameLogicHandler();
      Runnable onDetectExit = () -> {
        handler.handleAction(GameAction.makeExit(PlayerSymbol.O));
      };
      var gameStateServer = new GameStateServer(handler, onDetectExit);
      Runnable onMessageProcessed = () -> {
        gameStateServer.broadcast();
        if (handler.isInExitProcedure) {
          handler.afterExit();
        }
      };
      var gameActionServer = new GameActionServer(handler, onMessageProcessed);
      handler.postExit = () -> {
        gameStateServer.halt();
      };
      new Thread(gameActionServer).start();
      new Thread(gameStateServer).start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
