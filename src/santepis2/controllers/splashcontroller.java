/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santepis2.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import santepis2.SantePiS2;

/**
 * FXML Controller class
 *
 * @author SKANDER
 */
public class splashcontroller implements Initializable {

     @FXML
    private AnchorPane splashAnchorPane;
   
        SantePiS2 su;
        Stage stage;
        
      public  void Main( SantePiS2 su,Stage stage){
        this.stage=stage;
        this.su=su;
        
        }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       new splash().start();
    }
    
    class splash extends Thread{

        @Override
        public void run() {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    try {
                        Thread.sleep(1500);
                        FadeTransition fadeout=new FadeTransition(Duration.seconds(4), splashAnchorPane);
                        fadeout.setFromValue(1);
                        fadeout.setToValue(0);
                        fadeout.setCycleCount(1);
                        fadeout.play();
                        
                        fadeout.setOnFinished(e ->{
                       
                        });
                       
                    } 
                    catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
    
    }
    
}
