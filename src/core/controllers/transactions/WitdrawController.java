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
public class WitdrawController {

    public static Response makeTrasaction(String destinationAccountId, String amount) {
        try {
            // Validar si los campos están vacíos
            if (destinationAccountId == null || destinationAccountId.trim().isEmpty()) {
                return new Response("Destination account field cannot be empty", Status.BAD_REQUEST);
            }
            if (amount == null || amount.trim().isEmpty()) {
                return new Response("Amount field cannot be empty", Status.BAD_REQUEST);
            }

            double amountDouble;

            // Validación del formato de la cuenta
            destinationAccountId = destinationAccountId.trim();
            if (!destinationAccountId.matches("\\d{3}-\\d{6}-\\d{2}")) {
                return new Response("Destination account Id must follow the format XXX-XXXXXX-XX", Status.BAD_REQUEST);
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

            // Verificar si hay fondos suficientes
            if (destinationAccount.getBalance() < amountDouble) {
                return new Response("Insufficient funds", Status.BAD_REQUEST);
            }

            // Crear transacción de retiro
            Transaction withdrawTransaction = new Transaction(TransactionType.WITHDRAW, null, destinationAccount, amountDouble);

            // Registrar la transacción en el TransactionStorage
            transactionStorage.addTransaction(withdrawTransaction);

            // Actualizar el saldo de la cuenta destino
            destinationAccount.setBalance(destinationAccount.getBalance() - amountDouble);

            return new Response("Withdraw registered successfully", Status.CREATED);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
