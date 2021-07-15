package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.paint.*;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Image image = new Image("/Images/student.png");
        primaryStage.getIcons().add(image);
        primaryStage.setTitle("Student Login");
        primaryStage.setScene(new Scene(root));
        root.getStylesheets().add(getClass().getResource("Css.css").toExternalForm());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
