/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santepis2.entities;

import java.awt.Image;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author TOSHIBA
 */
public class Evenement {
    
    public int id;
    public String titre;
    public String Desciption;
    public Time DateDebut;
    public Time Datefin;
    public Date DateEvenement;
   
    public String url_image ;
    
    public Evenement() {
    }

    
    public Evenement(int id, String titre, String Desciption, Time DateDebut, Time Datefin, Date DateEvenement) {
        this.id = id;
        this.titre = titre;
        this.Desciption = Desciption;
        this.DateDebut = DateDebut;
        this.Datefin = Datefin;
        this.DateEvenement = DateEvenement;
        
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

    public String getDesciption() {
        return Desciption;
    }

    public void setDesciption(String Desciption) {
        this.Desciption = Desciption;
    }

    public Time getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Time DateDebut) {
        this.DateDebut = DateDebut;
    }

    public Time getDatefin() {
        return Datefin;
    }

    public void setDatefin(Time Datefin) {
        this.Datefin = Datefin;
    }

   

    public Date getDateEvenement() {
        return DateEvenement;
    }

    public void setDateEvenement(Date DateEvenement) {
        this.DateEvenement = DateEvenement;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }
    
    
    
    
}
