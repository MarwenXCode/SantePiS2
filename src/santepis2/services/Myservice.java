/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Etablissement;
import edu.esprit.util.Connexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 *
 * @author SKANDER
 */
public class Myservice {
    
    Statement st;
     

      public void insert (Etablissement e)
    {  	 
        try { 
           st = Connexion.getInstance().getConnexion().createStatement();
           //  String req="INSERT INTO etablissement ( nom, address, telephone, web) VALUES('"+e.getNom()+"','"+e.getAddress()+"','"+e.getTelephone()+"','"+e.getWeb()+"')";
            System.out.println(e);
           String req="INSERT INTO establishment (nom , address, icon, telephone, web, latitude, longitude, name_image, user_id) VALUES('"+e.getNom()+"','"+e.getAddress()+"','"+e.getIcon()+"','"+e.getTelephone()+"','"+e.getWeb()+"','"+e.getLatitude()+"','"+e.getLongitude()+"','"+e.getName_image()+"','"+6+"')";
              PreparedStatement ps=Connexion.getInstance().getConnexion().prepareStatement(req);
            st.executeUpdate(req);
            System.out.println("Etablissement ajouté");
        } catch (SQLException ex) {
            System.out.println("ajout failed");
            Logger.getLogger(Myservice.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
      
        
    
        public void update (Etablissement e, int i)
    {  	 
        try { 
           st = Connexion.getInstance().getConnexion().createStatement();
           

           String req="UPDATE establishment SET nom = '"+e.getNom()+"', address= '"+e.getAddress()+"', icon = '"+e.getIcon()+"', telephone = '"+e.getTelephone()+"', web = '"+e.getWeb()+"', latitude = '"+e.getLatitude()+"', longitude = '"+e.getLongitude()+"', rating = '"+e.getRating()+"', name_image = '"+e.getName_image()+"' where id='"+i+"' ";

     
           PreparedStatement ps=Connexion.getInstance().getConnexion().prepareStatement(req);
            st.executeUpdate(req);
            System.out.println("update réalisé avec succees modifiée");
        } catch (SQLException ex) {
            System.out.println("update failed");
            Logger.getLogger(Myservice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
         public int count(Etablissement e){  	 
        try { 
           st = Connexion.getInstance().getConnexion().createStatement();
           

           String req="count (*) from establishment  ";

     
           PreparedStatement ps=Connexion.getInstance().getConnexion().prepareStatement(req);
            st.executeUpdate(req);
            
            System.out.println("update réalisé avec succees modifiée");
            
        } catch (SQLException ex) {
            System.out.println("update failed");
            Logger.getLogger(Myservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
   
    }
        
        public void delete (int id)
    {  	 
        try { 
           st = Connexion.getInstance().getConnexion().createStatement();
           

           String req=" delete from etablissement where id = "+id+" " ;
     
           PreparedStatement ps=Connexion.getInstance().getConnexion().prepareStatement(req);
            st.executeUpdate(req);
            System.out.println("delete réalisé avec succees ");
        } catch (SQLException ex) {
            System.out.println("delete failed");
            Logger.getLogger(Myservice.class.getName()).log(Level.SEVERE, null, ex);
        }
   
       
    }
        
          public ObservableList<Etablissement> recherche (String nom)
    {  	 
         ObservableList<Etablissement> et =FXCollections.observableArrayList();
       
        try { 
           st = Connexion.getInstance().getConnexion().createStatement();
           

           String req=" select * from establishment where nom like '%"+nom+"%' " ;
     
           Statement ps = Connexion.getInstance().getConnexion().createStatement();

              ResultSet rs=ps.executeQuery(req);
            
                      while (rs.next()){
               
                  Image img=new Image(rs.getString(10));
                  Etablissement e = new Etablissement();
                  e.setId(rs.getInt(1)) ;
                  e.setNom(rs.getString(2)) ;
                  e.setAddress(rs.getString(3)) ;
                  e.setIcon(rs.getString(4)) ;
                  e.setTelephone(rs.getInt(5)) ;
                  e.setWeb(rs.getString(6)) ;
                  e.setLatitude(rs.getDouble(7)) ;
                  e.setLongitude(rs.getDouble(8)) ;
                  e.setRating(rs.getInt(9)) ;
                  
                  e.setName_image(rs.getString(10)) ;
                  
                  e.setUser_id(rs.getInt(11)) ;
                  
                   et.add(e);
             System.out.println(e);
             System.out.println("établissement trouvée");
               
              }
 
            
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(Myservice.class.getName()).log(Level.SEVERE, null, ex);
        }
   
       return et;
    }
   
     public ObservableList<Etablissement> afficher()
    {
          ObservableList<Etablissement> et =FXCollections.observableArrayList();
        try   {
            String reqSelect="Select * From establishment ";
          Statement st = Connexion.getInstance().getConnexion().createStatement();

              ResultSet rs=st.executeQuery(reqSelect);
              
             // rs.beforeFirst();
              while (rs.next()){
               
                  Image img=new Image(rs.getString(10));
                  Etablissement e = new Etablissement();
                  e.setId(rs.getInt(1)) ;
                  e.setNom(rs.getString(2)) ;
                  e.setAddress(rs.getString(3)) ;
                  e.setIcon(rs.getString(4)) ;
                  e.setTelephone(rs.getInt(5)) ;
                  e.setWeb(rs.getString(6)) ;
                  e.setLatitude(rs.getDouble(7)) ;
                  e.setLongitude(rs.getDouble(8)) ;
                  e.setRating(rs.getInt(9)) ;
                  
                  e.setName_image(rs.getString(10)) ;
                  
                  e.setUser_id(rs.getInt(11)) ;
                 
                  et.add(e);

                  System.out.println(e);
              }
        } catch (Exception e) {
            System.out.println(e);
              Logger.getLogger(Myservice.class.getName()).log(Level.SEVERE, null, e);
        }
        return  et;
    }
   
}
