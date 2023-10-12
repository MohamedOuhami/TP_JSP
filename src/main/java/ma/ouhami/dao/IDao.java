/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ma.ouhami.dao;

import java.util.List;

/**
 *
 * @author moham
 */
public interface IDao<T> {
    
    boolean create(T o);
    boolean update(T o);
    boolean delete(T o);
    T findById(int id);
    List<T> findAll();
}
