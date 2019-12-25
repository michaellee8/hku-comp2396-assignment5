package com.example.hku.comp2396.assignment5;

import com.example.hku.comp2396.assignment5.GameState.PlayerSymbol;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TicTacToeClient {

  WindowListener closeListener = new WindowAdapter() {

    @Override
    public void windowClosing(WindowEvent windowEvent) {

      GameActionClient.send(GameAction.makeExit(PlayerSymbol.O));
    }
  };

  static String rules = "Some information about the game:\n"
//      + "\n"
      + "Criteria for a valid move:\n"
//      + "\n"
      + "-The move is not occupied by any mark,\n"
//      + "\n"
      + "-The move Is made in the player's turn.\n"
//      + "\n"
      + "-The move is made within the 3 x 3 board,\n"
//      + "\n"
      + "The game would continue and switch among the opposite player until it reaches either one of the following conditions:\n"
      + "-Player 1 wins.\n"
//      + "\n"
      + "-Player 2 wins.\n"
//      + "\n"
      + "-Draw.";

  public OutputStream os;

  // Main frame
  private JFrame frame = new JFrame();

  // Content panel
  private JPanel jContentPane = new JPanel();

  // Menu bar
  private JMenuBar menuBar = new JMenuBar();
  private JMenu controlMenu = new JMenu("Control");
  private JMenu helpMenu = new JMenu("Help");
  private JMenuItem exitMenuItem = new JMenuItem("Exit");
  private JMenuItem instructionMenuItem = new JMenuItem("Instruction");

  // Game message
  private JLabel msgLabel = new JLabel("Enter your player name...");


  // TicTacToe boxes
  private JButton[][] boxes = {
      {new JButton(" "), new JButton(" "), new JButton(" ")},
      {new JButton(" "), new JButton(" "), new JButton(" ")},
      {new JButton(" "), new JButton(" "), new JButton(" ")},
  };
  private JPanel boxesPanel = new JPanel();

  // Register control
  private JSplitPane registerSplitPane = new JSplitPane();
  private JTextField nameInput = new JTextField();
  private JButton submitButton = new JButton("Submit");

  private GameState currentState = new GameState();


  public void init() {

    // Configure boxes for holding the symbols
    for (var boxRow : boxes) {
      for (var box : boxRow) {
        box.setHorizontalAlignment(SwingConstants.CENTER);
        box.setVerticalAlignment(SwingConstants.CENTER);
        box.setPreferredSize(new Dimension(100, 100));
        box.setBackground(Color.WHITE);
        box.setForeground(Color.BLACK);
        box.setFont(new Font(null, Font.BOLD, 72));
      }
    }

    // Configure menu bar
    frame.setJMenuBar(menuBar);
    menuBar.add(controlMenu);
    menuBar.add(helpMenu);
    controlMenu.add(exitMenuItem);
    helpMenu.add(instructionMenuItem);

    // Configure panel
    frame.setContentPane(jContentPane);
    frame.setResizable(false);
    frame.addWindowListener(closeListener);
    jContentPane.setLayout(new BorderLayout());
    jContentPane.add(msgLabel, BorderLayout.NORTH);
    jContentPane.add(boxesPanel, BorderLayout.CENTER);
    jContentPane.add(registerSplitPane, BorderLayout.SOUTH);

    // Configure register control
    registerSplitPane.setDividerLocation(350);
    registerSplitPane.setLeftComponent(nameInput);
    registerSplitPane.setRightComponent(submitButton);
    registerSplitPane.setEnabled(false);

    // Configure box panel
    boxesPanel.setLayout(new GridBagLayout());
    for (var row = 0; row < 3; row++) {
      for (var col = 0; col < 3; col++) {
        boxesPanel.add(boxes[row][col],
            new GridBagConstraints(col, row, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.CENTER,
                new Insets(0, 0, 0, 0), 70, 70));
      }
    }

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 800);
    frame.setVisible(true);
    frame.setTitle("Tic Tac Toe");
    frame.repaint();

    // Register action handlers
    submitButton.addActionListener((actionEvent) -> {
      if (currentState.getName(PlayerSymbol.O).equals("")) {
        GameActionClient
            .send(GameAction.makeRegister(PlayerSymbol.O, nameInput.getText()));
        symbol = PlayerSymbol.O;
        return;
      }
      if (currentState.getName(PlayerSymbol.X).equals("")) {
        GameActionClient
            .send(GameAction.makeRegister(PlayerSymbol.X, nameInput.getText()));
        symbol = PlayerSymbol.X;
        return;
      }
      System.out.println("assert fail, not available side left");

    });
    for (var row = 0; row < 3; row++) {
      for (var col = 0; col < 3; col++) {
        final int r = row;
        final int c = col;
        boxes[row][col].addActionListener((actionEvent -> {
          GameActionClient.send(GameAction.makePerformMove(symbol, r, c));
        }));
      }
    }
    exitMenuItem.addActionListener(actionEvent -> {
      GameActionClient.send(GameAction.makeExit(symbol));
      System.exit(0);
    });
    instructionMenuItem.addActionListener(actionEvent -> {
      JOptionPane.showMessageDialog(frame, rules);
    });
  }

  PlayerSymbol symbol = PlayerSymbol.E;

  public void applyState(GameState state) {
    currentState = state;
    if (symbol != PlayerSymbol.O && symbol != PlayerSymbol.X) {
      if (!state.OEndGameMessage.equals("")) {
        JOptionPane.showMessageDialog(frame, state.OEndGameMessage);
        System.exit(0);
      }
      return;
    }
    frame.setTitle(state.getTitle(symbol));
    msgLabel.setText(state.getMessage(symbol));
    for (var row = 0; row < 3; row++) {
      for (var col = 0; col < 3; col++) {
        boxes[row][col].setText(translateSymbol(state.board[row][col]));

        System.out.println(
            row + " " + col + " " + translateSymbol(state.board[row][col]));
      }
    }
    if (!state.getName(symbol).equals("")) {
      nameInput.setEnabled(false);
      submitButton.setEnabled(false);
    }
    if (!state.getEndGameMessage(symbol).equals("")) {
      JOptionPane.showMessageDialog(frame, state.getEndGameMessage(symbol));
      System.exit(0);
    }
  }


  public String translateSymbol(PlayerSymbol symbol) {
    switch (symbol) {
      case E:
        return " ";
      case O:
        return "O";
      case X:
        return "X";
    }
    return null;
  }

  static final String host = "127.0.0.1";

  public static void main(String[] args) {
    var client = new TicTacToeClient();
    client.init();

    try {
      var gameStateClient = new GameStateClient((state) -> {
        client.applyState(state);
      }, () -> {
        System.exit(0);
      });

      new Thread(gameStateClient).start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
