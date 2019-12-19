package com.example.hku.comp2396.assignment5;

import com.example.hku.comp2396.assignment5.GameState.PlayerSymbol;
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

  public GameAction(PlayerSymbol side, String actionType, String payload) {
    assert side == PlayerSymbol.O || side == PlayerSymbol.X;
    this.side = side;
    this.actionType = actionType;
    this.payload = payload;
  }
}

