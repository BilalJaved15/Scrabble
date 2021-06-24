public class Board {
    private String[][] board;

    public Board() {
        board = new String[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                String boardVal = getDefaultBoardPos(i, j);
                board[i][j] = boardVal;
            }
        }
    }

    public String get(int row, int col) {
        if (row < 15 && col < 15) {
            return board[row][col];
        } else {
            return null;
        }
    }

    public void set(int row, int col, String s) {
        if (row < 15 && col < 15) {
            board[row][col] = s;
        }
    }

    public boolean isBlankPlace(int row, int col) {
        if (row < 0 || col < 0 || row > 14 || col > 14) {
            return false;
        }
        if (board[row][col].equals("")
                || board[row][col].equals("*")
                || board[row][col].equals("3L")
                || board[row][col].equals("3W")
                || board[row][col].equals("2L")
                || board[row][col].equals("2W")) {
            return true;
        }
        return false;
    }


    public static String getDefaultBoardPos(int row, int col) {
        int n = 14;
        if (row == n / 2 && col == n / 2) {
            return "*";
        } else if ((row == 0 || row == n / 2 || row == n) &&
                (col == 0 || col == n / 2 || col == n)) {
            return "3W";
        } else if (((row == 5 || row == 9) && (col == 1 || col == 5 || col == 9 || col == 13)) ||
                (row == 1 || row == 13) && (col == 5 || col == 9)) {
            return "3L";
        } else if ((row % 7 == 0 && (col == 3 || col == 11)) ||
                ((row == 6 || row == 8) && (col == 2 || col == 6 || col == 8 || col == 12) ||
                        ((row == 2 || row == 12) && (col == 6 || col == 8)) ||
                        ((row == 3 || row == 11) && (col % 7 == 0)))) {
            return "2L";
        } else if (row == col || (row + col == 14)) {
            return "2W";
        } else {
            return "";
        }
    }

    public void printBoard() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (board[i][j].length() == 1) {
                    System.out.print(board[i][j] + " ");
                } else {
                    System.out.print("-" + " ");
                }
            }
            System.out.println();
        }
    }
}

