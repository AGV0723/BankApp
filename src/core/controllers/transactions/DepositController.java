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
            double amountDouble;

            // Validación del formato de ID
            destinationAccountId = destinationAccountId.trim();
            if (!destinationAccountId.matches("\\d{3}-\\d{6}-\\d{2}")) {
                return new Response("Id must follow the format XXX-XXXXXX-XX", Status.BAD_REQUEST);
            }

            // Validación del monto
            amount = amount.trim();
            try {
                amountDouble = Double.parseDouble(amount);
                if (amountDouble < 0) {
                    return new Response("Amount must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Amount must be numeric", Status.BAD_REQUEST);
            }

            // Acceso a datos
            AccountStorage accountStorage = AccountStorage.getInstance();
            TransactionStorage transactionStorage = TransactionStorage.getInstance();

            // Verificar si la cuenta destino existe
            Account destinationAccount = accountStorage.getAccount(destinationAccountId);
            if (destinationAccount == null) {
                return new Response("Destination account not found", Status.BAD_REQUEST);
            }

            // Realizar la transacción
            Transaction depositTransaction = new Transaction(TransactionType.DEPOSIT, null, destinationAccount, amountDouble);
            transactionStorage.addTransaction(depositTransaction);
            destinationAccount.setBalance(destinationAccount.getBalance() + amountDouble);

            return new Response("Deposit registered successfully", Status.CREATED);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
