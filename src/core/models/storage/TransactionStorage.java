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
import core.models.transactions.Transaction;
import java.util.ArrayList;
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

    public ArrayList<Transaction> getAllTransactions() {
        return new ArrayList<>(this.transactions); // Devuelve una copia de la lista
    }

    public boolean addTransaction(Transaction transactions) {
        this.transactions.add(transactions);
        return true;
    }

    public void printTransactions(DefaultTableModel model) {
        if (this.transactions.isEmpty()) {
            System.out.println("No hay usuarios en la lista.");
            return;
        }
        
        

    }
}
