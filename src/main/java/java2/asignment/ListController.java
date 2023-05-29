package java2.asignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

    @FXML
    private MenuBar fileMenu;
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

                return ;

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

    public void handleSave(ActionEvent actionEvent){
        Stage secondaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save file Class room Table");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        if(dataList().isEmpty()) {
            secondaryStage.initOwner(this.fileMenu.getScene().getWindow());
            Alert alter = new Alert(Alert.AlertType.ERROR, "EMPTY Table", ButtonType.OK);

            alter.setContentText("You have nothing to save");
            alter.initModality(Modality.APPLICATION_MODAL);
            alter.initOwner(this.fileMenu.getScene().getWindow());
            alter.showAndWait();

            if(alter.getResult() == ButtonType.OK){
                alter.close();
            }
        }
        else{
            File file = fileChooser.showSaveDialog(secondaryStage);
            if(file != null){
                saveFile(table_view.getItems(), file);
            }
        }

    }

    public void saveFile(ObservableList<ClassRoom> observableList, File file){
        try{
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));

            for(ClassRoom classRoom : observableList){
                outWriter.write(classRoom.toString());
                outWriter.newLine();
            }

            outWriter.close();

        }
        catch (IOException e){
            Alert alter = new Alert(Alert.AlertType.ERROR, "OOPS!", ButtonType.OK);
            alter.setContentText("Sorry. An error has occurred.");
            alter.showAndWait();

            if(alter.getResult() == ButtonType.OK){
                alter.close();
            }
        }
    }

   public void closeApp(ActionEvent actionEvent){

   }
    @Override
 public void initialize(URL url, ResourceBundle resourceBundle) {

     showClass();
 }
}
