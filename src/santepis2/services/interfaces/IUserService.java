/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santepis2.services.interfaces;

import java.util.List;
import santepis2.entities.User;

/**
 *
 * @author sisyph
 */
public interface IUserService {
    void add(User r);
    void updateC(User r);
    void update(User r);
    void updateRole(User r);
    void delete(int id);

    List<User> getAll();
    
    User findById(int id);
    User findByEmail(String mail);
    void setenabledtrue(int i);
    void updateban(int i);
}
