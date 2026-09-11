/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entity.Fibonacci;

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
        System.out.println("The 45 sequence fibonacci:");
        // In dãy số ra màn hình
        Fibonacci fibonacci = new Fibonacci(45);
        for (int i = 0; i < 45; i++) {
            System.out.print(fibonacci.getFibonacci(i));
            if (i == 44) {
                System.out.println(".");
            } else {
                System.out.print(", ");
            }
        }

    }

}
