/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.User;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class UserStorage {
    
     // Instancia Singleton
    private static UserStorage instance;
    
    // Atributos del Storage
    private ArrayList<User> persons;
    
    private UserStorage() {
        this.persons = new ArrayList<>();
    }
    
    public static UserStorage getInstance() {
        if (instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }
    
    public boolean addUser(User user) {
        for (User p : this.persons) {
            if (p.getId() == user.getId()) {
                return false;
            }
        }
        this.persons.add(user);
        return true;
    }
    
    public User getUser(int id) {
        for (User user : this.persons) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
    
    public boolean delUser(int id) {
        for (User user : this.persons) {
            if (user.getId() == id) {
                this.persons.remove(user);
                return true;
            }
        }
        return false;
    }
}
