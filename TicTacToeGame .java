import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame extends JFrame {
    private JButton[][] buttons;
    private JLabel statusLabel;
    private int[][] board;
    private int currentPlayer;
    private int player1Score;
    private int player2Score;
    private int movesMade;

    public TicTacToeGame() {
        setTitle("Tic Tac Toe");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize variables
        buttons = new JButton[3][3];
        board = new int[3][3];
        currentPlayer = 1;
        player1Score = 0;
        player2Score = 0;
        movesMade = 0;

        // Create the game grid (buttons)
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(200, 200));
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
                buttons[i][j] = button;
                final int finalI = i;
                final int finalJ = j;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (board[finalI][finalJ] == 0) {
                            board[finalI][finalJ] = currentPlayer;
                            if (currentPlayer == 1) {
                                button.setText("X");
                                button.setForeground(Color.BLUE); // Set X color to blue
                                currentPlayer = 2;
                            } else {
                                button.setText("O");
                                button.setForeground(Color.RED); // Set O color to red
                                currentPlayer = 1;
                            }
                            button.setEnabled(false);
                            movesMade++;
                            updateStatus();
                            checkGame();
                        }
                    }
                });
                gamePanel.add(button);
            }
        }

        // Status label
        statusLabel = new JLabel("Player 1's turn (X)");
        statusLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Scoreboard panel
        JPanel scoreboardPanel = new JPanel();
        scoreboardPanel.setLayout(new GridLayout(2, 1));
        JLabel player1ScoreLabel = new JLabel("Player 1 Score: " + player1Score);
        player1ScoreLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        player1ScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel player2ScoreLabel = new JLabel("Player 2 Score: " + player2Score);
        player2ScoreLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        player2ScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreboardPanel.add(player1ScoreLabel);
        scoreboardPanel.add(player2ScoreLabel);

        // Adding components to the frame
        add(gamePanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.NORTH);
        add(scoreboardPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void updateStatus() {
        if (currentPlayer == 1) {
            statusLabel.setText("Player 1's turn (X)");
        } else {
            statusLabel.setText("Player 2's turn (O)");
        }
    }

    private void checkGame() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                handleWin(board[i][0]);
                return;
            }
            if (board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                handleWin(board[0][i]);
                return;
            }
        }
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            handleWin(board[0][0]);
            return;
        }
        if (board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            handleWin(board[0][2]);
            return;
        }
        
        // Check for draw
        if (movesMade == 9) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetGame();
        }
    }

    private void handleWin(int player) {
        if (player == 1) {
            JOptionPane.showMessageDialog(this, "Player 1 (X) wins!");
            player1Score++;
        } else {
            JOptionPane.showMessageDialog(this, "Player 2 (O) wins!");
            player2Score++;
        }
        res

