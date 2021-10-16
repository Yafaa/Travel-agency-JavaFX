/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;

/**
 *
 * @author Lenovo
 */
public class TravelMessage {

    private int FromWhom;
    private int TowHOMS;
    private String message;
    private Timestamp messageTime ;

    public TravelMessage() {
    }

    public TravelMessage(int FromWhom, int TowHOMS, String message) {
        this.FromWhom = FromWhom;
        this.TowHOMS = TowHOMS;
        this.message = message;
    }

    public int getFromWhom() {
        return FromWhom;
    }

    public int getTowHOMS() {
        return TowHOMS;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getMessageTime() {
        return messageTime;
    }
    
    

}
