/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Account;
import core.models.User;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class AccountStorage {
    
    // Instancia Singleton
    private static AccountStorage instance;
    
    // Atributos del Storage
    private ArrayList<Account> accounts;
    
    private AccountStorage() {
        this.accounts = new ArrayList<>();
    }
    
    public static AccountStorage getInstance() {
        if (instance == null) {
            instance = new AccountStorage();
        }
        return instance;
    }
    
    public boolean addAccount(Account accounts) {
        for (Account p : this.accounts) {
            if (p.getId() == accounts.getId()) {
                return false;
            }
        }
        this.accounts.add(accounts);
        return true;
    }
    
    public Account getAccount(int id) {
        for (Account account : this.accounts) {
            if (Integer.parseInt(account.getId()) == id) {
                return account;
            }
        }
        return null;
    }
}
