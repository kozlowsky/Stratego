package game;

public class Game {

    private int board[][];
    private int boardSize;
    private int player;
    private int playerPoints[];

    private static int[][] directions = { {-1, -1}, {1, 1}, {-1, 1}, {1, -1} };

    public Game(int boardSize) {
        this.boardSize = boardSize;
        board = new int[boardSize][boardSize];
        player = 1;
        playerPoints = new int[2];
        playerPoints[0] = 0;
        playerPoints[1] = 0;
    }

    public void onFieldClicked(int x, int y) {
        markField(x, y);
        checkPoints(x, y);
        changePlayer();
        printBoard();
    }

    private void markField(int x, int y) {
        board[x][y] = player;
    }

    private void checkPoints(int x, int y) {
        if(checkRow(x))
            playerPoints[player - 1] += boardSize;
        if(checkColumn(y))
            playerPoints[player - 1] += boardSize;

        int pointsDiagonal = checkDiagonal(x, y, 0);
        int pointsDiagonalOffset = checkDiagonal(x, y, 2);
        if(pointsDiagonal > 0)
            pointsDiagonal++;
        if(pointsDiagonalOffset > 0)
            pointsDiagonalOffset++;

        playerPoints[player - 1] += pointsDiagonal + pointsDiagonalOffset;
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

    private int checkDiagonal(int x, int y, int offset) {
        int points = 0;
        for (int i = offset; i < directions.length / 2 + offset; i++) {
            int tmpX = x + directions[i][0];
            int tmpY = y + directions[i][1];

            while(tmpX >= 0 && tmpX < boardSize && tmpY >= 0 && tmpY < boardSize) {
                if(board[tmpX][tmpY] == 0)
                    return 0;

                tmpX += directions[i][0];
                tmpY += directions[i][1];
                points++;
            }
        }
        return points;
    }

    private void changePlayer() {
        player = (player == 1) ? 2 : 1;
    }

    public void printBoard() {
        System.out.println("Player One points: " + playerPoints[0]);
        System.out.println("Player Two points: " + playerPoints[1]);
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j]);
            }
            System.out.print("\n");
        }
        System.out.println("-----------");
    }

    public int[] getPlayerPoints() {
        return this.playerPoints;
    }

    public int getPlayer() {
        return this.player;
    }
}
