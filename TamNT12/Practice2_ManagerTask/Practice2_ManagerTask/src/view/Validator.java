/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Validator {

    private static final Scanner SCANNER = new Scanner(System.in);

    private Validator() {

    }

    public static int getInt(String messageInfo, String messageErrorOutOfRange, String messageErrorNumber, int min, int max) {
        do {
            try {
                System.out.print(messageInfo);
                int number = Integer.parseInt(SCANNER.nextLine());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println(messageErrorOutOfRange);
                }
            } catch (Exception e) {
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }

    public static double getDouble(String messageInfo, String messageErrorOutOfRange, String messageErrorNumber, double min, double max) {
        do {
            try {
                System.out.print(messageInfo);
                double number = Double.parseDouble(SCANNER.nextLine());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println(messageErrorOutOfRange);
                }
            } catch (Exception e) {
                System.out.println(messageErrorNumber);
            }

        } while (true);

    }

    public static String getString(String messageInfo, String messageError, final String REGEX) {
        do {
            System.out.print(messageInfo);
            String str = SCANNER.nextLine();
            if (str.matches(REGEX)) {
                return str;
            } else {
                System.out.println(messageError);
            }
        } while (true);

    }

        public static Date getDateOrNull(String messageInfo, String messageError,final String format) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setLenient(false);
            while (true) {
                System.out.print(messageInfo);
                String input = SCANNER.nextLine().trim();
                if(input.isEmpty()){
                    return null;
                }
//ý là lối null là bình thường chương trình vẫn có thể chạy được , nhét xuống try catch khi sai định dạng input cần phải xử lý nếu không thì
//không chạy được chương trình
                try {
                    Date date = dateFormat.parse(input);

                        return date;//  chuyển từ string vừa nhập sang date, nếu nhập theo đúng định dạng thì return ra date không thì xuống catch bắt lỗi


                } catch (Exception e) {
                    System.out.println(messageError);
                }
            }

    }
}
