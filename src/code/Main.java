package code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("IS za prodaju igracaka");
        primaryStage.setScene(new Scene(root, 400, 375));
        primaryStage.show();

        //DB baza = DB.getInstance();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
