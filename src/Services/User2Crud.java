/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Utils.MyDBcon;
import Entities.Travelbuddy;
import Utils.MyDBcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.sql.Date ;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 *
 * @author Yefet
 */
public class User2Crud {

    public static void AjouterTravelBuddy(Travelbuddy t) throws SQLException {
        Connection con = MyDBcon.getInstance().getCon();

        //need to set current user //
        String query = "INSERT INTO `travel_buddy`(`description`,`destination` ,`date_debut` ,`date_fin` ,`id_user`) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement stm = con.prepareStatement(query);

            stm.setString(1, t.getDescription());
            stm.setString(2, t.getDestination());
            stm.setDate(3, t.getDate_debut());
            stm.setDate(4,t.getDate_fin());
            stm.setInt(5,t.getId_user()) ;  
            stm.executeUpdate() ;
        } catch (SQLException ex) {
            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static void supprimerTravelBuddy(Travelbuddy t) throws SQLException {  //Travelbuddy got deleteD By his userName //
        Connection con = MyDBcon.getInstance().getCon();
        String query = "DELETE FROM `travel_buddy` WHERE `id_user`=?";
        try {

            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1,t.getId_user());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public static List<Travelbuddy> getAllTravelbuddy() throws SQLException {
        Connection con = MyDBcon.getInstance().getCon();
        ArrayList<Travelbuddy> retour = new ArrayList<Travelbuddy>();
        Statement stm = con.createStatement();
        String query = "SELECT * from `travel_buddy` ";
        try {
            ResultSet resultat = stm.executeQuery(query);
            while (resultat.next()) {
                int id = resultat.getInt(1);
                String description = resultat.getString(2);
                String destination = resultat.getString(3);
                Date date_debut = resultat.getDate(4);
                Date date_fin = resultat.getDate(5);
                int id_user = resultat.getInt(6);
//                User u = UserCrud.GetUserById(id_user) ;  Jamila need add a method GetUserById//            
               
                retour.add(new Travelbuddy(id,description, destination, date_debut, date_fin,id_user));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);

        }
        return retour;

    }

    public static List<Travelbuddy> getByIdUser(int id) throws SQLException {
        Connection con = MyDBcon.getInstance().getCon();
        ArrayList<Travelbuddy> retour = new ArrayList<Travelbuddy>();
        Statement stm = con.createStatement();
        String query = "SELECT * from `travel_buddy` WHERE user_id =" + id;
        try {
            ResultSet resultat = stm.executeQuery(query);
            while (resultat.next()) {
                retour.add(new Travelbuddy(resultat.getString("description"), resultat.getString("destination"), resultat.getDate("date_debut"), resultat.getDate("date_fin"), resultat.getInt("id_user")));
                return retour;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retour;
    }
    public static int getIdTravelBuddyByIdUser(int id) throws SQLException {
        int id1 = 0 ;
        Connection con = MyDBcon.getInstance().getCon();
        String query = "SELECT id_travel_buddy FROM `travel_buddy` WHERE id_user="+id;
        try {

            Statement stm = con.createStatement();
            ResultSet resultat = stm.executeQuery(query);
            while (resultat.next()){
                id1 = resultat.getInt("id_travel_buddy") ;
                return id1 ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
      return id1 ;
    }
            
    
    //second method cuz he could be more than travel budy by same user id //
     public static List<Integer> getIdTravelBuddyByIdUser1(int id) throws SQLException {
        int id1 = 0 ;
         List<Integer> ids = new ArrayList() ;
        Connection con = MyDBcon.getInstance().getCon();
        String query = "SELECT id_travel_buddy FROM `travel_buddy` WHERE id_user="+id;
        try {

            Statement stm = con.createStatement();
            ResultSet resultat = stm.executeQuery(query);
            while (resultat.next()){
                id1 = resultat.getInt("id_travel_buddy") ;
                 ids.add(id1) ;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
      return ids ;
    }
    
    
    

    public static List<Travelbuddy> RechercheByDestination(String dest) throws SQLException {
        Connection con = MyDBcon.getInstance().getCon();
        ArrayList<Travelbuddy> retour = new ArrayList<Travelbuddy>();
        Statement stm = con.createStatement();
        String query = "SELECT `description`,`destination`,`date_debut`,`date_fin`,`id_user`FROM `travel_buddy` WHERE `destination` =" + dest;
        try {
            ResultSet resultat = stm.executeQuery(query);
            while (resultat.next()) {
                 retour.add(new Travelbuddy(resultat.getString("description"), resultat.getString("destination"), resultat.getDate("date_debut"), resultat.getDate("date_fin"), resultat.getInt("id_user")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retour;
    }

//    public static void updateTravelBuddy(Travelbuddy t) throws SQLException {
//         Connection con = MyDBcon.getInstance().getCon();
//         String query = "UPDATE `travel_buddy` SET  "
//         
//         
//         
//         
//         
//        
//    }
//    public static List<Travelbuddy> getAllTravelbuddy () throws SQLException {
//        Connection con = MyDBcon.getInstance().getCon() ;
//        ArrayList<Travelbuddy> retour = new ArrayList<Travelbuddy>() ;
//        Statement stm = con.createStatement() ;
//        String query = "SELECT"
//                + " t.id_travel_buddy, t.description, t.destination, t.date_debut, t.date_fin, t.id_user,p.date_naissance,p.photo_p,u.firstname,u.nationalite,u.langues"
//                + " from `travel_buddy` t,`profil` p,`users`u"
//                + " WHERE u.id = t.id_user = p.id_up  " ;
//        try {
//        ResultSet resultat = stm.executeQuery(query) ;
//        while (resultat.next()) {
//            int id = resultat.getInt(1);
//            String description = resultat.getString(2);
//            String destination = resultat.getString(3);
//            Date date_debut = resultat.getDate(4);
//            Date date_fin = resultat.getDate(5);
//            int id_user = resultat.getInt(6) ;
//            Date date_naissance = resultat.getDate(7) ;
//            String picture = resultat.getString(8) ;
//            String name = resultat.getString(9) ;
//            String nationalite = resultat.getString(10) ;
//            String langues = resultat.getString(11) ;
//            
//            int age = getAge(date_naissance) ; //to get theAge from birthDate (not sure if the function work need testit) //
//             
//            List<String> languuages =Stream.of(langues.split(",")).collect(Collectors.toList()) ; //to split languages//
//            retour.add(new Travelbuddy(id, name, description, nationalite, age, lanqguuages, destination, picture, date_debut, date_fin, id_user)) ;
//            return retour ;
//            
//            
//        } } catch (SQLException ex) {
//            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
//            
//        }
//     return null ; 
//     
// }    
    //fonction mise a jour des String de table travelBuddy
    public static void update(String row, String value, int id) throws SQLException {
		Connection con = MyDBcon.getInstance().getCon();
		String strSQLQuery = String.format("UPDATE `travel_buddy` set %s = '%s' WHERE id_travel_buddy=%s", row, value, id);
		System.out.println(strSQLQuery);
		try {
			PreparedStatement ste = con.prepareStatement(strSQLQuery);
			ste.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(TravelGroupCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
    
    
       //fonction mise a jour des int de table travelBuddy
	public static void update(String row, int value, int id) throws SQLException {
		Connection con = MyDBcon.getInstance().getCon();
		String strSQLQuery = String.format("UPDATE `travel_buddy` set %s = '%s' WHERE id_travel_buddy=%s", row, value, id);
		System.out.println(strSQLQuery);
		try {
			PreparedStatement ste = con.prepareStatement(strSQLQuery);
			ste.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
  
    
    
    
    //fonction mise a jour des dates de table TravelBuddy//
	public static void update(String row, Date value, int id) throws SQLException {
		Connection con = MyDBcon.getInstance().getCon();
		String strSQLQuery = String.format("UPDATE `travel_buddy` set %s = '%s' WHERE id_travel_buddy=%s", row, value, id);
		System.out.println(strSQLQuery);
		try {

			PreparedStatement ste = con.prepareStatement(strSQLQuery);
			ste.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
 
    
    
}