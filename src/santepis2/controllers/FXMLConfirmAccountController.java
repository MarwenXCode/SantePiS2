/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santepis2.controllers;

import santepis2.entities.User;
import santepis2.services.UserService;
import santepis2.services.interfaces.IUserService;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author adel
 */
public class FXMLConfirmAccountController implements Initializable {

    @FXML
    private JFXTextField code;

    @FXML
    void Confirmaccount(ActionEvent event) throws IOException {
    IUserService userservice = new UserService();
    int id = FXMLAuthentificationController.getInstance().getUser().getId();
    User u = userservice.findById(id);
    Integer conf = Integer.parseInt(code.getText());
      
if((conf).equals(u.getCodeconfirmation())){
    
    userservice.setenabledtrue(id);
    
      Parent parent1255 = FXMLLoader.load(getClass().getResource("/santepis2/gui/FXMLhomeDocument.fxml"));
       Scene scene11166 = new Scene(parent1255 );
       Stage stage11166  = (Stage)((Node)event.getSource()).getScene().getWindow();
       stage11166 .hide();
       stage11166 .setScene(scene11166 );
       stage11166 .show();
    
    
    
}else {
             TrayNotification tn = new TrayNotification();
            tn.setTitle("ALERT");
            tn.setMessage("Code incorrect");
            tn.setNotificationType(NotificationType.WARNING);
            tn.setAnimationType(AnimationType.SLIDE);
            tn.showAndWait();
                          
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
