package com.example.hku.comp2396.assignment5;

import com.example.hku.comp2396.assignment5.GameAction.Type;
import com.example.hku.comp2396.assignment5.GameState.PlayerSymbol;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

public class GameLogicHandler {

  public GameState state = new GameState();

  public void handleAction(GameAction action) {
    assert action.side == PlayerSymbol.O || action.side == PlayerSymbol.X;
    switch (action.actionType) {
      case register:
        register(action.side, action.payload);
        break;
      case performMove:
        var val = action.payload.split(",", 2);
        var row = Integer.parseInt(val[0]);
        var col = Integer.parseInt(val[1]);
        performMove(action.side, row, col);
        break;
      case exit:
        exit(action.side);
        break;
    }
  }

  public PlayerSymbol detectRow() {
    for (var i = 0; i < 3; i++) {
      if (
          Collections.frequency(Arrays.asList(state.board[i]), PlayerSymbol.O)
              == 3) {
        return PlayerSymbol.O;
      }
      if (
          Collections.frequency(Arrays.asList(state.board[i]), PlayerSymbol.X)
              == 3) {
        return PlayerSymbol.X;
      }
    }
    return PlayerSymbol.E;
  }

  public PlayerSymbol detectCol() {
    for (var i = 0; i < 3; i++) {
      if (
          Collections.frequency(Arrays
                  .asList(state.board[0][i], state.board[1][i], state.board[2][i]),
              PlayerSymbol.O)
              == 3) {
        return PlayerSymbol.O;
      }
      if (
          Collections.frequency(Arrays
                  .asList(state.board[0][i], state.board[1][i], state.board[2][i]),
              PlayerSymbol.X)
              == 3) {
        return PlayerSymbol.X;
      }
    }
    return PlayerSymbol.E;
  }

  public PlayerSymbol detectSlash() {
    if (state.board[0][0] == PlayerSymbol.O
        && state.board[1][1] == PlayerSymbol.O
        && state.board[2][2] == PlayerSymbol.O) {
      return PlayerSymbol.O;
    }
    if (state.board[0][2] == PlayerSymbol.O
        && state.board[1][1] == PlayerSymbol.O
        && state.board[2][0] == PlayerSymbol.O) {
      return PlayerSymbol.O;
    }
    if (state.board[0][0] == PlayerSymbol.X
        && state.board[1][1] == PlayerSymbol.X
        && state.board[2][2] == PlayerSymbol.X) {
      return PlayerSymbol.X;
    }
    if (state.board[0][2] == PlayerSymbol.X
        && state.board[1][1] == PlayerSymbol.X
        && state.board[2][0] == PlayerSymbol.X) {
      return PlayerSymbol.X;
    }
    return PlayerSymbol.E;
  }

  public void register(PlayerSymbol side, String name) {
    if (name.equals("")) {
      return;
    }
    state.setName(side, name);
  }

  public void performMove(PlayerSymbol side, int row, int col) {
    state.board[row][col] = side;
  }

  public void exit(PlayerSymbol side) {
    state.setEndGameMessage(
        PlayerSymbol.O,
        "Game Ends. One of the players left."
    );
    state.setEndGameMessage(
        PlayerSymbol.X,
        "Game Ends. One of the players left."
    );
  }
}
