package ai;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Node {

    private int board[][];
    private int size;
    private int player;
    private Map<Point, Node> children;
    private int currentPlayerPoints;

    public Node(int[][] board, int player){
        this.size = board.length;
        this.board = board;
        children = new HashMap<>();
        this.player = player;
        this.currentPlayerPoints = 0;
    }

    ArrayList<Point> getAvailableMoves(){
        ArrayList<Point> emptyCells = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(board[i][j] == 0){
                    emptyCells.add(new Point(i, j));
                }
            }
        }
        return emptyCells;
    }

    int[][] getBoard(){
        return board;
    }

    int getPlayer(){
        return player;
    }

    Map<Point, Node> getChildren(){
        return children;
    }

    void setCurrentPlayerPoints(int currentPlayerPoints) {
        this.currentPlayerPoints = currentPlayerPoints;
    }
}
