/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Scanner;

public class Inputter { // Nhập dữ liệu - Class tính năng
    // --- Validate Rule
    public static final String STUDENT_ID_VALIDATE = "^(SE|HE|DE|QE|CE)\\d{6}$";
    public static final String NAME_VALIDATE = "^[A-Za-zÀ-ỹ\\s]{2,20}$";
    public static final String PHONE_NUMBER_VALIDATE = "^(03[2-9]|05[6|8|9]|07[0|6-9]|08[1-5]|09\\d)\\d{7}$";
    public static final String EMAIL_VALIDATE = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$";
    public static final String MOUNTAIN_CODE_VALIDATE = "^[\\d]$";
    public static final String TUTION_FEE_VALIDATE = "^(03[2-9]|086|09[6-8]|08[1-5]|088|09[14])\\d{7}$";
    public static final String YES_NO_VALIDATE = "^(Y|N)$";
    static Scanner sc = new Scanner(System.in);

    // Bắt buộc nhập - Nhập ko đúng thì bắt nhập lại
    public static String input(String label){
        System.out.print(label);
        String input = sc.nextLine();
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


