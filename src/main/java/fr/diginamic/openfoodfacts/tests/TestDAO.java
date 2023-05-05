/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fr.diginamic.openfoodfacts.tests;

import fr.diginamic.openfoodfacts.dao.CategorieDAO;
import fr.diginamic.openfoodfacts.model.Categorie;
import java.util.List;

/**
 *
 * @author dmouchagues
 */
public class TestDAO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CategorieDAO categorieDAO = CategorieDAO.getInstance();
        
        List<Categorie> allCategories = categorieDAO.getAll();
        
        for(Categorie categorie : allCategories){
            System.out.println(categorie.getNom());
        }
        
        categorieDAO.closeEM();
    }
    
}
