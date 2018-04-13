/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santepis2.entities;
import java.awt.Image;
import java.util.Date ;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author TOSHIBA
 */
public class Article {
    
    public int id;
    public String titre;
    public String Auteur;
    public String Contenu;
    public String url_video ;

   
    public Date DateCreation;
    public String ids ;
    public Image image;
    public String url_image ;

    public Article(int id, String titre, String Auteur, String Contenu, Date DateCreation, String url_image) {
        this.id = id;
        this.titre = titre;
        this.Auteur = Auteur;
        this.Contenu = Contenu;
        this.DateCreation = DateCreation;
        this.url_image = url_image;
    }

    public Article() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return Auteur;
    }

    public void setAuteur(String Auteur) {
        this.Auteur = Auteur;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

    public Date getDateCreation() {
        return DateCreation;
    }

    public void setDateCreation(Date DateCreation) {
        this.DateCreation = DateCreation;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
     public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getUrl_video() {
        return url_video;
    }

    public void setUrl_video(String url_video) {
        this.url_video = url_video;
    }
    
   
    

   
     
    
}
