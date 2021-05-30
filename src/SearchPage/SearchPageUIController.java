/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchPage;

import ServerLink.ServerConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Onyekachukwu
 */
public class SearchPageUIController implements Initializable {

    @FXML
    private ImageView imageNext;

    @FXML
    private Button BackBtn;

    @FXML
    private JFXTextField SearchBar;

    @FXML
    private Label CustomerName;

    @FXML
    private ImageView photoFrame;

    @FXML
    private JFXButton clearBtn;

    @FXML
    private void clearBtnAction() {
        SearchBar.clear();
    }

    // action for button(next)
    @FXML
    private void BackBtnAction() throws IOException {
        backToMainPage();
    }

    // action for image(next)
//    private void imageClickAction() throws IOException{
//        
//    }
    // method for going back to the prevoius page
    public void backToMainPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/imagedb/FrontPage.fxml"));
        Stage stage1 = (Stage) BackBtn.getScene().getWindow();
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.setTitle("Reg");
        stage1.close();
        stage.show();
    }

    Connection connection;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageNext.setPickOnBounds(true);
        imageNext.setOnMouseClicked(e -> {
            try {
                backToMainPage();
            } catch (IOException ex) {
                Logger.getLogger(SearchPageUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        clearBtn.setText(null);

        // funtion of the search bar
        SearchBar.setOnKeyReleased((KeyEvent event) -> {
            ServerLink.ServerConnection sc = new ServerConnection();

            try {
                String query = "SELECT Customer_name, Customer_photo FROM customers.customers WHERE Customer_name LIKE '" + SearchBar.getText()+"%'";
                connection = sc.link();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {
                    String name = rs.getString(1);
                    InputStream photoInputStream = rs.getBinaryStream(2);
                    CustomerName.setText(rs.getString(1));
                    Image image = new Image(photoInputStream);
                    photoFrame.setImage(image);
//                    CustomerName.setText(null);
//                    photoFrame.setImage(null);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SearchPageUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
