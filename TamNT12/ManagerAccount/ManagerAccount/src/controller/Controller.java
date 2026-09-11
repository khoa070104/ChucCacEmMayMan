/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;

import model.Account;

import view.AccountInput;
import view.Validator;

/**
 *
 * @author Admin
 */
public class Controller {

    private ArrayList<Account> list = new ArrayList<>();
    private AccountInput view = new AccountInput();

    public boolean isExistUserName(String userName) {
        for (Account acc : list) {
            if (acc.getUserName().equalsIgnoreCase(userName)) {
                return true;
            }
        }
        return false;
    }

    public void addAccount() {
        Account acc = view.getAccout(this);
        acc.setPassword(Validator.getMD5(acc.getPassword()));
        list.add(acc);
        System.out.println("Account added successfull!");
    }

    public void login() {
        if (list.isEmpty()) {
            System.out.println("List is empty.Please enter add an account first!!");
            return;
        }
        String[] loginInfo = view.login();
        String hashPass = Validator.getMD5(loginInfo[1]);
        for (Account acc : list) {
            if (acc.getUserName().equalsIgnoreCase(loginInfo[0]) && acc.getPassword().equals(hashPass)) {
                view.afterLoginAccount(this, acc);
                return;
            }
        }
        System.out.println("Login fail!! UserName or Password is incorrect!!");
    }

 public boolean verifyOldPass(Account acc, String oldPass, String newPass) {
    if (!acc.getPassword().equals(Validator.getMD5(oldPass))) {
        System.out.println("Old pass is incorrect!!");
        return false;
    }
    if (oldPass.equals(newPass)) {
        System.out.println("New password must be different from old password!!");
        return false;
    } 
    acc.setPassword(Validator.getMD5(newPass));
    return true;
}
}
