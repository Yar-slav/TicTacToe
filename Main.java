import java.util.Scanner;

public class Main {
    public static final String EMPTY = " ", CROSS = "X", ZERO = "O";
    public static final int LINE = 3, COLUMN = 3;
    static String[][] mas = new String[LINE][COLUMN];
    public static int gameStatus;
    public static final int GAME_NOT_FINISH = 0, DRAW = 1, X_WIN = 2, O_WIN = 3;
    public static String activePlayers;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        startGame();
        do {
            analiseBoard();
            board();
            if (gameStatus == X_WIN) {
                System.out.println("X wins");
            } else if (gameStatus == O_WIN) {
                System.out.println("O wins");
            } else if (gameStatus == DRAW) {
                System.out.println("Game end. Draw");
            }

            activePlayers = activePlayers == CROSS ? ZERO : CROSS;
        }
        while (gameStatus == GAME_NOT_FINISH);
    }

    public static void board() {
        System.out.println("---------");
        for (int i = 0; i < LINE; i++) {
            System.out.print("| ");
            for (int j = 0; j < COLUMN; j++) {
                System.out.print(mas[i][j] + " ");
            }
            System.out.println("| ");
        }
        System.out.println("---------");
    }

    public static void startGame() {
        for (int i = 0; i < LINE; i++) {
            for (int j = 0; j < COLUMN; j++) {
                mas[i][j] = EMPTY;
            }
        }
        activePlayers = CROSS;
        board();
    }

    public static void analiseBoard() {
        String winner = getWinner();
        if (winner.equals(CROSS)) {
            gameStatus = X_WIN;
        } else if (winner.equals(ZERO)) {
            gameStatus = O_WIN;
        } else if (emptyBoard()) {
            gameStatus = DRAW;
        } else {
            gameStatus = GAME_NOT_FINISH;
        }
    }

    public static String getWinner() {
        takeChar();
        String line = null;

        for (int i = 0; i < 8; i++) {
            switch (i) {
                case (0):
                    line = mas[0][0] + (mas[0][1]) + mas[0][2];
                    break;
                case (1):
                    line = mas[1][0] + mas[1][1] + mas[1][2];
                    break;
                case (2):
                    line = mas[2][0] + mas[2][1] + mas[2][2];
                    break;
                case (3):
                    line = mas[0][0] + mas[1][0] + mas[2][0];
                    break;
                case (4):
                    line = mas[0][1] + mas[1][1] + mas[2][1];
                    break;
                case (5):
                    line = mas[0][2] + mas[1][2] + mas[2][2];
                    break;
                case (6):
                    line = mas[0][0] + mas[1][1] + mas[2][2];
                    break;
                case (7):
                    line = mas[0][2] + mas[1][1] + mas[2][0];
                    break;
            }

            if (line.equals("XXX")) {
                return CROSS;
            } else if (line.equals("OOO")) {
                return ZERO;
            }
        }
        return EMPTY;
    }

    public static boolean emptyBoard() {
        for (int i = 0; i < LINE; i++) {
            for (int j = 0; j < COLUMN; j++) {
                if (mas[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void takeChar() {
        boolean take = false;
        do {
            System.out.print(activePlayers + " enter the coordinates: ");
            try {
                int line;
                int column;
                String str = scanner.nextLine();

                String[] s = str.split(" ");
                line = Integer.valueOf(s[0]) -1;
                column = Integer.valueOf(s[1]) -1;

                if (!(line >= 0 && line < LINE && column >= 0 && column < COLUMN)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (mas[line][column] != EMPTY) {
                    System.out.println("This cell is occupied! Choose another one!");
                }
                  else {
                    mas[line][column] = activePlayers;
                    take = true;
                }
            } catch (RuntimeException e) {
                System.out.println("You should enter numbers!");
            }
        }
        while (!take);
    }
}
