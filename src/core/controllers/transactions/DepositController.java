/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.transactions;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.storage.AccountStorage;
import core.models.storage.TransactionStorage;
import core.models.transactions.Transaction;
import core.models.transactions.type.TransactionType;

/**
 *
 * @author adrianonzalezrubiovilla
 */
public class DepositController {

    public static Response makeTrasaction(String destinationAccountId, String amount) {
        try {
            int destinationAccountIdInt; 
            double amountDouble;

            try {
                destinationAccountIdInt = Integer.parseInt(destinationAccountId.trim());
                if (destinationAccountIdInt < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            try {
                amountDouble = Double.parseDouble(amount.trim());
                if (amountDouble < 0) {
                    return new Response("Amount must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Amount must be numeric", Status.BAD_REQUEST);
            }

            AccountStorage accountStorage = AccountStorage.getInstance();
            TransactionStorage transactionStorage = TransactionStorage.getInstance();

            // Verificar si la cuenta destino existe
            Account destinationAccount = accountStorage.getAccount(destinationAccountIdInt);
            if (destinationAccount == null) {
                return new Response("Destination account not found", Status.BAD_REQUEST);
            }

            Transaction depositTransaction = new Transaction(TransactionType.DEPOSIT,null, destinationAccount,amountDouble);
            
            // Registrar la transacción en el TransactionStorage
            transactionStorage.addTransaction(depositTransaction);
            
            // Actualizar el saldo de la cuenta destino
            destinationAccount.setBalance(destinationAccount.getBalance() + amountDouble);
            
            return new Response("Deposit registered successfully", Status.CREATED);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
