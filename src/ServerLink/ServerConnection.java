/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerLink;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Onyekachukwu
 */
public class ServerConnection {
    
   // initializing connection link to server
   Connection connection;
   // Server link
   String serverLink = "jdbc:mysql://localhost:3306/customers";
   String user = "root";
   String password = "";
   
   // constructing the class
   public ServerConnection(){
   }
   
   // creating funtions for connection
   public Connection link() throws SQLException{
       if (connection == null) {
           // when connection is down connect
          connection = (Connection)DriverManager.getConnection(serverLink,user,password); 
           System.out.println("Successfully connected");
       }else{
           // when two or link is open allow one route and close other
           connection.close();
       }
       return connection;
   }
}
