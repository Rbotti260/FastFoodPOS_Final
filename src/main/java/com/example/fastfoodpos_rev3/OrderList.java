//***************************************************************
// Title:  Something POS
// Author:  Robert Botti
// Course Section: CMIS202-ONL (Seidel) Spring 2022
// File: FastFoodRestaurant
// Description:  Something Restaurant Point of Sale Application
//***************************************************************
package com.example.fastfoodpos_rev3;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class OrderList {
    /** Array Lists to store the items ordered as well as the prices
     *
     */
    public ArrayList<String> orderList = new ArrayList<String>(); // String list for items ordered
    public ArrayList<Double> orderPrice = new ArrayList<Double>(); // Double list to store prices of items ordered
    public ArrayList<Integer> itemNumber = new ArrayList<Integer>(); // Integer to setup sorting algorithm



    /**Method to add items ordered to the shopping cart
     *
     * @param item
     */
    public void addOrderItem(String item) {
        orderList.add(item); //adds items to that array list.

    }

    /** Method to add item numbers to order
     *
     * */
    public void addComboNumber(Integer comboNumber) {
        itemNumber.add(comboNumber);  //
    }

    /** Method for adding item numbers to heap
     *
     * @param objects
     * @param <addComboNumber>
     */
    public <addComboNumber> void Heap(addComboNumber[] objects) {
        for (int i = 0; i < objects.length; i++)
            itemNumber.add(i);  // inputs numbers from comboNumber method into Heap
    }

    /**method to add pricing for items ordered
     *
     * @param price
     */

    public void addOrderPrice(double price) {
        orderPrice.add(price);//assigns price to items in array list.

    }

    /** Prints list of items ordered along with total price of order
     *
     */
    public void printOrderList() throws FileNotFoundException {
        String receiptNumber = String.format("%.0f", Math.floor(Math.random() * (999 - 100 + 1) + 100));
        PrintWriter outputfile = new PrintWriter("Receipt.txt");
        outputfile.println("You have " + orderList.size() + " items in your shopping cart.");
        System.out.println("You have " + orderList.size() + " items in your shopping cart.");
        System.out.println("Your order number is: " + receiptNumber);
        outputfile.println("Your order number is: " + receiptNumber);
        for (int i = 0; i < orderList.size(); i++) {
            outputfile.println((i + 1) + ". " + orderList.get(i));
            System.out.println((i + 1) + ". " + orderList.get(i)); //retrieves the list of items in the array list.

        }
        outputfile.println("The total price of your order is:  $" + String.format("%.2f", totalPrice()*1.06));
        System.out.println("The total price of your order is:  $" + String.format("%.2f", totalPrice()*1.06));
        outputfile.close();
    }

    /** Calculates the total price of the items in the shopping cart
     *
     * @return
     */
    public double totalPrice() {
        double total = 0;
        for (int i = 0; i < orderPrice.size(); i++) //for loop to calculate the sum of the items in the array list (shopping cart).
            total += orderPrice.get(i);
        //double taxTotal = total * 1.06;
        return total;
    }

}
