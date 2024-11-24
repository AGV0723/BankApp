/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.User;
import core.models.storage.AccountStorage;
import core.models.storage.UserStorage;
/**
 *
 * @author Usuario
 */
public class AccountController {
    public static Response CreateAccount(String id, String Balance) {
        try {
            int idInt;
            double balanceInt;
            
            try {
                idInt = Integer.parseInt(id);
                if (idInt < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            try {
                balanceInt = Double.parseDouble(Balance);
                if (balanceInt < 0) {
                    return new Response("balance must be positive", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("balance must be numeric", Status.BAD_REQUEST);
            }
         
            AccountStorage storage = AccountStorage.getInstance(); 
            UserStorage userStorage = UserStorage.getInstance();
            
            User user = userStorage.getUser (idInt);
            
            if (!storage.addAccount(new Account(user, balanceInt))) {
                return new Response("A person with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Account created successfully", Status.CREATED);
            
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
}
