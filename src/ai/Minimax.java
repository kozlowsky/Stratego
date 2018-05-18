package ai;


import game.Game;
import game.GameLogic;

import java.awt.*;
import java.util.ArrayList;

public class Minimax implements IStrategy {

    private Game currentGame;

    public Minimax(Game game) {
        this.currentGame = game;
    }

    public Node constructTree(Node node, int depth) {
        ArrayList<Point> listOfPossibleMoves = node.getAvailableMoves();
        for (Point p : listOfPossibleMoves) {
            int[][] newBoard = new int[node.getBoard().length][node.getBoard().length];
            for (int i = 0; i < newBoard.length; i++) {
                newBoard[i] = node.getBoard()[i].clone();
            }
            int currentPlayer = node.getPlayer() == 1 ? 2 : 1;
            newBoard[p.x][p.y] = currentPlayer;
            node.setCurrentPlayerPoints(GameLogic.checkPoints(p.x,p.y,newBoard));
            Node childNode = new Node(newBoard, currentPlayer);
            node.getChildren().put(p, constructTree(childNode, depth + 1));

        }
        return node;
    }
}
