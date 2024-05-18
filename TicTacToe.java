// "TicTac Toe is fun!".

import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends Frame implements ActionListener {
    private Button[][] buttons = new Button[3][3];
    private boolean playerX = true; // true for 'X', false for 'O'
    private Label statusLabel = new Label("Player X's turn");

    public TicTacToe() {
        setLayout(new BorderLayout());
        setSize(400, 400);
        setTitle("Tic Tac Toe");

        Panel gridPanel = new Panel();
        gridPanel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].addActionListener(this);
                gridPanel.add(buttons[i][j]);
            }
        }
        add(gridPanel, BorderLayout.CENTER);

        Panel controlPanel = new Panel();
        controlPanel.setLayout(new BorderLayout());
        Button newGameButton = new Button("New Game");
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        controlPanel.add(statusLabel, BorderLayout.CENTER);
        controlPanel.add(newGameButton, BorderLayout.EAST);
        add(controlPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    // Rafael Carbajosa
    @Override
    public void actionPerformed(ActionEvent e) {
        Button clickedButton = (Button) e.getSource();
        if (clickedButton.getLabel().equals("")) {
            clickedButton.setLabel(playerX ? "X" : "O");
            if (checkWinner()) {
                statusLabel.setText("Player " + (playerX ? "X" : "O") + " wins!");
                disableButtons();
            } else if (isBoardFull()) {
                statusLabel.setText("It's a draw!");
                disableButtons();
            } else {
                playerX = !playerX;
                statusLabel.setText("Player " + (playerX ? "X" : "O") + "'s turn");
            }
        }
    }

    private void resetGame() {
        playerX = true;
        statusLabel.setText("Player X's turn");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setLabel("");
                buttons[i][j].setEnabled(true);
            }
        }
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getLabel().equals("") &&
                    buttons[i][0].getLabel().equals(buttons[i][1].getLabel()) &&
                    buttons[i][1].getLabel().equals(buttons[i][2].getLabel())) {
                return true;
            }
            if (!buttons[0][i].getLabel().equals("") &&
                    buttons[0][i].getLabel().equals(buttons[1][i].getLabel()) &&
                    buttons[1][i].getLabel().equals(buttons[2][i].getLabel())) {
                return true;
            }
        }
        if (!buttons[0][0].getLabel().equals("") &&
                buttons[0][0].getLabel().equals(buttons[1][1].getLabel()) &&
                buttons[1][1].getLabel().equals(buttons[2][2].getLabel())) {
            return true;
        }
        if (!buttons[0][2].getLabel().equals("") &&
                buttons[0][2].getLabel().equals(buttons[1][1].getLabel()) &&
                buttons[1][1].getLabel().equals(buttons[2][0].getLabel())) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getLabel().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    // ITCC11
    public static void main(String[] args) {
        new TicTacToe();
    }
}
