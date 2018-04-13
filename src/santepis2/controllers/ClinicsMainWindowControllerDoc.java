package santepis2.controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author MOHAMMED
 */
public class ClinicsMainWindowControllerDoc implements Initializable {

    

    
    

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/santepis2/gui/navigationDrawerFXMLDoc.fxml"));
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
        

    }
    @FXML
    public void OpenRDV(ActionEvent event) {
       
    }

    @FXML
    public void OpenArticle(ActionEvent event) throws IOException {
        Stage stage11=null;
    Parent parent11 = FXMLLoader.load(getClass().getResource("/santepis2/gui/AffichageArticle.fxml"));
       Scene scene11 = new Scene(parent11);
       stage11 = (Stage)((Node)event.getSource()).getScene().getWindow();
       stage11.hide();
       stage11.setScene(scene11);
       stage11.show();
       
    }

    @FXML
    public void OpenEvents(ActionEvent event) {
       
    }

    @FXML
    public void OpenForum(ActionEvent event) {
       
    }

    @FXML
    public void OpenShop(ActionEvent event) {
        
    }

    @FXML
    public void OpenEstab(ActionEvent event) {
       
    }

    
    

   
}
