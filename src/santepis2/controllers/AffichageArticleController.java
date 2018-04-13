/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santepis2.controllers;




import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import santepis2.entities.Article;
import santepis2.entities.Evenement;
import santepis2.entities.raiting;
import santepis2.entities.Commentaire;
import java.awt.Panel;
import java.awt.Rectangle;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Blob;
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
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.controlsfx.control.Rating;
import santepis2.services.ServicesArticles;
import santepis2.utils.FileUploader;
import santepis2.utils.Connexion;

/**
 * FXML Controller class
 *
 * @author TOSHIBA
 */
public class AffichageArticleController implements Initializable {
    @FXML
    private ListView<String> listview;
    private Label label;
    @FXML
    private Label titre;
    @FXML
    private Label auteur;
    @FXML
    private ImageView image;
    @FXML
    private Text text;
    private Text texte;
    @FXML
    private Label date1;
    @FXML
    private Button play;
    @FXML
    private Button stop;
    @FXML
    private MediaView mediaview;
    private MediaPlayer mediaplayer ;
    @FXML
    private ToolBar barSearch;
    @FXML
    private Button buttonCloseSearch;
    @FXML
    private TextField Search1;
    @FXML
    private Label labelMatches;
    @FXML
    private Text textcomment;
    private JFXTextArea txomment;
    @FXML
    private Button ajoutcom;
    @FXML
    private Label id ;
    @FXML
    private ListView<String> listcomment;
    @FXML
    private Rating rating;
    @FXML
    private Label labrating;
    @FXML
    private Text tedate;
    private JFXTextArea txcmment;
    @FXML
    private JFXTextArea textcommentaire;
    @FXML
    private JFXButton reflichir;
    
    

