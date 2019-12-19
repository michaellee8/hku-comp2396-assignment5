package com.example.hku.comp2396.assignment5;


import java.io.Serializable;

public class GameState implements Serializable {

  public enum PlayerSymbol {
    O,
    X,
    E
  }

  public PlayerSymbol[][] board = {
      {PlayerSymbol.E, PlayerSymbol.E, PlayerSymbol.E},
      {PlayerSymbol.E, PlayerSymbol.E, PlayerSymbol.E},
      {PlayerSymbol.E, PlayerSymbol.E, PlayerSymbol.E}
  };

  public PlayerSymbol winner = PlayerSymbol.E;

  public PlayerSymbol nextStep = PlayerSymbol.O;

  public String OName = "";

  public String XName = "";

  public String OMessage = "Enter your player name...";

  public String XMessage = "Enter your player name...";

  public String OTitle = "Tic Tac Toe";

  public String XTitle = "Tic Tac Toe";

  /**
   * The message to be displayed to the O user as an alert before game ends for
   * any reason. After displaying such alert, the client should close itself,
   * and the server should reset the state.
   */
  public String OEndGameMessage = "";

  /**
   * The message to be displayed to the X user as an alert before game ends for
   * any reason. After displaying such alert, the client should close itself,
   * and the server should reset the state.
   */
  public String XEndGameMessage = "";

  public String getName(PlayerSymbol ps) {
    assert ps == PlayerSymbol.O || ps == PlayerSymbol.X;
    if (ps == PlayerSymbol.O) {
      return OName;
    }
    if (ps == PlayerSymbol.X) {
      return XName;
    }
    return "";
  }

  public String getMessage(PlayerSymbol ps) {
    assert ps == PlayerSymbol.O || ps == PlayerSymbol.X;
    if (ps == PlayerSymbol.O) {
      return OMessage;
    }
    if (ps == PlayerSymbol.X) {
      return XMessage;
    }
    return "";
  }

  public String getTitle(PlayerSymbol ps) {
    assert ps == PlayerSymbol.O || ps == PlayerSymbol.X;
    if (ps == PlayerSymbol.O) {
      return OTitle;
    }
    if (ps == PlayerSymbol.X) {
      return XTitle;
    }
    return "";
  }

  public String getEndGameMessage(PlayerSymbol ps) {
    assert ps == PlayerSymbol.O || ps == PlayerSymbol.X;
    if (ps == PlayerSymbol.O) {
      return OEndGameMessage;
    }
    if (ps == PlayerSymbol.X) {
      return XEndGameMessage;
    }
    return "";
  }

  public void setName(PlayerSymbol ps, String payload) {
    assert ps == PlayerSymbol.O || ps == PlayerSymbol.X;
    if (ps == PlayerSymbol.O) {
      OName = payload;
    }
    if (ps == PlayerSymbol.X) {
      XName = payload;
    }
  }

  public void setMessage(PlayerSymbol ps, String payload) {
    assert ps == PlayerSymbol.O || ps == PlayerSymbol.X;
    if (ps == PlayerSymbol.O) {
      OMessage = payload;
    }
    if (ps == PlayerSymbol.X) {
      XMessage = payload;
    }
  }

  public void setTitle(PlayerSymbol ps, String payload) {
    assert ps == PlayerSymbol.O || ps == PlayerSymbol.X;
    if (ps == PlayerSymbol.O) {
      OTitle = payload;
    }
    if (ps == PlayerSymbol.X) {
      XTitle = payload;
    }
  }

  public void setEndGameMessage(PlayerSymbol ps, String payload) {
    assert ps == PlayerSymbol.O || ps == PlayerSymbol.X;
    if (ps == PlayerSymbol.O) {
      OEndGameMessage = payload;
    }
    if (ps == PlayerSymbol.X) {
      XEndGameMessage = payload;
    }
  }

}
