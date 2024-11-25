/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.transactions.handler;

import core.controllers.transactions.Transactions;
import core.controllers.utils.Response;
import core.models.Account;
import core.models.storage.AccountStorage;

/**
 *
 * @author Usuario
 */
public class EventHandler {

    private String DestinationAccountId;
    private String SourceAccountId;
    private String Amount;

    public EventHandler(String DestinationAccountId, String SourceAccountId, String Amount) {
        
        this.DestinationAccountId = DestinationAccountId;
        this.SourceAccountId = SourceAccountId;
        this.Amount = Amount;
        
    }
    
    

    private Account getDestinationAccount() {
        
        int DestinationInt;
        DestinationInt = Integer.parseInt(DestinationAccountId);
        
        AccountStorage storage = AccountStorage.getInstance();
        Account accountD = storage.getAccount(DestinationInt);
        return accountD;
        
    } 
    
    private Account getSourceAccount() {
        
        int SourceInt;
        SourceInt = Integer.parseInt(SourceAccountId);
        
        AccountStorage storage = AccountStorage.getInstance();
        Account accountS = storage.getAccount(SourceInt);
        return accountS;
        
    } 
    
    private double getAmount(){
        double amountDouble;
        amountDouble = Double.parseDouble(Amount);
        return amountDouble;
    }
    

    public void MakeTransaction(Transactions transactions) {
        
    Account destinationAccount = getDestinationAccount();
    Account sourceAccount = getSourceAccount();
    
    double amountDouble = getAmount();
    
    destinationAccount.setBalance(transactions.execute(DestinationAccountId, SourceAccountId, Amount));
    }

}
