package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
//        Game game = new Game(3);
//        game.onFieldClicked(0, 0);
//        game.printBoard();
//        game.onFieldClicked(1, 1);
//        game.printBoard();
//        game.onFieldClicked(2, 2);
//        game.printBoard();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
