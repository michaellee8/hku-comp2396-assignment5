package com.example.hku.comp2396.assignment5;

import com.example.hku.comp2396.assignment5.GameAction.Type;
import com.example.hku.comp2396.assignment5.GameState.PlayerSymbol;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

public class GameLogicHandler {

  static final String loseMessage = "You lose.";
  static final String winMessage = "Congratulations. You Win.";
  static final String drawMessage = "Draw.";
  static final String playerLeftMessage =
      "Game Ends. One of the players left.";
  static final String selfMovedMessage =
      "Valid move, wait for your opponent.";
  static final String opponentMovedMessage =
      "Your opponent has moved, now is your turn.";

  public GameState state = new GameState();

  public boolean isInExitProcedure = false;

  public synchronized void handleAction(GameAction action) {
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
        exit();
        break;
    }
  }

  public PlayerSymbol detectWinner() {
    if (detectRow() != PlayerSymbol.E) {
      return detectRow();
    }
    if (detectCol() != PlayerSymbol.E) {
      return detectCol();
    }
    if (detectSlash() != PlayerSymbol.E) {
      return detectSlash();
    }
    return PlayerSymbol.E;
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

  public boolean detectDraw() {
    for (var i = 0; i < 3; i++) {
      for (var j = 0; j < 3; j++) {
        if (state.board[i][j] == PlayerSymbol.E) {
          return false;
        }
      }
    }
    return true;
  }

  public void register(PlayerSymbol side, String name) {
    if (name == null || name.equals("")) {
      return;
    }
    if (!state.getName(side).equals("")) {
      return;
    }
    state.setName(side, name);
    state.setMessage(side, "WELCOME " + name);
  }

  public void performMove(PlayerSymbol side, int row, int col) {
    if (!isGameStarted()) {
      return;
    }
    if (state.board[row][col] != PlayerSymbol.E) {
      return;
    }
    if (state.nextStep != side) {
      return;
    }
    state.board[row][col] = side;
    if (side == PlayerSymbol.O) {
      state.setMessage(PlayerSymbol.O, selfMovedMessage);
      state.setMessage(PlayerSymbol.X, opponentMovedMessage);
      state.nextStep = PlayerSymbol.X;
    }
    if (side == PlayerSymbol.X) {
      state.setMessage(PlayerSymbol.X, selfMovedMessage);
      state.setMessage(PlayerSymbol.O, opponentMovedMessage);
      state.nextStep = PlayerSymbol.O;
    }
    var winner = detectWinner();
    if (winner == PlayerSymbol.O) {
      state.setEndGameMessage(PlayerSymbol.O, winMessage);
      state.setEndGameMessage(PlayerSymbol.X, loseMessage);
      return;
    }
    if (winner == PlayerSymbol.X) {
      state.setEndGameMessage(PlayerSymbol.X, winMessage);
      state.setEndGameMessage(PlayerSymbol.O, loseMessage);
      return;
    }
    if (detectDraw()) {
      state.setEndGameMessage(PlayerSymbol.O, drawMessage);
      state.setEndGameMessage(PlayerSymbol.X, drawMessage);
      return;
    }
  }

  public void exit() {
    state.setEndGameMessage(
        PlayerSymbol.O,
        playerLeftMessage
    );
    state.setEndGameMessage(
        PlayerSymbol.X,
        playerLeftMessage
    );
    isInExitProcedure = true;
  }

  public void afterExit() {
    state = new GameState();
    isInExitProcedure = false;
  }

  public boolean isGameStarted() {
    return !state.OName.equals("") && !state.XName.equals("");
  }

  public GameState getState() {
    return state;
  }
}
