/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fr.diginamic.openfoodfacts.dao;

import java.util.List;

/**
 *
 * @author dmouchagues
 */
public interface IDAO<T> {
    T get(long id);
    List<T> getAll();
    void save(T t);
    void update(T t, String[] params);
    void delete(T t);
    void closeEM();
}
