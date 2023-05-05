/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fr.diginamic.openfoodfacts.service;

import java.util.List;

/**
 *
 * @author dmouchagues
 * @param <T>
 */
public interface IService<T> {

    /**
     *
     * @param id
     * @return
     */
    public T get(long id);

    /**
     *
     * @return
     */
    public List<T> getAll();

    /**
     *
     * @param t
     * @param params
     */
    public void update(T t, String[] params);

    /**
     *
     * @param t
     */
    public void save(T t);

    /**
     *
     * @param t
     */
    public void delete(T t);

    /**
     *
     */
    public void closeEM();
}
