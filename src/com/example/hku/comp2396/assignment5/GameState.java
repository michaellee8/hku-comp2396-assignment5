package com.example.hku.comp2396.assignment5;


public class GameState {

  enum Symbol {
    O,
    X,
    E
  }

  Symbol[][] board = {
      {Symbol.E, Symbol.E, Symbol.E},
      {Symbol.E, Symbol.E, Symbol.E},
      {Symbol.E, Symbol.E, Symbol.E}
  };

  Symbol winner = Symbol.E;

  Symbol nextStep = Symbol.O;

  String OName = "";

  String XName = "";

}
