/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.controller;

import fr.diginamic.openfoodfacts.model.Additif;
import fr.diginamic.openfoodfacts.model.Allergene;
import fr.diginamic.openfoodfacts.model.Categorie;
import fr.diginamic.openfoodfacts.model.Ingredient;
import fr.diginamic.openfoodfacts.model.Marque;
import fr.diginamic.openfoodfacts.model.Produit;
import fr.diginamic.openfoodfacts.service.Stock;
import fr.diginamic.openfoodfacts.service.AdditifService;
import fr.diginamic.openfoodfacts.service.AllergeneService;
import fr.diginamic.openfoodfacts.service.CategorieService;
import fr.diginamic.openfoodfacts.service.IngredientService;
import fr.diginamic.openfoodfacts.service.MarqueService;
import fr.diginamic.openfoodfacts.service.ProduitService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dmouchagues
 */
public class MainController {
    
    private final static MainController INSTANCE = new MainController();
    private ProduitService produitService = ProduitService.getInstance();
    private CategorieService categorieService = CategorieService.getInstance();
    private MarqueService marqueService = MarqueService.getInstance();
    private IngredientService ingredientService = IngredientService.getInstance();
    private AdditifService additifService = AdditifService.getInstance();
    private AllergeneService allergeneService = AllergeneService.getInstance();
    
    private MainController(){}
    
    /**
     *
     * @return unique instance of MainController
     */
    public static MainController getInstance(){
        return INSTANCE;
    }
    
    /**
     * Insert all data of CSV in the database
     */
    public void insertData(){
        List<Produit> produits = Stock.getInstance().getProduits();
        for(Produit unProduit : produits){
            saveCategorie(unProduit);
            saveLists(unProduit);
            produitService.save(unProduit);
        }
        produitService.closeEM();
    }
    
    /**
     * Save the Categorie of a Produit in the database
     */
    private void saveCategorie(Produit unProduit){
        Categorie categorie = unProduit.getCategorie();
        if(!categorieAlreadyExists(categorie)){
            categorieService.save(categorie);
        } else {
            unProduit.setCategorie(categorieService.getByName(categorie.getNom()));
        }
    }
    
    /**
     * Save lists (Marque, Ingredient, Additif and Allergene) of a Produit in the database
     */
    private void saveLists(Produit unProduit){
        List<Marque> marquesProduit = new ArrayList<>();
        List<Ingredient> ingredientsProduit = new ArrayList<>();
        List<Additif> additifsProduit = new ArrayList<>();
        List<Allergene> allergenesProduit = new ArrayList<>();
        
        for(Marque uneMarque : unProduit.getMarques()){
            if(!marqueAlreadyExists(uneMarque)){
                marquesProduit.add(uneMarque);
                marqueService.save(uneMarque);
            } else {
                marquesProduit.add(marqueService.getByName(uneMarque.getNom()));
            }
        }
        for(Ingredient unIngredient : unProduit.getListeIngredients()){
            if(!ingredientAlreadyExists(unIngredient)){
                ingredientsProduit.add(unIngredient);
                ingredientService.save(unIngredient);
            } else {
                ingredientsProduit.add(ingredientService.getByName(unIngredient.getNom()));
            }
        }
        for(Additif unAdditif : unProduit.getListeAdditifs()){
            if(!additifAlreadyExists(unAdditif)){
                additifsProduit.add(unAdditif);
                additifService.save(unAdditif);
            } else {
                additifsProduit.add(additifService.getByName(unAdditif.getNom()));
            }
        }
        for(Allergene unAllergene : unProduit.getListeAllergenes()){
            if(!allergeneAlreadyExists(unAllergene)){
                allergenesProduit.add(unAllergene);
                allergeneService.save(unAllergene);
            } else {
                allergenesProduit.add(allergeneService.getByName(unAllergene.getNom()));
            }
        }
        unProduit.setMarques(marquesProduit);
        unProduit.setListeIngredients(ingredientsProduit);
        unProduit.setListeAdditifs(additifsProduit);
        unProduit.setListeAllergenes(allergenesProduit);
    }
    
    /**
     * Check if a Categorie exists in database
     */
    private Boolean categorieAlreadyExists(Categorie uneCategorie){
        if(categorieService.getByName(uneCategorie.getNom()) != null){
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Check if a Marque exists in database
     */
    private Boolean marqueAlreadyExists(Marque uneMarque){
        if(marqueService.getByName(uneMarque.getNom()) != null){
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Check if an Ingredient exists in database
     */
    private Boolean ingredientAlreadyExists(Ingredient unIngredient){
        if(ingredientService.getByName(unIngredient.getNom()) != null){
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Check if an Additif exists in database
     */
    private Boolean additifAlreadyExists(Additif unAdditif){
        if(additifService.getByName(unAdditif.getNom()) != null){
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Check if an Allergene exists in database
     */
    private Boolean allergeneAlreadyExists(Allergene unAllergene){
        if(allergeneService.getByName(unAllergene.getNom()) != null){
            return true;
        } else {
            return false;
        }
    }
    
    
}
