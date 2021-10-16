/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.ChatRoom.GUIClient;
import Controllers.ChatRoom.GUIServer;
import com.jfoenix.controls.JFXButton;
import java.awt.BorderLayout;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class LiveChatLoaderController implements Initializable {
    @FXML
    private JFXButton loadSwing;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(MouseEvent event) {
        
        SwingUtilities.invokeLater(new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            JFrame frame = new JFrame("Live chat");

            // 2. Optional: What happens when the frame closes?
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // 3. Create components and put them in the frame.
            // ...create emptyLabel...
//            frame.getContentPane().add(new JLabel("Test"), BorderLayout.CENTER);
       
              new GUIServer().setVisible(true);
           new GUIClient("localhost", 1000).setVisible(true);
            // 4. Size the frame.
            frame.pack();

            // 5. Show it.
            frame.setVisible(true);
        }
    });
        
        
    }
    
}
