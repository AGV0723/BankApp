/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.storage.AccountStorage;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class ListAccountController {
    public static Response showAccountList(DefaultTableModel  model){
       try {
            AccountStorage accountStorage = AccountStorage.getInstance();
            accountStorage.printAccounts(model);
            // Retornar respuesta exitosa si no hay excepciones
            return new Response("User list fetched successfully", Status.OK);
        } catch (Exception ex) {
            // Manejar cualquier excepción y retornar error
            return new Response("Unexpected error: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }
}
