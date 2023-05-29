package java2.asignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static java2.asignment.ListController.connectDb;


public class AddNewController {

    private PreparedStatement prepare;

    @FXML
    private TextField txtClassRoom;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSchoolYear;

    public void insert(){
         Connection connect = connectDb();

         String sql = "insert into classinfo (name, classRoom, schoolYear) values (?, ?, ?)";


         try{
             if(txtName.getText().isEmpty() | txtClassRoom.getText().isEmpty() | txtSchoolYear.getText().isEmpty()){
                 Alert alert = new Alert(Alert.AlertType.ERROR);

                 alert.setTitle("Error Message");
                 alert.setHeaderText(null);
                 alert.setContentText("Enter all blank fields!");
                 alert.showAndWait();
             }
             else{

                 prepare = connect.prepareStatement(sql);

                 prepare.setString(1, txtName.getText());
                 prepare.setString(2, txtClassRoom.getText());
                 prepare.setInt(3, Integer.valueOf(txtSchoolYear.getText()));

                 prepare.executeUpdate();

                 txtName.clear();
                 txtClassRoom.clear();
                 txtSchoolYear.clear();
             }
         }
         catch (Exception e){

         }
    }

    @FXML
    void backToListClass(MouseEvent mouseEvent) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("list-class.fxml"));
        Main.mainStage.setScene(new Scene(root));
    }

}
