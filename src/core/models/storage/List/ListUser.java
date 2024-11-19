/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage.list;

import core.models.User;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ListUser {
    private ArrayList<User> users;
    
    public void saveData(int id, String firstName, String lastName, int age) {
        this.users.add(new User(id, firstName,lastName, age));
    
    }
    
}
