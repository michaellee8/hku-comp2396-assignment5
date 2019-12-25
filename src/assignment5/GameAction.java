package assignment5;

import assignment5.GameState.PlayerSymbol;
import java.io.Serializable;

public class GameAction implements Serializable {

  /**
   * Types of actions.
   */
  public enum Type {
    register,
    performMove,
    exit
  }

  /**
   * Side of player who makes the action.
   */
  public PlayerSymbol side;

  /**
   * Type of action.
   */
  public Type actionType;

  /**
   * Payload of the action.
   */
  public String payload;

  /**
   * Construct a GameAction.
   *
   * @param side       side of player
   * @param actionType type of action
   * @param payload    action content
   */
  public GameAction(PlayerSymbol side, Type actionType, String payload) {
    assert side == PlayerSymbol.O || side == PlayerSymbol.X;
    this.side = side;
    this.actionType = actionType;
    this.payload = payload;
  }

  /**
   * Make an action used for registration.
   *
   * @param side side of player
   * @param name name to register
   * @return GameAction of type register.
   */
  public static GameAction makeRegister(PlayerSymbol side, String name) {
    return new GameAction(side, Type.register, name);
  }

  /**
   * Make an action used for performing move
   *
   * @param side side of player
   * @param row  row of move
   * @param col  col of move
   * @return GameAction of type move.
   */
  public static GameAction makePerformMove(PlayerSymbol side, int row,
      int col) {
    return new GameAction(side, Type.performMove,
        String.valueOf(row) + "," + String.valueOf(col));
  }

  /**
   * Make an action used for exit game
   *
   * @param side side of player
   * @return GameAction of type exit.
   */
  public static GameAction makeExit(PlayerSymbol side) {
    return new GameAction(side, Type.exit, "");
  }
}

