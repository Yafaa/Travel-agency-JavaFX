/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Utils.MyDBcon;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yefet
 */
public class Travelbuddy {

    private int id;
    private String description;
    private String destination;
    private Date date_debut;
    private Date date_fin;
    private int id_user ;
    List<TravelGroup> travelGroups;
    private String picture ;
    
    public Travelbuddy() {}
    
    

    public Travelbuddy(String description, String destination, Date date_debut, Date date_fin, int id_user) {
        
        this.description = description;
        this.destination = destination;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_user = id_user;
        
    }
    
      public Travelbuddy(int id ,String description, String destination, Date date_debut, Date date_fin, int id_user , String picture) {
        this.id = id;
        this.description = description;
        this.destination = destination;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_user = id_user;
        this.picture = picture ; }
      
      
      public Travelbuddy(int id ,String description, String destination, Date date_debut, Date date_fin, int id_user) {
        this.id = id;
        this.description = description;
        this.destination = destination;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_user = id_user;
        
    }
      public Travelbuddy(String description, String destination, Date date_debut, Date date_fin, int id_user , String picture) {
        this.description = description;
        this.destination = destination;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_user = id_user;
        this.picture = picture ;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public int getId_user() {
        return id_user;
    }

    public String getPicture() {
        return picture;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setTravelGroups(List<TravelGroup> travelGroups) {
        this.travelGroups = travelGroups;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    
    

    public List<TravelGroup> getTravelGroups() {
        return travelGroups;
    }

    @Override
    public String toString() {
        return "Travelbuddy{" + "description=" + description + ", destination=" + destination + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }


    }


