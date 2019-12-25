package assignment5;

import assignment5.GameState.PlayerSymbol;
import java.io.Serializable;

public class GameAction implements Serializable {

  public enum Type {
    register,
    performMove,
    exit
  }

  public PlayerSymbol side;
  public Type actionType;
  public String payload;

  public GameAction(PlayerSymbol side, Type actionType, String payload) {
    assert side == PlayerSymbol.O || side == PlayerSymbol.X;
    this.side = side;
    this.actionType = actionType;
    this.payload = payload;
  }

  public static GameAction makeRegister(PlayerSymbol side, String name) {
    return new GameAction(side, Type.register, name);
  }

  public static GameAction makePerformMove(PlayerSymbol side, int row,
      int col) {
    return new GameAction(side, Type.performMove,
        String.valueOf(row) + "," + String.valueOf(col));
  }

  public static GameAction makeExit(PlayerSymbol side) {
    return new GameAction(side, Type.exit, "");
  }
}

