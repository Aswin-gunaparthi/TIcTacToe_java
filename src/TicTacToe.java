import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650; //50px for text panel

    JFrame jFrame = new JFrame("Tic-Tac-Toe");
    JLabel textLable = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3]; //to track button input
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    boolean gameOver = false;
    int turns = 0;

    TicTacToe(){
        jFrame.setVisible(true); //displays the window on the screen
        jFrame.setSize(boardWidth, boardHeight); //width and height of the window
        jFrame.setLocationRelativeTo(null); //keeping the window at the center
        jFrame.setResizable(false); //no resizability
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE); //the close button on top right corner
        jFrame.setLayout(new BorderLayout()); //Arrange components inside this frame using BorderLayou

        textLable.setBackground(Color.cyan);  //background color
        textLable.setForeground(Color.white); //font color
        textLable.setFont(new Font("Arial", Font.BOLD, 50)); //font settings
        textLable.setHorizontalAlignment(JLabel.CENTER); //text alignment
        textLable.setText("Tic - Tac - Toe"); //default text
        textLable.setOpaque(true); //Make this label paint its background

        textPanel.setLayout(new BorderLayout()); //Arrange components inside this panel using BorderLayou
        textPanel.add(textLable);
        jFrame.add(textPanel, BorderLayout.NORTH); //textPanel addition to gui and its position

        boardPanel.setLayout(new GridLayout(3, 3)); //adding layout to board panel
        boardPanel.setBackground(Color.cyan); //board panel color
        jFrame.add(boardPanel);  //adding board panel

        for (int r = 0; r < 3; r++){
            for (int c = 0; c < 3; c++){
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.cyan); //each tile color
                tile.setForeground(Color.white); //color whin interacting with tiles
                tile.setFont(new Font("Arial", Font.BOLD, 120)); // each tile font
                tile.setFocusable(false); //idk
                //tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText().isEmpty()){
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver){
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLable.setText(currentPlayer + "'S Turn");
                            }


                        }
                    }
                });

            }
        }
    }
    void checkWinner(){
        //horizontal
        for (int r = 0; r < 3; r++){
            if (board[r][0].getText().isEmpty()) continue;

            if (board[r][0].getText() == board[r][1].getText() &&
                board[r][1].getText() == board[r][2].getText()) {
                for (int i = 0; i < 3; i++){
                    setwinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }
        //vertical
        for (int c = 0; c < 3; c++){
            if (board[0][c].getText().isEmpty()) continue;

            if (board[0][c].getText().equals(board[1][c].getText()) &&
                    board[1][c].getText().equals(board[2][c].getText())) {
                for (int i = 0; i < 3; i++){
                    setwinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }
        //diagonal
        if (board[0][0].getText().equals(board[1][1].getText() )&&
            board[2][2].getText().equals(board[1][1].getText() )&&
            !board[0][0].getText().isEmpty()){
            for (int i = 0; i < 3; i++){
                setwinner(board[i][i]);
            }
            gameOver = true;
            return;
        }
        //other diagonal
        if (board[0][2].getText().equals(board[1][1].getText()) && board[2][0].getText().equals(board[1][1].getText()) && !board[0][2].getText().isEmpty()){
            setwinner(board[0][2]);
            setwinner(board[1][1]);
            setwinner(board[2][0]);
            gameOver = true;
                return;
        }
        if (turns == 9){
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    setTie(board[i][j]);
                }
            }
            gameOver = true;
        }
    }

    public void setwinner(JButton tile) {
        tile.setBackground(Color.GREEN);
        tile.setForeground(Color.DARK_GRAY);
        textLable.setText(currentPlayer + " is the winner");
    }

    public void setTie(JButton tile) {
        tile.setBackground(Color.GREEN);
        tile.setForeground(Color.darkGray);
        textLable.setText("TIE!");
    }
}
