package java2.asignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.concurrent.Callable;

import static java2.asignment.ListController.listClassRoom;

public class FormAddNewController {

    @FXML
    private TextField txtClassRoom;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSchoolYear;

    @FXML
    void backToListClass(MouseEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("list-class.fxml"));
        Main.mainStage.setScene(new Scene(root));
    }

    @FXML
    public void onSubmit(ActionEvent actionEvent){
       try{
           String name = txtName.getText();
           String classRoom = txtClassRoom.getText();
           var schoolYear = Integer.valueOf(txtSchoolYear.getText());


           for(ClassRoom c : listClassRoom){
               if(c.getName().equals(name) && c.getClassRoom().equals(classRoom) && c.getSchoolYear() == schoolYear){
                   throw new Exception("This class is existed!!!");
               }

               if(schoolYear < 0) {
                   throw new Exception("This value of school year is invalid!!!");
               }
           }

          ClassRoom ClassInfo = new ClassRoom(name, classRoom, schoolYear);
           listClassRoom.add(ClassInfo);
           txtName.clear();
           txtClassRoom.clear();
           txtSchoolYear.clear();
       }
       catch (Exception e){
         Alert alert= new Alert(Alert.AlertType.ERROR);
         alert.setContentText(e.getMessage());
         alert.show();
       }

    }
}
