/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagedb;

import ServerLink.ServerConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Onyekachukwu
 */
public class ImageDB extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        ServerConnection serverConnection = new ServerConnection();
        serverConnection.link();
        System.out.println("Connected successfully");
        
        Parent root = FXMLLoader.load(getClass().getResource("FrontPage.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
