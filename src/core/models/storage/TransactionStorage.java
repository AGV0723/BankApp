/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;
import core.models.Account;
import core.models.User;
import core.models.transactions.Transaction;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class TransactionStorage {
    
      // Instancia Singleton
    private static TransactionStorage instance;
    
    // Atributos del Storage
    private ArrayList<Transaction> transactions;
    
    private TransactionStorage() {
        this.transactions = new ArrayList<>();
    }
    
    public static TransactionStorage getInstance() {
        if (instance == null) {
            instance = new TransactionStorage();
        }
        return instance;
    }
    
    public boolean addTransaction(Transaction transactions) {
        this.transactions.add(transactions);
        return true;
    }
    
     public void printTransactions(DefaultTableModel  model) {
        if (this.transactions.isEmpty()) {
            System.out.println("No hay usuarios en la lista.");
            return;
        }

        System.out.println("Lista de Usuarios:");
        for (Transaction  transaction : this.transactions) {
            System.out.println("ID: " + user.getId() + ", Nombre: " + user.getFirstname() + ", Apellido: " + user.getLastname()+ ", age: " + user.getAge());
        }    
            this.transactions.sort((obj1, obj2) -> (obj1.getId() - obj2.getId()));
        
        for (User user1 : this.transactions) {
            model.addRow(new Object[]{user1.getId(), user1.getFirstname() + " " + user1.getLastname(), user1.getAge(), user1.getNumAccounts()});
        }
        
    }

}
