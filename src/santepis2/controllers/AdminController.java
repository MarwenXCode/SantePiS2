
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminController;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import medlife.Medlife;
import static medlife.Medlife.stage1;

/**
 *
 * @author danml
 */
public class AdminController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane menuAbout;
    @FXML
    private AnchorPane menuCompany;
    @FXML
    private AnchorPane menuCourse;
    @FXML
    private AnchorPane menuStudent;
    @FXML
    private AnchorPane menuSynch;
    @FXML
    private AnchorPane menuTraining;
    @FXML
    private AnchorPane menuScheduling;
    @FXML
    private AnchorPane menuReports;
    @FXML
    private AnchorPane menuDatabase;
    @FXML
    private VBox groupCompany;
    @FXML
    private VBox groupTraining;
    @FXML
    private VBox groupReports;
    @FXML
    private VBox groupSettings;
    @FXML
    private VBox groupAbout;
    @FXML
    private AnchorPane menuSettings;
    public static AnchorPane staticPane;
    public static StackPane mainRootPane;
    @FXML
    private Pane popUpHolder;
    @FXML
    private JFXButton btnLogoff;
    @FXML
    private JFXButton btnClose;
    @FXML
    private JFXButton btnAccount;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        staticPane=menuStudent;
        mainRootPane=rootPane;
        setUpRipples();
    }

    @FXML
    public void showAbout() {
               setStage("/GUIAdmin/About.fxml");
    }

    @FXML
    private void showCompanies(MouseEvent event) {
             System.out.println("abab");    


    }

    @FXML
    private void showCourse(MouseEvent event) {
         System.out.println("abab");    }

    @FXML
    private void showStudent(MouseEvent event) throws IOException {
        System.out.println("abab");

    }

    public void setUpRipples() {
        JFXRippler fXRippler = new JFXRippler(menuAbout, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        JFXRippler fXRippler2 = new JFXRippler(menuDatabase, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        groupAbout.getChildren().addAll(fXRippler2, fXRippler);

        JFXRippler fXRippler3 = new JFXRippler(menuSettings, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        JFXRippler fXRippler4 = new JFXRippler(menuStudent, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        groupSettings.getChildren().addAll(fXRippler3, fXRippler4);

        JFXRippler fXRippler5 = new JFXRippler(menuReports, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        JFXRippler fXRippler6 = new JFXRippler(menuCourse, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        groupReports.getChildren().addAll(fXRippler5, fXRippler6);

        JFXRippler fXRippler7 = new JFXRippler(menuTraining, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        JFXRippler fXRippler8 = new JFXRippler(menuScheduling, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        groupTraining.getChildren().addAll(fXRippler7, fXRippler8);

        JFXRippler fXRippler9 = new JFXRippler(menuCompany, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        JFXRippler fXRippler10 = new JFXRippler(menuSynch, JFXRippler.RipplerMask.RECT, JFXRippler.RipplerPos.FRONT);
        groupCompany.getChildren().addAll(fXRippler9, fXRippler10);

    }

    @FXML
    private void showScheduling(MouseEvent event) throws IOException {
        menuScheduling.getScene().getWindow().hide();
        Stage news=new Stage();
        Parent root=FXMLLoader.load(getClass().getResource("/GUIAdmin/EstablishmentAdmin.fxml"));
        Scene s=new Scene(root);
        news.setScene(s);
        news.show();
    }

    @FXML
    private void showTraining(MouseEvent event) throws IOException {
         setStage("/GUIAdmin/EstablishmentAdmin.fxml");  
    }

    @FXML
    private void synchroniseOnline(MouseEvent event) {
        setStage("/modules/Cloud.fxml");
    }

    public void setStage(String fxml) {
        try {
            //dim overlay on new stage opening
            Region veil = new Region();
            veil.setPrefSize(1100, 650);
            veil.setStyle("-fx-background-color:rgba(0,0,0,0.3)");
            Stage newStage = new Stage();
           Parent parent = FXMLLoader.load(getClass().getResource(fxml));
            
            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            newStage.setScene(scene);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.initStyle(StageStyle.TRANSPARENT);
            newStage.getScene().getRoot().setEffect(new DropShadow());
            newStage.showingProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    rootPane.getChildren().add(veil);
                } else if (rootPane.getChildren().contains(veil)) {
                    rootPane.getChildren().removeAll(veil);
                }
                
            });
            newStage.centerOnScreen();
            newStage.show();
        } catch (IOException ex) {
            System.out.println("ss");
        }
    }
    
 
     
     
     

    @FXML
    private void showReports(MouseEvent event) {
    }

    @FXML
    private void showSettings(MouseEvent event) {
    }

    
    @FXML
    private void showBackUps(MouseEvent event) {
        setStage("/modules/BackUps.fxml");
    }

    @FXML
    private void loggOff(ActionEvent event) throws IOException {
        btnAccount.getScene().getWindow().hide();
        Stage news=new Stage();
        Parent root=FXMLLoader.load(getClass().getResource("/view/signin.fxml"));
        Scene s=new Scene(root);
        news.setScene(s);
        news.show();
    }

    @FXML
    private void close(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to exit MedLife?", ButtonType.YES, ButtonType.CANCEL);
        Optional<ButtonType> rs = alert.showAndWait();
        if(rs.get() == ButtonType.YES)
            Platform.exit();
    }

 Medlife su;
        Stage stage;
        
      public  void Main( Medlife su,Stage stage){
        this.stage=stage;
        this.su=su;
        
        }
    
      
      
    @FXML
    private void showAction(ActionEvent event) {
        JFXPopup popup=new JFXPopup();
       popup.setPopupContent(popUpHolder);
         popup.show(rootPane, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, -45, 65);
    }

}
