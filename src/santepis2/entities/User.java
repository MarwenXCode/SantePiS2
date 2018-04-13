/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santepis2.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sysiph
 */
@Entity
@Table(name = "fos_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByUsernameCanonical", query = "SELECT u FROM User u WHERE u.usernameCanonical = :usernameCanonical"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByEmailCanonical", query = "SELECT u FROM User u WHERE u.emailCanonical = :emailCanonical"),
    @NamedQuery(name = "User.findByEnabled", query = "SELECT u FROM User u WHERE u.enabled = :enabled"),
    @NamedQuery(name = "User.findBySalt", query = "SELECT u FROM User u WHERE u.salt = :salt"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByLastLogin", query = "SELECT u FROM User u WHERE u.lastLogin = :lastLogin"),
    @NamedQuery(name = "User.findByConfirmationToken", query = "SELECT u FROM User u WHERE u.confirmationToken = :confirmationToken"),
    @NamedQuery(name = "User.findByPasswordRequestedAt", query = "SELECT u FROM User u WHERE u.passwordRequestedAt = :passwordRequestedAt"),
    @NamedQuery(name = "User.findByLastname", query = "SELECT u FROM User u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "User.findByCodeconfirmation", query = "SELECT u FROM User u WHERE u.codeconfirmation = :codeconfirmation"),
    @NamedQuery(name = "User.findByPhotoMembre", query = "SELECT u FROM User u WHERE u.photoMembre = :photoMembre"),
    @NamedQuery(name = "User.findByTelephone", query = "SELECT u FROM User u WHERE u.telephone = :telephone"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findBySpecialty", query = "SELECT u FROM User u WHERE u.specialty = :specialty")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "username_canonical")
    private String usernameCanonical;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "email_canonical")
    private String emailCanonical;
    @Basic(optional = false)
    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "salt")
    private String salt;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Column(name = "confirmation_token")
    private String confirmationToken;
    @Column(name = "password_requested_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passwordRequestedAt;
    @Basic(optional = false)
    @Lob
    @Column(name = "roles")
    private String roles;
    @Basic(optional = false)
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "codeconfirmation")
    private Integer codeconfirmation;
    @Column(name = "photo_membre")
    private String photoMembre;
    @Column(name = "telephone")
    private Integer telephone;
    @Column(name = "name")
    private String name;
    @Column(name = "specialty")
    private String specialty;
    private static int idofconnecteduser ;
    private boolean ban ;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }
    public User(String username, String usernameCanonical, String email, String emailCanonical, boolean enabled, String salt, String password, Date lastLogin, String confirmationToken, Date passwordRequestedAt, String roles, Integer telephone, String photoMembre, String name ,String lastname,String specialty) {
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.lastLogin = lastLogin;
        this.confirmationToken = confirmationToken;
        this.passwordRequestedAt = passwordRequestedAt;
        this.roles = roles;
        this.telephone = telephone;
        this.photoMembre = photoMembre;
        this.name = name ;
        this.lastname = lastname ;
        this.specialty = specialty;
    }

    

    public String getClearRoles() {
        String a = "";
        String s = "";
        Pattern p = Pattern.compile("\".+\"");
        Matcher m = p.matcher(roles);
        while (m.find()) {
            System.out.println(m.group().replace("\"", ""));
            s = m.group().replace("\"", "");
            a=a+""+s;
        }

        
        return a;
    }

    public User(Integer id, String username, String usernameCanonical, String email, String emailCanonical, boolean enabled, String salt, String password, java.sql.Date lastLogin, String confirmationToken, java.sql.Date passwordRequestedAt, String roles, String lastname,Integer codeconfirmation, String photoMembre,Integer telephone,String name,String specialty ) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.lastLogin = lastLogin;
        this.confirmationToken = confirmationToken;
        this.passwordRequestedAt = passwordRequestedAt;
        this.roles = roles;
        this.lastname = lastname;
        this.codeconfirmation = codeconfirmation;
        this.photoMembre = photoMembre;
        this.telephone = telephone;
        this.name = name;
        this.specialty = specialty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameCanonical() {
        return usernameCanonical;
    }
    

    public void setUsernameCanonical(String usernameCanonical) {
        this.usernameCanonical = usernameCanonical;
    }
    public static int getIdofconnecteduser() {
        return idofconnecteduser;
    }
    public static void setIdofconnecteduser(int idofconnecteduser) {
        User.idofconnecteduser = idofconnecteduser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public void setPasswordRequestedAt(Date passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getCodeconfirmation() {
        return codeconfirmation;
    }

    public void setCodeconfirmation(Integer codeconfirmation) {
        this.codeconfirmation = codeconfirmation;
    }

    public String getPhotoMembre() {
        return photoMembre;
    }

    public void setPhotoMembre(String photoMembre) {
        this.photoMembre = photoMembre;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
    public boolean getBan() {
        return ban;
    }

    public void setBan(boolean ban) {
        this.ban = ban;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "santepis2.entities.User[ " + "id=" + id + ", username=" + username + ", username_canonical=" + usernameCanonical + ", email=" + email + ", email_canonical=" + emailCanonical + ", enabled=" + enabled + ", salt=" + salt + ", password=" + password + ", last_login=" + lastLogin + ", confirmation_token=" + confirmationToken + ", password_requested_at=" + passwordRequestedAt + ", roles=" + roles +", telephone=" + telephone +", photo_membre=" + photoMembre +" ]";
    }
    
}
