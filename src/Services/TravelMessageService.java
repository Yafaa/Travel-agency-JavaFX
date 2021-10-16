/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.TravelMessage;
import Entities.User;
import Utils.MyDBcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class TravelMessageService {
    
    
    public static void ajouterMessage(TravelMessage t ,User fromwhom, User toWhom) throws SQLException{
         Connection con = MyDBcon.getInstance().getCon();
        String query = "INSERT INTO `travelMessage`(`id_userFrom`,`id_userTo` ,`Message`,`MessageTime` ) VALUES (?,?,?)";

        try {
            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, fromwhom.getId());
            stm.setInt(2, toWhom.getId());
            stm.setString(3, t.getMessage());  
            stm.setTimestamp(1,t.getMessageTime());
            stm.executeUpdate() ;
        } catch (SQLException ex) {
            Logger.getLogger(TravelbuddyCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
            
    public static void supprimerMessage(){
}        
    public static List<TravelMessage> GetAllMessages() {
        return null ;
    }
    public static List<TravelMessage> GetMessageByID() {
        return null;
    }
    
}
