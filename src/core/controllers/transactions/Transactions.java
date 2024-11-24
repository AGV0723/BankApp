/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.controllers.transactions;

import core.controllers.utils.Response;

/**
 *
 * @author Usuario
 */
public interface Transactions {
     public abstract Response makeTrasaction(String destinationAccountId, String sourceAccountId, String amount);
}
