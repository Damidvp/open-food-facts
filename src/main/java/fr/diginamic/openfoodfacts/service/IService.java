/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package fr.diginamic.openfoodfacts.service;

import java.util.List;

/**
 *
 * @author dmouchagues
 */
public interface IService<T> {
    public T get(long id);
    public List<T> getAll();
    public void update(T t, String[] params);
    public void save(T t);
    public void delete(T t);
}
