/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santepis2.controllers;

import santepis2.entities.Sms;
import santepis2.entities.User;
import santepis2.services.SmsService;
import santepis2.techniques.BCrypt;
import santepis2.services.interfaces.IUserService;
import santepis2.services.UserService;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author adel
 */
public class FXMLInscriptionController implements Initializable {
    
    
    @FXML
    private JFXPasswordField pwd1;
    @FXML
    private ChoiceBox<String> typeprofile;
    
    
    @FXML
    private JFXPasswordField pwd2;
    @FXML
    private JFXTextField numerotel;
     @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField Email;
    
    
    @FXML
    void Back(ActionEvent event) throws IOException{
      Parent root11 = FXMLLoader.load(getClass().getResource("/santepis2/gui/FXMLSignInDocument.fxml"));
        Stage stage111=null;    
         stage111 = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root11);
        
        stage111.setScene(scene);
        stage111.show();
       }
    @FXML
    void Inscription(ActionEvent event) throws IOException {
         UserService us = new UserService();
        String typeprof = typeprofile.getValue();
        String username = nom.getText();
        
        String email = Email.getText();
        String pw = pwd1.getText();
        String pwc = pwd2.getText();

        if (username.length() > 0 && email.length() > 0 && pw.length() > 0 && pwc.length() > 0) {
            if (us.findByUsername(username) == null) {
                if (isValidInteger(numerotel,8)){
                    
                if (ValidateEmail(email)) {
                    if (us.findByEmail(email) == null) {
                        if (pw.equals(pwc)) {
                            String hashedPassword = BCrypt.hashpw(pw, BCrypt.gensalt(13));
                             StringBuilder finalpassword = new StringBuilder(hashedPassword);
                            finalpassword.setCharAt(2, 'y');
                            String pass = finalpassword.toString();
                            String role = "";
                            if (typeprof.equals("Client")) {
                                role = "a:1:{i:0;s:11:\"ROLE_MEMBRE\";}";
                            } else if (typeprof.equals("Doctor")) {
                                role = "a:1:{i:0;s:11:\"ROLE_DOCTOR\";}";
                            }
                            Integer phone = Integer.parseInt(numerotel.getText());
                            User u = new User(username, username, email, email, false, null, pass, null, null, null, role, phone, null, null, null, null );
                            us.add(u);
                            Random rand = new Random();

                                int  n = rand.nextInt(4000) + 1000;
                                u.setCodeconfirmation(n);
                                 new SmsService().sendSms(new Sms("+216"+phone, "Hello this is your code auth:Marwen  "+n));
                                
    TrayNotification tn = new TrayNotification();
            tn.setTitle("SUCCESS");
            tn.setMessage("Registration successful u can login after confirming your account ");
            tn.setNotificationType(NotificationType.SUCCESS);
            tn.setAnimationType(AnimationType.SLIDE);
            tn.showAndWait();
            Parent parent1255 = FXMLLoader.load(getClass().getResource("/santepis2/gui/FXMLInscriptionController.fxml"));
       Scene scene11166 = new Scene(parent1255 );
       Stage stage11166  = (Stage)((Node)event.getSource()).getScene().getWindow();
       stage11166 .hide();
       stage11166 .setScene(scene11166 );
       stage11166 .show();
                          
                            
                        } else {
                             TrayNotification tn = new TrayNotification();
            tn.setTitle("ALERT");
            tn.setMessage("Confirmation password invalid ");
            tn.setNotificationType(NotificationType.WARNING);
            tn.setAnimationType(AnimationType.SLIDE);
            tn.showAndWait();
                          
                        }
                    } else {
                         TrayNotification tn = new TrayNotification();
            tn.setTitle("ALERT");
            tn.setMessage("Email Already used ");
            tn.setNotificationType(NotificationType.WARNING);
            tn.setAnimationType(AnimationType.SLIDE);
            tn.showAndWait();
                          
                        
                    }
                } else {
                     TrayNotification tn = new TrayNotification();
            tn.setTitle("ALERT");
            tn.setMessage("Email Invalid ");
            tn.setNotificationType(NotificationType.WARNING);
            tn.setAnimationType(AnimationType.SLIDE);
            tn.showAndWait();
                          
                    
                }
                } else {
                 TrayNotification tn = new TrayNotification();
            tn.setTitle("ALERT");
            tn.setMessage("Phone numbre must be an 8 digits numbre ");
            tn.setNotificationType(NotificationType.WARNING);
            tn.setAnimationType(AnimationType.SLIDE);
            tn.showAndWait();
                          
              
            }
            } else {
                 TrayNotification tn = new TrayNotification();
            tn.setTitle("ALERT");
            tn.setMessage("Username already exists ");
            tn.setNotificationType(NotificationType.WARNING);
            tn.setAnimationType(AnimationType.SLIDE);
            tn.showAndWait();
                          
              
            }
        } else {
             TrayNotification tn = new TrayNotification();
            tn.setTitle("ALERT");
            tn.setMessage("Check Fields");
            tn.setNotificationType(NotificationType.WARNING);
            tn.setAnimationType(AnimationType.SLIDE);
            tn.showAndWait();
                          
        }
    }

    public Boolean ValidateEmail(String email) {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(email);

        if (m.find() && m.group().equals(email)) {
            return true;
        } else {
            return false;
        }
    }
    public boolean isValidInteger(TextField tf, int i) {
        boolean b = false;
        if (!(tf.getText() == null || tf.getText().length() == 0)) {
            try {
                int value = Integer.parseInt(tf.getText());
                if ((tf.getText().length() == i)) {
                    b = true;
                }
            } catch (NumberFormatException e) {
            }
        }
        return b;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
