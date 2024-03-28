import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private String[] board;
    private String turn;
    private JLabel statusLabel;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);

        buttons = new JButton[3][3];
        board = new String[9];
        turn = "X";

        setLayout(new GridLayout(4, 3));

        for (int i = 0; i < 9; i++) {
            board[i] = String.valueOf(i + 1);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 50));
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        statusLabel = new JLabel();
        add(statusLabel);

        updateBoard();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == buttonClicked) {
                    int index = i * 3 + j;
                    if (board[index].equals(String.valueOf(index + 1))) {
                        board[index] = turn;
                        buttonClicked.setText(turn);
                        if (turn.equals("X")) {
                            turn = "O";
                        } else {
                            turn = "X";
                        }
                        updateBoard();
                        String winner = checkWinner();
                        if (winner != null) {
                            if (winner.equals("draw")) {
                                statusLabel.setText("It's a draw! Thanks for playing.");
                            } else {
                                statusLabel.setText("Congratulations! " + winner + "'s have won! Thanks for playing.");
                            }
                            disableAllButtons();
                        }
                    }
                    return;
                }
            }
        }
    }

    private String checkWinner() {
        for (int a = 0; a < 8; a++) {
            String line = null;

            switch (a) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }
            if (line.equals("XXX")) {
                return "X";
            } else if (line.equals("OOO")) {
                return "O";
            }
        }

        for (int a = 0; a < 9; a++) {
            if (Arrays.asList(board).contains(String.valueOf(a + 1))) {
                break;
            } else if (a == 8) {
                return "draw";
            }
        }

        return null;
    }

    private void updateBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(board[i * 3 + j]);
            }
        }
        statusLabel.setText("Player " + turn + "'s turn");
    }

    private void disableAllButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
