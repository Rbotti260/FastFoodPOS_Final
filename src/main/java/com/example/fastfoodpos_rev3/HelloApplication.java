//***************************************************************
// Title:  Something POS
// Author:  Robert Botti
// Course Section: CMIS202-ONL (Seidel) Spring 2022
// File: FastFoodRestaurant
// Description:  Something Restaurant Point of Sale Application
//***************************************************************
package com.example.fastfoodpos_rev3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Welcome.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }



    public static void main(String[] args) {
        //Help.about();  // moved to separate window of application
        launch();
    }
}