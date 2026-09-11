/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


/**
 *
 * @author win
 */
public class Fibonacci {

    private int[] fibonacci;

    public Fibonacci(int numberOfFibo) {
        fibonacci = new int[numberOfFibo];
    }

    public int getFibonacci(int index) {
        if (index == 0 || index == 1) {
            return index;
        }
        //Kiểm tra xem F[n] đã được tính hay chưa? F[n]==0 thì là chưa được tính, != là đã tính
        if (fibonacci[index] != 0) {
            return fibonacci[index];
        }
        //Nếu F[n] chưa được tính thì đi tính nó và lưu lại
        fibonacci[index] = getFibonacci(index - 1) + getFibonacci(index - 2);
        return fibonacci[index];
    }
}
