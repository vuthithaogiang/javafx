package java2.asignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    public static  void main(String[] args){
        launch(args);
    }

    public static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("list-class.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Java2 Session 1");
        primaryStage.show();

    }
}
