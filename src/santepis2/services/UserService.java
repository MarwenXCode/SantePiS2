/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santepis2.services;

import santepis2.entities.User;
import santepis2.services.interfaces.IUserService;
import santepis2.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sisyph
 */
public class UserService implements IUserService{
    private Connection connection;
    
 

    public UserService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(User r) {
        try {
            String req = "insert into fos_user(username, username_canonical, email, email_canonical, enabled,  salt, password, last_login, confirmation_token, password_requested_at, roles, lastname, codeconfirmation, photo_membre, telephone, name, specialty) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, r.getUsername());
            ps.setString(2, r.getUsernameCanonical());
            ps.setString(3, r.getEmail());
            ps.setString(4, r.getEmailCanonical());
            ps.setBoolean(5, r.getEnabled());
            ps.setString(6, r.getSalt());
            ps.setString(7, r.getPassword());
            ps.setDate(8, (Date) r.getLastLogin());
            ps.setString(9, r.getConfirmationToken());
            ps.setDate(10, (Date) r.getPasswordRequestedAt());
            ps.setString(11, r.getRoles());
            ps.setString(12, r.getLastname());
            ps.setInt(13, r.getCodeconfirmation());
            ps.setString(14, r.getPhotoMembre());
            ps.setInt(15, r.getTelephone());
            ps.setString(16, r.getName());
            ps.setString(17, r.getSpecialty());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User r) {
        try {
            String req = "update fos_user set username=?, username_canonical=?, email=?, email_canonical=?, enabled=?,  salt=?, password=?, last_login=?, confirmation_token=?, password_requested_at=?, roles=?, lastname=?, photo_membre=?, telephone=?, name=?, specialty=? where id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, r.getUsername());
            ps.setString(2, r.getUsernameCanonical());
            ps.setString(3, r.getEmail());
            ps.setString(4, r.getEmailCanonical());
            ps.setBoolean(5, r.getEnabled());
            ps.setString(6, r.getSalt());
            ps.setString(7, r.getPassword());
            ps.setDate(8, (Date) r.getLastLogin());
            ps.setString(9, r.getConfirmationToken());
            ps.setDate(10, (Date) r.getPasswordRequestedAt());
            ps.setString(11, r.getRoles());
            ps.setString(12, r.getLastname());
         
            ps.setString(13, r.getPhotoMembre());
            ps.setInt(14, r.getTelephone());
            ps.setString(15, r.getName());
            ps.setString(16, r.getSpecialty());
            ps.setInt(17, r.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateC(User r) {
        try {
            String req = "update fos_user set username=?, username_canonical=?, email=?, email_canonical=?, enabled=?,  salt=?, password=?, last_login=?, confirmation_token=?, password_requested_at=?, roles=?, lastname=?,  photo_membre=?, telephone=?, name=?  where id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, r.getUsername());
            ps.setString(2, r.getUsernameCanonical());
            ps.setString(3, r.getEmail());
            ps.setString(4, r.getEmailCanonical());
            ps.setBoolean(5, r.getEnabled());
            ps.setString(6, r.getSalt());
            ps.setString(7, r.getPassword());
            ps.setDate(8, (Date) r.getLastLogin());
            ps.setString(9, r.getConfirmationToken());
            ps.setDate(10, (Date) r.getPasswordRequestedAt());
            ps.setString(11, r.getRoles());
            ps.setString(12, r.getLastname());
            
            ps.setString(13, r.getPhotoMembre());
            ps.setInt(14, r.getTelephone());
            ps.setString(15, r.getName());
        
            ps.setInt(16,r.getId());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateEnabled(User r) {
        try {
            String req = "update fos_user SET enabled=? where id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setBoolean(1, true);
            ps.setInt(2, r.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(int id) {
        try {
            String req = "delete from fos_user where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            String req = "select * from fos_user";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getString(7), rs.getString(8), rs.getDate(9), rs.getString(10), rs.getDate(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15), rs.getInt(16), rs.getString(17), rs.getString(18));
                users.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }
    
    public List<User> getAllClient() {
        List<User> users = new ArrayList<>();
        try {
            String req = "select * from fos_user where roles=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,"a:1:{i:0;s:11:\"ROLE_MEMBRE\";}");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getString(7), rs.getString(8), rs.getDate(9), rs.getString(10), rs.getDate(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15), rs.getInt(16), rs.getString(17), rs.getString(18));
                users.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }
    
    public List<User> getAllDoc() {
        List<User> users = new ArrayList<>();
        try {
            String req = "select * from fos_user where roles=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,"a:1:{i:0;s:11:\"ROLE_DOCTOR\";}");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getString(7), rs.getString(8), rs.getDate(9), rs.getString(10), rs.getDate(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15), rs.getInt(16), rs.getString(17), rs.getString(18));
                users.add(u);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try {
            String req = "select * from fos_user where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getString(7), rs.getString(8), rs.getDate(9), rs.getString(10), rs.getDate(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15), rs.getInt(16), rs.getString(17), rs.getString(18));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public User findByUsername(String username) {
        User user = null;
        try {
            String req = "select * from fos_user where username =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getString(7), rs.getString(8), rs.getDate(9), rs.getString(10), rs.getDate(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15), rs.getInt(16), rs.getString(17), rs.getString(18));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public User findByEmail(String email) {
        User user = null;
        try {
            String req = "select * from fos_user where email =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getString(7), rs.getString(8), rs.getDate(9), rs.getString(10), rs.getDate(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15), rs.getInt(16), rs.getString(17), rs.getString(18));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    
    
    
    
    
    
    
         @Override
     public void updateRole(User r) {
        try{
            String req ="update fos_user set roles=? where id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            System.out.println("role "+r.getRoles());
            
            ps.setString(1, r.getRoles());
            ps.setInt(2, r.getId());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
     
    @Override
     public void updateban(int id) {
        
            String request = "update fos_user set ban=? where id=?";
        PreparedStatement st;
        
    try {
              st = connection.prepareStatement(request);
              st.setInt(1, 1);
              st.setInt(2, id);
              st.executeUpdate();
              
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
     @Override
    public void setenabledtrue(int id) {
         String request = "update fos_user set enabled=? where id=?";
        PreparedStatement st;
        
    try {
              st = connection.prepareStatement(request);
              st.setInt(1, 1);
              st.setInt(2, id);
              st.executeUpdate();
              
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
  
    }
}