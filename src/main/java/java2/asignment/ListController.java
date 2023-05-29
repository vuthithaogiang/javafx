package java2.asignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.EOFException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListController implements Initializable {
   public static ObservableList<ClassRoom> listClassRoom = FXCollections.observableArrayList();

    @FXML
    private  TableView<ClassRoom> table_view;
    @FXML
    private TableColumn<ClassRoom, String> col_classRoom;

    @FXML
    private TableColumn<ClassRoom, String> col_name;

    @FXML
    private TableColumn<ClassRoom, Integer> col_schoolYear;


    public static ClassRoom updateClassRoom;

    @FXML
    void goToUpdateInfoClass(MouseEvent event) throws IOException {

        try{
             ClassRoom classRoom = table_view.getSelectionModel().getSelectedItem();

            int num = table_view.getSelectionModel().getSelectedIndex();

            if (num - 1 < -1) {
                throw new Exception("Click class failed!");

            }
            updateClassRoom = classRoom;
            Parent root = FXMLLoader.load(getClass().getResource("form-update-class.fxml"));

            Main.mainStage.setScene(new Scene(root));



        }
        catch (Exception e){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
            updateClassRoom = null;
        }

    }

    @FXML
    void goToAddNewClass(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("form-add-new-class.fxml"));
        Main.mainStage.setScene(new Scene(root));

    }

    public void showClass(){
       col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
       col_classRoom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
       col_schoolYear.setCellValueFactory(new PropertyValueFactory<>("yearSchool"));

       table_view.setItems(listClassRoom);

    }

    public static int findClassRoom(ObservableList<ClassRoom> list, ClassRoom classRoom){
        for(var c : list){
            if(c.getName().compareTo(classRoom.getName()) == 0
            && c.getClassRoom().compareTo(classRoom.getClassRoom()) == 0
            && c.getYearSchool() == classRoom.getYearSchool()) {
                return list.indexOf(c);
            }
        }
        return  -1;
    }


 @Override
 public void initialize(URL url, ResourceBundle resourceBundle) {
     table_view.setItems(listClassRoom);
     showClass();
 }
}
