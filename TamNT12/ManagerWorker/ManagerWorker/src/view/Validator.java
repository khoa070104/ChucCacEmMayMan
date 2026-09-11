/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class Validator {

    private static final Scanner sc = new Scanner(System.in);

    private Validator() {

    }

    public static int getInt(String msgInfo, String msgError, String msgOutOfRange, int min, int max) {
        do {
            try {
                System.out.print(msgInfo);
                int number = Integer.parseInt(sc.nextLine());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println(msgOutOfRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(msgError);
            }
        } while (true);
    }

    public static String getString(String msgInfo, String msgError, final String REGEX) {
        do {
            System.out.print(msgInfo);
            String str = sc.nextLine();
            if (str.matches(REGEX)) {
                return str;
            } else {
                System.out.println(msgError);
            }
        } while (true);

    }

    public static Date getDateOrNull(String messageInfo, String messageError, final String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        while (true) {
            System.out.print(messageInfo);
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }

            try {
                Date date = dateFormat.parse(input);

                return date;//  chuyển từ string vừa nhập sang date, nếu nhập theo đúng định dạng thì return ra date không thì xuống catch bắt lỗi

            } catch (Exception e) {
                System.out.println(messageError);
            }
        }
    }

    public static Date getDate(String messageInfo, String messageError, final String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        while (true) {
            System.out.print(messageInfo);
            String input = sc.nextLine().trim();
            try {
                return dateFormat.parse(input);
            } catch (Exception e) {
                System.out.println(messageError);
            }
        }
    }// tách ra getDate phục vụ cho option 2 và 3 vì nếu dùng chung getDateOrnull nếu người dùng ko nhập bất cứ thứ gì  thì date = null, truyền vào trong controlelr
    // khi so sánh d.before(today) sẽ bị crash chương trình vì null không có method before  
}
