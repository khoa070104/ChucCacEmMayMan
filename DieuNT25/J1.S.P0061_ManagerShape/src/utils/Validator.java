/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Validator {
    private static final Scanner SCANNER = new Scanner(System.in);
    
    private Validator(){
    
    }
    public static int getInt(String messageInfo, String messageErrorOutOfRange, String messageErroNumber, int min , int max){
        do{
            try{
                System.out.print(messageInfo);
                int number = Integer.parseInt(SCANNER.nextLine());
                if(number >= min && number <= max){
                    return number;
                }
                else{
                    System.out.println(messageErrorOutOfRange);
                }
                
            }catch(NumberFormatException e){
                System.out.println(messageErroNumber);
            }
        }while(true);
    }
    
    public static double getDouble(String messageInfo, String messageErrorOutOfRange, String messageErroNumber, double min , double max){
        do{
            try{
                System.out.print(messageInfo);
                double number = Double.parseDouble(SCANNER.nextLine());
                if(number >= min && number <= max){
                    return number;
                }
                else{
                    System.out.println(messageErrorOutOfRange);
                }
                
            }catch(NumberFormatException e){
                System.out.println(messageErroNumber);
            }
        }while(true);
    }
}
