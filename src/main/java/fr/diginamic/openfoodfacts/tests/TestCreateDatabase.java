/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fr.diginamic.openfoodfacts.tests;

import fr.diginamic.openfoodfacts.model.Additif;
import fr.diginamic.openfoodfacts.model.Allergene;
import fr.diginamic.openfoodfacts.model.Categorie;
import fr.diginamic.openfoodfacts.model.Ingredient;
import fr.diginamic.openfoodfacts.model.Marque;
import fr.diginamic.openfoodfacts.model.Produit;
import fr.diginamic.openfoodfacts.service.Stock;
import fr.diginamic.openfoodfacts.utils.JPAUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author dmouchagues
 */
public class TestCreateDatabase {

    /**
     * @param args the command line arguments
     * Test connexion database
     */
    public static void main(String[] args) {
        EntityManager em = JPAUtils.getInstance().getEntityManager();
        createDatabase(em); 
        em.close();
    }
    
    /**
     *
     * @param em
     */
    public static void createDatabase(EntityManager em){
        List<Produit> allProduits = Stock.getInstance().getProduits();
        List<Marque> marques = new ArrayList<>();
        List<Categorie> categories = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();
        List<Additif> additifs = new ArrayList<>();
        List<Allergene> allergenes = new ArrayList<>();
        HashSet<String> nomsMarques = new HashSet<>();
        HashSet<String> nomsCategories = new HashSet<>();
        HashSet<String> nomsIngredients = new HashSet<>();
        HashSet<String> nomsAdditifs = new HashSet<>();
        HashSet<String> nomsAllergenes = new HashSet<>();
        
        em.getTransaction().begin();
        for(Produit unProduit : allProduits){
            Categorie categorie = unProduit.getCategorie();
            if(nomsCategories.add(categorie.getNom())){
                categorie.getProduits().add(unProduit);
                categories.add(categorie);
                em.persist(categorie);
            } else {
                unProduit.setCategorie(chercherCategorie(categories, categorie.getNom()));
            }
            em.persist(unProduit);
            
            for(Marque uneMarque : unProduit.getMarques()){
                em.persist(uneMarque);
            }
            for(Additif unAdditif : unProduit.getListeAdditifs()){
                if(nomsAdditifs.add(unAdditif.getNom())){
                    additifs.add(unAdditif);
                    em.persist(unAdditif);
                } else {
                    unProduit.getListeAdditifs().add(chercherAdditif(additifs, unAdditif.getNom()));
                }
            }
            for(Allergene unAllergene : unProduit.getListeAllergenes()){
                em.persist(unAllergene);
            }
            for(Ingredient unIngredient : unProduit.getListeIngredients()){
                em.persist(unIngredient);
            }
        }
        em.getTransaction().commit();
    }
    
    /**
     *
     * @param liste
     * @param nom
     * @return
     */
    public static Marque chercherMarque(List<Marque> liste, String nom){
        Marque marqueTrouvee = null;
        for(Marque m : liste){
            if(m.getNom().equals(nom)){
                marqueTrouvee = m;
                break;
            }
        }
        return marqueTrouvee;
    }
    
    /**
     *
     * @param liste
     * @param nom
     * @return
     */
    public static Categorie chercherCategorie(List<Categorie> liste, String nom){
        Categorie categorieTrouvee = null;
        for(Categorie c : liste){
            if(c.getNom().equals(nom)){
                categorieTrouvee = c;
                break;
            }
        }
        return categorieTrouvee;
    }
    
    /**
     *
     * @param liste
     * @param nom
     * @return
     */
    public static Ingredient chercherIngredient(List<Ingredient> liste, String nom){
        Ingredient ingredientTrouve = null;
        for(Ingredient i : liste){
            if(i.getNom().equals(nom)){
                ingredientTrouve = i;
                break;
            }
        }
        return ingredientTrouve;
    }
    
    /**
     *
     * @param liste
     * @param nom
     * @return
     */
    public static Additif chercherAdditif(List<Additif> liste, String nom){
        Additif additifTrouve = null;
        for(Additif a : liste){
            if(a.getNom().equals(nom)){
                additifTrouve = a;
                break;
            }
        }
        return additifTrouve;
    }
    
    /**
     *
     * @param liste
     * @param nom
     * @return
     */
    public static Allergene chercherAllergene(List<Allergene> liste, String nom){
        Allergene allergeneTrouve = null;
        for(Allergene a : liste){
            if(a.getNom().equals(nom)){
                allergeneTrouve = a;
                break;
            }
        }
        return allergeneTrouve;
    }
}
