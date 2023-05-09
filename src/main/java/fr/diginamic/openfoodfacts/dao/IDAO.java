/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fr.diginamic.openfoodfacts.dao;

import java.util.List;

/**
 *
 * @author dmouchagues
 * @param <T> class
 */
public interface IDAO<T> {

    /**
     *
     * @param id
     * @return the Object which has been found
     */
    T get(long id);

    /**
     *
     * @return all occurences of T
     */
    List<T> getAll();

    /**
     *
     * @param t to save
     */
    void save(T t);

    /**
     *
     * @param t to update
     * @param params array of fields to update
     */
    void update(T t, String[] params);

    /**
     *
     * @param t to delete
     */
    void delete(T t);

    /**
     * Closes the EntityManager
     */
    void closeEM();
}
