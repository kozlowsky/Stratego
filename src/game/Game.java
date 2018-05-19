package game;

import ai.Minimax;

import java.awt.*;

public class Game {

    private int board[][];
    private int boardSize;
    private int currentPlayer;
    private int playerPoints[];
    private int movesCount;
    private boolean isGameOver;

    public Game(int boardSize) {
        this.boardSize = boardSize;
        board = new int[boardSize][boardSize];
        currentPlayer = 1;
        playerPoints = new int[2];
        playerPoints[0] = 0;
        playerPoints[1] = 0;
        movesCount = 0;
        isGameOver = false;
    }

    public void onFieldClicked(int x, int y) {
        GameLogic.markField(x, y, currentPlayer, board);
        playerPoints[currentPlayer - 1] += GameLogic.checkPoints(x, y, board);
        printBoard();

        movesCount++;
        if(movesCount == boardSize * boardSize)
            isGameOver = true;
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }

    private void printBoard() {
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
        return this.currentPlayer;
    }

    public boolean isGameOver() {
        return this.isGameOver;
    }

    public int[][] getBoard(){
        return board;
    }

    public Point getAIBestMove(int depth){
        Minimax m = new Minimax(depth);
        return m.miniMax(board, null, 0, true);
        }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
