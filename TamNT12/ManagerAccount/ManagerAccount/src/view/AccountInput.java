/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.Date;
import model.Account;

/**
 *
 * @author Admin
 */
public class AccountInput {

    public int showMenu() {
        System.out.println("============ Login Program =========\n"
                + "1. Add User\n"
                + "2. Login\n"
                + "3) Exit");
        return Validator.getInt("Please choice one option:", "Just 1-3", "Invalid", 1, 3);
    }

    public Account getAccout(Controller controller) {
        System.out.println("---------- Add User --------");
        String userName;
        while (true) {
            userName = Validator.getString("UserName: ", "Username cannot empty", ".*[^ ].*");
            if (controller.isExistUserName(userName)) {
                System.out.println("UserName already existed!! Try another one!!");
            } else {
                break;
            }
        }
        String password = Validator.getString("Password: ", "Password cannot empty!!", ".*[^ ].*");
        String name = Validator.getString("Name: ", "Name cannot empty!!", ".*[^ ].*").trim();
        String phone = Validator.getString("Phone: ", "Phone must be 10 or 11 number!!", "[0-9]{10,11}");
        String email = Validator.getString("Email: ", "Email invalid format.", "[A-Za-z0-9_.-]+@[A-Za-z0-9.-]+");
        String address = Validator.getString("Address: ", "Address cannot empty!!", ".*[^ ].*").trim();
        Date dob = Validator.getDate("Date Of Birth: ", "Date of birth is vaild format dd/MM/yyyy", "dd/MM/yyyy");
        return new Account(userName, password, name, phone, email, address, dob);
    }

    public String[] login() {
        System.out.println("------------- Login ----------------");
        String userName = Validator.getString("Account: ", "Account cannot empty", ".*[^ ].*").trim();
        String password = Validator.getString("Password: ", "Password cannot empty", ".*[^ ].*").trim();
        return new String[]{userName, password};
    }

    public void afterLoginAccount(Controller controller, Account acc) {
        System.out.println("------------ Wellcome -----------");
        String action = Validator.getString("Hi " + acc.getUserName() + ", do you want change password now? Y/N:",
                "Just y or n", "[YNyn]");

        if (action.equalsIgnoreCase("Y")) {
            while (true) {
                String oldPassword = Validator.getString("Old Password: ", "Cannot empty!!", ".*[^ ].*");

                while (true) {
                    String newPassword = Validator.getString("New Password: ", "Cannot empty!!", ".*[^ ].*").trim();
                    String rePassword = Validator.getString("Re Password: ", "Cannot empty!!", ".*[^ ].*").trim();

                    if (!newPassword.equals(rePassword)) {
                        System.out.println("New password not match rePassword. Please try again!");
                        continue;
                    }

                    // Gọi controller và kiểm tra kết quả boolean
                    if (controller.verifyOldPass(acc, oldPassword, newPassword)) {
                        System.out.println("Change password successfull");
                        return; // Đổi thành công, thoát hàm
                    } else {
                        break;
                    }
                }
            }
        }
    }

}
