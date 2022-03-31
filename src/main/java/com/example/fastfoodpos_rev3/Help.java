//***************************************************************
// Title:  Something POS
// Author:  Robert Botti
// Course Section: CMIS202-ONL (Seidel) Spring 2022
// File: FastFoodRestaurant
// Description:  Something Restaurant Point of Sale Application
//***************************************************************
package com.example.fastfoodpos_rev3;

import java.util.Scanner;

public class Help {
    public static void about() {

        Scanner enter = new Scanner(System.in);
        /** Displays the about page representing the function of this application
         */

        String aboutText = "This is a self service kiosk designed for restaurant customers to \n" +
                "to place their own orders. " + "When the customer \n" +
                "presses the checkout button, the platform will close and the \ntotal price of the order including the" +
                "the sales tax will populate.\n" +
                "This revision now features a user interface with touchscreen capability.";

        System.out.println("About:\n" + aboutText + "\n\nPress enter to continue");
        enter.nextLine();
    }
}
