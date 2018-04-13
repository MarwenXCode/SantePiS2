/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santepis2.services;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.sql.PreparedStatement;
import java.util.Scanner;
import santepis2.entities.Article ;
import santepis2.entities.Evenement ;
import santepis2.entities.Commentaire;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date ;
import java.sql.Timestamp ;
import java.util.ArrayList;
import java.util.List;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdk.nashorn.internal.runtime.Debug;

import santepis2.utils.Connexion;

/**
 *
 * @author TOSHIBA
 */
public class ServicesArticles {
    private ObservableList<Article> ArticleData = FXCollections.observableArrayList();
        private List<Article> ArticleData1 =  new ArrayList();
        

   
    
   
    
    public void ajouterArticle (Article A )
    {  	 	
       
        try {

            Scanner sc = new  Scanner(System.in);
           
                  String req="insert into article (titre,Auteur,Contenu,DateCreation,url_image,url_video) values (?,?,?,?,?,?)";
                  
                   PreparedStatement ps=Connexion.getInstance().getConnexion().prepareStatement(req);
                   ps.setString(1,A.titre);
                   ps.setString(2, A.Auteur);
                   ps.setString(3, A.Contenu);
                   ps.setDate(4, (java.sql.Date) A.DateCreation);
                   ps.setString(5,A.url_image );
                   ps.setString(6, A.url_video);
                   ps.execute();
                  System.out.println("ajout succesful");
        } catch (Exception e) {
            System.out.println("non ajouteé");
            e.printStackTrace();
            
        }
       
    }
    
    
    
    public void ajouterEvenement (Evenement E )
    {  	 	
       
        try {
            

            Scanner sc = new  Scanner(System.in);
       
                 
                  String req="insert into evenement (titre,description,date_Evenement,date_debut,date_fin,url_image ) values (?,?,?,?,?,?)";
                  
                   PreparedStatement ps=Connexion.getInstance().getConnexion().prepareStatement(req);
                   ps.setString(1, E.titre);
                   ps.setString(2, E.Desciption);
                   ps.setDate(3, (java.sql.Date) E.DateEvenement);
                   ps.setTime(4, E.DateDebut);
                   ps.setTime(5, E.Datefin);
                   ps.setString(6, E.url_image);
                  
                   ps.execute();
                  System.out.println("ajout Evenment succesful");
        } catch (Exception e) {
            System.out.println("non ajouteé");
            e.printStackTrace();
            
        }
       
    }
    public  void deleteArticle(int m) throws SQLException {

		
try {
		String deleteSQL = "DELETE from article WHERE id = ?";
PreparedStatement ps=Connexion.getInstance().getConnexion().prepareStatement(deleteSQL);
                        Scanner sc = new  Scanner(System.in);
             ps.setInt(1, m);
		ps.executeUpdate();
			System.out.println("Record is deleted!");
		} catch (SQLException e) {
                    e.printStackTrace();

			System.out.println("non supp");
}

	}
    public  void deleteEvenement(int m ) throws SQLException {

		
try {
		String deleteSQL = "DELETE from evenement WHERE id = ?";
PreparedStatement ps=Connexion.getInstance().getConnexion().prepareStatement(deleteSQL);
                        Scanner sc = new  Scanner(System.in);
              ps.setInt(1, m);
	ps.executeUpdate();
System.out.println("evenement is deleted!");
		} catch (SQLException e) {
                    e.printStackTrace();
			System.out.println("non supp");
}

	}
    public void updateArticle (Article A,int m)
    {  	 	
       
        try {
             String req="UPDATE article set titre = ?,Auteur=?,Contenu=?,DateCreation=? , url_image=?,url_image=? where id =?";
                   PreparedStatement ps=Connexion.getInstance().getConnexion().prepareStatement(req);
                   
                   ps.setInt(7,m);
                   System.out.println(A.id);
                   
                   
                   ps.setString(1, A.titre);
                   ps.setString(2, A.Auteur);
                   ps.setString(3, A.Contenu);
                   ps.setDate(4, (java.sql.Date) A.DateCreation);
                   ps.setString(5,A.url_image );
                   ps.setString(6, A.url_image);
                   
                   ps.executeUpdate();
                   System.out.println("oui");
                  
        } catch (Exception e) {
            
            e.printStackTrace();
                               System.out.println("non");

            
        }
       
    }
     public void updateEvenement (Evenement E,int m )
    {  	 	
       try {
            

            Scanner sc = new  Scanner(System.in);
            
             String req="UPDATE evenement set titre = ?,description=?,date_Evenement=?,date_debut=?,date_Fin=?,url_image=? WHERE id =?";
                   PreparedStatement ps=Connexion.getInstance().getConnexion().prepareStatement(req);
                   ps.setInt(7, m);
                   ps.setString(1, E.titre);
                   ps.setString(2, E.Desciption);
                   ps.setDate(3, (java.sql.Date) E.DateEvenement);
                   ps.setTime(4, E.DateDebut);
                   ps.setTime(5, E.Datefin);
                   ps.setString(6, E.url_image);
                  
                   ps.execute();
                  System.out.println("ajout succesful");
        } catch (Exception e) {
            System.out.println("non ajouteé");
            e.printStackTrace();
            
        }
        
       
    }
     List <Article> listArticle = new ArrayList<Article>();
     List <Evenement> listEvenement = new ArrayList<Evenement>();
    private ArrayList Article;
     
    
    
