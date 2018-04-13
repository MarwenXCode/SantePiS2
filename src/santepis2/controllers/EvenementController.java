/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santepis2.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import santepis2.entities.Article;
import santepis2.entities.Evenement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import santepis2.services.ServicesArticles;
import santepis2.utils.Connexion;
import santepis2.utils.FileUploader;

/**
 * FXML Controller class
 *
 * @author TOSHIBA
 */
public class EvenementController implements Initializable {
    @FXML
    private TableView<Evenement> tableview;
    private TableColumn<Evenement, Integer> id;
    @FXML
    private TableColumn<Evenement, Date> date;
    @FXML
    private TableColumn<Evenement, String> titre;
    @FXML
    private TextField titre1;
    @FXML
    private JFXTextArea contenu1;
    @FXML
    private DatePicker date1;
    @FXML
    private Button update;
    private TextField idmahdi;
    @FXML
    private Button supp;
    @FXML
    private Button reflichir;
    @FXML
    private Label label;
    @FXML
    private ChoiceBox<String> ch1;
    @FXML
    private ChoiceBox<String> ch2;
    @FXML
    private ChoiceBox<String> ch3;
    @FXML
    private ChoiceBox<String> ch4;
    private ObservableList<Evenement> EvenementData = FXCollections.observableArrayList();
    private ObservableList<Integer> List12 = FXCollections.observableArrayList();
    ObservableList<String> List = FXCollections.observableArrayList("10","11","12","13","14","15","16","17","18","19");
        ObservableList<String> List1 = FXCollections.observableArrayList("00","05","10","15","20","25","30","35","40","45","50","55");
    @FXML
    private TableColumn<Evenement, String> description;
    @FXML
    private TableColumn<Evenement, Time> date_debut;
    @FXML
    private TableColumn<Evenement, Time> date_fin;
    @FXML
    private Label label10;
    @FXML
    private JFXButton image;
    @FXML
    private Label valider1;
    @FXML
    private Label valider2;
    @FXML
    private Label valider4;
    @FXML
    private Label valider5;
    @FXML
    private JFXButton ajoute;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        click();
 
        
       cetCelltable();
       afficherbasse();
        
        
        ch1.setItems(List);
        ch2.setItems(List1);
        ch3.setItems(List);
        ch4.setItems(List1);
        // TODO
    }    

    
    
     private void afficherbasse( ) {
        
        try {
            String reqSelect="Select * From evenement ";
          Statement ps = Connexion.getInstance().getConnexion().createStatement();
 ResultSet rs =ps.executeQuery(reqSelect);
             while (rs.next()){
                  EvenementData.add(new Evenement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTime(4), rs.getTime(6), rs.getDate(5)) );
                 List12.add(rs.getInt(1));
               
          }
        } catch (Exception e) {
            System.out.println("non affiché");
             e.printStackTrace();
        }
        tableview.setItems(EvenementData);
        
        
        
        
        
    }
     public void cetCelltable ()
    {
    titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        description.setCellValueFactory(new PropertyValueFactory<>("Desciption"));
        date_debut.setCellValueFactory(new PropertyValueFactory<>("DateDebut"));
        date.setCellValueFactory(new PropertyValueFactory<>("DateEvenement"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("Datefin"));
        
        
    }

    @FXML
    private void updatearticle(ActionEvent event) {
        try {
        ServicesArticles ms = new ServicesArticles() ;
        Evenement p1 = new Evenement() ;
        String m1 =label.getText();
 p1.titre=titre1.getText();
        p1.Desciption=contenu1.getText();
       
        LocalDate m=date1.getValue() ;
        String date2 = m.getYear()+"-"+m.getMonthValue()+"-"+m.getDayOfMonth();
   java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
   Evenement prod = tableview.getSelectionModel().getSelectedItem();
   String ch11 = ch1.getValue()+":"+ch2.getValue() ;
        String ch22 = ch3.getValue()+":"+ch4.getValue() ;
        Date indfm = new SimpleDateFormat("HH:mm").parse(ch11) ;
        java.sql.Time dateDebut = new java.sql.Time(indfm.getTime());
        Date indfm1 = new SimpleDateFormat("HH:mm").parse(ch22) ;
        java.sql.Time dateFin = new java.sql.Time(indfm1.getTime());
        

        
       int a = prod.id ;
                  java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        p1.DateEvenement=sqlDate ;
        p1.DateDebut=dateDebut;
        

        p1.Datefin=dateFin;
                
      p1.url_image=label.getText() ;
       ms.updateEvenement(p1,a);
       titre1.setText("");
        contenu1.setText("");
       intial();
                System.out.println("update");
}
        catch (Exception e)
        {
        System.out.println("non update");
             e.printStackTrace();
    }
        reflich();
    }

    @FXML
    private String ajouterimage(ActionEvent event) {
  
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
       
        chooser.setInitialDirectory(new File ("C:\\Users\\TOSHIBA\\Pictures\\devis"));
        chooser.getExtensionFilters().add(filter);
        chooser.setTitle("Select a photo");
        File photo = chooser.showOpenDialog(null);
        String name = photo.getName() ;
      String path = photo.getAbsolutePath();
      String type = "image" ;
      String path1 = FileUploader.upload(name,path,type);
      label.setText(path1);
         
           return path ;
           
        }

    @FXML
    private void suppArticle(ActionEvent event) throws SQLException, ParseException {
        ServicesArticles ms = new ServicesArticles() ;
        Evenement p1 = new Evenement() ;
        Evenement prod = tableview.getSelectionModel().getSelectedItem();
        
        int a = prod.id ;
        reflichir(event);
        
        ms.deleteEvenement(a);
      intial();
    }

    @FXML
    private void reflichir(ActionEvent event) throws ParseException {
        reflich();
        deleteEvenmentpasse();
    }

    private void ajouter1(ActionEvent event) throws IOException {
       try { 
        Parent root = FXMLLoader.load(getClass().getResource("ajouterevenement.fxml")) ;
        Scene scene = new Scene(root);
       Stage stage = new Stage();       
       stage.setScene(scene);
        stage.show();
       }
       catch (Exception e)
       {
           System.out.println("NON");
           e.printStackTrace();
       } }
    
    
     public void reflich()
    {
    EvenementData.clear();
    List12.clear();
     try {
            String reqSelect="Select * From evenement ";
          Statement ps = Connexion.getInstance().getConnexion().createStatement();
 ResultSet rs =ps.executeQuery(reqSelect);
             while (rs.next()){
  EvenementData.add(new Evenement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTime(4),rs.getTime(6),rs.getDate(5)) );
                 List12.add(rs.getInt(1));
                 
            }
        } catch (Exception e) {
            System.out.println("non affiché");
             e.printStackTrace();
        }
        tableview.setItems(EvenementData);
    
    }
 private void click ()
    { tableview.setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            int prod = tableview.getSelectionModel().getSelectedIndex();
            Evenement a = tableview.getItems().get(prod) ;
            contenu1.setText(a.getDesciption());
                  titre1.setText(a.getTitre());
                  
            
                 // String mahdi = a.getId()+"" ;
                 // idmahdi.setText(mahdi);
           
        }
    });
    
    }
 
 public void deleteEvenmentpasse() throws ParseException
 {
     ServicesArticles ms = new ServicesArticles() ;
        Evenement p1 = new Evenement() ;
        ms.deleteevenementpassetemp();
 
 
 }
 
 
 
    public boolean validationdate(Date date ) throws ParseException
    { 
        String  time = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()) ;
           
           java.util.Date utilDate = new SimpleDateFormat("yyyy/MM/dd").parse(time);
           java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
           if (date.compareTo(sqlDate) > 0)
           {
               return true ;
           } 
    
        
        return false ; 
        
    }
      public int validation (String titre  )
    {        int m = 0 ;
    for (int i=0;i<titre.length();i++)
    {
char first = titre.charAt(i);
if 
( Character.toString(first).matches("[a-z]")|| Character.toString(first).matches("[A-Z]")|| Character.toString(first).matches(" "))
{m = 1 ;}
else 
{i = titre.length();
m = 0 ; }
 }
    return m ;
    
  }
    

    @FXML
    private void ajouterevenement(ActionEvent event) {
        try {
        ServicesArticles ms = new ServicesArticles() ;
        Evenement p1 = new Evenement() ;
        p1.titre=titre1.getText();
        p1.Desciption=contenu1.getText();
        String cha = ch1.getValue()+":"+ch2.getValue() ;
        String chb = ch3.getValue()+":"+ch4.getValue() ;
        Date indfm = new SimpleDateFormat("HH:mm").parse(cha) ;
        java.sql.Time dateDebut = new java.sql.Time(indfm.getTime());
        Date indfm1 = new SimpleDateFormat("HH:mm").parse(chb) ;
        java.sql.Time dateFin = new java.sql.Time(indfm1.getTime());
        
        LocalDate m=date1.getValue() ;
        String date1 = m.getYear()+"-"+m.getMonthValue()+"-"+m.getDayOfMonth();
         
         Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                  java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                  
                  p1.DateDebut=dateDebut;
                  p1.Datefin=dateFin;
        p1.DateEvenement=sqlDate ;
        
        
        
         if (validation(p1.titre)==0)
         {
         valider1.setText("Champ invalid");}
         else {
             valider1.setText("");
         
         }
        if (validationdate(sqlDate)==false)
                  {
                         valider2.setText("Champ invalid");

                  }
                  else {
                      valider2.setText("");
                  
                  }
        if (validationdate(utilDate)==true&&validation(p1.titre)==1)
        { 
        ms.ajouterEvenement(p1);
        intial();
        valider1.setText("");
        valider2.setText("");
        
        
                valider5.setText("Evenement ajoutée");
                        valider4.setText("");

        
        
        }
        else {
        valider4.setText("Evenment non ajouter");
        valider5.setText("");
        }
        

        }
        catch (Exception e)
        {
        System.out.println("non ajouté");
             e.printStackTrace();
        
        }
        reflich();
    }
    
    
    public void intial ()
    {
        titre1.setText("");
        contenu1.setText("");
        
    
    
    }
    
}
