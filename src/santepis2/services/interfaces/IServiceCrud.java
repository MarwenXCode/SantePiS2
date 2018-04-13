/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santepis2.services.interfaces;

import java.util.List;

/**
 *
 * @author m_s info
 */
public interface IServiceCrud <T> {
    
    public void add(T t);
    
    public void update(T t);
    
    public void delete(int id);
    
    public List<T> getAll();
    
    public T findById(int id);
    
    
}
