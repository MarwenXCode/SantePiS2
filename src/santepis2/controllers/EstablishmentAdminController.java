/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.esprit.entities.Etablissement;
import edu.esprit.services.Myservice;
import edu.esprit.util.Connexion;
import edu.esprit.util.FileUploader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import medlife.Medlife;
import adminController.AdminController;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author SKANDER
 */
public class EstablishmentAdminController implements Initializable {

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField adresse;

    @FXML
    private JFXTextField tel;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private JFXTextField longitude;

    @FXML
    private JFXTextField latitude;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnView;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TextField chercher;

    @FXML
    private RadioButton rdClinic;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton rdGym;

    @FXML
    private RadioButton rdHospital;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label label;
    
    @FXML
    private Label date;


    @FXML
    private ChoiceBox<String> choice;

    @FXML
    private TableView<Etablissement> tableview;

    @FXML
    private JFXTextField web;

    @FXML
    private TableColumn<Etablissement, Integer> id;

    @FXML
    private TableColumn<Etablissement, String> nom;

    @FXML
    private TableColumn<Etablissement, String> tabadresse;

    @FXML
    private TableColumn<Etablissement, String> tabicon;

    @FXML
    private TableColumn<Etablissement, Integer> telTab;

    @FXML
    private TableColumn<Etablissement, String> webe;

    @FXML
    private TableColumn<Etablissement, Double> tablongitude;

    @FXML
    private TableColumn<Etablissement, Double> tablatitude;

    @FXML
    private TableColumn<Etablissement, Integer> rating;

    @FXML
    private TableColumn<Etablissement, String> image;

    @FXML
    private TableColumn<Etablissement, Integer> user;

