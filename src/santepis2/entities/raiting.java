/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santepis2.entities;

/**
 *
 * @author TOSHIBA
 */
public class raiting {
    
    public int id_raiting ;
    public int id_user ;
    public int id_article ;
    public String raiting ;

    public int getId_raiting() {
        return id_raiting;
    }

    public void setId_raiting(int id_raiting) {
        this.id_raiting = id_raiting;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public String getRaiting() {
        return raiting;
    }

    public void setRaiting(String raiting) {
        this.raiting = raiting;
    }
    
    
    
}
