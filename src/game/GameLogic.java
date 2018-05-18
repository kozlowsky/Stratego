package game;

public class GameLogic {

    private static int[][] directions = { {-1, -1}, {1, 1}, {-1, 1}, {1, -1} };

    static void markField(int x, int y, int currentPlayer, int[][] board) {
        board[x][y] = currentPlayer;
    }

    public static int checkPoints(int x, int y, int[][] board) {
        int points = 0;

        if(checkRow(x, board))
            points += board.length;
        if(checkColumn(y, board))
            points += board.length;

        int pointsDiagonal = checkDiagonal(x, y, 0, board);
        int pointsDiagonalOffset = checkDiagonal(x, y, 2, board);
        if(pointsDiagonal > 0)
            pointsDiagonal++;
        if(pointsDiagonalOffset > 0)
            pointsDiagonalOffset++;

        points += pointsDiagonal + pointsDiagonalOffset;
        return points;
    }

    private static boolean checkRow(int x, int[][] board) {
        for(int i = 0; i < board.length; i++) {
            if(board[x][i] == 0)
                return false;
        }
        return true;
    }

    private static boolean checkColumn(int y, int[][] board) {
        for(int i = 0; i < board.length; i++) {
            if(board[i][y] == 0)
                return false;
        }
        return true;
    }

    private static int checkDiagonal(int x, int y, int offset, int[][] board) {
        int points = 0;
        for (int i = offset; i < directions.length / 2 + offset; i++) {
            int tmpX = x + directions[i][0];
            int tmpY = y + directions[i][1];

            while(tmpX >= 0 && tmpX < board.length && tmpY >= 0 && tmpY < board.length) {
                if(board[tmpX][tmpY] == 0)
                    return 0;

                tmpX += directions[i][0];
                tmpY += directions[i][1];
                points++;
            }
        }
        return points;
    }
}