    @FXML
    Label nbetablissement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Myservice ec = new Myservice();
        
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                System.out.println("run");
                Platform.runLater(() -> {
                    nbetablissement.setText(tableview.getItems().size() + "");
                });
            }
        };
        
        
        Timer timer = new Timer();
        timer.schedule(tt, 1000);

        

        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tabadresse.setCellValueFactory(new PropertyValueFactory<>("address"));
        tabicon.setCellValueFactory(new PropertyValueFactory<>("icon"));
        telTab.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        webe.setCellValueFactory(new PropertyValueFactory<>("web"));
        tablatitude.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        tablongitude.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        image.setCellValueFactory(new PropertyValueFactory<>("name_image"));
        tableview.getItems().size();
        
       date.setText(new SimpleDateFormat("dd/mm/yyyy").format(new Date())) ;
        image.setCellFactory((param) -> {
            TableCell<Etablissement, String> cell = new TableCell<Etablissement, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    if (item != null) {

                        System.out.println(item);
                        ImageView img = new ImageView(item);

                        img.setFitHeight(100);
                        img.setFitWidth(100);

                        setGraphic(img);
                    }
                }

            };
            return cell;

        });

        tableview.setItems(ec.afficher());
        tableview.setOnMouseClicked(ev -> {
            if (ev.getButton() == MouseButton.PRIMARY) {
                int etab = tableview.getSelectionModel().getSelectedIndex();

                Etablissement e = tableview.getItems().get(etab);

                name.setText(e.getNom());
                adresse.setText(e.getAddress());
                tel.setText(String.valueOf(e.getTelephone()));
                longitude.setText(String.valueOf(e.getLongitude()));
                latitude.setText(String.valueOf(e.getLatitude()));
                web.setText(e.getWeb());
                choice.setValue(e.getIcon());
                label.setText(e.getName_image());
            }
        });

    }

    private void afficherbase() {

        Myservice ec = new Myservice();
        tableview.setItems(ec.afficher());

    }

    @FXML
    void adda(ActionEvent event) throws IOException, SQLException {
        Myservice s = new Myservice();
        Statement conn = Connexion.getInstance().getConnexion().createStatement();

        Etablissement et = new Etablissement();
 if (nom.getText().length() > 0 && adresse.getText().length() > 0 && Integer.parseInt(latitude.getText()) > 0 && Integer.parseInt(longitude.getText()) > 0 && Integer.parseInt(tel.getText()) > 0){ 
      if (isValidInteger(tel,8)){
          
        et.setNom(name.getText());
        et.setAddress(adresse.getText());
        et.setTelephone(Integer.parseInt(tel.getText()));
        et.setWeb(web.getText());
        et.setLatitude(Double.parseDouble(latitude.getText()));
        et.setLongitude(Double.parseDouble(longitude.getText()));
        et.setName_image(label.getText());

        String type = choice.getValue();
        

        et.setIcon(type);
//         Image img1 = new Image(UPLOAD_URL + sign.getUrlPhoto());
//            signalPhoto.setImage(img1);

        s.insert(et);

        // santepiTest.SantePi.rightpane.table.setItems(ec.afficher());
        name.setText("");
        adresse.setText("");
        tel.setText("");
        web.setText("");
        latitude.setText("");
        longitude.setText("");
      }
          else {
                 TrayNotification tn = new TrayNotification();
            tn.setTitle("ALERT");
            tn.setMessage("Phone numbre must be an 8 digits numbre");
            tn.setNotificationType(NotificationType.WARNING);
            tn.setAnimationType(AnimationType.SLIDE);
            tn.showAndWait();
                          
              
     }}
  
       else {
                 TrayNotification tn = new TrayNotification();
            tn.setTitle("ALERT");
            tn.setMessage("you need to fill all the textfields ");
            tn.setNotificationType(NotificationType.WARNING);
            tn.setAnimationType(AnimationType.SLIDE);
            tn.showAndWait();
                          
              
            }

 }

    

    Medlife su;
    Stage stage;

    public void main(Medlife su, Stage stage) {
        this.stage = stage;
        this.su = su;

    }

    @FXML
    public String fileChooser(ActionEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filterJPG = new FileChooser.ExtensionFilter("JPG files(*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter filterPNG = new FileChooser.ExtensionFilter("PNG files(*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(filterPNG, filterJPG);
        fileChooser.setTitle("Select a photo");
        //show open dialog
        File file = fileChooser.showOpenDialog(null);

        String name = file.getName();

        String path = file.getAbsolutePath();
        String type = "image";
        String path1 = FileUploader.upload(name, path, type);
        label.setText(path1);
        label.setVisible(false);

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
        return path;
    }

//      public static void main(String[] args) {
//
//             Connection conn = Connexion.getInstance().getConnexion();
////         
//         
//                      Etablissement e1=new Etablissement("zz", "ss", 0, "aza", 0, 0, "health");
//                        Myservice s=new Myservice();
////                      
////                      
////                      
////                      
////                      
//                      System.out.println(conn);
//                      
//                     // s.insert(e);
//                    // s.insert(e2);
//                     //s.delete(6);
//                  //  s.update(e1);
//                   
//                //     s.recherche("gg");
//                    s.afficher();
//                  
//                      
//
//    }
    @FXML
    void delete(ActionEvent event) {

        Myservice ec = new Myservice();
        Etablissement e = tableview.getSelectionModel().getSelectedItem();
        ec.delete(e.getId());
        tableview.getItems().removeIf(elem -> elem.getId() == e.getId());
        tableview.refresh();

    }

    @FXML
    void search(ActionEvent event) {
        Myservice ec = new Myservice();
        this.tableview.setItems(ec.recherche(chercher.getText()));
        this.tableview.refresh();
    }

    @FXML
    void update(ActionEvent event) {
        Myservice ec = new Myservice();
        Etablissement e = tableview.getSelectionModel().getSelectedItem();
        Etablissement nv = new Etablissement();

        ec.update(nv, e.getId());
        this.tableview.setItems(ec.afficher());
        this.tableview.refresh();

    }
    
   @FXML
    void view(ActionEvent event) {
      
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

    
}
