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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import java.sql.*;

public class ListController implements Initializable {

    private static Connection connect;

    private PreparedStatement prepare;

    private Statement statement;

    private ResultSet result;


    @FXML
    private  TableView<ClassRoom> table_view;
    @FXML
    private  TableColumn<ClassRoom, String> col_classRoom;

    @FXML
    private  TableColumn<ClassRoom, String> col_name;

    @FXML
    private   TableColumn<ClassRoom, Integer> col_schoolYear;


    public static ClassRoom updateClassRoom;

    public static   Connection connectDb() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/classinfo",
                    "root", "");

            System.out.println("Connect successfully!");

            return connect;
        }
        catch(Exception e){
            System.out.println("Connect failed!!");
        }

        return null;
    }



    public  ObservableList<ClassRoom> dataList() {

        connect = connectDb();
        ObservableList<ClassRoom> dataList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM classinfo";

        try{

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ClassRoom classRoom;

            while (result.next()){
                classRoom = new ClassRoom(result.getString("name"),
                        result.getString("classRoom"),
                        result.getInt("schoolYear"));

                dataList.add(classRoom);
            }
        }
        catch (Exception e){

        }

        return dataList;

    }

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
        ObservableList<ClassRoom> showList = dataList();

       try {
            col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_classRoom.setCellValueFactory(new PropertyValueFactory<>("classRoom"));
            col_schoolYear.setCellValueFactory(new PropertyValueFactory<>("schoolYear"));

            System.out.println(showList.size());

            table_view.setItems(showList);
        }
       catch (Exception e){

       }

    }





    public static int findClassRoom(ObservableList<ClassRoom> list, ClassRoom classRoom){
        for(var c : list){
            if(c.getName().compareTo(classRoom.getName()) == 0
            && c.getClassRoom().compareTo(classRoom.getClassRoom()) == 0
            && c.getSchoolYear() == classRoom.getSchoolYear()) {
                return list.indexOf(c);
            }
        }
        return  -1;
    }


 @Override
 public void initialize(URL url, ResourceBundle resourceBundle) {

     showClass();
 }
}
