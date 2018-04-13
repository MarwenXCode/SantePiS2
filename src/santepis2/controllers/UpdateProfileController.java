package santepis2.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import santepis2.entities.User;
import santepis2.services.UserService;
import santepis2.services.interfaces.IUserService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author MOHAMMED
 */
public class UpdateProfileController implements Initializable {

    

    
    

    @FXML
    private Pane backgroundPane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger humburger;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        FadeTransition fadeout = new FadeTransition(Duration.seconds(2.5), backgroundPane);
        fadeout.setFromValue(1);
        fadeout.setToValue(0);
        fadeout.play();
        fadeout.setOnFinished(event -> {
            
            backgroundPane.setStyle(" -fx-background-image: url(\"/santepis2/images/image-1.jpg\");");
            
            FadeTransition fadein = new FadeTransition(Duration.seconds(2.5), backgroundPane);
            fadein.setFromValue(0);
            fadein.setToValue(0.6);
            fadein.play();
            
            fadein.setOnFinished(e -> {
                
                backgroundPane.setStyle(" -fx-background-image: url(\"/santepis2/images/sgh-main-banner4.jpg\");");
                FadeTransition fadein2 = new FadeTransition(Duration.seconds(2.5), backgroundPane);
                fadein2.setFromValue(0);
                fadein2.setToValue(1);
                fadein2.play();
                
                fadein2.setOnFinished(event2 -> {
                    
                    backgroundPane.setStyle(" -fx-background-image: url(\"/santepis2/images/banner_aae1.jpg\");");
                    
                    FadeTransition fadein3 = new FadeTransition(Duration.seconds(2.5), backgroundPane);
                    fadein3.setFromValue(1);
                    fadein3.setToValue(0);
                    fadein3.play();
                    
                    fadein3.setOnFinished(event3 -> {
                        backgroundPane.setStyle(" -fx-background-image: url(\"/santepis2/images/image-1.jpg\");");
                        
                        FadeTransition fadein4 = new FadeTransition(Duration.seconds(2.5), backgroundPane);
                        fadein4.setFromValue(0);
                        fadein4.setToValue(1);
                        fadein4.play();
                        
                        fadein4.setOnFinished(event4 -> {
                            backgroundPane.setStyle(" -fx-background-image: url(\"/santepis2/images/banner5.jpg\");");
                            
                            FadeTransition fadein5 = new FadeTransition(Duration.seconds(2.5), backgroundPane);
                            fadein5.setFromValue(0);
                            fadein5.setToValue(1);
                            fadein5.play();
                            
                        });
                        
                    });
                });

            });
            
        });
        
             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/santepis2/gui/navigationDrawerFXML.fxml"));
            AnchorPane pane = loader.load();
            drawer.setSidePane(pane);
        } catch (IOException ex) {
            System.out.print("salal"+ex);
           
        }
        HamburgerBackArrowBasicTransition hamburderTrans = new HamburgerBackArrowBasicTransition(humburger);
        hamburderTrans.setRate(-1);
        humburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            hamburderTrans.setRate(hamburderTrans.getRate() * -1);
            hamburderTrans.play();
           
            
            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
            
        });
        
        int id = FXMLAuthentificationController.getInstance().getUser().getId();
        IUserService userservice = new UserService();
       User user =  userservice.findById(id);
       nom.setText(user.getName());
       prenom.setText(user.getLastname());
       mail.setText(user.getEmail());
       telephone.setText(String.valueOf(user.getTelephone()));
       photoprofilimagepath.setText(user.getPhotoMembre());
       
       
       

    }
     
     
    
    @FXML
    private JFXTextField telephone;
    @FXML
    private JFXTextField mail;

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField photoprofilimagepath;
    @FXML
    private JFXTextField prenom;
  
    private static Path destination1;
    private static File selectedfile1;
    
    
   
    @FXML
    void ajouterphotoprofil(ActionEvent event) {
       FileChooser fc = new FileChooser();
       selectedfile1 = fc.showOpenDialog(null);
        
     destination1 = Paths.get("C:\\Users\\sysiph\\Documents\\NetBeansProjects\\SantePiS2\\src\\santepis2\\images",selectedfile1.getName());
    photoprofilimagepath.setText(selectedfile1.getAbsolutePath());
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
    
    @FXML
    void UpdateProfile(ActionEvent event) throws IOException {
        
        int id = FXMLAuthentificationController.getInstance().getUser().getId();
        IUserService us = new UserService();
       User user =  us.findById(id);
       
        String username = nom.getText();
        Integer phone = Integer.parseInt(telephone.getText());
        int t =Integer.toString(phone).length();
        String email = mail.getText();
        String pw = nom.getText();
        String pwc = prenom.getText();
      if (username.length() > 0 && email.length() > 0 && pw.length() > 0 && pwc.length() > 0){ 
      if (isValidInteger(telephone,8)){
          if (ValidateEmail(email)) {
            
          
      
        User u = new User(user.getUsername(),user.getUsernameCanonical(),mail.getText(),mail.getText(),user.getEnabled(),user.getSalt(),user.getPassword(),user.getLastLogin(),user.getConfirmationToken(),user.getPasswordRequestedAt(),user.getRoles(),Integer.parseInt(telephone.getText()),user.getPhotoMembre(),nom.getText(),prenom.getText(), user.getSpecialty());
        
          if(selectedfile1==null){
        
        u.setPhotoMembre(null);
        }else{
            
             Files.copy(selectedfile1.toPath(),destination1,StandardCopyOption.REPLACE_EXISTING); 
             u.setPhotoMembre(destination1.toString()); 
          }
          u.setId(id);
          
        
        us.updateC(u);
              TrayNotification tn = new TrayNotification();
            tn.setTitle("Success");
            tn.setMessage("User was successfully Edited");
            tn.setNotificationType(NotificationType.SUCCESS);
            tn.setAnimationType(AnimationType.SLIDE);
            tn.showAndWait();
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

   
}
