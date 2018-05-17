package game;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Controller {

    @FXML
    public GridPane board;

    @FXML
    public Button add;

    @FXML
    public TextField size;

    public Game game;

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
                    rec.setOnMouseClicked(event -> {
                        if(game.getPlayer()==1) rec.setFill(new ImagePattern(new Image("/game/android.png")));
                        else rec.setFill(new ImagePattern(new Image("/game/apple.png")));
                        game.onFieldClicked(GridPane.getRowIndex(rec), GridPane.getColumnIndex(rec));
                        rec.setDisable(true);
                    });
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
}
