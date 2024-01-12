package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private char[][] board = new char[3][3];
    private JButton[][] buttons = new JButton[3][3];
    private char currentPlayer = 'X';

    // Constructor - initializes the game board and GUI
    public TicTacToeGUI() {
        initializeBoard();
        initializeGUI();
    }

    // Initializes the game board with empty spaces
    private void initializeBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = ' ';
            }
        }
    }

    // Initializes the graphical user interface (GUI)
    private void initializeGUI() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        // Create buttons for the game grid
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 40));
                buttons[row][col].setFocusPainted(false);

                // Add ActionListener to handle button clicks
                final int finalRow = row;
                final int finalCol = col;

                buttons[row][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        buttonClicked(finalRow, finalCol);
                    }
                });

                add(buttons[row][col]);
            }
        }
    }

    // Handles the button click event
    private void buttonClicked(int row, int col) {
        if (board[row][col] == ' ') {
            // Update the game board and button text with the current player's mark
            board[row][col] = currentPlayer;
            buttons[row][col].setText(String.valueOf(currentPlayer));

            // Check for a win
            if (haveWon(board, currentPlayer)) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " has won!");
                resetGame(); // Reset the game after a win
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch to the other player
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid move. Try again!");
        }

        // Check for a draw and reset the game if the board is full
        if (isBoardFull() && !haveWon(board, currentPlayer)) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
        }
    }

    // Checks if the current player has won
    private boolean haveWon(char[][] board, char player) {
        // Check rows
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == player && board[row][1] == player && board[row][2] == player) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == player && board[1][col] == player && board[2][col] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    // Checks if the board is full (draw)
    private boolean isBoardFull() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == ' ') {
                    return false; // If any cell is empty, the board is not full
                }
            }
        }
        return true; // All cells are filled, the board is full
    }

    // Resets the game to the initial state
    private void resetGame() {
        initializeBoard();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText(""); // Clear button texts
            }
        }
        currentPlayer = 'X'; // Reset to player X
    }

    // Main method - entry point of the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TicTacToeGUI().setVisible(true); // Create and show the GUI
            }
        });
    }
}
