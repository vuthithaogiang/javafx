package java2.asignment;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

import static java2.asignment.ListController.connectDb;
import static java2.asignment.ListController.updateClassRoom;

public class UpdateController implements Initializable {

    private Statement statement;
    @FXML
    private TextField txtClassRoom;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSchoolYear;

    @FXML
    void backToListClass(MouseEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("list-class.fxml"));
        Main.mainStage.setScene(new Scene(root));
    }

    @FXML
    void delete() {
        String sql = "delete from classinfo where `name` = '" + txtName.getText() + "' and " +
                "`classRoom` = '" + txtClassRoom.getText() + "'";

        Connection connect = connectDb();

        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure that you want to delete it?");

            Optional<ButtonType> buttonType = alert.showAndWait();

            if(buttonType.get() == ButtonType.OK) {

                statement = connect.createStatement();
                statement.executeUpdate(sql);
            }
            else{
                return;
            }

            txtName.clear();
            txtClassRoom.clear();
            txtSchoolYear.clear();

        }
        catch(Exception e){

        }

    }

    @FXML
    void update() {
        Connection connect = connectDb();
        System.out.println(updateClassRoom.getName());
        System.out.println(updateClassRoom.getClassRoom());
        System.out.println(updateClassRoom.getSchoolYear());


        String sql = "update classinfo set `name` = '" + txtName.getText() +
                "', `classRoom` = '" + txtClassRoom.getText() +
                "', `schoolYear` = '" + Integer.valueOf(txtSchoolYear.getText()) +
                "' where  name = '" + updateClassRoom.getName() + "' and classRoom = '"
                + updateClassRoom.getClassRoom() + "' and schoolYear = " +
                updateClassRoom.getSchoolYear();

        try{

            if(txtName.getText().isEmpty() | txtClassRoom.getText().isEmpty() |
            txtClassRoom.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Enter all blank fields!");
                alert.showAndWait();
            }

            else{
                statement = connect.createStatement();
                statement.executeUpdate(sql);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("MarcoMan Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Update the data!");
                alert.showAndWait();
            }

        }
        catch (Exception e){

        }

        txtName.clear();
        txtClassRoom.clear();
        txtSchoolYear.clear();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(updateClassRoom != null){
            txtName.setText(updateClassRoom.getName());
            txtClassRoom.setText(updateClassRoom.getClassRoom());
            txtSchoolYear.setText(String.valueOf(updateClassRoom.getSchoolYear()));
        }
        else{
            System.out.println("Failed");
        }
    }
}
