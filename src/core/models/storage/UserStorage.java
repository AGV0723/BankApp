/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;
import core.models.User;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

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
    public void printUsers(DefaultTableModel  model) {
        if (this.persons.isEmpty()) {
            System.out.println("No hay usuarios en la lista.");
            return;
        }

        System.out.println("Lista de Usuarios:");
        for (User  user : this.persons) {
            System.out.println("ID: " + user.getId() + ", Nombre: " + user.getFirstname() + ", Apellido: " + user.getLastname()+ ", age: " + user.getAge());
        }    
            this.persons.sort((obj1, obj2) -> (obj1.getId() - obj2.getId()));
        
        for (User user1 : this.persons) {
            model.addRow(new Object[]{user1.getId(), user1.getFirstname() + " " + user1.getLastname(), user1.getAge(), user1.getNumAccounts()});
        }
        
    }
}
