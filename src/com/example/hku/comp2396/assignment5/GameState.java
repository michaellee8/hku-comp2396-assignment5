package com.example.hku.comp2396.assignment5;


import java.io.Serializable;

public class GameState implements Serializable {

  enum PlayerSymbol {
    O,
    X,
    E
  }

  PlayerSymbol[][] board = {
      {PlayerSymbol.E, PlayerSymbol.E, PlayerSymbol.E},
      {PlayerSymbol.E, PlayerSymbol.E, PlayerSymbol.E},
      {PlayerSymbol.E, PlayerSymbol.E, PlayerSymbol.E}
  };

  PlayerSymbol winner = PlayerSymbol.E;

  PlayerSymbol nextStep = PlayerSymbol.O;

  String OName = "";

  String XName = "";

  String OMessage = "Enter your player name...";

  String XMessage = "Enter your player name...";

  String OTitle = "Tic Tac Toe";

  String XTitle = "Tic Tac Toe";

  /**
   * The message to be displayed to the user as an alert before game ends for
   * any reason. After displaying such alert, the client should close itself,
   * and the server should reset the state.
   */
  String endGameMessage = "";

  String getName(PlayerSymbol ps) {
    assert ps == PlayerSymbol.O || ps == PlayerSymbol.X;
    if (ps == PlayerSymbol.O) {
      return OName;
    }
    if (ps == PlayerSymbol.X) {
      return XName;
    }
    return "";
  }

  String getMessage(PlayerSymbol ps) {
    assert ps == PlayerSymbol.O || ps == PlayerSymbol.X;
    if (ps == PlayerSymbol.O) {
      return OMessage;
    }
    if (ps == PlayerSymbol.X) {
      return XMessage;
    }
    return "";
  }

  String getTitle(PlayerSymbol ps) {
    assert ps == PlayerSymbol.O || ps == PlayerSymbol.X;
    if (ps == PlayerSymbol.O) {
      return OTitle;
    }
    if (ps == PlayerSymbol.X) {
      return XTitle;
    }
    return "";
  }

}
