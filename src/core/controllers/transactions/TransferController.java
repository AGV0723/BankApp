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
public class TransferController {
    
    public static Response makeTrasaction(String destinationAccountId, String sourceAccountId, String amount) {
        
        try {
            
            if (destinationAccountId == null || destinationAccountId.trim().isEmpty()) {
                return new Response("Destination account field cannot be empty", Status.BAD_REQUEST);
            }
            
            if (sourceAccountId == null || sourceAccountId.trim().isEmpty()) {
                return new Response("Source Account field cannot be empty", Status.BAD_REQUEST);
            }
            
            if (amount == null || amount.trim().isEmpty()) {
                return new Response("Amount field cannot be empty", Status.BAD_REQUEST);
            }
            
            int sourceAccountIdInt, destinationAccountIdInt;
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
                sourceAccountIdInt = Integer.parseInt(sourceAccountId.trim());
                if (sourceAccountIdInt < 0) {
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
            
            // Verificar si la cuenta fuente existe
            Account sourceAccount = accountStorage.getAccount(sourceAccountIdInt);
            if (sourceAccount == null) {
                return new Response("Source account not found", Status.BAD_REQUEST);
            }

            // Verificar si la cuenta fuente tiene plata suficiente
            if (sourceAccount.getBalance() < amountDouble) {
                return new Response("Insufficient funds", Status.BAD_REQUEST);
            }

            Transaction transferTransaction = new Transaction(TransactionType.TRANSFER, sourceAccount, destinationAccount, amountDouble);

            // Registrar la transacción en el TransactionStorage
            transactionStorage.addTransaction(transferTransaction);

            // Actualizar el saldos de ambas cuentas
            sourceAccount.setBalance(destinationAccount.getBalance() - amountDouble);
            destinationAccount.setBalance(destinationAccount.getBalance() + amountDouble);

            return new Response("Transfer registered successfully", Status.CREATED);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
}
