/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.User;
import core.models.storage.UserStorage;

/**
 *
 * @author Usuario
 */
public class UserController {

    public static Response registerUser(String id, String firstname, String lastname, String age) {
        try {
            int idInt, ageInt;

            // Validación del ID
            try {
                idInt = Integer.parseInt(id);
                if (idInt < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
                if (id.length() < 9) {
                    return new Response("Id must have at least 9 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            // Validación del nombre
            if (firstname == null || firstname.trim().isEmpty()) {
                return new Response("Firstname must not be empty", Status.BAD_REQUEST);
            }

            // Validación del apellido
            if (lastname == null || lastname.trim().isEmpty()) {
                return new Response("Lastname must not be empty", Status.BAD_REQUEST);
            }

            // Validación de la edad
            try {
                ageInt = Integer.parseInt(age);
                if (ageInt < 18) {
                    return new Response("Age must be at least 18", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Age must be numeric", Status.BAD_REQUEST);
            }

            // Registro del usuario
            UserStorage storage = UserStorage.getInstance();
            if (!storage.addUser(new User(idInt, firstname, lastname, ageInt))) {
                return new Response("A person with that ID already exists", Status.BAD_REQUEST);
            }

            return new Response("Person created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
}
