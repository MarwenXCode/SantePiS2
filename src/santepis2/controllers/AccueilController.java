/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santepis2.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import santepis2.entities.Article;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
public class AccueilController implements Initializable {
    @FXML
    private TableView<Article> tableview;
    
    @FXML
    private TableColumn<Article, String> auteur;
    @FXML
    private TableColumn<Article, String> contenu;
    @FXML
    private TableColumn<Article, Date> date;
    @FXML
    private TableColumn<Article, String> titre;
    
    @FXML
    private TextField titre1;
    @FXML
    private TextField auteur1;
    @FXML
    private JFXTextArea contenu1;
    @FXML
    private DatePicker date1;
    @FXML
    private JFXButton image;
    @FXML
    private Label label;
    @FXML
    private ToolBar barSearch;
    @FXML
    private Button buttonCloseSearch;
    @FXML
    private TextField Search1;
    @FXML
    private Label labelMatches;
    @FXML
    private JFXButton ajouter;
    @FXML
    private Label valide1;
    @FXML
    private Label valider2;
    @FXML
    private Label valider3;
    @FXML
    private Label valide4;
    @FXML
    private Label valider5;
    @FXML
    private Label oui;
    @FXML
    private JFXButton video;
    @FXML
    private Label labv;
    @FXML
    private JFXButton update;
    @FXML
    private JFXButton sup;
    @FXML
    private Text text;
    @FXML
    private ImageView image11;
    @FXML
    private TableColumn<?, ?> image25;
    @FXML
    private JFXButton reflichir;

    /**
     * Initializes the controller class.
     */
    @Override
    
    
    public void initialize(URL url, ResourceBundle rb) {
       
        click();
    
        
 
        try {
            Statement ps = Connexion.getInstance().getConnexion().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
        }
       cetCelltable();
       afficherbasse();// TODO
       
       
       
       
    }
    private ObservableList<Article> ArticleData = FXCollections.observableArrayList();
    private ObservableList<Integer> List12 = FXCollections.observableArrayList();
    private ObservableList<Article> ArticleData1 = FXCollections.observableArrayList();
    
    public void cetCelltable ()
    {
    titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        auteur.setCellValueFactory(new PropertyValueFactory<>("Auteur"));
        contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        date.setCellValueFactory(new PropertyValueFactory<>("DateCreation"));
        image25.setCellValueFactory(new PropertyValueFactory<>("url_image"));
        
        
    }
    
    
    
