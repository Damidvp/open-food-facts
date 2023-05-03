/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fr.diginamic.openfoodfacts.tests;

import fr.diginamic.openfoodfacts.utils.JPAUtils;
import jakarta.persistence.EntityManager;

/**
 *
 * @author zdamy
 */
public class TestCreateDatabase {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManager em = JPAUtils.getInstance().getEntityManager();
        
        em.close();
    }
    
}
