/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.diginamic.openfoodfacts.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dmouchagues
 * Classe Stock, créant une liste de tous les produits
 */
public class Stock {
    private final static Stock INSTANCE = new Stock();
    private List<Produit> produits = new ArrayList<>();
    
    private Stock(){
        try {
            List<String> linesFile = new ArrayList<>();
            Path pathFile = Paths.get("c:/dev-java/open-food-facts.csv");
            linesFile = Files.readAllLines(pathFile, StandardCharsets.UTF_8);
            for(String line : linesFile){
                if(linesFile.indexOf(line) > 0){
                    Produit produit = new Produit();
                    String[] tokens = line.split("\\|");
                    
                    Categorie categorie = new Categorie();
                    categorie.setNom(tokens[0]);
                    produit.setCategorie(categorie);
                    
                    Marque marque = new Marque();
                    marque.setNom(tokens[1]);
                    produit.setMarque(marque);
                    
                    produit.setNom(tokens[2]);
                    produit.setScore(tokens[3].charAt(0));
                    produit.setEnergie100g(getValueOf(tokens[5])); //Si la valeur est une String vide, on affecte 0
                    produit.setGraisse100g(getValueOf(tokens[6]));
                    produit.setSucres100g(getValueOf(tokens[7]));
                    produit.setFibres100g(getValueOf(tokens[8]));
                    produit.setProteines100g(getValueOf(tokens[9]));
                    produit.setSel100g(getValueOf(tokens[10]));
                    produit.setVitA100g(getValueOf(tokens[11]));
                    produit.setVitD100g(getValueOf(tokens[12]));
                    produit.setVitE100g(getValueOf(tokens[13]));
                    produit.setVitK100g(getValueOf(tokens[14]));
                    produit.setVitC100g(getValueOf(tokens[15]));
                    produit.setVitB1100g(getValueOf(tokens[16]));
                    produit.setVitB2100g(getValueOf(tokens[17]));
                    produit.setVitPP100g(getValueOf(tokens[18]));
                    produit.setVitB6100g(getValueOf(tokens[19]));
                    produit.setVitB9100g(getValueOf(tokens[20]));
                    produit.setVitB12100g(getValueOf(tokens[21]));
                    produit.setCalcium100g(getValueOf(tokens[22]));
                    produit.setMagnesium100g(getValueOf(tokens[23]));
                    produit.setIron100g(getValueOf(tokens[24]));
                    produit.setFer100g(getValueOf(tokens[25]));
                    produit.setBetaCarotene100g(getValueOf(tokens[26]));
                    produit.setPresenceHuilePalme(Boolean.valueOf(tokens[27]));
                    
                    List<Ingredient> ingredients = new ArrayList<>();
                    List<Allergene> allergenes = new ArrayList<>();
                    List<Additif> additifs = new ArrayList<>();
                    
                    String[] tokensIngredients = tokens[4].split(",");
                    String[] tokensAllergenes = tokens[28].split(",");
                    String[] tokensAdditifs = tokens[29].split(",");
                    
                    for(String unIngredient : tokensIngredients){
                        Ingredient ingredient = new Ingredient();
                        ingredient.setNom(unIngredient.substring(0, unIngredient.length() < 200 ? unIngredient.length() : 199));
                        ingredients.add(ingredient);
                    }
                    for(String unAllergene : tokensAllergenes){
                        Allergene allergene = new Allergene();
                        allergene.setNom(unAllergene.substring(0, unAllergene.length() < 200 ? unAllergene.length() : 199));
                        allergenes.add(allergene);
                    }
                    for(String unAdditif : tokensAdditifs){
                        Additif additif = new Additif();
                        additif.setNom(unAdditif.substring(0, unAdditif.length() < 200 ? unAdditif.length() : 199));
                        additifs.add(additif);
                    }
                    produit.setListeIngredients(ingredients);
                    produit.setListeAllergenes(allergenes);
                    produit.setListeAdditifs(additifs);
                    
                    produits.add(produit);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Stock getInstance(){
        return INSTANCE;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
    
    private Float getValueOf(String token){
        Float result = 0F;
        try{
            result = Float.valueOf(token);
        } catch(NumberFormatException e){
            e.printStackTrace();
        }
        return result;
    }
    
}
