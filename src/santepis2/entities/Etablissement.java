/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.entities;

import edu.esprit.entities.Enumeration.Type;

/**
 *
 * @author SKANDER
 */
public class Etablissement {
    
    int id;
    String nom;
    String address;
    String icon;
    int telephone;
    String web;
    double latitude;
    double longitude;
    int rating;
    String name_image;
    int user_id;

    
    
   

    public Etablissement(String nom, String address, int telephone, String web, double latitude, double longitude,String icon) {
        this.nom = nom;
        this.address = address;
        this.telephone = telephone;
        this.web = web;
        this.latitude = latitude;
        this.longitude = longitude;
        this.icon = icon;
    }

    public Etablissement() {
        }
    
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

  

    public int getTelephone() {
        return telephone;
    }

    public String getIcon() {
        return icon;
    }
      public void setIcon(String icon) {
        this.icon = icon;
    }


    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getName_image() {
        return name_image;
    }

    public void setName_image(String name_image) {
        this.name_image = name_image;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Etablissement{" + "id=" + id + ", nom=" + nom + ", address=" + address + ", icon=" + icon + ", telephone=" + telephone + ", web=" + web + ", latitude=" + latitude + ", longitude=" + longitude + ", rating=" + rating + ", name_image=" + name_image + ", user_id=" + user_id + '}';
    }

   
    
    
}