    private void afficherbasse( ) {
        
        try {
            String reqSelect="Select * From article ";
          Statement ps = Connexion.getInstance().getConnexion().createStatement();
 ResultSet rs =ps.executeQuery(reqSelect);
             while (rs.next()){
                 
                  ArticleData.add(new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(10)) );
          }
        } catch (Exception e) {
            System.out.println("non affiché");
             e.printStackTrace();
        }
        tableview.setItems(ArticleData);
       }

    @FXML
    private String ajouterimage ( ) throws FileNotFoundException {
  
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
      Image img = new Image(path1);
                  
              image11.setImage(img);
         
           return path ;
           
        }
    
   
    @FXML
    private void updatearticle(ActionEvent event) {
     try {
        ServicesArticles ms = new ServicesArticles() ;
        Article p1 = new Article() ;
        String m1 =label.getText();
        p1.titre=titre1.getText();
        p1.Auteur=auteur1.getText();
        p1.Contenu=contenu1.getText();
        LocalDate m=date1.getValue() ;
        
        String date2 = m.getYear()+"-"+m.getMonthValue()+"-"+m.getDayOfMonth();
        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
        Article prod = tableview.getSelectionModel().getSelectedItem();
        
        int a = prod.id ;
        System.out.println(a);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        p1.DateCreation=sqlDate ;
        p1.url_image=label.getText() ;
        
        
       ms.updateArticle(p1,a);
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
    private void suppArticle(ActionEvent event) throws SQLException {
        ServicesArticles ms = new ServicesArticles() ;
        Article p1 = new Article() ;
        Article prod = tableview.getSelectionModel().getSelectedItem();
        
        int a = prod.id ;
        reflichir(event);
        
        ms.deleteArticle(a); 
intial();
        reflich();
    }
    List <Article> Articles = new ArrayList<Article> ();
  
    
    public void reflich()
    {
    ArticleData.clear();
    List12.clear();
     try {
            String reqSelect="Select * From article ";
          Statement ps = Connexion.getInstance().getConnexion().createStatement();
 ResultSet rs =ps.executeQuery(reqSelect);
             while (rs.next()){
                  ArticleData.add(new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(10)) );
                 List12.add(rs.getInt(1));
                 
            }
        } catch (Exception e) {
            System.out.println("non affiché");
             e.printStackTrace();
        }
        tableview.setItems(ArticleData);
    
    }

    @FXML
    private void reflichir(ActionEvent event) {
        reflich();
    }

    

    
 private void click ()
    { tableview.setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            int prod = tableview.getSelectionModel().getSelectedIndex();
            Article a = tableview.getItems().get(prod) ;
            contenu1.setText(a.getContenu());
                  titre1.setText(a.getTitre());
                  auteur1.setText(a.getAuteur());
                  String ab = a.getUrl_image();
                  System.out.println(ab);
                 Image img = new Image(a.getUrl_image());
                  
              image11.setImage(img);
              text.setFont(Font.font("Verdana", FontWeight.THIN, 15));
              text.setWrappingWidth(900);
            
              text.setText(a.getContenu());
                  
           
        }
    });
    
    }

    

    @FXML
    private void searchtitre(ActionEvent event) {
        
        
        try {
            ArticleData.clear();
            
    
            String s = Search1.getText();
            System.out.println(s);
            String req="Select * From article where titre =? or auteur=?";
          PreparedStatement ps=Connexion.getInstance().getConnexion().prepareStatement(req);
          ps.setString(1, s);
          ps.setString(2, "ahmed");
            ResultSet rs = ps.executeQuery();
             while (rs.next()){
                  ArticleData.add(new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), null) );
          }
        } catch (Exception e) {
            System.out.println("non affiché");
             e.printStackTrace();
        }
        tableview.setItems(ArticleData);
        
        
    }
    
    
    
    public int validation (String titre)
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
    
    public boolean validationdate(java.util.Date date ) throws ParseException
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
@FXML
    public void ajouterarticle(ActionEvent event) {
       


        
      try {
        ServicesArticles ms = new ServicesArticles() ;
        Article p1 = new Article() ;
        
        p1.titre=titre1.getText();
        
         
         if (validation(p1.titre)==0)
         {
         valide1.setText("Champ invalid");}
         else {
             valide1.setText("");
         
         }
       p1.Auteur=auteur1.getText();
        if (validation(p1.Auteur)==0)
        {
         valider2.setText("Champ invalid");}
        else {valider2.setText("");}
        
        p1.Contenu=contenu1.getText();
        LocalDate m=date1.getValue() ;
        String date1 = m.getYear()+"-"+m.getMonthValue()+"-"+m.getDayOfMonth();
         
         java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
                  java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                  
                  if (validationdate(sqlDate)==false)
                  {
                                     valider3.setText("Champ invalid");

                  }
                  else {
                      valider3.setText("");
                  
                  }
        p1.DateCreation=sqlDate ;
        
        p1.url_image=label.getText() ;
        p1.url_video=labv.getText();
         Image img = new Image(p1.url_image);
                  
              image11.setImage(img);
              
            
              
        
        
        if (validationdate(utilDate)==true&&validation(p1.titre)==1&&validation(p1.Auteur)==1)
        { 
        ms.ajouterArticle(p1);
        intial();
        valide1.setText("");
        valider2.setText("");
        valider3.setText("");
        
                oui.setText("Article ajoutée");
        
        
        }
        else {
        valide4.setText("Article non ajouter");
        }
        

        }
        catch (Exception e)
        {
        System.out.println("non ajouté");
             e.printStackTrace();
        
        }
      reflich();
    }

    @FXML
    private String ajoutervideo(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Video", "*.mp4");
        chooser.getExtensionFilters().add(filter);
        chooser.setTitle("Select Video");
        File video = chooser.showOpenDialog(null);
        String name = video.getName() ;
        
        
      String path = video.getAbsolutePath();
      String type = "video" ;
      String path1 = FileUploader.upload(name,path,type);
      labv.setText(path1);
         
           return path ;
    }
    public void intial ()
    {
        titre1.setText("");
        contenu1.setText("");
        auteur1.setText("");
    
    
    }

    
    

    
    
    
    
    
    
}
