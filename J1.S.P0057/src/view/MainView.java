package view;

import controller.UserManager;
import controller.Inputter;
import model.Account;
import common.Messages;

public class MainView {
    private UserManager userManager;

    public MainView() {
        this.userManager = new UserManager();
    }

    public void displayMenu() {
        System.out.println("\n====== USER MANAGEMENT SYSTEM ======");
        System.out.println("1. Create a new account");
        System.out.println("2. Login system");
        System.out.println("3. Exit");
        System.out.println("=====================================");
    }

    public void run() {
        while (true) {
            displayMenu();
            int choice = getMenuChoice();
            
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    loginSystem();
                    break;
                case 3:
                    System.out.println(Messages.MSG_PROGRAM_EXIT);
                    return;
                default:
                    System.out.println(Messages.ERR_INVALID_CHOICE);
            }
        }
    }

    private int getMenuChoice() {
        int choice;
        while (true) {
            try {
                choice = Inputter.inputInt("> Choose: ");
                if (choice >= 1 && choice <= 3) {
                    return choice;
                } else {
                    System.out.println(Messages.ERR_INVALID_CHOICE);
                }
            } catch (Exception e) {
                System.out.println(Messages.ERR_INVALID_CHOICE);
            }
        }
    }

    private void createAccount() {
        System.out.println("\n--- Create New Account ---");
        
        String username = Inputter.inputUsername();
        String password = Inputter.inputPassword();
        
        Account newAccount = new Account(username, password);
        
        try {
            userManager.addAccount(newAccount);
            System.out.println(Messages.MSG_ACCOUNT_CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void loginSystem() {
        System.out.println("\n--- Login System ---");
        
        String username = Inputter.inputUsername();
        String password = Inputter.inputPassword();
        
        Account loginAccount = new Account(username, password);
        
        try {
            Account foundAccount = userManager.find(loginAccount);
            if (foundAccount != null) {
                System.out.println(Messages.MSG_LOGIN_SUCCESS);
            } else {
                System.out.println(Messages.ERR_INVALID_CREDENTIALS);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
