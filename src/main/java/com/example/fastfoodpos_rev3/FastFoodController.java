//***************************************************************
// Title:  Something POS
// Author:  Robert Botti
// Course Section: CMIS202-ONL (Seidel) Spring 2022
// File: FastFoodRestaurant
// Description:  Something Restaurant Point of Sale Application
//***************************************************************
package com.example.fastfoodpos_rev3;


import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FastFoodController implements Initializable {
    private static OrderList menuList = new OrderList();


    /**
     * Populates the contents from item and price ArrayLists
      */
    @FXML
    protected ListView lvOrderList = new ListView();
    @FXML
    protected ListView lvOrderPrice = new ListView();


    /** Generates properties for the ListView
     *
     */

    protected ListProperty<String> listProperty = new SimpleListProperty<>();

    protected ListProperty<Double> listPrice = new SimpleListProperty<>();


    /** These methods are now in place of the switch statements that were used to populate the ArrayLists.  When the user presses
     * a button the program will populate one ArrayList with the item ordered and the other with the price of the item.  In addition to the
     * ArrayLists being populated, the program will also print a message to the console to confirm the item ordered.
     * @param e
     */

    public void btnHamburger (ActionEvent e){  //orders Hamburger
        menuList.addOrderItem("Hamburger");  //adds item to ArrayList
        menuList.addOrderPrice(8.50);  //adds price to ArrayList
        menuList.addComboNumber(1);  //adds item number to ArrayList
        System.out.println("Hamburger");  //prints item to console
        listProperty.set(FXCollections.observableArrayList(menuList.orderList)); //populates item in listview
        listPrice.set(FXCollections.observableArrayList(menuList.orderPrice));   //populates price of item in listview
    }

    public void btnChickenTenders (ActionEvent e){ //orders Chicken Tenders
        menuList.addOrderItem("Chicken Tenders");  //adds item to ArrayList
        menuList.addOrderPrice(9.00);  //adds price to ArrayList
        menuList.addComboNumber(2);  //adds item number to ArrayList
        System.out.println("Chicken Tenders");  //prints item to console
        listProperty.set(FXCollections.observableArrayList(menuList.orderList));  //populates item in listview
        listPrice.set(FXCollections.observableArrayList(menuList.orderPrice));  //populates price of item in listview
    }

    public void btnHotDog (ActionEvent e){  //orders Hot Dog
        menuList.addOrderItem("Hot Dog");  //adds item to ArrayList
        menuList.addOrderPrice(6.00);  //adds price to ArrayList
        menuList.addComboNumber(3);  //adds item number to ArrayList
        System.out.println("Hot Dog");  //prints item to console
        listProperty.set(FXCollections.observableArrayList(menuList.orderList));  //populates item in listview
        listPrice.set(FXCollections.observableArrayList(menuList.orderPrice));  //populates price of item in listview
    }

    public void btnCheeseSteak (ActionEvent e){  //Orders Cheese Steak
        menuList.addOrderItem("Cheese Steak");  //adds item to ArrayList
        menuList.addOrderPrice(10.25);  //adds price to ArrayList
        menuList.addComboNumber(4);  //adds item number to ArrayList
        System.out.println("Cheese Steak");  //prints item to console
        listProperty.set(FXCollections.observableArrayList(menuList.orderList));  //populates item in listview
        listPrice.set(FXCollections.observableArrayList(menuList.orderPrice));  //populates price of item in listview
    }

    public void btnChickenCheeseSteak (ActionEvent e){  //Orders Chicken Cheese Steak
        menuList.addOrderItem("Chicken Cheese Steak");  //adds item to ArrayList
        menuList.addOrderPrice(10.50);  //adds price to ArrayList
        menuList.addComboNumber(5);  //adds item number to ArrayList
        System.out.println("Chicken Cheese Steak");  //prints item to console
        listProperty.set(FXCollections.observableArrayList(menuList.orderList));  //populates item in listview
        listPrice.set(FXCollections.observableArrayList(menuList.orderPrice));  //populates price of item in listview
    }


    public void btnVeggieBurger (ActionEvent e){  //Orders Veggie Burger
        menuList.addOrderItem("Veggie Burger");  //adds item to ArrayList
        menuList.addOrderPrice(8.50);  //adds price to ArrayList
        menuList.addComboNumber(6);  //adds item number to ArrayList
        System.out.println("Veggie burger");  //prints item to console
        listProperty.set(FXCollections.observableArrayList(menuList.orderList));  //populates item in listview
        listPrice.set(FXCollections.observableArrayList(menuList.orderPrice));  //populates price of item in listview
    }

    public void btnCheckOut (ActionEvent e) throws IOException {  //Completes the customers order
        menuList.printOrderList();  //prints completed order to console.
        Platform.exit();


    }

    /**
     * Launches the order now window.
     * @param e
     * @throws IOException
     */
    @FXML
    public void btnWelcomeClose(ActionEvent e) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("FastFood.fxml")); //pulls the FXML file required to place orders

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("OrderNow");
        stage.setScene(scene);
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        lvOrderList.itemsProperty().bind(listProperty);  //displays item in list on the user interface
        lvOrderPrice.itemsProperty().bind(listPrice);  //displays price of item in list on the user interface


    }


}
