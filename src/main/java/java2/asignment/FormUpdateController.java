package java2.asignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



import static java2.asignment.ListController.*;


public class FormUpdateController  implements Initializable {



    @FXML
    private TextField txtClassRoom;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSchoolYear;

    @FXML
    void backToListClass(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("list-class.fxml"));
        Main.mainStage.setScene(new Scene(root));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(updateClassRoom != null){
             var index = findClassRoom(listClassRoom, updateClassRoom);

            System.out.println(index);
            System.out.println(listClassRoom.size());

            txtName.setText(updateClassRoom.getName());
            txtClassRoom.setText(updateClassRoom.getClassRoom());
            txtSchoolYear.setText(String.valueOf(updateClassRoom.getSchoolYear()));
        }
        else{
            System.out.println("is null");
        }
    }

    public void update(ActionEvent actionEvent){
        int index = findClassRoom(listClassRoom, updateClassRoom);
         try{
              ClassRoom upClass = new ClassRoom();
              String name = txtName.getText();
              String classRoom = txtClassRoom.getText();
              var schoolYear = Integer.valueOf(txtSchoolYear.getText());

              upClass.setName(name);
              upClass.setClassRoom(classRoom);
              upClass.setSchoolYear(schoolYear);

             System.out.println(listClassRoom.get(index).getName());


              if(index != -1 && findClassRoom(listClassRoom, upClass) == -1){
                  listClassRoom.remove(listClassRoom.get(index));
                  listClassRoom.add(index, upClass);
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setContentText("Update class successfully!!");
                 alert.show();
              }
              else if(findClassRoom(listClassRoom, upClass) != -1){
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setContentText("This class is existed. Please try again!!");
                  alert.show();
              }
              else{
                  System.out.println("Failed");;
              }
         }
         catch (Exception e) {
             Alert alert= new Alert(Alert.AlertType.ERROR);
             alert.setContentText(e.getMessage());
             alert.show();
         }
    }

    public void delete(ActionEvent actionEvent){
        try{
            listClassRoom.remove(updateClassRoom);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Delete successfully!");
            alert.show();
        }
        catch (Exception e){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }
}
