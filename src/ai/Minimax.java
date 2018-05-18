package ai;


import game.Game;
import game.GameLogic;

import java.awt.*;
import java.util.ArrayList;

public class Minimax implements IStrategy {

    private Game currentGame;
    private int[] playerPoints;

    public Minimax(Game game) {
        this.currentGame = game;
        this.playerPoints = new int[2];
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
            Node childNode = new Node(newBoard, currentPlayer);
            childNode.setCurrentPlayerPoints(GameLogic.checkPoints(p.x, p.y, newBoard));
            childNode.setPoint(p);
//            if(depth == 1) System.out.println("CHILD Score: " + childNode.getCurrentPlayerPoints() + " Depth: " + depth + " " + childNode.getPoint());
            node.getChildren().put(p, constructTree(childNode, depth + 1));
        }
        Node bestChild = null;
        if (depth % 2 == 0) {
            int minimumInt = Integer.MIN_VALUE;
            for (Node child : node.getChildren().values()) {
                if (child.getCurrentPlayerPoints() > minimumInt) {
                    minimumInt = child.getCurrentPlayerPoints();
                    bestChild = child;
                }
            }
        } else {
            int maximumInt = Integer.MAX_VALUE;
            for (Node child : node.getChildren().values()) {
                if (child.getCurrentPlayerPoints() < maximumInt) {
                    maximumInt = child.getCurrentPlayerPoints();
                    bestChild = child;
                }
            }
        }
        if (bestChild != null) {
            playerPoints[depth % 2] += bestChild.getCurrentPlayerPoints();
            node.setCurrentPlayerPoints(bestChild.getCurrentPlayerPoints());
            node.setBestChildren(bestChild);
        }
        if(depth == 1) System.out.println("Score: " + node.getCurrentPlayerPoints() + " Depth: " + depth + " " + node.getPoint());

        return node;
    }
}
