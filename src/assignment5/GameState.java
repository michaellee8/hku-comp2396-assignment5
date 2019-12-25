package assignment5;


import java.io.Serializable;

public class GameState implements Serializable {

  /**
   * Represent different sides, E for unspecified/no player
   */
  public enum PlayerSymbol {
    O,
    X,
    E
  }

  /**
   * Represent current marks
   */
  public PlayerSymbol[][] board = {
      {PlayerSymbol.E, PlayerSymbol.E, PlayerSymbol.E},
      {PlayerSymbol.E, PlayerSymbol.E, PlayerSymbol.E},
      {PlayerSymbol.E, PlayerSymbol.E, PlayerSymbol.E}
  };

  /**
   * winder of hae if exist, E if no one wins
   */
  public PlayerSymbol winner = PlayerSymbol.E;

  /**
   * the player that should take the next step
   */
  public PlayerSymbol nextStep = PlayerSymbol.O;

  /**
   * Name of O
   */
  public String OName = "";

  /**
   * Name of X
   */
  public String XName = "";

  /**
   * Message to O
   */
  public String OMessage = "Enter your player name...";

  /**
   * Message to X
   */
  public String XMessage = "Enter your player name...";

  /**
   * Title of O's window
   */
  public String OTitle = "Tic Tac Toe";

  /**
   * Title of X's window
   */
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

  /**
   * Get name of player
   *
   * @param ps side of player
   * @return such name
   */
  public String getName(PlayerSymbol ps) {
    assert ps == PlayerSymbol.O || ps == PlayerSymbol.X;
    if (ps == PlayerSymbol.O) {
      return OName;
    }
    //noinspection ConstantConditions
    if (ps == PlayerSymbol.X) {
      return XName;
    }
    return "";
  }

  /**
   * Get message of player
   *
   * @param ps side of player
   * @return such message
   */
  public String getMessage(PlayerSymbol ps) {
    assert ps == PlayerSymbol.O || ps == PlayerSymbol.X;
    if (ps == PlayerSymbol.O) {
      return OMessage;
    }
    //noinspection ConstantConditions
    if (ps == PlayerSymbol.X) {
      return XMessage;
    }
    return "";
  }

  /**
   * Get title of player
   *
   * @param ps side of player
   * @return such title
   */
  public String getTitle(PlayerSymbol ps) {
    assert ps == PlayerSymbol.O || ps == PlayerSymbol.X;
    if (ps == PlayerSymbol.O) {
      return OTitle;
    }
    //noinspection ConstantConditions
    if (ps == PlayerSymbol.X) {
      return XTitle;
    }
    return "";
  }

  /**
   * Get end message of player
   *
   * @param ps side of player
   * @return such end message
   */
  public String getEndGameMessage(PlayerSymbol ps) {
    assert ps == PlayerSymbol.O || ps == PlayerSymbol.X;
    if (ps == PlayerSymbol.O) {
      return OEndGameMessage;
    }
    //noinspection ConstantConditions
    if (ps == PlayerSymbol.X) {
      return XEndGameMessage;
    }
    return "";
  }

  /**
   * Set name of player
   *
   * @param ps      side of player
   * @param payload name of player
   */
  public void setName(PlayerSymbol ps, String payload) {
    assert ps == PlayerSymbol.O || ps == PlayerSymbol.X;
    if (ps == PlayerSymbol.O) {
      OName = payload;
    }
    if (ps == PlayerSymbol.X) {
      XName = payload;
    }
  }


  /**
   * Set message of player
   *
   * @param ps      side of player
   * @param payload message of player
   */
  public void setMessage(PlayerSymbol ps, String payload) {
    assert ps == PlayerSymbol.O || ps == PlayerSymbol.X;
    if (ps == PlayerSymbol.O) {
      OMessage = payload;
    }
    if (ps == PlayerSymbol.X) {
      XMessage = payload;
    }
  }


  /**
   * Set title of player
   *
   * @param ps      side of player
   * @param payload title of player
   */
  public void setTitle(PlayerSymbol ps, String payload) {
    assert ps == PlayerSymbol.O || ps == PlayerSymbol.X;
    if (ps == PlayerSymbol.O) {
      OTitle = payload;
    }
    if (ps == PlayerSymbol.X) {
      XTitle = payload;
    }
  }


  /**
   * Set end game message of player
   *
   * @param ps      side of player
   * @param payload end game message of player
   */
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
