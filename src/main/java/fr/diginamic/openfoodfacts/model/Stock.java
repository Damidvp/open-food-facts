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
import java.util.HashSet;
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
            
            //HashSet permettant de stocker les noms des entités de manière unique
            HashSet<String> nomsCategories = new HashSet<String>();
            HashSet<String> nomsMarques = new HashSet<String>();
            HashSet<String> nomsIngredients = new HashSet<String>();
            HashSet<String> nomsAllergenes = new HashSet<String>();
            HashSet<String> nomsAdditifs = new HashSet<String>();
            
            for(String line : linesFile){
                if(linesFile.indexOf(line) > 0){
                    Produit produit = new Produit();
                    String[] tokens = line.split("\\|");
                    
                    Categorie categorie = new Categorie();
                    categorie.setNom(tokens[0]);
                    produit.setCategorie(categorie);
                    
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
                    
                    List<Marque> marques = new ArrayList<>();
                    List<Ingredient> ingredients = new ArrayList<>();
                    List<Allergene> allergenes = new ArrayList<>();
                    List<Additif> additifs = new ArrayList<>();
                    
                    String[] tokensMarques;
                    tokensMarques = tokens[1].split(",");
                    
                    String[] tokensIngredients;
                    if(tokens[4].contains(",")){
                        tokensIngredients = tokens[4].split(",");
                    } else if (tokens[4].contains("-")){
                        tokensIngredients = tokens[4].split("\\-");
                    } else {
                        tokensIngredients = tokens[4].split("\\;");
                    }
                    String[] tokensAllergenes;
                    if(tokens[28].contains(",")){
                        tokensAllergenes = tokens[28].split(",");
                    } else if (tokens[28].contains("-")){
                        tokensAllergenes = tokens[28].split("\\-");
                    } else {
                        tokensAllergenes = tokens[28].split("\\;");
                    }
                    String[] tokensAdditifs;
                    if(tokens[29].contains(",")){
                        tokensAdditifs = tokens[29].split(",");
                    } else if (tokens[29].contains("-")){
                        tokensAdditifs = tokens[29].split("\\-");
                    } else {
                        tokensAdditifs = tokens[29].split("\\;");
                    }
                    
                    for(String uneMarque : tokensMarques){
                        Marque marque = new Marque();
                        String nom = formatStr(uneMarque);
                        marque.setNom(nom);
                        marques.add(marque);
                    }
                    for(String unIngredient : tokensIngredients){
                        Ingredient ingredient = new Ingredient();
                        String nom = formatStr((unIngredient.length() < 256) ? unIngredient : unIngredient.substring(0, 255));
                        ingredient.setNom(nom);
                        if(nomsIngredients.add(ingredient.getNom().toLowerCase())){
                            ingredients.add(ingredient);
                        }
                    }
                    for(String unAllergene : tokensAllergenes){
                        Allergene allergene = new Allergene();
                        String nom = formatStr((unAllergene.length() < 256) ? unAllergene : unAllergene.substring(0, 255));
                        allergene.setNom(nom);
                        if(nomsAllergenes.add(allergene.getNom().toLowerCase())){
                            allergenes.add(allergene);
                        }
                    }
                    for(String unAdditif : tokensAdditifs){
                        Additif additif = new Additif();
                        String nom = formatStr((unAdditif.length() < 256) ? unAdditif : unAdditif.substring(0, 255));
                        additif.setNom(nom);
                        if(nomsAdditifs.add(additif.getNom().toLowerCase())){
                            additifs.add(additif);
                        }
                    }
                    
                    produit.setMarques(marques);
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
            //e.printStackTrace();
        }
        return result;
    }
    
    private String formatStr(String s){
        String chaineFormat = "";
        if(!s.equals("")){
            chaineFormat = s.replaceAll("\\*", "")
                .replaceAll("_", "")
                .replaceAll("\\(.*?\\)", "")
                .replaceAll("\\[.*?\\]", "")
                .replaceAll("\\s*\\d+(\\.\\d+)?%\\s*", "")
                .replaceAll("\\s*\\d+(\\.\\d+)? %\\s*", "");
            if(chaineFormat.length() > 0){
                if(chaineFormat.charAt(0) == ' '){
                    chaineFormat = chaineFormat.replaceFirst(" ", "");
                }
            }
        }
        return chaineFormat;
    }
    
}
