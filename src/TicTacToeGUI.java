import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton[][] buttons;
    private char currentPlayer;
    private int movesCount;

    public TicTacToeGUI() {
        buttons = new JButton[3][3];
        currentPlayer = 'X'; // Player X starts first
        movesCount = 0;

        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        // Initialize buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setActionCommand(i + "," + j);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        int row = Integer.parseInt(command.split(",")[0]);
        int col = Integer.parseInt(command.split(",")[1]);

        if (buttons[row][col].getText().equals("")) {
            buttons[row][col].setText(String.valueOf(currentPlayer));
            movesCount++;

            if (checkWin(row, col)) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                resetGame();
            } else if (movesCount == 9) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                resetGame();
            } else {
                switchPlayer();
            }
        }
    }

    private boolean checkWin(int row, int col) {
        // Check the row
        if (buttons[row][0].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[row][1].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[row][2].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }

        // Check the column
        if (buttons[0][col].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[1][col].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[2][col].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }

        // Check diagonals
        if (row == col) {
            if (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }

        if (row + col == 2) {
            if (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][0].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }

        return false;
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private void resetGame() {
        movesCount = 0;
        currentPlayer = 'X';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeGUI::new);
    }
}