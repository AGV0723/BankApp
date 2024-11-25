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
                return new Response("Source account field cannot be empty", Status.BAD_REQUEST);
            }

            if (amount == null || amount.trim().isEmpty()) {
                return new Response("Amount field cannot be empty", Status.BAD_REQUEST);
            }

            double amountDouble;

            // Validación del formato de las cuentas
            destinationAccountId = destinationAccountId.trim();
            sourceAccountId = sourceAccountId.trim();

            if (!destinationAccountId.matches("\\d{3}-\\d{6}-\\d{2}")) {
                return new Response("Destination account Id must follow the format XXX-XXXXXX-XX", Status.BAD_REQUEST);
            }

            if (!sourceAccountId.matches("\\d{3}-\\d{6}-\\d{2}")) {
                return new Response("Source account Id must follow the format XXX-XXXXXX-XX", Status.BAD_REQUEST);
            }

            // Validación del monto
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
            Account destinationAccount = accountStorage.getAccount(destinationAccountId);
            if (destinationAccount == null) {
                return new Response("Destination account not found", Status.BAD_REQUEST);
            }

            // Verificar si la cuenta fuente existe
            Account sourceAccount = accountStorage.getAccount(sourceAccountId);
            if (sourceAccount == null) {
                return new Response("Source account not found", Status.BAD_REQUEST);
            }

            // Verificar si la cuenta fuente tiene saldo suficiente
            if (sourceAccount.getBalance() < amountDouble) {
                return new Response("Insufficient funds", Status.BAD_REQUEST);
            }

            Transaction transferTransaction = new Transaction(TransactionType.TRANSFER, sourceAccount, destinationAccount, amountDouble);

            // Registrar la transacción en el TransactionStorage
            transactionStorage.addTransaction(transferTransaction);

            // Actualizar los saldos de ambas cuentas
            sourceAccount.setBalance(sourceAccount.getBalance() - amountDouble);
            destinationAccount.setBalance(destinationAccount.getBalance() + amountDouble);

            return new Response("Transfer registered successfully", Status.CREATED);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

}
