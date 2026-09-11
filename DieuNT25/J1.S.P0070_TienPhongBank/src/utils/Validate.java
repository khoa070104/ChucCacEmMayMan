/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;

public class Validate {

    public static final String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static final int CAPTCHA_LENGTH = 5;
    public static final String ACCOUNT_NUMBER = "^[0-9]{10}$";
    public static final String PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,31}$";
    public static final String TEXT = "^[A-Za-z0-9 ,\\.]+$";
    public static final String CAPTCHA = "^[A-Za-z0-9]{5}$";
    private static final Scanner SCANNER = new Scanner(System.in);

    private Validate() {

    }

    public static int getInt(String messageInfo, String messsageErrorOutOfRange,
            String messageErrorNumber, int min, int max) {
        do {
            try {
                System.out.print(messageInfo);
                int number = Integer.parseInt(SCANNER.nextLine().trim());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println(messsageErrorOutOfRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }

    public static String getString(String messageInfo, String messageError, final String REGEX) {
        do {
            System.out.print(messageInfo);
            String str = SCANNER.nextLine().trim();
            if (str.matches(REGEX)) {
                return str;
            }
            System.out.println(messageError);
        } while (true);
    }

    public static String getString(String messageInfo) {
        return SCANNER.nextLine().trim();
    }
}
