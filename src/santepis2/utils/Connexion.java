/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santepis2.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MBM info
 */
public class Connexion {
    
        public static Connexion instance;
        String url = "jdbc:mysql://localhost:3306/santepi" ;
        String login ="root";
        String pw1="";
        public Connection  cnx;
    private Connexion(){
            try {
                this.cnx=DriverManager.getConnection(url,login,pw1);
                System.out.println("connexion etablit");
                if (this.cnx != null) { System.out.println("Successfully connected to MySQL database test"); }


       } catch (SQLException e) {
           System.out.println("connexion non etablit");
           e.printStackTrace();
            
        }
    }
    
    public static Connexion getInstance() 
    {
        if (instance==null)
                   instance = new Connexion();
                return instance;
    }
    
    public Connection getConnexion()
    {
       
        return this.cnx;
    }
    
     

      
    
}
