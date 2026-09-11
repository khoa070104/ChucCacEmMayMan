/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entity.Circle;
import entity.Rectangle;
import entity.Shape;
import entity.Triangle;
import utils.Validator;

/**
 *
 * @author Admin
 */
public class Menu {

    public static Shape chooseShape() {
        System.out.println("---Add a new shape ---");
        System.out.println("1. Rectangle");
        System.out.println("2. Circle");
        System.out.println("3. Triangle");
        System.out.println("4. Display");
        int choice = Validator.getInt("input your choice", "number in 1 -4", "number in 1 - 4", 1, 4);
        
        switch (choice) {
            case 1:
                double width = Validator.getDouble("Please enter the width: ",
                         "Please enter number >0", "Invalid!",
                         Double.MIN_VALUE, Double.MAX_VALUE);
                double lenght = Validator.getDouble("Please enter the lenght: ", "Please enter number >0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
                return new Rectangle(width, lenght);

            case 2:
                double radius = Validator.getDouble("Please enter the radius: ", "Please enter number >0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
                return new Circle(radius);

            case 3:
                double sideA;
                double sideB;
                double sideC;
                while (true) {
                    sideA = Validator.getDouble("Please enter the SideA: ", "Please enter number >0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
                    sideB = Validator.getDouble("Please enter the SideB: ", "Please enter number >0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
                    sideC = Validator.getDouble("Please enter the SideC: ", "Please enter number >0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
                    if (sideA + sideB > sideC || sideA + sideC > sideB || sideB + sideC > sideA) {
                        break;
                    } else {
                        System.out.println("Sum of two side must be greater than remaining side.");
                    }
                }
                return new Triangle(sideA, sideB, sideC);

            case 4:
                return null;
        }
        return null;
    }
}
