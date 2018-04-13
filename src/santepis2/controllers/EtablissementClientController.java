/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
//import com.lynden.gmapsfx.GoogleMapView;
//import com.lynden.gmapsfx.javascript.object.GoogleMap;
//import com.lynden.gmapsfx.javascript.object.LatLong;
//import com.lynden.gmapsfx.javascript.object.MapOptions;
//import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import edu.esprit.entities.Etablissement;
import edu.esprit.services.Myservice;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import medlife.Medlife;


/**
 * FXML Controller class
 *
 * @author danml
 */
public class EtablissementClientController implements Initializable {

    
//    @FXML
//    GoogleMapView mapView;
//    
//    private GoogleMap map;
    @FXML
    private ImageView imgBack;
    @FXML
    private AnchorPane topAnchor;
    @FXML
    private ImageView imgProfile;
    @FXML
    private JFXButton btnChoose;
    @FXML
    private StackPane rootPane;
    @FXML
    private ToggleGroup gender;
    @FXML
    private ToggleGroup family;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private JFXTextField txtSerialNo;
    @FXML
    private JFXButton btnGenerate;
    @FXML
    private JFXComboBox<String> comboLicence;
    @FXML
    private JFXComboBox<Number> comboYear;
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXSnackbar snackEdit;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField adresse;
      @FXML
    private JFXTextField web;

    @FXML
    private JFXTextField phone;
   
   

    @FXML
    private JFXComboBox<String> comboPLate;
    @FXML
    private TableView<?> tableDriversInfo;
    @FXML
    private TableColumn<?, ?> colNames;
    @FXML
    private TableColumn<?, ?> colDob;
    @FXML
    private TableColumn<?, ?> colGender;
    @FXML
    private TableColumn<?, ?> colPhone;
    @FXML
    private TableColumn<?, ?> colStatus;
    @FXML
    private TableColumn<?, ?> colLicense;
    @FXML
    private TableColumn<?, ?> colIssued;
  

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Instantiate db class
     
        JFXRippler backRippler = new JFXRippler(imgBack, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        topAnchor.getChildren().add(imgBack);
       
        // Set default selected radio buttons
       // rdMale.setSelected(true);
        //rdSingle.setSelected(true);
        
        
        
//        MapOptions mapOptions = new MapOptions();
//        mapOptions.center(new LatLong(12, 14))
//                .mapType(MapTypeIdEnum.ROADMAP)
//                .zoom(10)
//                .zoomControl(false);
//        
//        map=mapView.createMap(mapOptions);
//        
        
        
//        Rectangle clip=new Rectangle(imgProfile.getFitWidth(), imgProfile.getFitHeight());
//        clip.setArcHeight(120);
//        clip.setArcWidth(120);
//        imgProfile.setClip(clip);
//        SnapshotParameters parameters=new SnapshotParameters();
//        parameters.setFill(Color.TRANSPARENT);
//        WritableImage img=imgProfile.snapshot(parameters, null);
//        imgProfile.setClip(null);
//        imgProfile.setImage(img);
    }

    private void goBack(MouseEvent event) {
        try {
            imgBack.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("Menus.fxml"));
            Scene scene = new Scene(root);
            Stage menuStage = new Stage();
            menuStage.setScene(scene);
            menuStage.show();
        } catch (IOException ex) {
            }
    }

    private void choosePhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filterJPG = new FileChooser.ExtensionFilter("JPG files(*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter filterPNG = new FileChooser.ExtensionFilter("PNG files(*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(filterPNG, filterJPG);
        //show open dialog
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
                Rectangle clip = new Rectangle(imgProfile.getFitWidth(), imgProfile.getFitHeight());
                clip.setArcHeight(180);
                clip.setArcWidth(180);
                imgProfile.setImage(image);
                imgProfile.setClip(clip);
                SnapshotParameters parameters = new SnapshotParameters();
                parameters.setFill(Color.TRANSPARENT);
                WritableImage img = imgProfile.snapshot(parameters, null);
                imgProfile.setClip(null);
                imgProfile.setImage(img);

            } catch (IOException ex) {
                 }
        }

    }

   

    private void editDriver(ActionEvent event) {
        JFXSnackbar fXSnackbar = new JFXSnackbar(rootPane);
        fXSnackbar.show("Edit functionality will be implemented later on", 5000);
    }

    private void generateSerialNo(ActionEvent event) {
        int random = 10000000 + (int) (Math.random() * 10000000);
        txtSerialNo.setText(String.valueOf(random));
    }


  


   Medlife su;
        Stage stage;
        public  void Main( Medlife su,Stage stage){
        this.stage=stage;
        this.su=su;
        }
        
    @FXML
    void add(ActionEvent event) {
        Myservice service = new Myservice();
        
        //Etablissement e=new Etablissement(nom.getText(), adresse.getText(), Integer.parseInt(phone.getText()),web.getText() );
     //   service.insert(e);
//
    }
    
        @FXML
    void map(ActionEvent event) {
        
        

    }

}
