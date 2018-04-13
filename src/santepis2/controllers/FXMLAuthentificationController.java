/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santepis2.controllers;

import santepis2.entities.User;
import santepis2.services.UserService;
import santepis2.services.interfaces.IUserService;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import santepis2.techniques.BCrypt;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author sisyph
 */


public class FXMLAuthentificationController implements Initializable {
    
   @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField login;
   public static FXMLAuthentificationController FXMLAuthentificationController;
    private User user;
    
    
    Stage stage;
    Scene scene;
    Parent root;

    static User currentUser;

    /**
     * Initializes the controller class.
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        FXMLAuthentificationController.currentUser = currentUser;
        
    }
      @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }  
    public FXMLAuthentificationController() {
        FXMLAuthentificationController = this;
    }

    public static FXMLAuthentificationController getInstance() {
        return FXMLAuthentificationController;
    }

    public User getUser() {
        return user;
    }
    
    private Connection connection;
   

  

    @FXML
    private void pfUserPassOnHitEnter(ActionEvent event) {
    }

    @FXML
    private void pfUserNameOnHitEnter(ActionEvent event) {
    }

    @FXML
    private void auth(ActionEvent event) throws IOException {
   
        user = new User();
 
       
            
            Stage stage1=null;
        String username = login.getText();
        UserService us = new UserService();
        User u = us.findByUsername(username);
       
        System.out.print(u);
        
        if (u != null) {
            StringBuilder finalpassword = new StringBuilder(u.getPassword());
        finalpassword.setCharAt(2, 'a');
        String pass = finalpassword.toString();
            if (BCrypt.checkpw(password.getText(), pass)) {
                currentUser = u;
                user=u;
                if ("a:1:{i:0;s:10:\"ROLE_ADMIN\";}".equals(u.getRoles())) {
                    stage = (Stage) login.getScene().getWindow();
                    root = FXMLLoader.load(getClass().getResource("/GUI/AdminProfile.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else if ("a:1:{i:0;s:11:\"ROLE_MEMBRE\";}".equals(u.getRoles())) {
                    if(u.getEnabled()==false){
                    Parent parent125 = FXMLLoader.load(getClass().getResource("/santepis2/gui/FXMLConfirmAccount.fxml"));
                      Scene scene1116 = new Scene(parent125 );
                      Stage stage1116  = (Stage)((Node)event.getSource()).getScene().getWindow();
                      stage1116 .hide();
                      stage1116 .setScene(scene1116 );
                      stage1116 .show();}else{
                        
                    Parent parent1 = FXMLLoader.load(getClass().getResource("/santepis2/gui/FXMLhomeDocument.fxml"));
                            Scene scene1 = new Scene(parent1);
                            stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
                            stage1.hide();
                            stage1.setScene(scene1);
                            stage1.show();}
                       
                    

                } else if ("a:1:{i:0;s:11:\"ROLE_DOCTOR\";}".equals(u.getRoles())) {
                    if(u.getEnabled()==false){
                    Parent parent125 = FXMLLoader.load(getClass().getResource("/santepis2/gui/FXMLConfirmAccount.fxml"));
                      Scene scene1116 = new Scene(parent125 );
                      Stage stage1116  = (Stage)((Node)event.getSource()).getScene().getWindow();
                      stage1116 .hide();
                      stage1116 .setScene(scene1116 );
                      stage1116 .show();}else{
                    stage = (Stage) login.getScene().getWindow();
                    root = FXMLLoader.load(getClass().getResource("/santepis2/gui/FXMLhomeDocumentDoc.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.centerOnScreen();
                    stage.show();}
                
                
            }} else {
                TrayNotification tn = new TrayNotification();
            tn.setTitle("AVERTISSEMENT");
            tn.setMessage("Wrong Password ");
            tn.setNotificationType(NotificationType.WARNING);
            tn.setAnimationType(AnimationType.SLIDE);
            tn.showAndWait();
            }
            
        } else if (u == null) {
            TrayNotification tn = new TrayNotification();
            tn.setTitle("AVERTISSEMENT");
            tn.setMessage("User does not exist ");
            tn.setNotificationType(NotificationType.WARNING);
            tn.setAnimationType(AnimationType.SLIDE);
            tn.showAndWait();
        }

        
    
    }
    
    @FXML
    void inscription(ActionEvent event) throws IOException {
    Stage stage11=null;
    Parent parent11 = FXMLLoader.load(getClass().getResource("/santepis2/gui/FXMLregister.fxml"));
       Scene scene11 = new Scene(parent11);
       stage11 = (Stage)((Node)event.getSource()).getScene().getWindow();
       stage11.hide();
       stage11.setScene(scene11);
       stage11.show();
    }
    
    }
   

