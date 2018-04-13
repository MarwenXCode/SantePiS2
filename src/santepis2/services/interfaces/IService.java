/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santepis2.services.interfaces;

import santepis2.entities.User;
import java.util.List;

/**
 *
 * @author adel
 */
public interface IService<T, R> {

    void add(T t);

    void update(T t);

    void delete(R id);

    List<T> getAll();

    T findById(R id);
}
