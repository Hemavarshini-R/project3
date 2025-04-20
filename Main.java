import java.util.Scanner;

class Player {
    String name;
    char symbol;

    Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }
}
class TicTacToe {
    private char[][] board;
    private Player player1, player2;
    private Player currentPlayer;

    public TicTacToe(Player p1, Player p2) {
        board = new char[3][3];
        player1 = p1;
        player2 = p2;
        currentPlayer = player1;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = '-';
    }

    public void printBoard() {
        System.out.println("\nBoard:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }

    public boolean playTurn(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
            board[row][col] = currentPlayer.symbol;
            return true;
        } else {
            System.out.println("Invalid move. Try again.");
            return false;
        }
    }

    public boolean checkWin() {
        char s = currentPlayer.symbol;

        for (int i = 0; i < 3; i++)
            if ((board[i][0] == s && board[i][1] == s && board[i][2] == s) ||
                (board[0][i] == s && board[1][i] == s && board[2][i] == s))
                return true;

        if ((board[0][0] == s && board[1][1] == s && board[2][2] == s) ||
            (board[0][2] == s && board[1][1] == s && board[2][0] == s))
            return true;

        return false;
    }

    public boolean checkDraw() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == '-')
                    return false;
        return true;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Player 1 name: ");
        Player p1 = new Player(sc.nextLine(), 'X');

        System.out.print("Enter Player 2 name: ");
        Player p2 = new Player(sc.nextLine(), 'O');

        TicTacToe game = new TicTacToe(p1, p2);

        while (true) {
            game.printBoard();
            Player current = game.getCurrentPlayer();

            System.out.println(current.name + "'s turn (" + current.symbol + ")");
            System.out.print("Enter row (0-2): ");
            int row = sc.nextInt();
            System.out.print("Enter col (0-2): ");
            int col = sc.nextInt();

            if (game.playTurn(row, col)) {
                if (game.checkWin()) {
                    game.printBoard();
                    System.out.println(current.name + " wins!");
                    break;
                } else if (game.checkDraw()) {
                    game.printBoard();
                    System.out.println("It's a draw!");
                    break;
                }
                game.switchPlayer();
            }
        }

        sc.close();
    }
}
