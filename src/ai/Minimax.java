package ai;


import game.GameLogic;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Minimax implements IStrategy {

    private int maxDepth;

    public Minimax(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public Point miniMax(int[][] board, Point move, int depth, boolean isMax) {

        if(depth == maxDepth) {
            return move;
        }

        ArrayList<Point> availableMoves = getAvailableMoves(board);
        int maxScore = Integer.MIN_VALUE;
        int minScore = Integer.MAX_VALUE;
        Point bestMove = null;

        if(availableMoves.size() > 0) {
            bestMove = availableMoves.get(0);
        }

        for(Point point : availableMoves) {
            int[][] newBoard = new int[board.length][board.length];
            for (int i = 0; i < newBoard.length; i++) {
                newBoard[i] = board[i].clone();
            }
            newBoard[point.x][point.y] = 2;
            if(isMax) {
                Point nextMove = miniMax(newBoard, point, depth + 1, false);
                if(nextMove != null) {
                    int points = GameLogic.checkPoints(nextMove.x, nextMove.y, newBoard);
                    if(points > maxScore) {
                        maxScore = points;
                        bestMove = nextMove;
                    }
                }
            }
            else {
                Point nextMove = miniMax(newBoard, point, depth + 1, true);
                if(nextMove != null) {
                    int points = GameLogic.checkPoints(nextMove.x, nextMove.y, newBoard);
                    if(points < minScore) {
                        minScore = points;
                        bestMove = nextMove;
                    }
                }
            }
        }
        if( (isMax && maxScore == 0) || (!isMax && minScore == 0) )
            bestMove = availableMoves.get(new Random().nextInt(availableMoves.size()));
        return bestMove;
    }


    public Node miniMax2(int[][] board, Node move, int depth, boolean isMax) {

        if(depth == maxDepth) {
            return move;
        }

        ArrayList<Point> availableMoves = getAvailableMoves(board);
        int maxScore = Integer.MIN_VALUE;
        int minScore = Integer.MAX_VALUE;
        Node bestMove = null;

        if(availableMoves.size() > 0) {
            bestMove = new Node(availableMoves.get(0), 0);
        }

        for(Point point : availableMoves) {
            int[][] newBoard = new int[board.length][board.length];
            for (int i = 0; i < newBoard.length; i++) {
                newBoard[i] = board[i].clone();
            }
            newBoard[point.x][point.y] = 2;
            if(isMax) {
                Node nextMove = new Node(point, 0);
                nextMove = miniMax2(newBoard, nextMove, depth + 1, false);
                if(nextMove != null) {
                    int points = GameLogic.checkPoints(nextMove.getPoint().x, nextMove.getPoint().y, newBoard);
                    nextMove.setPoint(point);
                    if(points > maxScore) {
                        maxScore = points;
                        bestMove = nextMove;
                    }
                }
            }
            else {
                Node nextMove = new Node(point, 0);
                nextMove = miniMax2(newBoard, nextMove, depth + 1, true);
                if(nextMove != null) {
                    int points = GameLogic.checkPoints(nextMove.getPoint().x, nextMove.getPoint().y, newBoard);
                    nextMove.setPoint(point);
                    if(points < minScore) {
                        minScore = points;
                        bestMove = nextMove;
                    }
                }
            }
        }
        if( (isMax && maxScore == 0) || (!isMax && minScore == 0) ) {
            bestMove = new Node(availableMoves.get(new Random().nextInt(availableMoves.size())), 0);
        }

        return bestMove;
    }

    private ArrayList<Point> getAvailableMoves(int[][] board) {
        ArrayList<Point> emptyFields = new ArrayList<>();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                if(board[i][j] == 0)
                    emptyFields.add(new Point(i, j));
            }
        }
        return emptyFields;
    }
}
