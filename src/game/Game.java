package game;

public class Game {

    private int board[][];
    private int boardSize;
    private int player;
    private int playerPoints[];

    private static int[][] directions = { {-1, -1}, {-1, 1}, {1, 1}, {1, -1} };

    public Game(int boardSize) {
        this.boardSize = boardSize;
        board = new int[boardSize][boardSize];
        player = 1;
        playerPoints = new int[2];
    }

    private void markField(int x, int y) {
        board[x][y] = player;
    }

    private void checkPoints(int x, int y) {
        if(checkRow(x))
            playerPoints[player - 1] += boardSize;
        if(checkColumn(y))
            playerPoints[player - 1] += boardSize;

        playerPoints[player - 1] += checkDiagonal(x, y);
    }

    private boolean checkRow(int x) {
        for(int i = 0; i < boardSize; i++) {
            if(board[x][i] == 0)
                return false;
        }
        return true;
    }

    private boolean checkColumn(int y) {
        for(int i = 0; i < boardSize; i++) {
            if(board[i][y] == 0)
                return false;
        }
        return true;
    }

    private int checkDiagonal(int x, int y) {
        int points = 0;
        for (int[] direction : directions) {
            int tmpX = x;
            int tmpY = y;

            while(tmpX >= 0 && tmpX < boardSize && tmpY >= 0 && tmpY < boardSize) {
                if(board[tmpX][tmpY] == 0)
                    return 0;

                tmpX += direction[0];
                tmpY += direction[1];
                points++;
            }
        }
        return points;
    }

    private void changePlayer() {
        player = (player == 1) ? 0 : 1;
    }
}
