/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public static Date getDate(String messageInfo, String messageError, final String REGEX) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        do {
            System.out.println(messageInfo);
            try {
                Date date = dateFormat.parse(SCANNER.nextLine());
                return date;
            } catch (Exception e) {
                System.out.println(messageError);
            }
        } while (true);

    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");// khởi tạo thuật toán băm
            byte[] messageDigest = md.digest(input.getBytes());// chuyển password từ kí tự sang byte dựa vào bảng ASCII(các byte tương ứng với số kí tự password
//sau đó thực hiện MD5 để chuyển sang 16byte
            BigInteger no = new BigInteger(1, messageDigest);// ghép thành một chuỗi số liên tục
//vậy số 1 để phòng trường hợp trong hệ cơ số 10 nếu byte đầu có số là số âm(>=128) tức khi chuyển sang hệ nhi phân số 1 đứng trước java sẽ 
//tự hiểu là số âm nên có số 1 để ép sang dương
            String hashText = no.toString(16);// chuyển đổi từ hệ cơ số 10 sang hexcamdecial(bằng cách chia cho 16) chỉ có dạng 0-9 A-F a-f
            while (hashText.length() < 32) {
                return hashText = "0" + hashText;
            }// nếu bigInteger tự ý bỏ số 0 ở đầu thì cộng thêm "0" cho đủ 32 kí tự
            return hashText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }

    }

}
