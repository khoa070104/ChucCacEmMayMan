/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entity.Array;
import java.util.Arrays;
import utils.Validator;

/**
 *
 * @author win
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int number = Validator.getInt("Enter number of array: ",
                "Number must be >0", "Invalid!", 1, Integer.MAX_VALUE);
        Array array = null;
        try {
            array = new Array(number);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.print("UnSorted array: ");
        System.out.println(Arrays.toString(array.getArray()));
        array.sort();
        System.out.print("Sorted array: ");
        System.out.println(Arrays.toString(array.getArray()));
        int key = Validator.getInt("Enter search value: ",
                "Error range!", "Invalid!", Integer.MIN_VALUE, Integer.MAX_VALUE);
        int index = array.binarySearch(key);
        if (index == -1) {
            System.out.println("Can not found");
        } else {
            System.out.println("Found " + key + " at index: " + index);
        }
    }

}
