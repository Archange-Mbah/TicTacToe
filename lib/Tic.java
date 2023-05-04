import java.util.*;

public class TicTacToe {
    private int[] board = new int[9];
    private int player = 1;
    
    public void play() {
        while (!isGameOver()) {
            printBoard();
            int move;
            if (player == 1) {
                System.out.println("It's your turn (X). Enter a number from 0-8:");
                move = getInput();
            } else {
                System.out.println("It's the computer's turn (O):");
                move = minimax();
            }
            makeMove(move);
            player = -player;
        }
        printBoard();
        int winner = getWinner();
        if (winner == 1) {
            System.out.println("You win!");
        } else if (winner == -1) {
            System.out.println("The computer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }
    
     public void printBoard() {
        System.out.println();
        for (int i = 0; i < 9; i++) {
            if (board[i] == 1) {
                System.out.print("X");
            } else if (board[i] == -1) {
                System.out.print("O");
            } else {
                System.out.print(" ");
            }
            if (i % 3 == 2) {
                System.out.println();
            } else {
                System.out.print("|");
            }
        }
        System.out.println();
    }
    private int getInput() {
        Scanner scanner = new Scanner(System.in);
        int move = scanner.nextInt();
        while (board[move] != 0) {
            System.out.println("Invalid move. Please enter an empty position from 0-8:");
            move = scanner.nextInt();
        }
        return move;
    }
    
    private void makeMove(int move) {
        board[move] = player;
    }
    
    private int minimax() {
        int bestMove = -1;
        int bestScore = Integer.MIN_VALUE;
        for (int i = 0; i < 9; i++) {
            if (board[i] == 0) {
                board[i] = -1;
                int score = evaluate();
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
                board[i] = 0;
            }
        }
        return bestMove;
    }
    
    private int evaluate() {
        int winner = getWinner();
        if (winner == 1) {
            return -1;
        } else if (winner == -1) {
            return 1;
        } else if (isFull()) {
            return 0;
        } else {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 9; i++) {
                if (board[i] == 0) {
                    board[i] = player;
                    player = -player;
                    int score = -evaluate();
                    player = -player;
                    board[i] = 0;
                    if (score > bestScore) {
                        bestScore = score;
                    }
                }
            }
            return bestScore;
        }
    }
    
    private boolean isGameOver() {
        return isFull() || getWinner() != 0;
    }
    
    private boolean isFull() {
        for (int i = 0; i < 9; i++) {
            if (board[i] == 0) {
                return false;
            }
        }
        return true;
    }
    
    private int getWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i] != 0 && board[i] == board[i + 3] && board[i] == board[i + 6]) {
                return board[i];
            }
        }
        for (int i = 0; i < 9; i += 3) {
            if (board[i] != 0 && board[i] == board[i + 1] && board[i] == board[i + 2]) {
                return board[i];
            }
        }
        if (board[0] != 0 && board[0] == board[4] && board[0] == board[8]) {
            return board[0];
        }
        if (board[2] != 0 && board[2] == board[4] && board[2] == board[6]) {
            return board[2];
        }
        return 0;
    }
       
}

            