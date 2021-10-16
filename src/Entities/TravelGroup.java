/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.List;
import javax.print.DocFlavor;

/**
 *
 * @author Yefet
 */
public class TravelGroup {
    
    
    
    private int id ;
    private List<Travelbuddy> Members ;
    private String title ; 
    private String destination ;
    private Date date_debut ;
    private String picture ;
    private Date date_fin ;
    private String plan ;

    public TravelGroup(){}
    
    public TravelGroup(String title, String destination, Date date_debut, Date date_fin, String plan) {
        this.title = title;
        this.destination = destination;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.plan = plan;
        
    }
    public TravelGroup(String title, String destination, Date date_debut, Date date_fin, String plan,String picture) {
        this.title = title;
        this.destination = destination;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.plan = plan;
        this.picture = picture ;
        
    }
    public TravelGroup(int id ,String title, String destination, Date date_debut, Date date_fin, String plan) {
        this.id = id ;
        this.title = title;
        this.destination = destination;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.plan = plan;
        
        
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMembers(List<Travelbuddy> Members) {
        this.Members = Members;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
    

    public int getId() {
        return id;
    }

    public List<Travelbuddy> getMembers() {
        return Members;
    }

    public String getTitle() {
        return title;
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

    public String getPlan() {
        return plan;
    }

    public String getPicture() {
        return picture;
    }
    

    @Override
    public String toString() {
        return "TravelGroup{title=" + title + ", destination=" + destination + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", plan=" + plan + '}';
    }
    
    
    
    

}
