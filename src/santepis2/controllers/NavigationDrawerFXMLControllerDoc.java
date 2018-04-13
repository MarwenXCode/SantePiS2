/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santepis2.controllers;

import santepis2.entities.User;
import santepis2.services.UserService;
import santepis2.services.interfaces.IUserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.ConditionalFeature.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author MOHAMMED
 */
public class NavigationDrawerFXMLControllerDoc implements Initializable {

    @FXML
    private ImageView Photoprofil;

    @FXML

    private Label NomUtil;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
       File file=null ;
         int id = FXMLAuthentificationController.getInstance().getUser().getId();
        IUserService userservice = new UserService();
       User user =  userservice.findById(id);
       if(user.getPhotoMembre()==null){
           System.out.print("yoooo");
           file = new File(""); 
       }else{
           System.out.print("ybbboooo");
             file = new File(user.getPhotoMembre()); 
       }
     
        Image image = new Image(file.toURI().toString());
      
         Photoprofil.setImage(image);
    
      
        NomUtil.setText(user.getUsername());
    }

    @FXML
    void showStatistics(ActionEvent event) throws IOException {
    Parent parent123 = FXMLLoader.load(getClass().getResource("/santepis2/gui/UpdateProfileDoc.fxml"));
       Scene scene1113 = new Scene(parent123 );
       Stage stage1113  = (Stage)((Node)event.getSource()).getScene().getWindow();
       stage1113 .hide();
       stage1113 .setScene(scene1113 );
       stage1113.show();    
        
    }

    @FXML
    void about(ActionEvent event) throws IOException {
        Parent parent1236 = FXMLLoader.load(getClass().getResource("/santepis2/gui/FXMLhomeDocumentDoc.fxml"));
       Scene scene11136 = new Scene(parent1236 );
       Stage stage11136  = (Stage)((Node)event.getSource()).getScene().getWindow();
       stage11136 .hide();
       stage11136 .setScene(scene11136 );
       stage11136.show();
        
    }
    
   

    @FXML
     void logout(ActionEvent event) throws IOException {
     Parent parent125 = FXMLLoader.load(getClass().getResource("/santepis2/gui/FXMLSignInDocument.fxml"));
       Scene scene1116 = new Scene(parent125 );
       Stage stage1116  = (Stage)((Node)event.getSource()).getScene().getWindow();
       stage1116 .hide();
       stage1116 .setScene(scene1116 );
       stage1116.show();
     }
}
