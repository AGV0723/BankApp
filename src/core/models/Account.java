/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;
import java.util.Random;
/**
 *
 * @author edangulo
 */
public class Account {

  
    private String id;
    private User owner;
    private double balance;

    public Account(User owner) {
        this.id = this.createID();
        this.owner = owner;
        this.balance = 0;
        this.owner.addAccount(this);
    }

    public Account(User owner, double balance) {
        this.id = this.createID();
        this.owner = owner;
        this.balance = balance;

        this.owner.addAccount(this);
    }

    public String getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    

    public void deposit(double amount) {
        this.balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > this.balance) {
            return false;
        }
        this.balance -= amount;
        return true;
    }
    
    public int getOwnerID (){
        return owner.getId();
    }

    private String createID() {
        Random random = new Random();
        int first = random.nextInt(1000);
        int second = random.nextInt(1000000);
        int third = random.nextInt(100);

        String accountId = String.format("%03d", first) + "-" + String.format("%06d", second) + "-" + String.format("%02d", third);
        

        return accountId;    

    }
}
