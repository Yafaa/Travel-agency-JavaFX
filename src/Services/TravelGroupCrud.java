/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.TravelGroup ;
import Entities.Travelbuddy;
import Entities.User;
import Utils.MyDBcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yefet
 */
public class TravelGroupCrud {
    
    
       public static int GetLastGroupId() throws SQLException {

        Connection con = MyDBcon.getInstance().getCon();
        String req = "SELECT MAX(id_travel_group) FROM `travel_group`";
        try {

            Statement stm = con.createStatement();
            ResultSet resultat = stm.executeQuery(req);
            while (resultat.next()) {
                int lastGroupId = resultat.getInt(1);
                return lastGroupId ;

            }
        } catch (SQLException ex) {
            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0 ;
    }

               
              

    public static void AjouterTravelGroup(TravelGroup t, int id_user/*createur*/) throws SQLException {
        try {
            Connection con = MyDBcon.getInstance().getCon();

            String req = "INSERT INTO `travel_group`( `title`, `destination`, `date_debut`, `date_fin` , `plan`) VALUES (?,?,?,?,?)";
            PreparedStatement pstm = con.prepareStatement(req);
            pstm.setString(1, t.getTitle());
            pstm.setString(2, t.getDestination());
            pstm.setDate(3, t.getDate_debut());
            pstm.setDate(4,  t.getDate_fin());
            pstm.setString(5, t.getPlan());
            pstm.executeUpdate();
            
            
//            String req1 ="INSERT INTO `group_members` (`id_travel_group`,`id_travel_buddy`) VALUES (?,?)" ;
//            PreparedStatement stm = con.prepareStatement(req1) ;
//            stm.setInt(1, GetLastGroupId()) ;
//            stm.setInt(2,t1.getId());   // ====> big prblm how to solve it //
//            stm.executeUpdate() ;
//            
        } catch (SQLException ex) {
            Logger.getLogger(TravelGroupCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ajouterUnMember(t.getId(),id_user);
    }

    public static void supprimerTravelGroup(int id) throws SQLException {

        Connection con = MyDBcon.getInstance().getCon();
        String query = "DELETE FROM `travel_group` WHERE id_travel_group =?";
        try {

            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1,id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    public static void ajouterUnMember(int id_group, int id_user) throws SQLException {  
        Connection con = MyDBcon.getInstance().getCon();

        //need to set current user //
        String query = "INSERT INTO `group_members` (`id_travel_group`,`id_travel_buddy`) VALUES (?,?)";

        try {
            int id_buddy = TravelbuddyCrud.getIdTravelBuddyByIdUser(id_user) ;
            PreparedStatement stm = con.prepareStatement(query);

            stm.setInt(1, id_group);
            stm.setInt(2, id_buddy);
            stm.executeUpdate() ;
            
        } catch (SQLException ex) {
            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
 

    public static void supprimerUnMember(int id_travelgroup,int id_user) throws SQLException {
        Connection con = MyDBcon.getInstance().getCon();
        String query = "DELETE FROM `group_members` WHERE id_travel_buddy =? and id_travel_group =?";
        try {

            PreparedStatement stm = con.prepareStatement(query);
            int id_travelBuddy = TravelbuddyCrud.getIdTravelBuddyByIdUser(id_user) ;
            stm.setInt(1,id_travelBuddy);         
            stm.setInt(2,id_travelgroup);         
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    public static List<TravelGroup> getAllTravelGroup() throws SQLException {

        Connection con = MyDBcon.getInstance().getCon();
        String query = "SELECT `id_travel_group` ,`title`,`destination`,`date_debut`,`date_fin`,`plan` FROM `travel_group`";
        ArrayList<TravelGroup> retour = new ArrayList<TravelGroup>();
        
        try {

            Statement stm = con.createStatement();
            ResultSet resultat = stm.executeQuery(query);
            while (resultat.next()) {
                

                retour.add(new TravelGroup(resultat.getInt("id_travel_group"),resultat.getString("title"),resultat.getString("destination"), resultat.getDate("date_debut"), resultat.getDate("date_fin"), resultat.getString("plan")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retour;
    }
    
    
    public static List<TravelGroup> RechercherSelonDestination(String dest) throws SQLException {
    
     
     Connection con = MyDBcon.getInstance().getCon();
     String query = "SELECT `title`,`destination`,`date_debut`,`date_fin`,`plan` FROM `travel_group` WHERE `destination` =? ";
     List<TravelGroup> retour = new ArrayList<TravelGroup>() ;
     try {
            PreparedStatement stm = con.prepareStatement(query);
            stm.setString(1, dest);
            ResultSet resultat = stm.executeQuery();
            
            while(resultat.next())
                
            {
retour.add(new TravelGroup(resultat.getString("title"),resultat.getString("destination"), resultat.getDate("date_debut"), resultat.getDate("date_fin"), resultat.getString("plan")));
return retour ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
            
        }
     return null ; 
     
 }
    
    
    public static List<Travelbuddy> getMemembersByIdGroup(int id) throws SQLException {

        Connection con = MyDBcon.getInstance().getCon();
        String query = "SELECT t.id_travel_buddy ,t.description,t.destination,t.date_debut,t.date_fin,t.id_user FROM travel_buddy t, travel_group t1 , group_members g WHERE t1.id_travel_group = "+id+" and t.id_travel_buddy = g.id_travel_buddy and t1.id_travel_group = g.id_travel_group";
        ArrayList<Travelbuddy> retour = new ArrayList<Travelbuddy>();
        
        try {

            Statement stm = con.createStatement();
            ResultSet resultat = stm.executeQuery(query);
            
            while (resultat.next()) {
                

                retour.add(new Travelbuddy(resultat.getInt("id_travel_buddy"),resultat.getString("description"),resultat.getString("destination"), resultat.getDate("date_debut"), resultat.getDate("date_fin"),resultat.getInt("id_user")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retour;
    }
    
    
    //i added this method cuz to delete travel buddy u need delete him from groupes //
    public static void supprimerUnMemberFromAll(int id_user) throws SQLException {
        Connection con = MyDBcon.getInstance().getCon();
        String query = "DELETE FROM `group_members` WHERE id_travel_buddy =?";
        try {

            PreparedStatement stm = con.prepareStatement(query);
            int id_travelBuddy = TravelbuddyCrud.getIdTravelBuddyByIdUser(id_user) ;
            stm.setInt(1,id_travelBuddy);                           
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
