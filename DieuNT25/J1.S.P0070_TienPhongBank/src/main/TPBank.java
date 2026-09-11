/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entity.Account;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import utils.Validate;

/**
 *
 * @author win
 */
public class TPBank {

    private List<Account> listAccount;
    private Random random;

    public TPBank() {
        listAccount = new ArrayList<>();
        random = new Random();

        listAccount.add(new Account("1029817261", "tuan26062002"));
        listAccount.add(new Account("1039817261", "minh12345677"));
        listAccount.add(new Account("1049817261", "chung3245677"));
        listAccount.add(new Account("1059817261", "dhdi129YIUQWIE"));
    }

    public List<Account> getListAccount() {
        return listAccount;
    }

    public void login(Locale locale) {

        ResourceBundle resourceBundle = ResourceBundle.getBundle("resources/Language", locale);
        Account validAccount = null;

        // 1. Vòng lặp nhập TÀI KHOẢN: Sai thì báo lỗi và lặp lại việc nhập
        while (true) {
            String account = Validate.getString(
                    resourceBundle.getString("account"),
                    resourceBundle.getString("accountInvalid"),
                    Validate.ACCOUNT_NUMBER);

            validAccount = getAccount(account);

            if (validAccount != null) {
                break; // Tài khoản tồn tại -> Thoát vòng lặp, đi tiếp xuống bước nhập mật khẩu
            } else {
                // Báo lỗi và vòng lặp sẽ tự động quay lại dòng nhập tài khoản
                System.out.println(resourceBundle.getString("accountIncorrect"));
            }
        }

        // 2. Vòng lặp nhập MẬT KHẨU: Sai thì báo lỗi và lặp lại việc nhập
        while (true) {
            String password = Validate.getString(
                    resourceBundle.getString("password"),
                    resourceBundle.getString("passwordInvalid"),
                    Validate.PASSWORD);

            if (validAccount.getPassword().equals(password)) {
                break; // Mật khẩu đúng -> Thoát vòng lặp, đi tiếp xuống bước Captcha
            } else {
                // Báo lỗi và vòng lặp sẽ tự động quay lại dòng nhập mật khẩu
                System.out.println(resourceBundle.getString("passwordIncorrect"));
            }
        }

        // 3. Đã vượt qua 2 vòng lặp trên (tức là tài khoản và mật khẩu đều đúng) -> Chạy Captcha
        String captchaGenerate = this.generateCaptcha(Validate.CAPTCHA_LENGTH);
        System.out.println(resourceBundle.getString("captcha") + captchaGenerate);

        while (true) {
            if (verifyCaptcha(
                    resourceBundle.getString("inputCaptcha"),
                    captchaGenerate
            )) {
                System.out.println(resourceBundle.getString("loginSuccess"));
                break;
            } else {
                System.out.println(resourceBundle.getString("captchaInvalid"));
            }
        }
    }

    private String generateCaptcha(int length) {
        StringBuilder captchaGen = new StringBuilder();
        for (int i = 0; i < length; i++) {
            captchaGen.append(Validate.ALPHA_NUMERIC.charAt(random.nextInt(Validate.ALPHA_NUMERIC.length())));
        }
        return captchaGen.toString();
    }

    // Hàm này giúp tìm xem tài khoản có tồn tại trong hệ thống hay không
    private Account getAccount(String account) {
        for (Account a : this.listAccount) {
            if (a.getAccount().equals(account)) {
                return a; // Trả về object Account nếu tìm thấy
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    //Ham xac thuc capcha
    private boolean verifyCaptcha(String message, String captchaGenerate) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        String captchaInput = sc.nextLine();
        return captchaGenerate.equals(captchaInput);
    }
}
