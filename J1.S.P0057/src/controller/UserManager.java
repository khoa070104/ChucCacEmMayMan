package controller;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import common.Constants;
import common.Messages;

public class UserManager {
    private List<Account> accounts;
    private String filename;

    public UserManager() {
        this.accounts = new ArrayList<>();
        this.filename = Constants.USER_FILE;
        loadAccounts();
    }

    public void addAccount(Account acc) throws Exception {
        if (acc == null || acc.getUsername() == null || acc.getPassword() == null) {
            throw new IllegalArgumentException("Account information is invalid");
        }

        if (isUsernameExists(acc.getUsername())) {
            throw new IllegalArgumentException(Messages.ERR_USERNAME_EXISTS);
        }

        accounts.add(acc);

        saveAccounts();
    }

    public Account find(Account acc) throws Exception {
        if (acc == null || acc.getUsername() == null || acc.getPassword() == null) {
            throw new IllegalArgumentException("Account information is invalid");
        }

        loadAccounts();

        for (Account account : accounts) {
            if (account.getUsername().equals(acc.getUsername()) && 
                account.getPassword().equals(acc.getPassword())) {
                return account;
            }
        }

        return null; 
    }

    private boolean isUsernameExists(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private void loadAccounts() {
        accounts.clear();
        File file = new File(filename);
        
        if (!file.exists()) {
            return; 
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Account account = (Account) ois.readObject();
                    accounts.add(account);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(Messages.ERR_FILE_READ);
        }
    }

    private void saveAccounts() throws Exception {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename, false))) {
            for (Account account : accounts) {
                oos.writeObject(account);
            }
        } catch (IOException e) {
            throw new Exception(Messages.ERR_FILE_WRITE);
        }
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts);
    }

    public int getAccountCount() {
        return accounts.size();
    }
}
