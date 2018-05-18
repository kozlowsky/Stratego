package ai;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Node {

    int board[][];
    int size;
    int player;
    Map<Point, Node> children;

    public Node(int[][] board, int player){
        this.size = board.length;
        this.board = board;
        children = new HashMap<>();
        this.player = player;
    }

    public ArrayList<Point> getAvailableMoves(){
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

    public int[][] getBoard(){
        return board;
    }

    public int getPlayer(){
        return player;
    }

    public Map<Point, Node> getChildren(){
        return children;
    }
}
