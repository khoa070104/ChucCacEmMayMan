/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import controller.Controller;
import controller.Menu;
import entity.Shape;

/**
 *
 * @author Admin
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("===Caculator Shape Program===");
        Controller controller = new Controller();

        while (true) {
            Shape shape = Menu.chooseShape();
            if (shape == null) {
                break;
            }
            controller.addShape(shape);
        }

        controller.display();
    }
}
