/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Account;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

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
    
     public void printAccounts(DefaultTableModel  model) {
        if (this.accounts.isEmpty()) {
            System.out.println("No hay usuarios en la lista.");
            return;
        }

        System.out.println("Lista de Usuarios:");
        for (Account  account : this.accounts) {
            System.out.println("ID: " + account.getId() + ", Balance: " + account.getBalance());
        }    
            this.accounts.sort((obj1, obj2) -> (obj1.getId().compareTo(obj2.getId())));
        
        for (Account account1 : this.accounts) {
            model.addRow(new Object[]{account1.getId(), account1.getId() + " " + account1.getBalance()});
        }
        
    }
}