    public void  afficher()
    {
        try {
            String reqSelect="Select * From article ";
          Statement ps = Connexion.getInstance().getConnexion().createStatement();


              ResultSet rs =ps.executeQuery(reqSelect);
                    

              while (rs.next()){
                  
                  Article p = new Article();
                  p.id= rs.getInt(1);
                  p.titre = rs.getString(2);
                p.Auteur=rs.getString(3);
                  p.Contenu=rs.getString(4);
                  p.DateCreation=rs.getDate(5);
                  
                  ArticleData.add(p);
                 

                  System.out.println("id:"+rs.getInt("id")+"|Titre:"+rs.getString("titre")+"|Auteur:"+rs.getString("Auteur")+"|Contenu:"+rs.getString("Contenu")+"|Date:"+rs.getDate("DateCreation"));
            
              }
        } catch (Exception e) {
            System.out.println("non affiché");
             e.printStackTrace();
        }
        
    }
    
    
    

    /**
     * Constructor
     */
    
    
    public void deleteevenementpassetemp() throws ParseException
    {
        try {
           String  time = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()) ;
           
           java.util.Date utilDate = new SimpleDateFormat("yyyy/MM/dd").parse(time);
           java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
String deleteSQL = "DELETE from evenement WHERE  date_Evenement < ? ";
PreparedStatement ps=Connexion.getInstance().getConnexion().prepareStatement(deleteSQL);
                        Scanner sc = new  Scanner(System.in);
                        ps.setDate(1, sqlDate);
            
			ps.executeUpdate();

			System.out.println("evenement passe  is deleted!");

		} catch (SQLException e) {
                    e.printStackTrace();

			System.out.println("non supp");

		

		}
    
    
    }
    
    public void ajouterComment (Commentaire C  )
    {  	 	
       
        try {

            Scanner sc = new  Scanner(System.in);
           
                  String req="insert into commentaire (comment,id_article,date,id_user) values (?,?,?,?)";
                  
                   PreparedStatement ps=Connexion.getInstance().getConnexion().prepareStatement(req);
                   ps.setString(1,C.comment);
                   ps.setInt(2, C.id_article);
                   ps.setInt(4, 5);
                   
                   ps.setDate(3, (java.sql.Date) C.date);
                   
                   ps.execute();
                  System.out.println("ajout succesful");
        } catch (Exception e) {
            System.out.println("non ajouteé");
            e.printStackTrace();
            
        }
       
    }
   
}
