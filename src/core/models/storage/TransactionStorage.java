/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import javax.swing.table.DefaultTableModel;
import core.models.transactions.Transaction;
import java.util.ArrayList;
import java.util.Collections;

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
        ArrayList<Transaction> transactionsCopy = (ArrayList<Transaction>) this.transactions.clone();
        Collections.reverse(transactionsCopy);
 
        for (Transaction transaction : transactionsCopy) {
            model.addRow(new Object[]{transaction.getType().name(), (transaction.getSourceAccount() != null ? transaction.getSourceAccount().getId() : "None"), (transaction.getDestinationAccount() != null ? transaction.getDestinationAccount().getId() : "None"), transaction.getAmount()});
        }
    }
}
