/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author dmouchagues
 * Singleton to get an EntityManager from JPA
 */
public class JPAUtils {
    private final static JPAUtils INSTANCE = new JPAUtils();
    private final static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("TestJPA");
    private final static EntityManager EM = EMF.createEntityManager();
    private JPAUtils(){}

    /**
     *
     * @return an instance of JPAUtils
     */
    public static JPAUtils getInstance(){
        return INSTANCE;
    }

    /**
     *
     * @return an EntityManager
     */
    public EntityManager getEntityManager(){
        return EM;
    }
    
    /**
     * Closes the current EntityManager
     */
    public void close(){
        if(EM != null){
            EM.close();
        }
    }
}
