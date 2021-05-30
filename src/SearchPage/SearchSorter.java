/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SearchPage;

import javafx.fxml.FXMLLoader;

/**
 *
 * @author Onyekachukwu
 */
public class SearchSorter {
    public SearchSorter(){
        System.out.println("I am a sorter");
    }
    
    public void sort(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SearchPage/SearchPageUI.fxml"));
        SearchPageUIController searchPageUIController = (SearchPageUIController)loader.getController();
    }
}
