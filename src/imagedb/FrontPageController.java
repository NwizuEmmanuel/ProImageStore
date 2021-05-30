/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagedb;

import ServerLink.ServerConnection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Onyekachukwu
 */
public class FrontPageController implements Initializable {

    @FXML
    private TextField nameFld;

    @FXML
    private Button addPhotoBtn;

    @FXML
    private ImageView photoFrame;

    @FXML
    private Button saveBtn;

    @FXML
    private Button searchCusBtn;

    String path;

    @FXML
    private void searchCusBtnAction() throws IOException {
        // giving the search customers button a function when its clicked to pop a new page with search components
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SearchPage/SearchPageUI.fxml"));
        loader.load();
        Parent parent = loader.getRoot();
        Scene scene = new Scene(parent);
        // creating another stage for the previous page 
        Stage stage1 = (Stage) searchCusBtn.getScene().getWindow();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Search");
        stage.show();
        stage1.close();
    }

    // creating a file chooser
    // declaring extensions
    @FXML
    private void addPhotoBtnAction() throws FileNotFoundException {
        // initializing filechooser
        FileChooser chooserh = new FileChooser();

        // declaring extensions for jpg, png, PNG, JPG
        FileChooser.ExtensionFilter imgExtension = new FileChooser.ExtensionFilter("jpg files", "*.jpg");
        FileChooser.ExtensionFilter PNGExtension = new FileChooser.ExtensionFilter("PNG files", "*.PNG");
        FileChooser.ExtensionFilter JPGExtension = new FileChooser.ExtensionFilter("JPG files", "*.JPG");
        FileChooser.ExtensionFilter pngExtension = new FileChooser.ExtensionFilter("png files", "*.png");

        // adding extensions to file chooser inorder to filter
        chooserh.getExtensionFilters().addAll(imgExtension, pngExtension, PNGExtension, JPGExtension);

        // displaying the file chooser
        File file = chooserh.showOpenDialog(addPhotoBtn.getScene().getWindow());
        // set file name to name
        String name = file.getName();
        // set path to path
        path = file.getAbsolutePath();

        // serializing image file
        FileInputStream fileInputStream = new FileInputStream(file);
        // identifying file as image
        Image image = new Image(fileInputStream);
        // setting image to photo frame
        photoFrame.setImage(image);
        // just testing
//        ServerConnection sc = new ServerConnection();
//        try {
//            System.out.println(sc.link());
//        } catch (SQLException ex) {
//            Logger.getLogger(FrontPageController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    // initializing connection to server
    Connection connection;
    File file;

    @FXML
    private void saveBtnAction() throws SQLException, FileNotFoundException {
        System.out.println("-----------------");
        // storing text field (for name)object to name var
        String name = nameFld.getText();

        // calling serverConnection class
        ServerConnection sc = new ServerConnection();
        // link connection to serverConnection link
        connection = sc.link();

        // creating sql command
        String sqlCmd = "INSERT INTO customers(Customer_name, Customer_photo) VALUES (?,?)";
        // initializing link to database
        PreparedStatement ps = connection.prepareStatement(sqlCmd);

        // add stuffs to db through connection link
        ps.setString(1, name);

        // setting image path to file
        file = new File(path);

        // inputting file to database
        FileInputStream fileInputStream = new FileInputStream(file);
        ps.setBinaryStream(2, fileInputStream, (int) file.length());
        ps.execute();
        
        // calling an alert of success
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Reg");
        alert.setContentText("Successfully added");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
