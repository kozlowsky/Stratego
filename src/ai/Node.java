package ai;

import java.awt.*;

public class Node {
    private Point point;
    private int score;

    public Node(Point point, int score) {
        this.point = point;
        this.score = score;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
