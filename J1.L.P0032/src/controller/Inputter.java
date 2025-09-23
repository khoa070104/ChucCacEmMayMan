/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Scanner;

public class Inputter { // Nhập dữ liệu - Class tính năng
    // --- Validate Rule
    public static final String VALIDATE_FRAME_ID = "^F\\d{5}$";
    public static final String VALIDATE_ENGINE_ID = "^E\\d{5}$";
    
    static Scanner sc = new Scanner(System.in);

    // Bắt buộc nhập - Nhập ko đúng thì bắt nhập lại
    public static String input(String label){
        System.out.print(label);
        String input = sc.nextLine();
        return input;
    }
    
    public static String inputRequired(String label){
        String input;
        do{
            System.out.print(label);
            input = sc.nextLine();
        } while( input.isEmpty() );
        return input;
    }
    
    public static String inputRequired(String label, String regex){
        String input;
        do{
            System.out.print(label);
            input = sc.nextLine();
        } while( !input.matches(regex));
        return input;
    }

    // Nhập đúng thì trả về giá trị vừa nhập , còn bỏ trống thì trả về chuỗi rỗng
    // Có nhập , nhma nhập sai => Thì bắt nhập lại
    
    public static String inputOptional(String label){
        String input;
        while(true){
            System.out.print(label);
            input = sc.nextLine();
            if(input == null || input.isEmpty()) return "";
            else return input;
        }
    }
    
    public static String inputOptional(String label, String regex){
        String input;
        while(true){
            System.out.print(label);
            input = sc.nextLine();
            if(input == null || input.isEmpty()) return "";
            if(input.matches(regex)) return input;
        }
    }
}


