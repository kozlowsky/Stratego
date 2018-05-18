package gui;

import ai.Minimax;
import ai.Node;
import game.CustomAlert;
import game.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.awt.*;

public class Controller {

    @FXML
    private GridPane board;

    @FXML
    private TextField size;

    @FXML
    private Label score1;

    @FXML
    private Label score2;


    private Game game;

    public void newGame() {
        game = new Game(getBoardSize());
        clearBoard();
        board.setGridLinesVisible(true);
        addFields();
    }

    private int getBoardSize() {
        int boardSize = 0;
        try {
            boardSize = Integer.valueOf(size.getText());
        } catch (NumberFormatException e) {
            e.getMessage();
        }

        return boardSize;
    }

    private void clearBoard() {
        score1.setText("0");
        score2.setText("0");
        board.getChildren().clear();
        board.getColumnConstraints().clear();
        board.getRowConstraints().clear();
    }

    private void addFields() {
        int boardSize = getBoardSize();
        if (boardSize > 1 && boardSize < 150) {
            for (int i = 0; i < boardSize; i++) {
                board.addColumn(i);
                board.getColumnConstraints().add(new ColumnConstraints(Constants.BOARD_WINDOW_SIZE * 1.0 / boardSize));
                for (int j = 0; j < boardSize; j++) {
                    Rectangle rec = new Rectangle(0, 0, Constants.BOARD_WINDOW_SIZE * 1.0 / boardSize, Constants.BOARD_WINDOW_SIZE * 1.0 / boardSize);
                    // Rectangles' on click event
                    rec.setOnMouseClicked(event -> updateRectangleAfterClick(rec));
                    if ((i + j) % 2 == 0)
                        rec.setFill(Color.BLACK);
                    else rec.setFill(Color.WHITE);
                    board.add(rec, i, j);
                }
            }
        } else {
            CustomAlert alert = new CustomAlert(Alert.AlertType.ERROR);
            alert.throwAlert("Wrong size input!", "Board size input you have entered is incorrect", "Please input correct board size in order to start new game.");
        }
    }

    private void updateRectangleAfterClick(Rectangle rec) {
        game.onFieldClicked(GridPane.getRowIndex(rec), GridPane.getColumnIndex(rec));
        if (game.getPlayer() == 1) {
            rec.setFill(new ImagePattern(new Image("/static/android.png")));
            score1.setText(String.valueOf(game.getPlayerPoints()[0]));
        } else {
            rec.setFill(new ImagePattern(new Image("/static/apple.png")));
            score2.setText(String.valueOf(game.getPlayerPoints()[1]));

        }

        if (game.isGameOver()) {
            CustomAlert alert = new CustomAlert(Alert.AlertType.INFORMATION);
            alert.throwAlert("GAME OVER", "THE RESULT OF THE GAME", getWinner() + " POINTS!");
        }

        game.changePlayer();
        rec.setDisable(true);
        Point point = game.getAIBestMove();
        if(point != null) {
            System.out.println(point);

        }
//        game.changePlayer();
    }

    private String getWinner() {
        if (Integer.valueOf(score1.getText()) > Integer.valueOf(score2.getText())) {
            return "PLAYER 1 IS A WINNER WITH " + Integer.valueOf(score1.getText());
        } else if (Integer.valueOf(score1.getText()) < Integer.valueOf(score2.getText())) {
            return "PLAYER 2 IS A WINNER WITH " + Integer.valueOf(score2.getText());
        } else return "DRAW";
    }
}