    /**
     * Initializes the controller class.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherbasse();
        list();
        rating();
        
        // TODO
        
        
    //read();
        
   
    
    }    
     
    private ObservableList<Article> ArticleData = FXCollections.observableArrayList();
        private ObservableList<String> Titres = FXCollections.observableArrayList();
        private ObservableList<String> Commment = FXCollections.observableArrayList();

private void afficherbasse( ) {
        
        try {
            String reqSelect="Select * From article ";
          Statement ps = Connexion.getInstance().getConnexion().createStatement();
 ResultSet rs =ps.executeQuery(reqSelect);
             while (rs.next()){
                  ArticleData.add(new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), null) );
                  Titres.add(rs.getString(2)) ;
             }
        } catch (Exception e) {
            System.out.println("non affiché");
             e.printStackTrace();
        }
        listview.setItems( Titres);
        
       }   
private void list ()
{

listview.setOnMouseClicked(new EventHandler<MouseEvent>() {
    private ResultSet rs;

    public void handle(MouseEvent e) {
        try {
           String s = listview.getSelectionModel().getSelectedItem();
       
           String req="select url_image,titre,contenu,auteur,dateCreation,url_video,id From article  where titre =? ";
           PreparedStatement ps=Connexion.getInstance().getConnexion().prepareStatement(req);
           ps.setString(1, s);
           rs =ps.executeQuery();
           while (rs.next()){
              
              titre.setText(rs.getString(2));
              auteur.setText(rs.getString(4));
              Date date = rs.getDate(5) ;
              java.sql.Date sqlDate = new java.sql.Date(date.getTime());
              String date5 = sqlDate.toString();
              String url_image1 = rs.getString(1);
              String url_video1 = rs.getString(6);
            
              
            
             image.setFitHeight(200);
             image.setFitWidth(355);
             int id1 = rs.getInt(7) ;
             String id2 = id1+"" ;
             id.setText(id2);
             
        Image img = new Image(rs.getString(1));
              image.setImage(img);
              text.setFont(Font.font("Verdana", FontWeight.THIN, 15));
              text.setWrappingWidth(900);
            
              text.setText(rs.getString(3));
              date1.setText(date5);
              String url= rs.getString(6) ;
              System.out.println(url);
               read(url);
               affichcomment();
               list1();
               
            }
            
            
        } catch (SQLException e1) {
            System.out.println("non affiché");
            e1.printStackTrace();
        } 
        
    }
});



    
}
  public void read (String path)
  { 
      System.out.println(path);
    Media media = new  Media(path);
    MediaPlayer mediaplayer = new MediaPlayer(media);
    mediaview.setFitHeight(200);
    mediaview.setFitWidth(440);
    mediaview.setMediaPlayer(mediaplayer);
   // mediaplayer.setAutoPlay(true);
    stop.setOnMouseClicked(e -> {
        if(MediaPlayer.Status.PLAYING.equals(mediaplayer.getStatus())) {
            mediaplayer.pause();
            stop.setText("restart");
        } else {
            mediaplayer.play();
        }
        if (MediaPlayer.Status.PAUSED.equals(mediaplayer.getStatus()))
        {
            stop.setText("Stop");
        mediaplayer.play();
        }
                
    });
    play.setOnMouseClicked(e -> {
        if(MediaPlayer.Status.STOPPED.equals(mediaplayer.getStatus())) {
            mediaplayer.play();
        } else {
            mediaplayer.play();
        }
        if (MediaPlayer.Status.PAUSED.equals(mediaplayer.getStatus()))
        {
        mediaplayer.play();
        }
        
        
        
            
    });

  }

     @FXML
    private void searchtitre(ActionEvent event) {
        
        
        try {
            ArticleData.clear();
    Titres.clear();
            String s = Search1.getText();
            System.out.println(s);
            String req="Select * From article where titre =? or auteur=?";
          PreparedStatement ps=Connexion.getInstance().getConnexion().prepareStatement(req);
          ps.setString(1, s);
          ps.setString(2, "ahmed");
            ResultSet rs = ps.executeQuery();
             while (rs.next()){
                  ArticleData.add(new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), null) );
          Titres.add(rs.getString(2)) ;
             }
        } catch (Exception e) {
            System.out.println("non affiché");
             e.printStackTrace();
        }
        listview.setItems(Titres);
        
        
    }

    @FXML
    private void ajoutercomm(ActionEvent event) throws ParseException {
      try {  
        String idd = id.getText();
        int idd1 = Integer.parseInt(idd);
        System.out.println(idd1);
        ServicesArticles ms = new ServicesArticles() ;
        Commentaire C = new Commentaire();
        C.comment = textcommentaire.getText();
       // System.out.println(txomment.getText());
                  

        C.id_article=idd1 ;
        String  time = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").format(Calendar.getInstance().getTime()) ;
           
           java.util.Date utilDate = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss").parse(time);
           java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
           
  java.util.Date date_util = new java.util.Date();
            java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
           
           C.date= date_sql;
           
           ms.ajouterComment(C);
           textcommentaire.setText("");
    affichcomment();
                System.out.println("ajouté");

      }
    catch (Exception e) {
            System.out.println("non ajouté");
             e.printStackTrace();
        }
    }
    
    
    public void affichcomment()
    {
        
        try {
            Commment.clear();
            String reqSelect="Select comment From commentaire where id_article =?";
          PreparedStatement ps = Connexion.getInstance().getConnexion().prepareStatement(reqSelect);
                    
        String idd = id.getText();
        int m = Integer.parseInt(idd);
        
          ps.setInt(1, m);
              ResultSet rs =ps.executeQuery();
                    

              while (rs.next()){
                  
                  Commentaire p = new Commentaire();
                 // p.id= rs.getInt(1);
                  p.comment = rs.getString(1);
                  
                  Commment.add(p.comment);
             
              }
        } catch (Exception e) {
            System.out.println("non affiché");
             e.printStackTrace();
        }
        listcomment.setItems(Commment);
        
    
    
    }
    
    private void list1 ()
{

listcomment.setOnMouseClicked(new EventHandler<MouseEvent>() {
    private ResultSet rs;

    public void handle(MouseEvent e) {
        try {
            String s = listcomment.getSelectionModel().getSelectedItem();
       
           String req="select comment,date From commentaire  where comment =? ";
           PreparedStatement ps=Connexion.getInstance().getConnexion().prepareStatement(req);
          ps.setString(1, s);
         rs =ps.executeQuery();
           while (rs.next()){
              String comment = rs.getString(1) ;
              Date date = rs.getDate(2) ;
              java.sql.Date sqlDate = new java.sql.Date(date.getTime());
              String date5 = sqlDate.toString();
              textcomment.setFont(Font.font("Verdana", FontWeight.THIN, 15));
              textcomment.setWrappingWidth(200);
              textcomment.setText(comment);
              tedate.setText(date5);
               
            }
            
            
        } catch (SQLException e1) {
            System.out.println("non affiché");
            e1.printStackTrace();
        } 
        
    }
});
  
}
    
    public void rating ()
    {
        rating.ratingProperty().addListener(new ChangeListener<Number>()
                
                {

            @Override
            public void changed(ObservableValue<? extends Number> args0, Number t, Number t1) {
                 labrating.setText("rating:" + t1.toString());
                  try {
        String idd = id.getText();
        int idd1 = Integer.parseInt(idd);
            Scanner sc = new  Scanner(System.in);
            raiting r = new raiting();
           
                  String req="insert into rating (raiting,id_article,id_user) values (?,?,?)";
                  r.raiting=t1.toString();
                  r.id_article=idd1;
                   PreparedStatement ps=Connexion.getInstance().getConnexion().prepareStatement(req);
                   ps.setString(1,r.raiting);
                   ps.setInt(2, r.id_article);
                   ps.setInt(3, 5);
                   
                   
                   
                   ps.execute();
                  System.out.println("ajout succesful");
        } catch (Exception e) {
            System.out.println("non ajouteé");
            e.printStackTrace();
            
        }
                 
                
            }
                    
                
                }
        );
        
        
    
    }

    @FXML
    private void reflichir(ActionEvent event) {
        reflich();
        
        
    }
    public void reflich()
    {
    Titres.clear();
    
     try {
            String reqSelect="Select * From article ";
          Statement ps = Connexion.getInstance().getConnexion().createStatement();
 ResultSet rs =ps.executeQuery(reqSelect);
             while (rs.next()){
                  //ArticleData.add(new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(10)) );
                 Titres.add(rs.getString(2));
                 
            }
        } catch (Exception e) {
            System.out.println("non affiché");
             e.printStackTrace();
        }
        listview.setItems(Titres);
    
    }
}