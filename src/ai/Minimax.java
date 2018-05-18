package ai;


import java.awt.*;
import java.util.ArrayList;

public class Minimax implements IStrategy {

    private int count = 0;

    public int constructTree(Node node){
        count++;
        ArrayList<Point> listOfPossibleMoves = node.getAvailableMoves();
        for (Point p : listOfPossibleMoves) {
            int[][] newBoard = new int[node.getBoard().length][node.getBoard().length];
            for (int i = 0; i < newBoard.length; i++) {
                newBoard[i] = node.getBoard()[i].clone();
            }
            int currentPlayer = node.getPlayer() == 1 ? 2 : 1;
            newBoard[p.x][p.y] = currentPlayer;
            Node childNode = new Node(newBoard, currentPlayer);
            node.getChildren().put(p, childNode);
            constructTree(childNode);
        }
        return count;
    }
}
