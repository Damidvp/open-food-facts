/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fr.diginamic.openfoodfacts.tests;

import fr.diginamic.openfoodfacts.model.Additif;
import fr.diginamic.openfoodfacts.model.Allergene;
import fr.diginamic.openfoodfacts.model.Ingredient;
import fr.diginamic.openfoodfacts.model.Produit;
import fr.diginamic.openfoodfacts.model.Stock;
import fr.diginamic.openfoodfacts.utils.JPAUtils;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author zdamy
 */
public class TestCreateDatabase {

    /**
     * @param args the command line arguments
     * Test connexion database
     */
    public static void main(String[] args) {
        EntityManager em = JPAUtils.getInstance().getEntityManager();
        
        List<Produit> allProduits = Stock.getInstance().getProduits();
        em.getTransaction().begin();
        for(Produit unProduit : allProduits){
            em.persist(unProduit.getCategorie());
            em.persist(unProduit.getMarque());
            em.persist(unProduit);
            for(Additif unAdditif : unProduit.getListeAdditifs()){
                em.persist(unAdditif);
            }
            for(Allergene unAllergene : unProduit.getListeAllergenes()){
                em.persist(unAllergene);
            }
            for(Ingredient unIngredient : unProduit.getListeIngredients()){
                em.persist(unIngredient);
            }
        }
        em.getTransaction().commit();
        
        em.close();
    }
    
}
